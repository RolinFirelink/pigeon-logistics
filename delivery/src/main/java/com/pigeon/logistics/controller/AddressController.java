package com.pigeon.logistics.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pigeon.logistics.annotation.DeleteLog;
import com.pigeon.logistics.annotation.InsertLog;
import com.pigeon.logistics.annotation.UpdateLog;
import com.pigeon.logistics.entity.AddressEntity;
import com.pigeon.logistics.entity.VO.AddressVO;
import com.pigeon.logistics.entity.VO.CustomPage;
import com.pigeon.logistics.entity.VO.Result;
import com.pigeon.logistics.service.AddressService;
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

@Tag(name = "7-地址管理")
@CrossOrigin
@Slf4j
@Validated
@AllArgsConstructor
@RestController
@RequestMapping("/logistics/address")
public class AddressController {
    private static final String LOG_TITLE = "企业地址管理";
    private AddressService addressService;

    @Operation(summary = "新增一条地址", tags = "接口汇总（新增、创建）")
    @InsertLog(title = LOG_TITLE)
    @PostMapping
    public Result<Object> addOne(
            @Parameter(description = "新增地址信息", required = true)
            @RequestBody
            AddressVO address
    ) {
        var entity = BeanUtils.copyProperties(address, AddressEntity.class);
        return addressService.addOne(entity) ?
                new Result<>().success().message("添加地址成功") :
                new Result<>().fail().message("添加地址失败,请稍后重试");
    }

    @Operation(summary = "删除一个地址", tags = "接口汇总（删除、取消）")
    @DeleteLog(title = LOG_TITLE)
    @DeleteMapping("/{id}")
    public Result<Object> deleteOne(
            @PathVariable
            @Parameter(description = "地址ID", required = true)
            Long id
    ) {
        return addressService.deleteOne(id) ?
                new Result<>().success().message("删除地址成功") :
                new Result<>().fail().message("删除地址失败,请稍后重试");
    }

    @Operation(summary = "批量删除地址", tags = "接口汇总（删除、取消）")
    @DeleteLog(title = LOG_TITLE)
    @DeleteMapping
    public Result<Object> deleteByIds(
            @RequestBody
            @Parameter(description = "地址ID列表", required = true)
            Long[] ids) {
        return addressService.deleteByIds(Arrays.stream(ids).toList()) ?
                new Result<>().success().message("删除地址成功") :
                new Result<>().fail().message("删除地址失败,请稍后重试");
    }

    @Operation(summary = "更新地址", tags = "接口汇总（更新、编辑）")
    @UpdateLog(title = LOG_TITLE)
    @PutMapping
    public Result<Object> updateOne(
            @Parameter(description = "更新地址信息", required = true)
            @RequestBody
            AddressVO address
    ) {
        var entity = BeanUtils.copyProperties(address, AddressEntity.class);
        return addressService.updateOne(entity) ?
                new Result<>().success().message("更新地址成功") :
                new Result<>().fail().message("更新地址失败，请稍后重试");
    }

    @Operation(summary = "设置地址为默认地址", tags = "接口汇总（更新、编辑）")
    @UpdateLog(title = LOG_TITLE)
    @PutMapping("/{id}")
    public Result<Object> setDefault(
            @PathVariable
            @Parameter(description = "地址ID", required = true)
            Long id
    ) {
        return addressService.setDefault(id) ?
                new Result<>().success().message("设置默认地址成功") :
                new Result<>().fail().message("设置默认地址失败，请稍后重试");
    }

    // TODO 鉴权？临时不区分账号和角色
    // @GetMapping("/{companyId}/{page}/{size}")
    @Operation(summary = "获取地址列表", tags = "接口汇总（查询、获取）")
    @GetMapping("/{page}/{size}")
    public Result<CustomPage<AddressVO>> getPage(
            @Parameter(description = "页码", required = true)
            @Min(value = 1, message = "页码必须从1开始")
            @PathVariable
            Integer page,
            @Parameter(description = "大小", required = true)
            @Min(value = 1, message = "页面记录数不能小于1")
            @PathVariable
            Integer size
    ) {
        // TODO 临时不区分账号和角色
        var pageConfig = new Page<AddressEntity>(page, size);
//        var queryWrapper = new LambdaQueryWrapper<AddressEntity>();
//        queryWrapper.like(AddressEntity::getCompanyId, companyId);
        var entitiesPage = addressService.page(pageConfig);
//        var entitiesPage = addressService.page(pageConfig, queryWrapper);
        var res = new CustomPage<AddressVO>(entitiesPage);
        // 视图处理
        var customRecords = new ArrayList<AddressVO>();
        entitiesPage.getRecords().stream().map(item ->
                BeanUtils.copyProperties(item, AddressVO.class)
        ).forEach(customRecords::add);
        res.setRecords(customRecords);
        return new Result<CustomPage<AddressVO>>().success().message("查询成功").data(res);
    }

    @Operation(summary = "获取一个地址", tags = "接口汇总（查询、获取）")
    @GetMapping("/{id}")
    public Result<AddressVO> getOne(
            @Parameter(description = "地址id", required = true)
            @PathVariable
            Long id
    ) {
        var addressEntity = addressService.getById(id);
        if (addressEntity == null) {
            return new Result<AddressVO>().fail().message("未查询到该地址");
        } else {
            var addressVO = BeanUtils.copyProperties(addressEntity, AddressVO.class);
            return new Result<AddressVO>().success().message("查询成功").data(addressVO);
        }
    }
}
