package com.pigeon.logistics.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pigeon.logistics.annotation.DeleteLog;
import com.pigeon.logistics.annotation.InsertLog;
import com.pigeon.logistics.annotation.UpdateLog;
import com.pigeon.logistics.entity.DictionaryEntity;
import com.pigeon.logistics.entity.VO.CustomPage;
import com.pigeon.logistics.entity.VO.Result;
import com.pigeon.logistics.service.DictionaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;


/**
 * @author Rolin
 */
@Tag(name = "9-字典管理")
@CrossOrigin
@Slf4j
@Validated
@AllArgsConstructor
@RestController
@RequestMapping("/logistics/dictionary")
public class DictionaryController {

    private static final String LOG_TITLE = "字典管理";
    private DictionaryService dictionaryService;

    @Operation(summary = "新增一个头节点", tags = "接口汇总（新增、创建）")
    @InsertLog(title = LOG_TITLE)
    @PostMapping({"/{headName}"})
    public Result<Object> addHead(
            @PathVariable
            @Parameter(description = "头节点名", required = true)
            String headName
    ) {
        // TODO sort值暂时设置为-1
        var entity = new DictionaryEntity(null, headName, -1L, -1);
        return dictionaryService.addHead(entity) ?
                new Result<>().success().message("添加字典头节点成功") :
                new Result<>().fail().message("添加字典头节点失败,请稍后重试");
    }

    @Operation(summary = "新增一个节点", tags = "接口汇总（新增、创建）")
    @InsertLog(title = LOG_TITLE)
    @PostMapping({"/{name}/{headName}"})
    public Result<Object> addNode(
            @PathVariable
            @Parameter(description = "节点名", required = true)
            String name,
            @PathVariable
            @Parameter(description = "头结点名", required = true)
            String headName
    ) {
        return dictionaryService.addNode(name, headName) ?
                new Result<>().success().message("添加字典节点成功") :
                new Result<>().fail().message("添加字典节点失败,请稍后重试或检查头结点名是否存在");
    }

    @Operation(summary = "获取头节点列表", tags = "接口汇总（查询、获取）")
    @GetMapping("/{page}/{size}")
    public Result<CustomPage<DictionaryEntity>> getPage(
            @Parameter(description = "页码", required = true)
            @Min(value = 1, message = "页码必须从1开始")
            @PathVariable
            Integer page,
            @Parameter(description = "大小", required = true)
            @Min(value = 1, message = "页面记录数不能小于1")
            @PathVariable
            Integer size
    ) {
        var pageConfig = new Page<DictionaryEntity>(page, size);
        var queryWrapper = new LambdaQueryWrapper<DictionaryEntity>();
        queryWrapper.eq(DictionaryEntity::getParentId, -1);
        var entityPage = dictionaryService.page(pageConfig, queryWrapper);
        var res = new CustomPage<DictionaryEntity>(entityPage);
        res.setRecords(entityPage.getRecords());
        return new Result<CustomPage<DictionaryEntity>>().success().message("查询成功").data(res);
    }

    @Operation(summary = "获取头节点下的子节点列表", tags = "接口汇总（查询、获取）")
    @GetMapping("/{page}/{size}/{headName}")
    public Result<CustomPage<DictionaryEntity>> getHeadPage(
            @Parameter(description = "页码", required = true)
            @Min(value = 1, message = "页码必须从1开始")
            @PathVariable
            Integer page,
            @Parameter(description = "大小", required = true)
            @Min(value = 1, message = "页面记录数不能小于1")
            @PathVariable
            Integer size,
            @Parameter(description = "头结点名", required = true)
            @NonNull
            @PathVariable
            String headName
    ) {
        var queryWrapper = new LambdaQueryWrapper<DictionaryEntity>();
        queryWrapper.eq(DictionaryEntity::getName, headName);
        var entity = dictionaryService.getOne(queryWrapper);
        if (entity == null) {
            return new Result<CustomPage<DictionaryEntity>>().fail().message("没有符合的内容");
        }
        var pageConfig = new Page<DictionaryEntity>(page, size);
        queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DictionaryEntity::getParentId, entity.getId());
        var entityPage = dictionaryService.page(pageConfig, queryWrapper);
        var res = new CustomPage<DictionaryEntity>(entityPage);
        res.setRecords(entityPage.getRecords());
        return new Result<CustomPage<DictionaryEntity>>().success().message("查询成功").data(res);
    }

    @Operation(summary = "获取任意一个头结点的信息", tags = "接口汇总（查询、获取）")
    @GetMapping("/{headName}")
    public Result<DictionaryEntity> getHead(
            @Parameter(description = "头结点名", required = true)
            @NonNull
            @PathVariable
            String headName
    ) {
        var queryWrapper = new LambdaQueryWrapper<DictionaryEntity>();
        queryWrapper.eq(DictionaryEntity::getName, headName);
        var entity = dictionaryService.getOne(queryWrapper);
        return new Result<DictionaryEntity>().success().message("查询成功").data(entity);
    }

    @Operation(summary = "更新一个结点信息", tags = "接口汇总（更新、编辑）")
    @UpdateLog(title = LOG_TITLE)
    @PutMapping
    public Result<Object> updateOne(
            @Parameter(description = "用于更新结点信息", required = true)
            @RequestBody
            DictionaryEntity sys
    ) {
        var entity = dictionaryService.getById(sys.getId());
        if (entity.getParentId() == -1 && sys.getParentId() != -1) {
            return new Result<>().fail().message("不允许修改头结点的父节点值");
        }
        return dictionaryService.updateOne(sys) ?
                new Result<>().success().message("更新结点信息成功") :
                new Result<>().fail().message("更新结点信息失败，请稍后重试");
    }

    @Operation(summary = "删除一个头结点", tags = "接口汇总（删除、取消）")
    @DeleteLog(title = LOG_TITLE)
    @DeleteMapping("head/{id}")
    public Result<Object> deleteOne(
            @PathVariable
            @Parameter(description = "头结点ID", required = true)
            Long id
    ) {
        return dictionaryService.deleteHead(id) ?
                new Result<>().success().message("删除头结点成功") :
                new Result<>().fail().message("删除头结点失败,请稍后重试或检查该结点是否存在");
    }

    @Operation(summary = "删除一个子结点", tags = "接口汇总（删除、取消）")
    @DeleteLog(title = LOG_TITLE)
    @DeleteMapping("son/{sonId}")
    public Result<Object> deleteSon(
            @PathVariable
            @Parameter(description = "子结点ID", required = true)
            Long sonId
    ) {
        return dictionaryService.deleteSon(sonId) ?
                new Result<>().success().message("删除子结点成功") :
                new Result<>().fail().message("删除子结点失败,请稍后重试或检查该结点是否存在或为头结点");
    }
}
