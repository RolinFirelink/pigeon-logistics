package com.pigeon.logistics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pigeon.logistics.entity.SysLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author dxy
 * @date 2023年2月9日
 */
@Mapper
public interface LogMapper extends BaseMapper<SysLogEntity> {
}
