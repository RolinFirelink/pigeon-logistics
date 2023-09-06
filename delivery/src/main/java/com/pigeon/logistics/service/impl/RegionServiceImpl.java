package com.pigeon.logistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pigeon.logistics.entity.RegionEntity;
import com.pigeon.logistics.mapper.RegionMapper;
import com.pigeon.logistics.service.RegionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dxy
 * @date 2023年2月11日
 */
@Service
public class RegionServiceImpl extends ServiceImpl<RegionMapper, RegionEntity> implements RegionService {

    private final RegionMapper regionMapper;

    public RegionServiceImpl(RegionMapper regionMapper) {
        this.regionMapper = regionMapper;
    }


    @Override
    public RegionEntity getOneByStatisCode(String code) {
        var queryWrapper = new LambdaQueryWrapper<RegionEntity>();
        queryWrapper.eq(RegionEntity::getStatisCode, code);
        return getOne(queryWrapper);
    }

    @Override
    public List<RegionEntity> listByStatisCode(String code) {
        var thisOne = getOneByStatisCode(code);
        var queryWrapper = new LambdaQueryWrapper<RegionEntity>();
        queryWrapper.eq(RegionEntity::getParentId, thisOne.getId());
        return list(queryWrapper);
    }

    @Override
    public List<RegionEntity> getRegionParentDescList() {
        return regionMapper.getRegionParentDescList();
    }
}