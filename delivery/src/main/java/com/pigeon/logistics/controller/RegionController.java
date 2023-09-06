package com.pigeon.logistics.controller;

import com.pigeon.logistics.entity.RegionEntity;
import com.pigeon.logistics.entity.VO.RegionVO;
import com.pigeon.logistics.entity.VO.Result;
import com.pigeon.logistics.service.RegionService;
import com.pigeon.logistics.util.BeanUtils;
import com.pigeon.logistics.util.RegionUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 区域信息管理Controller
 * 四级地址编码
 *
 * @author dxy
 * @date 2023年3月22日
 */
@Tags({@Tag(name = "6-区域信息")})
@CrossOrigin
@Slf4j
@Validated
@AllArgsConstructor
@RestController
@RequestMapping("/region")
public class RegionController {

    private static final String REGION = "region";

    private final CacheManager cacheManager;

    private final RegionService regionService;

    /**
     * 获取全部区域信息
     */
    @Operation(summary = "获取全部区域信息", tags = "接口汇总（查询、获取）")
    @Cacheable(value = "region", key = "'region'.concat('-all')", unless = "!#result.success")
    @GetMapping("/all")
    public Result<List<RegionVO>> getAll() {
        var regionEntities = regionService.getRegionParentDescList();
        if (regionEntities == null) {
            return new Result<List<RegionVO>>().fail().message("查询失败");
        } else {
            RegionVO res = RegionUtils.regionListToTree(regionEntities);
            return new Result<List<RegionVO>>().success().message("查询成功").data(res.getChildren());
        }
    }

    @Operation(summary = "获取一个区域信息", tags = "接口汇总（查询、获取）")
    @Cacheable(value = "region", key = "'region'.concat(#code.toString())", unless = "!#result.success")
    @GetMapping("/{code}")
    public Result<RegionVO> getOne(
            @PathVariable
            @Parameter(description = "区域国际编码", required = true)
            @Length(min = 12, max = 12, message = "请输入12位区域国际编码")
            String code
    ) {
        var regionEntity = regionService.getOneByStatisCode(code);
        if (regionEntity == null) {
            return new Result<RegionVO>().fail().message("未查询到该区域信息");
        } else {
            var res = BeanUtils.copyProperties(regionEntity, RegionVO.class);
            return new Result<RegionVO>().success().message("查询成功").data(res);
        }
    }

    /**
     * 获取区域信息列表
     *
     * @param parentCode 区域国际编码
     */
    @Operation(summary = "获取区域信息列表", tags = "接口汇总（查询、获取）")
    @Cacheable(value = "region", key = "'regionList'.concat(#parentCode.toString())", unless = "!#result.success")
    @GetMapping("/list/{parentCode}")
    public Result<RegionVO> list(
            @PathVariable
            @Parameter(description = "区域国际编码", required = true)
            @Length(min = 12, max = 12, message = "请输入12位区域国际编码")
            String parentCode
    ) {
        var regionEntity = regionService.getOneByStatisCode(parentCode);
        List<RegionEntity> regionEntities = regionService.listByStatisCode(parentCode);
        var records = new ArrayList<RegionVO>();
        regionEntities.stream().map(item -> BeanUtils.copyProperties(item, RegionVO.class)).forEach(records::add);
        var res = RegionVO.builder()
                .name(regionEntity.getName())
                .fullName(regionEntity.getFullName())
                .statisCode(regionEntity.getStatisCode())
                .regionType(regionEntity.getRegionType())
                .children(records)
                .build();
        return new Result<RegionVO>().success().message("查询成功").data(res);
    }


    // TODO 更新

    // TODO 删除

    /**
     * 清空缓存
     */
    @Operation(summary = "清除全部地区信息缓存", tags = "接口汇总（删除、取消）")
    @CacheEvict(value = "region", allEntries = true)
    @DeleteMapping("/cache")
    public Result<Object> clearCache() {
        return new Result<>().success().message("清除缓存成功");
    }

    /**
     * 定时任务：定时删除区域信息缓存
     */
    @Async
    @Scheduled(fixedRate = 86400000) // 1000*60*60*24 = 24h = 1d
    public void deleteCacheScheduled() {
        log.info("Scheduled：Delete all region code list cache");
        var cache = cacheManager.getCache(REGION);
        if (null != cache) {
            cache.clear();
        }
    }

}
