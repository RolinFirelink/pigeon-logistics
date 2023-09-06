package com.pigeon.logistics.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pigeon.logistics.entity.VehicleEntity;
import com.pigeon.logistics.mapper.VehicleMapper;
import com.pigeon.logistics.service.VehicleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Rolin
 * @since 2023-04-08
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class VehicleServiceImpl extends ServiceImpl<VehicleMapper, VehicleEntity> implements VehicleService {

    @Override
    public boolean addOne(VehicleEntity entity) {
        entity.setCreateTime(LocalDateTime.now());
        entity.setUpdateTime(LocalDateTime.now());
        entity.setModelName(entity.getBrand() + " " + entity.getModelCode()
                + " " + entity.getVehicleType());
        entity.setDrivingPermitId(-1L);
        return save(entity);
    }

    @Override
    public boolean deleteOne(Long id) {
        return removeById(id);
    }

    @Override
    public boolean deleteByIds(List<Long> ids) {
        return removeByIds(ids);
    }

    @Override
    public boolean updateOne(VehicleEntity entity) {
        entity.setUpdateTime(LocalDateTime.now());
        entity.setModelName(entity.getBrand() + " " + entity.getModelCode()
                + " " + entity.getVehicleType());
        return updateById(entity);
    }
}
