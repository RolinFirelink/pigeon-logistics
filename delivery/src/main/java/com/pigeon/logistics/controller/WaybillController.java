package com.pigeon.logistics.controller;

import com.alibaba.fastjson.JSONArray;
import com.pigeon.logistics.annotation.DeleteLog;
import com.pigeon.logistics.annotation.InsertLog;
import com.pigeon.logistics.annotation.UpdateLog;
import com.pigeon.logistics.entity.VO.*;
import com.pigeon.logistics.entity.WaybillEntity;
import com.pigeon.logistics.enums.WaybillEvents;
import com.pigeon.logistics.service.WaybillService;
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
import java.util.Arrays;

/**
 * 运单类Controller
 *
 * @author dxy
 * @date 2023年3月22日
 */
@Tags({@Tag(name = "3-运单管理")})
@CrossOrigin
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/logistics/waybill")
public class WaybillController {

    private static final String LOG_TITLE = "旅行放行表管理";

    private static final String CACHE_NAME = "waybill";

    private final CacheManager cacheManager;

    private final WaybillService waybillService;

    @Operation(summary = "新建一个运单", tags = "接口汇总（新增、创建）")
    @InsertLog(title = LOG_TITLE)
    @PostMapping
    public Result<Object> addOne(
            @Parameter(description = "运单信息", required = true)
            @RequestBody
            WaybillVO waybill
    ) throws Exception {
        var entity = BeanUtils.copyProperties(waybill, WaybillEntity.class);
        return waybillService.createOne(entity) ?
                new Result<>().success().message("添加运单成功") :
                new Result<>().fail().message("添加运单失败，请稍后重试");
    }

    @Operation(summary = "获取一个运单详细信息", tags = "接口汇总（查询、获取）")
    @Cacheable(value = CACHE_NAME, key = "#code", unless = "!#result.success")
    @GetMapping("/{code}")
    public Result<WaybillVO> getOne(@PathVariable @Parameter(description = "运单号", required = true) String code) {
        var waybillEntity = waybillService.getOneByWaybillCode(code);
        if (waybillEntity == null) {
            return new Result<WaybillVO>().fail().message("未查询到该订单");
        } else {
            var res = getWayBillWithLog(waybillEntity);
            return new Result<WaybillVO>().success().message("查询成功").data(res);
        }
    }

    private WaybillVO getWayBillWithLog(WaybillEntity waybillEntity) {
        if (waybillEntity.getLogs() != null) {
            var logString = waybillEntity.getLogs().toString();
            var resWithoutLogs = BeanUtils.copyProperties(waybillEntity, WaybillVO.class);
            var logEntities = JSONArray.parseArray(logString, LogisticsLogVO.class);
            var logs = logEntities.stream()
                    .map(item -> BeanUtils.copyProperties(item, LogisticsLogVO.class))
                    .toList();
            var waybillOnlyLogs = new WaybillVO(logs);
            return BeanUtils.copyProperties(waybillOnlyLogs, resWithoutLogs);
        } else {
            return BeanUtils.copyProperties(waybillEntity, WaybillVO.class);
        }

    }

    @Operation(summary = "获取运单列表", tags = "接口汇总（查询、获取）")
    @GetMapping("/{page}/{size}")
    public Result<CustomPage<WaybillVO>> getPage(
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
        var entitiesPage = waybillService.page(page, size, searchCondition);
        var res = new CustomPage<WaybillVO>(entitiesPage);
        // 视图处理
        var customRecords = new ArrayList<WaybillVO>();
        entitiesPage.getRecords().stream().map(this::getWayBillWithLog).forEach(customRecords::add);
        res.setRecords(customRecords);
        res.setSearchCondition(searchCondition);

        return new Result<CustomPage<WaybillVO>>().success().message("查询成功").data(res);
    }

    // TODO 更新订单状态

    @Operation(summary = "删除一个运单", tags = "接口汇总（删除、取消）")
    @DeleteLog(LOG_TITLE)
    @CacheEvict(value = CACHE_NAME, key = "#code")
    @DeleteMapping("/{code}")
    public Result<Object> deleteOne(@PathVariable @Parameter(description = "运单号", required = true) String code) {
        return waybillService.deleteOne(code) ?
                new Result<>().success().message("取消运单成功") :
                new Result<>().fail().message("取消运单失败，请稍后重试");
    }

    @Operation(summary = "批量删除运单", tags = "接口汇总（删除、取消）")
    @DeleteLog(title = LOG_TITLE)
    @DeleteMapping
    public Result<Object> deleteBatch(
            @RequestBody
            @Parameter(description = "运单号列表", required = true)
            String[] codes) {
        // 因为 @CacheEvict 注解不能批量删除缓存，所以这里需要手动批量删除缓存
        var cache = cacheManager.getCache(CACHE_NAME);
        if (cache != null) {
            for (String code : codes) {
                cache.evict(code);
            }
        }

        return waybillService.deleteBatchByCodes(Arrays.stream(codes).toList()) ?
                new Result<>().success().message("批量删除运单成功") :
                new Result<>().fail().message("批量删除运单失败，请稍后重试");
    }


    @Operation(summary = "改变一个运单状态", tags = "接口汇总（更新、编辑）")
    @UpdateLog(title = LOG_TITLE)
    @CacheEvict(value = CACHE_NAME, key = "#code")
    @PutMapping("/{code}")
    public Result<WaybillVO> change(@PathVariable String code, WaybillEvents event) throws Exception {
        return switch (event) {
            case PAYED -> {
                var isValid = waybillService.pay(code);
                if (isValid) {
                    var res = getEntityAndConvertVoByCode(code);
                    yield new Result<WaybillVO>().success().message("支付成功").data(res);
                } else {
                    yield new Result<WaybillVO>().fail().message("支付失败");
                }
            }
            case DELIVERY -> {
                var isValid = waybillService.deliver(code);
                if (isValid) {
                    var res = getEntityAndConvertVoByCode(code);
                    yield new Result<WaybillVO>().success().message("发货成功").data(res);
                } else {
                    yield new Result<WaybillVO>().fail().message("发货失败，请稍后重试");
                }
            }
            case RECEIVE -> {
                var isValid = waybillService.receive(code);
                if (isValid) {
                    var res = getEntityAndConvertVoByCode(code);
                    yield new Result<WaybillVO>().success().message("签收成功").data(res);
                } else {
                    yield new Result<WaybillVO>().fail().message("签收失败，请稍后重试");
                }
            }
            case REFUSED -> {
                var isValid = waybillService.refulse(code);
                if (isValid) {
                    var res = getEntityAndConvertVoByCode(code);
                    yield new Result<WaybillVO>().success().message("拒签成功").data(res);
                } else {
                    yield new Result<WaybillVO>().fail().message("拒签失败，请稍后重试");
                }
            }
            default -> {
                yield new Result<WaybillVO>().success().message("测试状态机");
            }
        };

    }

    /**
     * 获取运单实体类并转换成视图类
     *
     * @param code 运单编号
     * @return 运单视图类
     */
    private WaybillVO getEntityAndConvertVoByCode(String code) {
        var waybillEntity = waybillService.getOneByWaybillCode(code);
        return BeanUtils.copyProperties(waybillEntity, WaybillVO.class);
    }

}
