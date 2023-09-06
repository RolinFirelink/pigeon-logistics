package com.pigeon.logistics.controller;

import com.alibaba.excel.EasyExcel;
import com.pigeon.logistics.annotation.DeleteLog;
import com.pigeon.logistics.annotation.InsertLog;
import com.pigeon.logistics.annotation.UpdateLog;
import com.pigeon.logistics.entity.OutboundOrderEntity;
import com.pigeon.logistics.entity.OutboundOrderItemEntity;
import com.pigeon.logistics.entity.VO.*;
import com.pigeon.logistics.service.OutboundOrderItemService;
import com.pigeon.logistics.service.OutboundOrderService;
import com.pigeon.logistics.util.BeanUtils;
import com.pigeon.logistics.util.MoneyUtils;
import com.spire.xls.FileFormat;
import com.spire.xls.Workbook;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ClassUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 出库单Controller
 *
 * @author dxy
 * @date 2023年3月22日
 */
@Slf4j
@Tags({@Tag(name = "2-出库单管理")})
@CrossOrigin
@Validated
@AllArgsConstructor
@RestController
@RequestMapping("/logistics/outbound/order")
public class OutboundOrderController {

    private static final String LOG_TITLE = "出库单管理";

    private static final String CACHE_NAME = "outboundOrder";

    private final CacheManager cacheManager;

    private final OutboundOrderService outboundOrderService;

    private final OutboundOrderItemService outboundItemService;


    @Operation(summary = "获取一个出库单", tags = "接口汇总（查询、获取）")
    @Cacheable(value = CACHE_NAME, key = "#code", unless = "!#result.success")
    @GetMapping("/{code}")
    public Result<OutboundOrderVO> getOne(@PathVariable @Parameter(description = "出库单号", required = true) String code) {
        var orderEntity = outboundOrderService.getOneByCode(code);
        if (orderEntity == null) {
            return new Result<OutboundOrderVO>().fail().message("未查询到该出库单");
        } else {
            var res = getOutboundOrderVoWithItems(orderEntity);
            return new Result<OutboundOrderVO>().success().message("查询成功").data(res);
        }
    }

    private List<OutboundOrderItemVO> getOutboundOrderVoItems(OutboundOrderEntity orderEntity) {
        return outboundItemService.listByOrderCode(orderEntity.getCode()).stream()
                .map(item -> {
                    item.setOutboundOrderCode(null);
                    return BeanUtils.copyProperties(item, OutboundOrderItemVO.class);
                })
                .toList();
    }

    private OutboundOrderVO getOutboundOrderVoWithItems(OutboundOrderEntity orderEntity) {
        var outboundWithoutItems = BeanUtils.copyProperties(orderEntity, OutboundOrderVO.class);
        var items = getOutboundOrderVoItems(orderEntity);
        var outboundOnlyItems = new OutboundOrderVO(items);
        return BeanUtils.copyProperties(outboundOnlyItems, outboundWithoutItems);
    }

    @Operation(summary = "获取出库单分页", tags = "接口汇总（查询、获取）")
    @GetMapping("/{page}/{size}")
    public Result<CustomPage<OutboundOrderVO>> getPage(
            @Parameter(description = "页码", required = true)
            @Min(value = 1, message = "页码必须从1开始")
            @PathVariable
            Integer page,
            @Parameter(description = "大小", required = true)
            @Min(value = 1, message = "页面记录数不能小于1")
            @PathVariable
            Integer size,
            // TODO 搜索条件，深入到明细；knife4j文档显示有问题
            SearchCondition searchCondition
    ) {
        // TODO 注意权限
        var entitiesPage = outboundOrderService.page(page, size, searchCondition);
        var res = new CustomPage<OutboundOrderVO>(entitiesPage);
        // 视图处理
        var customRecords = new ArrayList<OutboundOrderVO>();
        entitiesPage.getRecords().stream().map(this::getOutboundOrderVoWithItems).forEach(customRecords::add);
        res.setRecords(customRecords);
        res.setSearchCondition(searchCondition);

        return new Result<CustomPage<OutboundOrderVO>>().success().message("查询成功").data(res);
    }

    @Operation(summary = "新增一条出库单", tags = "接口汇总（新增、创建）")
    @InsertLog(title = LOG_TITLE)
    @PostMapping
    public Result<Object> addOne(
            @Parameter(description = "出库单信息", required = true)
            @RequestBody
            OutboundOrderVO order
    ) {
        var entity = BeanUtils.copyProperties(order, OutboundOrderEntity.class);

        var itemEntities = new ArrayList<OutboundOrderItemEntity>();
        if (order.items() != null) {
            order.items().stream().map(item -> BeanUtils.copyProperties(item, OutboundOrderItemEntity.class)).forEach(itemEntities::add);
        }

        return outboundOrderService.addOne(entity, itemEntities) ?
                new Result<>().success().message("添加出库单成功") :
                new Result<>().fail().message("添加出库单失败，请稍后重试");
    }

    @Operation(summary = "更新一个出库单（不更新明细）", tags = "接口汇总（更新、编辑）")
    @UpdateLog(title = LOG_TITLE)
    @CacheEvict(value = CACHE_NAME, key = "#order")
    @PutMapping
    public Result<Object> updateOne(
            @Parameter(description = "出库单", required = true)
            @RequestBody
            OutboundOrderVO order
    ) {
        var entity = BeanUtils.copyProperties(order, OutboundOrderEntity.class);
        return outboundOrderService.updateByOrderCode(entity) ?
                new Result<>().success().message("更新出库单成功") :
                new Result<>().fail().message("更新出库单失败，请稍后重试");
    }

    @Operation(summary = "删除一个出库单", tags = "接口汇总（删除、取消）")
    @DeleteLog(title = LOG_TITLE)
    @CacheEvict(value = CACHE_NAME, key = "#code")
    @DeleteMapping("/{code}")
    public Result<Object> deleteOne(@PathVariable @Parameter(description = "出库单编号", required = true) String code) {
        return outboundOrderService.deleteOne(code) ?
                new Result<>().success().message("删除出库单成功") :
                new Result<>().fail().message("删除出库单失败，请稍后重试");
    }

    @Operation(summary = "新增一个出库单的明细", tags = "接口汇总（新增、创建）")
    @InsertLog(title = LOG_TITLE)
    @CacheEvict(value = CACHE_NAME, key = "#orderCode")
    @PostMapping("/{orderCode}/item")
    public Result<Object> addItem(
            @PathVariable
            @Parameter(description = "出库单编号", required = true)
            String orderCode,
            @Parameter(description = "一条出库单明细的记录", required = true)
            @RequestBody
            OutboundOrderItemVO item
    ) {
        var itemEntity = BeanUtils.copyProperties(item, OutboundOrderItemEntity.class);
        return outboundItemService.addOneByOrderCode(orderCode, itemEntity) ?
                new Result<>().success().message("添加出库单明细成功") :
                new Result<>().fail().message("添加出库单明细失败，请稍后重试");
    }


    @Operation(summary = "删除一个出库单的明细", tags = "接口汇总（删除、取消）")
    @DeleteLog(title = LOG_TITLE)
    @CacheEvict(value = CACHE_NAME, key = "#orderCode")
    @DeleteMapping("/{orderCode}/item")
    public Result<Object> deleteItems(
            @PathVariable @Parameter(description = "出库单编号", required = true)
            String orderCode,
            @RequestParam @Parameter(description = "出库单明细编号列表", required = true)
            String[] itemCodes) {
        var orderEntity = outboundOrderService.getOneByCode(orderCode);
        if (orderEntity != null) {
            return outboundItemService.deleteBatch(orderCode, Arrays.stream(itemCodes).toList()) ?
                    new Result<>().success().message("删除记录成功")
                    : new Result<>().fail().message("删除记录失败，请稍后重试");
        }
        return new Result<>().fail().message("未找到该出库单");
    }

    @Operation(summary = "更新一个出库单明细", tags = "接口汇总（更新、编辑）")
    @UpdateLog(title = LOG_TITLE)
    @CacheEvict(value = CACHE_NAME, key = "#orderCode")
    @PutMapping("/{orderCode}/item")
    public Result<Object> updateItem(
            @PathVariable
            @Parameter(description = "出库单编号", required = true)
            String orderCode,
            @Parameter(description = "一条出库单明细的记录", required = true)
            @RequestBody
            OutboundOrderItemVO item
    ) {
        var itemEntity = BeanUtils.copyProperties(item, OutboundOrderItemEntity.class);
        return outboundItemService.updateOneByOrderCode(orderCode, itemEntity) ?
                new Result<>().success().message("更新出库单明细成功") :
                new Result<>().fail().message("更新出库单明细失败，请稍后重试");
    }

    @Operation(summary = "批量删除出库表", tags = "接口汇总（删除、取消）")
    @DeleteLog(title = LOG_TITLE)
    @DeleteMapping
    public Result<Object> deleteBatch(
            @RequestBody
            @Parameter(description = "出库表编号列表", required = true)
            String[] orderCodes
    ) {
        // 因为 @CacheEvict 注解不能批量删除缓存，所以这里需要手动批量删除缓存
        var cache = cacheManager.getCache(CACHE_NAME);
        if (cache != null) {
            for (String code : orderCodes) {
                cache.evict(code);
            }
        }

        return outboundOrderService.deleteBatchByIds(Arrays.stream(orderCodes).toList()) ?
                new Result<>().success().message("批量删除出库表成功") :
                new Result<>().fail().message("批量删除出库表失败，请稍后重试");
    }

    // TODO 送货单 是出库单还是入库单，需要根据业务讨论辨别（先做EXCEL，以后升级下载PDF版本的）

    @Operation(summary = "获取一个出库单的文件（building）", tags = "接口汇总（查询、获取）")
    @GetMapping("/{code}/download")
    public ResponseEntity<Object> downOne(
            @PathVariable
            @Parameter(description = "出库单号", required = true)
            String code
    ) {
        var orderEntity = outboundOrderService.getOneByCode(code);
        var items = outboundItemService.listByOrderCode(orderEntity.getCode());

        if (orderEntity.getCode() == null) {
            return ResponseEntity.ok(new Result<>().fail().message("未找到该出库表，请稍后重试"));
        }

        // 在聚合工程下这种写法不优雅。或者说因为这个需求让聚合工程的缺点凸显。
        var templatePathResource = new ClassPathResource("templates/TODO送货单.xlsx");
        String templateFileUrlPath;
        try {
            templateFileUrlPath = URLDecoder.decode(templatePathResource.getURL().getPath(), StandardCharsets.UTF_8);
            log.info("templateFileUrlPath:{}", templateFileUrlPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String fileOutputDirPath = URLDecoder.decode(
                ClassUtils.getDefaultClassLoader().getResource("").getPath() + "temp/",
                StandardCharsets.UTF_8
        );
        System.out.println(fileOutputDirPath);

        // 根据对象填充
        var fileName = orderEntity.getCode();
        var excelFileName = fileName.concat(".xlsx");
        var excelFilePathName = URLDecoder.decode(fileOutputDirPath.concat(excelFileName), StandardCharsets.UTF_8);
        try (var excelWriter = EasyExcel.write(excelFilePathName).withTemplate(templateFileUrlPath).build()) {
            var writeSheet = EasyExcel.writerSheet().build();
            excelWriter.fill(orderEntity, writeSheet);
            excelWriter.fill(items, writeSheet);

            Map<String, Object> custom = new HashMap<>();
            // 年月日
            custom.put("date", DateTimeFormatter.ofPattern("yyyy年MM月dd日").format(orderEntity.getEditDate()));
            // 金额中文大写
            custom.put("totalChineseAmount", MoneyUtils.toChinese(orderEntity.getTotalAmount().toString()));
            excelWriter.fill(custom, writeSheet);
        }

        // 导出PDF格式（开源组件的底层新版本JAVA配置module问题）
        // 加载Excel文档
        Workbook wb = new Workbook();
        wb.loadFromFile(excelFilePathName.substring(1));
        // 获取第2个工作表
//        Worksheet sheet = wb.getWorksheets().get(1);
        //调用方法保存为PDF格式
        var pdfFileName = fileName.concat(".pdf");
        var pdfFilePathName = fileOutputDirPath.concat(pdfFileName);
        wb.saveToFile(pdfFilePathName.substring(1), FileFormat.PDF);


        var file = new File(pdfFilePathName);

        String contentDisposition = ContentDisposition
                .attachment()
                .filename(URLEncoder.encode(pdfFileName, StandardCharsets.UTF_8))
                .build().toString();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_PDF)
                .body(new FileSystemResource(pdfFilePathName));
    }


}
