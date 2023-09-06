package com.pigeon.logistics.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pigeon.logistics.annotation.DeleteLog;
import com.pigeon.logistics.annotation.InsertLog;
import com.pigeon.logistics.annotation.UpdateLog;
import com.pigeon.logistics.entity.VehicleDriverEntity;
import com.pigeon.logistics.entity.VO.VehicleRelevanceVo;
import com.pigeon.logistics.entity.VO.CustomPage;
import com.pigeon.logistics.entity.VO.Result;
import com.pigeon.logistics.service.VehicleRelevanceService;
import com.pigeon.logistics.util.BeanUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.ArrayList;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Rolin
 * @since 2023-04-08
 */
@Tag(name = "12-车辆司机中间表管理")
@CrossOrigin
@Slf4j
@Validated
@AllArgsConstructor
@RestController
@RequestMapping("/logistics/vehicleRelevance")
public class VehicleRelevanceController {
    private static final String LOG_TITLE = "企业司机车辆关联数据管理";
    private VehicleRelevanceService vehicleRelevanceService;

    @Operation(summary = "新增一条司机车辆关联数据", tags = "接口汇总（新增、创建）")
    @InsertLog(title = LOG_TITLE)
    @PostMapping
    public Result<Object> addOne(
            @Parameter(description = "新增司机车辆关联数据", required = true)
            @RequestBody
            VehicleRelevanceVo vehicleRelevance
    ) {
        var entity = BeanUtils.copyProperties(vehicleRelevance, VehicleDriverEntity.class);
        return vehicleRelevanceService.addOne(entity) ?
                new Result<>().success().message("添加关联数据成功") :
                new Result<>().fail().message("添加关联数据失败,请稍后重试或检查司机与车辆是否存在");
    }

    @Operation(summary = "删除一条关联数据", tags = "接口汇总（删除、取消）")
    @DeleteLog(title = LOG_TITLE)
    @DeleteMapping("/{id}")
    public Result<Object> deleteOne(
            @PathVariable
            @Parameter(description = "关联数据ID", required = true)
            Long id
    ) {
        return vehicleRelevanceService.deleteOne(id) ?
                new Result<>().success().message("删除关联数据成功") :
                new Result<>().fail().message("删除关联数据失败,请稍后重试");
    }

    @Operation(summary = "更新一条关联数据", tags = "接口汇总（更新、编辑）")
    @UpdateLog(title = LOG_TITLE)
    @PutMapping
    public Result<Object> updateOne(
            @Parameter(description = "更新关联数据信息", required = true)
            @RequestBody
            VehicleRelevanceVo vehicleRelevance
    ) {
        var entity = BeanUtils.copyProperties(vehicleRelevance, VehicleDriverEntity.class);
        return vehicleRelevanceService.updateOne(entity) ?
                new Result<>().success().message("更新关联数据成功") :
                new Result<>().fail().message("更新关联数据失败，请稍后重试");
    }

    @Operation(summary = "获取关联数据列表", tags = "接口汇总（查询、获取）")
    @GetMapping("/{page}/{size}")
    public Result<CustomPage<VehicleRelevanceVo>> getPage(
            @Parameter(description = "页码", required = true)
            @Min(value = 1, message = "页码必须从1开始")
            @PathVariable
            Integer page,
            @Parameter(description = "大小", required = true)
            @Min(value = 1, message = "页面记录数不能小于1")
            @PathVariable
            Integer size
    ) {
        var pageConfig = new Page<VehicleDriverEntity>(page, size);
        var entityPage = vehicleRelevanceService.page(pageConfig);
        var res = new CustomPage<VehicleRelevanceVo>(entityPage);
        // 视图处理
        var customRecords = new ArrayList<VehicleRelevanceVo>();
        entityPage.getRecords().stream().map(item ->
                BeanUtils.copyProperties(item, VehicleRelevanceVo.class)
        ).forEach(customRecords::add);
        res.setRecords(customRecords);
        return new Result<CustomPage<VehicleRelevanceVo>>().success().message("查询成功").data(res);
    }
}
