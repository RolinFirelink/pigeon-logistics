package com.pigeon.logistics.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pigeon.logistics.annotation.DeleteLog;
import com.pigeon.logistics.annotation.InsertLog;
import com.pigeon.logistics.entity.LogisticsOrderEntity;
import com.pigeon.logistics.entity.VO.CustomPage;
import com.pigeon.logistics.entity.VO.LogisticsOrderVO;
import com.pigeon.logistics.entity.VO.Result;
import com.pigeon.logistics.service.LogisticsOrderService;
import com.pigeon.logistics.util.BeanUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.ArrayList;

/**
 * 物流订单Controller
 *
 * @author dxy
 * @date 2023年3月22日
 */
@Tag(name = "1-物流订单管理")
@CrossOrigin
@Validated
@AllArgsConstructor
@RestController
@RequestMapping("/logistics/order")
public class LogisticsOrderController {

    private static final String LOG_TITLE = "物流订单管理";

    private static final String CACHE_NAME = "lgOrder";

    private final CacheManager cacheManager;

    private final LogisticsOrderService logisticsOrderService;


    @Operation(summary = "获取一个物流订单详细信息", tags = "接口汇总（查询、获取）")
    @Cacheable(value = CACHE_NAME, key = "#code", unless = "!#result.success")
    @GetMapping("/{code}")
    public Result<LogisticsOrderVO> getOne(@PathVariable String code) {
        var orderEntity = logisticsOrderService.getOneByLgCode(code);
        if (orderEntity == null) {
            return new Result<LogisticsOrderVO>().fail().message("未查询到该订单");
        } else {
            var res = BeanUtils.copyProperties(orderEntity, LogisticsOrderVO.class);
            return new Result<LogisticsOrderVO>().success().message("查询成功").data(res);
        }
    }

    @Operation(summary = "获取物流订单列表", tags = "接口汇总（查询、获取）")
    @GetMapping("/{page}/{size}")
    public Result<CustomPage<LogisticsOrderVO>> getPage(
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
        var pageConfig = new Page<LogisticsOrderEntity>(page, size);
        var entitiesPage = logisticsOrderService.page(pageConfig);
        var res = new CustomPage<LogisticsOrderVO>(entitiesPage);
        // 视图处理
        var customRecords = new ArrayList<LogisticsOrderVO>();
        // entitiesPage.getRecords().forEach(item -> {customRecords.add(logisticsOrderEntity2VO(item));}); // 经典写法
        entitiesPage.getRecords().stream().map(item ->
                BeanUtils.copyProperties(item, LogisticsOrderVO.class)
        ).forEach(customRecords::add);
        res.setRecords(customRecords);
        return new Result<CustomPage<LogisticsOrderVO>>().success().message("查询成功").data(res);
    }

    @Operation(summary = "新增一条物流订单", tags = "接口汇总（新增、创建）")
    @InsertLog(title = LOG_TITLE)
    @PostMapping
    public Result<String> addOne(
            @Parameter(description = "物流订单信息", required = true)
            @RequestBody
            LogisticsOrderVO order
    ) {
        var entity = BeanUtils.copyProperties(order, LogisticsOrderEntity.class);
        return logisticsOrderService.addOne(entity) ?
                new Result<String>().success().message("添加订单成功") :
                new Result<String>().fail().message("添加订单失败，请稍后重试");
    }

    // TODO 更新订单状态

    @Operation(summary = "取消一个物流订单", tags = "接口汇总（删除、取消）")
    @DeleteLog(title = LOG_TITLE)
    @CacheEvict(value = CACHE_NAME, key = "#code")
    @DeleteMapping("/{code}")
    public Result<String> deleteOne(@PathVariable String code) {
        return logisticsOrderService.deleteOne(code) ?
                new Result<String>().success().message("取消订单成功") :
                new Result<String>().fail().message("取消订单失败，请稍后重试");
    }

}
