package com.pigeon.logistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pigeon.logistics.entity.RegionEntity;

import java.util.List;

/**
 * @author dxy
 * @date 2023年2月11日
 */
public interface RegionService extends IService<RegionEntity> {

    /**
     * 通过编码获取区域信息
     *
     * @param code 区域编码
     * @return 区域信息
     */
    RegionEntity getOneByStatisCode(String code);

    /**
     * 根据国际区域编码获取子区域列表
     *
     * @param code 国际区域编码
     * @return 区域信息列表
     */
    List<RegionEntity> listByStatisCode(String code);

    List<RegionEntity> getRegionParentDescList();
}
