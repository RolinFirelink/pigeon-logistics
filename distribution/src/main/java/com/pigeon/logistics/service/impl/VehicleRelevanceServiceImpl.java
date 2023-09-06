package com.pigeon.logistics.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pigeon.logistics.entity.VehicleDriverEntity;
import com.pigeon.logistics.mapper.VehicleDriverMapper;
import com.pigeon.logistics.service.VehicleRelevanceService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Rolin
 * @since 2023-04-08
 */
@Service
public class VehicleRelevanceServiceImpl extends ServiceImpl<VehicleDriverMapper, VehicleDriverEntity> implements VehicleRelevanceService {

    @Override
    public boolean addOne(VehicleDriverEntity entity) {
        entity.setCreateTime(LocalDateTime.now());
        entity.setUpdateTime(LocalDateTime.now());
        return save(entity);
    }

    @Override
    public boolean deleteOne(Long id) {
        return removeById(id);
    }

    @Override
    public boolean updateOne(VehicleDriverEntity entity) {
        return updateById(entity);
    }
}
