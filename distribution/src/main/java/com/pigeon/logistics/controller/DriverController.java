package com.pigeon.logistics.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pigeon.logistics.annotation.DeleteLog;
import com.pigeon.logistics.annotation.InsertLog;
import com.pigeon.logistics.annotation.UpdateLog;
import com.pigeon.logistics.entity.DriverEntity;
import com.pigeon.logistics.entity.VO.CustomPage;
import com.pigeon.logistics.entity.VO.DriverVo;
import com.pigeon.logistics.entity.VO.Result;
import com.pigeon.logistics.service.DriverService;
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
 * @author Rolin
 */
@Tag(name = "8-司机管理")
@CrossOrigin
@Slf4j
@Validated
@AllArgsConstructor
@RestController
@RequestMapping("/logistics/driver")
public class DriverController {

    private static final String LOG_TITLE = "企业司机管理";
    private DriverService driverService;

    @Operation(summary = "新增一位司机", tags = "接口汇总（新增、创建）")
    @InsertLog(title = LOG_TITLE)
    @PostMapping
    public Result<Object> addOne(
            @Parameter(description = "新增司机信息", required = true)
            @RequestBody
            DriverVo driver
    ) {
        var entity = BeanUtils.copyProperties(driver, DriverEntity.class);
        return driverService.addOne(entity) ?
                new Result<>().success().message("添加司机成功") :
                new Result<>().fail().message("添加司机失败,请稍后重试");
    }

    @Operation(summary = "删除一位司机", tags = "接口汇总（删除、取消）")
    @DeleteLog(title = LOG_TITLE)
    @DeleteMapping("/{id}")
    public Result<Object> deleteOne(
            @PathVariable
            @Parameter(description = "司机ID", required = true)
            Long id
    ) {
        return driverService.deleteOne(id) ?
                new Result<>().success().message("删除司机成功") :
                new Result<>().fail().message("删除司机失败,请稍后重试");
    }

    @Operation(summary = "批量删除司机", tags = "接口汇总（删除、取消）")
    @DeleteLog(title = LOG_TITLE)
    @DeleteMapping
    public Result<Object> deleteByIds(
            @RequestBody
            @Parameter(description = "司机ID列表", required = true)
            Long[] ids) {
        return driverService.deleteByIds(Arrays.stream(ids).toList()) ?
                new Result<>().success().message("删除司机成功") :
                new Result<>().fail().message("删除司机失败,请稍后重试");
    }

    @Operation(summary = "更新司机信息", tags = "接口汇总（更新、编辑）")
    @UpdateLog(title = LOG_TITLE)
    @PutMapping
    public Result<Object> updateOne(
            @Parameter(description = "更新司机信息", required = true)
            @RequestBody
            DriverVo driver
    ) {
        var entity = BeanUtils.copyProperties(driver, DriverEntity.class);
        return driverService.updateOne(entity) ?
                new Result<>().success().message("更新司机成功") :
                new Result<>().fail().message("更新司机失败，请稍后重试");
    }

    @Operation(summary = "获取司机列表", tags = "接口汇总（查询、获取）")
    @GetMapping("/{page}/{size}")
    public Result<CustomPage<DriverVo>> getPage(
            @Parameter(description = "页码", required = true)
            @Min(value = 1, message = "页码必须从1开始")
            @PathVariable
            Integer page,
            @Parameter(description = "大小", required = true)
            @Min(value = 1, message = "页面记录数不能小于1")
            @PathVariable
            Integer size
    ) {
        var pageConfig = new Page<DriverEntity>(page, size);
        var entityPage = driverService.page(pageConfig);
        var res = new CustomPage<DriverVo>(entityPage);
        // 视图处理
        var customRecords = new ArrayList<DriverVo>();
        entityPage.getRecords().stream().map(item ->
                BeanUtils.copyProperties(item, DriverVo.class)
        ).forEach(customRecords::add);
        res.setRecords(customRecords);
        return new Result<CustomPage<DriverVo>>().success().message("查询成功").data(res);
    }

    @Operation(summary = "获取一位司机信息", tags = "接口汇总（查询、获取）")
    @GetMapping("/{id}")
    public Result<DriverVo> getOne(
            @Parameter(description = "司机id", required = true)
            @PathVariable
            Long id
    ) {
        var driverEntity = driverService.getById(id);
        if (driverEntity == null) {
            return new Result<DriverVo>().fail().message("未查询到该司机");
        } else {
            var driverVo = BeanUtils.copyProperties(driverEntity, DriverVo.class);
            return new Result<DriverVo>().success().message("查询成功").data(driverVo);
        }
    }
}
