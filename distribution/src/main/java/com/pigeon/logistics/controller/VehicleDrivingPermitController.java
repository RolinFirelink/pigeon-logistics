package com.pigeon.logistics.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pigeon.logistics.annotation.DeleteLog;
import com.pigeon.logistics.annotation.InsertLog;
import com.pigeon.logistics.annotation.UpdateLog;
import com.pigeon.logistics.entity.VehicleDrivingPermitEntity;
import com.pigeon.logistics.entity.VO.VehicleDrivingPermitVo;
import com.pigeon.logistics.entity.VO.CustomPage;
import com.pigeon.logistics.entity.VO.Result;
import com.pigeon.logistics.service.VehicleDrivingPermitService;
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

@Tag(name = "11-车辆驾驶证管理")
@CrossOrigin
@Slf4j
@Validated
@AllArgsConstructor
@RestController
@RequestMapping("/logistics/vehicleDrivingPermit")
public class VehicleDrivingPermitController {
    private static final String LOG_TITLE = "企业行驶证管理";
    private VehicleDrivingPermitService vehicleDrivingPermitService;

    @Operation(summary = "新增一个行驶证", tags = "接口汇总（新增、创建）")
    @InsertLog(title = LOG_TITLE)
    @PostMapping
    public Result<Object> addOne(
            @Parameter(description = "新增行驶证信息", required = true)
            @RequestBody
            VehicleDrivingPermitVo vehicleDrivingPermit
    ) {
        var entity = BeanUtils.copyProperties(vehicleDrivingPermit, VehicleDrivingPermitEntity.class);
        return vehicleDrivingPermitService.addOne(entity) ?
                new Result<>().success().message("添加行驶证成功") :
                new Result<>().fail().message("添加行驶证失败,请稍后重试或检查该车是否存在和该车是否已经绑定行驶证");
    }

    @Operation(summary = "删除一个行驶证", tags = "接口汇总（删除、取消）")
    @DeleteLog(title = LOG_TITLE)
    @DeleteMapping("/{id}")
    public Result<Object> deleteOne(
            @PathVariable
            @Parameter(description = "行驶证ID", required = true)
            Long id
    ) {
        return vehicleDrivingPermitService.deleteOne(id) ?
                new Result<>().success().message("删除行驶证成功") :
                new Result<>().fail().message("删除行驶证失败,请稍后重试或检查该行驶证或车辆是否存在");
    }

    @Operation(summary = "更新行驶证信息", tags = "接口汇总（更新、编辑）")
    @UpdateLog(title = LOG_TITLE)
    @PutMapping
    public Result<Object> updateOne(
            @Parameter(description = "更新行驶证信息", required = true)
            @RequestBody
            VehicleDrivingPermitVo vehicleDrivingPermit
    ) {
        var entity = BeanUtils.copyProperties(vehicleDrivingPermit, VehicleDrivingPermitEntity.class);
        return vehicleDrivingPermitService.updateOne(entity) ?
                new Result<>().success().message("更新行驶证成功") :
                new Result<>().fail().message("更新行驶证失败，,请稍后重试或检查该车是否存在和该车是否已经绑定行驶证");
    }

    @Operation(summary = "获取行驶证列表", tags = "接口汇总（查询、获取）")
    @GetMapping("/{page}/{size}")
    public Result<CustomPage<VehicleDrivingPermitVo>> getPage(
            @Parameter(description = "页码", required = true)
            @Min(value = 1, message = "页码必须从1开始")
            @PathVariable
            Integer page,
            @Parameter(description = "大小", required = true)
            @Min(value = 1, message = "页面记录数不能小于1")
            @PathVariable
            Integer size
    ) {
        var pageConfig = new Page<VehicleDrivingPermitEntity>(page, size);
        var entityPage = vehicleDrivingPermitService.page(pageConfig);
        var res = new CustomPage<VehicleDrivingPermitVo>(entityPage);
        // 视图处理
        var customRecords = new ArrayList<VehicleDrivingPermitVo>();
        entityPage.getRecords().stream().map(item ->
                BeanUtils.copyProperties(item, VehicleDrivingPermitVo.class)
        ).forEach(customRecords::add);
        res.setRecords(customRecords);
        return new Result<CustomPage<VehicleDrivingPermitVo>>().success().message("查询成功").data(res);
    }

}
