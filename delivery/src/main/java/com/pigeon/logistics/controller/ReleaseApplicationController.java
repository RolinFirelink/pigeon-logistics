package com.pigeon.logistics.controller;

import com.pigeon.logistics.annotation.DeleteLog;
import com.pigeon.logistics.annotation.InsertLog;
import com.pigeon.logistics.annotation.UpdateLog;
import com.pigeon.logistics.entity.ReleaseApplicationFormEntity;
import com.pigeon.logistics.entity.ReleaseApplicationFormItemEntity;
import com.pigeon.logistics.entity.VO.*;
import com.pigeon.logistics.service.ReleaseApplicationItemService;
import com.pigeon.logistics.service.ReleaseApplicationService;
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
import java.util.List;

/**
 * 旅行放行条Controller
 *
 * @author dxy
 * @date 2023年3月22日
 */
@Tags({@Tag(name = "4-旅行放行表管理")})
@CrossOrigin
@Validated
@AllArgsConstructor
@RestController
@RequestMapping("/logistics/release/application")
public class ReleaseApplicationController {

    private static final String LOG_TITLE = "旅行放行表管理";

    private static final String CACHE_NAME = "releaseApplicationForm";

    private final CacheManager cacheManager;

    private final ReleaseApplicationService releaseApplicationService;

    private final ReleaseApplicationItemService releaseApplicationItemService;

    @Operation(summary = "获取一个旅行放行表详细信息", tags = "接口汇总（查询、获取）")
    @Cacheable(value = CACHE_NAME, key = "#id.toString()", unless = "!#result.success")
    @GetMapping("/{id}")
    public Result<ReleaseApplicationFormVO> getOne(
            @PathVariable
            @Parameter(description = "旅行放行表ID", required = true)
            Long id
    ) {
        var formEntity = releaseApplicationService.getById(id);
        if (formEntity == null) {
            return new Result<ReleaseApplicationFormVO>().fail().message("未查询到该订单");
        } else {
            var res = getReleaseApplicationVoWithItems(formEntity);
            return new Result<ReleaseApplicationFormVO>().success().message("查询成功").data(res);
        }
    }

    private List<ReleaseApplicationFormItemVO> getOutboundOrderVoItems(ReleaseApplicationFormEntity formEntity) {
        return releaseApplicationItemService.listByFormId(formEntity.getId()).stream()
                .map(item -> {
                    item.setReleaseApplicationFormId(null);
                    return BeanUtils.copyProperties(item, ReleaseApplicationFormItemVO.class);
                })
                .toList();
    }

    private ReleaseApplicationFormVO getReleaseApplicationVoWithItems(ReleaseApplicationFormEntity formEntity) {
        var voWithoutItems = BeanUtils.copyProperties(formEntity, ReleaseApplicationFormVO.class);
        var items = getOutboundOrderVoItems(formEntity);
        var voOnlyItems = new ReleaseApplicationFormVO(items);
        return BeanUtils.copyProperties(voOnlyItems, voWithoutItems);
    }

    @Operation(summary = "获取旅行放行表分页", tags = "接口汇总（查询、获取）")
    @GetMapping("/{page}/{size}")
    public Result<CustomPage<ReleaseApplicationFormVO>> getPage(
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
        var entitiesPage = releaseApplicationService.page(page, size, searchCondition);
        var res = new CustomPage<ReleaseApplicationFormVO>(entitiesPage);
        // 视图处理
        var customRecords = new ArrayList<ReleaseApplicationFormVO>();
        entitiesPage.getRecords().stream().map(this::getReleaseApplicationVoWithItems).forEach(customRecords::add);
        res.setRecords(customRecords);
        res.setSearchCondition(searchCondition);

        return new Result<CustomPage<ReleaseApplicationFormVO>>().success().message("查询成功").data(res);
    }

    @Operation(summary = "新增一条旅行放行表", tags = "接口汇总（新增、创建）")
    @InsertLog(title = LOG_TITLE)
    @PostMapping
    public Result<Object> addOne(
            @Parameter(description = "旅行放行表信息", required = true)
            @RequestBody
            ReleaseApplicationFormVO form
    ) {
        var entity = BeanUtils.copyProperties(form, ReleaseApplicationFormEntity.class);

        var itemEntities = new ArrayList<ReleaseApplicationFormItemEntity>();
        if (form.items() != null) {
            form.items().stream().map(item ->
                    BeanUtils.copyProperties(item, ReleaseApplicationFormItemEntity.class)).forEach(itemEntities::add);
        }

        return releaseApplicationService.addOne(entity, itemEntities) ?
                new Result<>().success().message("添加放行条成功") :
                new Result<>().fail().message("添加放行条失败，请稍后重试");
    }

    @Operation(summary = "更新一个旅行放行表（不更新明细）", tags = "接口汇总（更新、编辑）")
    @UpdateLog(title = LOG_TITLE)
    @CacheEvict(value = CACHE_NAME, key = "#form.id().toString()")
    @PutMapping
    public Result<Object> updateOne(
            @Parameter(description = "旅行放行表信息", required = true)
            @RequestBody
            ReleaseApplicationFormVO form
    ) {
        var entity = BeanUtils.copyProperties(form, ReleaseApplicationFormEntity.class);
        return releaseApplicationService.updateOne(entity) ?
                new Result<>().success().message("更新放行条成功") :
                new Result<>().fail().message("更新放行条失败，请稍后重试");
    }

    @Operation(summary = "取消一个旅行放行表", tags = "接口汇总（删除、取消）")
    @DeleteLog(title = LOG_TITLE)
    @CacheEvict(value = CACHE_NAME, key = "#id.toString()")
    @DeleteMapping("/{id}")
    public Result<Object> deleteOne(
            @PathVariable
            @Parameter(description = "旅行放行表ID", required = true)
            Long id
    ) {
        return releaseApplicationService.deleteOne(id) ?
                new Result<>().success().message("取消放行条成功") :
                new Result<>().fail().message("取消放行条失败，请稍后重试");
    }

    @Operation(summary = "批量删除旅行放行表", tags = "接口汇总（删除、取消）")
    @DeleteLog(title = LOG_TITLE)
    @DeleteMapping
    public Result<Object> deleteBatch(
            @RequestBody
            @Parameter(description = "放行表ID列表", required = true)
            Long[] ids
    ) {
        // 因为 @CacheEvict 注解不能批量删除缓存，所以这里需要手动批量删除缓存
        var cache = cacheManager.getCache(CACHE_NAME);
        if (cache != null) {
            for (Long id : ids) {
                cache.evict(id.toString());
            }
        }

        return releaseApplicationService.deleteBatchByIds(Arrays.stream(ids).toList()) ?
                new Result<>().success().message("批量删除放行条成功") :
                new Result<>().fail().message("批量删除放行条失败，请稍后重试");
    }

    @Operation(summary = "新增一个旅行放行表的明细", tags = "接口汇总（新增、创建）")
    @InsertLog(title = LOG_TITLE)
    @CacheEvict(value = CACHE_NAME, key = "#formId.toString()")
    @PostMapping("/{formId}/item")
    public Result<Object> addItem(
            @PathVariable
            @Parameter(description = "旅行放行表编号", required = true)
            Long formId,
            @Parameter(description = "一条旅行放行表明细的记录", required = true)
            @RequestBody
            ReleaseApplicationFormItemVO item
    ) {
        var itemEntity = BeanUtils.copyProperties(item, ReleaseApplicationFormItemEntity.class);
        return releaseApplicationItemService.addOneById(formId, itemEntity) ?
                new Result<>().success().message("添加旅行放行表明细成功") :
                new Result<>().fail().message("添加旅行放行表明细失败，请稍后重试");
    }


    @Operation(summary = "删除一个旅行放行表的明细", tags = "接口汇总（删除、取消）")
    @DeleteLog(title = LOG_TITLE)
    @CacheEvict(value = CACHE_NAME, key = "#formId.toString()")
    @DeleteMapping("/{formId}/item")
    public Result<Object> deleteItems(
            @PathVariable @Parameter(description = "旅行放行表编号", required = true)
            Long formId,
            @RequestParam @Parameter(description = "旅行放行表明细编号列表", required = true)
            Long[] itemCodes) {
        var orderEntity = releaseApplicationService.getById(formId);
        if (orderEntity != null) {
            return releaseApplicationItemService.deleteBatch(formId, Arrays.stream(itemCodes).toList()) ?
                    new Result<>().success().message("删除记录成功")
                    : new Result<>().fail().message("删除记录失败，请稍后重试");
        }
        return new Result<>().fail().message("未找到该旅行放行表");
    }

    @Operation(summary = "更新一个旅行放行表明细", tags = "接口汇总（更新、编辑）")
    @UpdateLog(title = LOG_TITLE)
    @CacheEvict(value = CACHE_NAME, key = "#formId.toString()")
    @PutMapping("/{formId}/item")
    public Result<Object> updateItem(
            @PathVariable
            @Parameter(description = "旅行放行表编号", required = true)
            Long formId,
            @Parameter(description = "一条旅行放行表明细的记录", required = true)
            @RequestBody
            ReleaseApplicationFormItemVO item
    ) {
        var itemEntity = BeanUtils.copyProperties(item, ReleaseApplicationFormItemEntity.class);
        return releaseApplicationItemService.updateOneById(formId, itemEntity) ?
                new Result<>().success().message("更新旅行放行表细成功") :
                new Result<>().fail().message("更新旅行放行表明细失败，请稍后重试");
    }

}
