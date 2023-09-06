package com.pigeon.logistics.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pigeon.logistics.annotation.DeleteLog;
import com.pigeon.logistics.annotation.InsertLog;
import com.pigeon.logistics.entity.StorageReceiptEntity;
import com.pigeon.logistics.entity.VO.CustomPage;
import com.pigeon.logistics.entity.VO.Result;
import com.pigeon.logistics.entity.VO.StorageReceiptVO;
import com.pigeon.logistics.service.StorageReceiptService;
import com.pigeon.logistics.util.BeanUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.AllArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.ArrayList;

/**
 * 入库单Controller
 *
 * @author dxy cza
 * @date 2023年3月22日
 */
@Tags({@Tag(name = "5-入库单管理")})
@CrossOrigin
@Validated
@AllArgsConstructor
@RestController
@RequestMapping("/logistics/storagereceipt/order") //入库单路径
public class StorageReceiptController {

    private static final String LOG_TITLE = "入库单管理";

    private static final String CACHE_NAME = "storageReceiptOrder";

    private final CacheManager cacheManager;

    private final StorageReceiptService storageReceiptService;

    @Operation(summary = "获取一个入库单详细信息", tags = "接口汇总（查询、获取）")
    @Cacheable(value = CACHE_NAME, key = "#code", unless = "!#result.success")
    @GetMapping("/{code}")
    public Result<StorageReceiptVO> getOne(@PathVariable @Parameter(description = "入库单号", required = true) String code) {
        var orderEntity = storageReceiptService.getOneByCode(code);
        if (orderEntity == null) {
            return new Result<StorageReceiptVO>().fail().message("未查询到该订单");
        } else {
            var res = BeanUtils.copyProperties(orderEntity, StorageReceiptVO.class);
            return new Result<StorageReceiptVO>().success().message("查询成功").data(res);
        }
    }

    @Operation(summary = "获取入库单列表", tags = "接口汇总（查询、获取）")
    @GetMapping("/{page}/{size}")
    public Result<CustomPage<StorageReceiptVO>> getPage(
            @Parameter(description = "页码", required = true)
            @Min(value = 1, message = "页码必须从1开始")
            @PathVariable
            Integer page,
            @Parameter(description = "大小", required = true)
            @Min(value = 1, message = "页面记录数不能小于1")
            @PathVariable
            Integer size
    ) {
        // TODO 注意权限
        var pageConfig = new Page<StorageReceiptEntity>(page, size);
        var entitiesPage = storageReceiptService.page(pageConfig);
        var res = new CustomPage<StorageReceiptVO>(entitiesPage);
        // 视图处理
        var customRecords = new ArrayList<StorageReceiptVO>();
        // entitiesPage.getRecords().forEach(item -> {customRecords.add(logisticsOrderEntity2VO(item));}); // 经典写法
        entitiesPage.getRecords().stream().map(item ->
                BeanUtils.copyProperties(item, StorageReceiptVO.class)
        ).forEach(customRecords::add);
        res.setRecords(customRecords);
        return new Result<CustomPage<StorageReceiptVO>>().success().message("查询成功").data(res);
    }

    @Operation(summary = "新增一条入库单", tags = "接口汇总（新增、创建）")
    @InsertLog(title = LOG_TITLE)
    @PostMapping
    public Result<String> addOne(
            @Parameter(description = "入库单信息", required = true)
            @RequestBody
            StorageReceiptVO order
    ) {
        var entity = BeanUtils.copyProperties(order, StorageReceiptEntity.class);
        return storageReceiptService.addOne(entity) ?
                new Result<String>().success().message("添加入库单成功") :
                new Result<String>().fail().message("添加入库单失败，请稍后重试");
    }

    // TODO 更新订单状态

    @Operation(summary = "删除一个入库单", tags = "接口汇总（删除、取消）")
    @DeleteLog(title = LOG_TITLE)
    @CacheEvict(value = CACHE_NAME, key = "#code")
    @DeleteMapping("/{code}")
    public Result<String> deleteOne(@PathVariable @Parameter(description = "入库单编号", required = true) String code) {
        return storageReceiptService.deleteOne(code) ?
                new Result<String>().success().message("删除入库单成功") :
                new Result<String>().fail().message("删除入库单失败，请稍后重试");
    }

}
