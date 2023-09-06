package com.pigeon.logistics.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pigeon.logistics.entity.SysLogEntity;
import com.pigeon.logistics.mapper.LogMapper;
import com.pigeon.logistics.service.LogService;
import org.springframework.stereotype.Service;

/**
 * @author dxy
 * @date 2023年02月09日
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, SysLogEntity> implements LogService {
}
