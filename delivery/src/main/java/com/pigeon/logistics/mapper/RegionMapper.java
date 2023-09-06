package com.pigeon.logistics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pigeon.logistics.entity.RegionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author dxy
 * @date 2023年02月11日
 */
@Mapper
public interface RegionMapper extends BaseMapper<RegionEntity> {

    /**
     * 根据父级id升序查询所有地区信息，用于生成树状结构
     *
     * @return 区域信息列表
     */
    @Select("SELECT * FROM region ORDER BY `parent_id`, `id`")
    List<RegionEntity> getRegionParentDescList();
}