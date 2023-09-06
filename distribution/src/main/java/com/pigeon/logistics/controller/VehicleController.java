package com.pigeon.logistics.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pigeon.logistics.annotation.DeleteLog;
import com.pigeon.logistics.annotation.InsertLog;
import com.pigeon.logistics.annotation.UpdateLog;
import com.pigeon.logistics.entity.VehicleEntity;
import com.pigeon.logistics.entity.VO.VehicleVo;
import com.pigeon.logistics.entity.VO.CustomPage;
import com.pigeon.logistics.entity.VO.Result;
import com.pigeon.logistics.service.VehicleService;
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
import java.util.Arrays;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Rolin
 * @since 2023-04-08
 */
@Tag(name = "10-车辆管理")
@CrossOrigin
@Slf4j
@Validated
@AllArgsConstructor
@RestController
@RequestMapping("/logistics/vehicle")
public class VehicleController {
    private static final String LOG_TITLE = "企业车辆管理";
    private VehicleService vehicleService;

    @Operation(summary = "新增一个车辆", tags = "接口汇总（新增、创建）")
    @InsertLog(title = LOG_TITLE)
    @PostMapping
    public Result<Object> addOne(
            @Parameter(description = "新增车辆信息", required = true)
            @RequestBody
            VehicleVo vehicle
    ) {
        var entity = BeanUtils.copyProperties(vehicle, VehicleEntity.class);
        return vehicleService.addOne(entity) ?
                new Result<>().success().message("添加车辆成功") :
                new Result<>().fail().message("添加车辆失败,请稍后重试");
    }

    @Operation(summary = "删除一个车辆", tags = "接口汇总（删除、取消）")
    @DeleteLog(title = LOG_TITLE)
    @DeleteMapping("/{id}")
    public Result<Object> deleteOne(
            @PathVariable
            @Parameter(description = "车辆ID", required = true)
            Long id
    ) {
        return vehicleService.deleteOne(id) ?
                new Result<>().success().message("删除车辆成功") :
                new Result<>().fail().message("删除车辆失败,请稍后重试");
    }

    @Operation(summary = "批量删除车辆", tags = "接口汇总（删除、取消）")
    @DeleteLog(title = LOG_TITLE)
    @DeleteMapping
    public Result<Object> deleteByIds(
            @RequestBody
            @Parameter(description = "车辆ID列表", required = true)
            Long[] ids) {
        return vehicleService.deleteByIds(Arrays.stream(ids).toList()) ?
                new Result<>().success().message("删除车辆成功") :
                new Result<>().fail().message("删除车辆失败,请稍后重试");
    }

    @Operation(summary = "更新一个车辆信息", tags = "接口汇总（更新、编辑）")
    @UpdateLog(title = LOG_TITLE)
    @PutMapping
    public Result<Object> updateOne(
            @Parameter(description = "更新车辆信息", required = true)
            @RequestBody
            VehicleVo vehicle
    ) {
        var entity = BeanUtils.copyProperties(vehicle, VehicleEntity.class);
        return vehicleService.updateOne(entity) ?
                new Result<>().success().message("更新车辆成功") :
                new Result<>().fail().message("更新车辆失败，请稍后重试");
    }

    @Operation(summary = "获取车辆列表", tags = "接口汇总（查询、获取）")
    @GetMapping("/{page}/{size}")
    public Result<CustomPage<VehicleVo>> getPage(
            @Parameter(description = "页码", required = true)
            @Min(value = 1, message = "页码必须从1开始")
            @PathVariable
            Integer page,
            @Parameter(description = "大小", required = true)
            @Min(value = 1, message = "页面记录数不能小于1")
            @PathVariable
            Integer size
    ) {
        var pageConfig = new Page<VehicleEntity>(page, size);
        var entityPage = vehicleService.page(pageConfig);
        var res = new CustomPage<VehicleVo>(entityPage);
        // 视图处理
        var customRecords = new ArrayList<VehicleVo>();
        entityPage.getRecords().stream().map(item ->
                BeanUtils.copyProperties(item, VehicleVo.class)
        ).forEach(customRecords::add);
        res.setRecords(customRecords);
        return new Result<CustomPage<VehicleVo>>().success().message("查询成功").data(res);
    }

    @Operation(summary = "获取一位车辆信息", tags = "接口汇总（查询、获取）")
    @GetMapping("/{id}")
    public Result<VehicleVo> getOne(
            @Parameter(description = "车辆id", required = true)
            @PathVariable
            Long id
    ) {
        var entity = vehicleService.getById(id);
        if (entity == null) {
            return new Result<VehicleVo>().fail().message("未查询到该车辆");
        } else {
            var vehicleVo = BeanUtils.copyProperties(entity, VehicleVo.class);
            return new Result<VehicleVo>().success().message("查询成功").data(vehicleVo);
        }
    }
}
