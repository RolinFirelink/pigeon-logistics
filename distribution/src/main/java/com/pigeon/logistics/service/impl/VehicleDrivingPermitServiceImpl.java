package com.pigeon.logistics.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pigeon.logistics.entity.VehicleDrivingPermitEntity;
import com.pigeon.logistics.mapper.DrivingPermitMapper;
import com.pigeon.logistics.service.VehicleDrivingPermitService;
import com.pigeon.logistics.service.VehicleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional(rollbackFor = Exception.class)
public class VehicleDrivingPermitServiceImpl extends ServiceImpl<DrivingPermitMapper, VehicleDrivingPermitEntity> implements VehicleDrivingPermitService {

    private final VehicleService vehicleService;

    public VehicleDrivingPermitServiceImpl(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @Override
    public boolean addOne(VehicleDrivingPermitEntity entity) {
        var vehicleEntity = vehicleService.getById(entity.getVehicleId());
        if (vehicleEntity == null || vehicleEntity.getDrivingPermitId() != -1) {
            return false;
        }
        entity.setId(IdWorker.getId());
        vehicleEntity.setUpdateTime(LocalDateTime.now());
        vehicleEntity.setDrivingPermitId(entity.getId());
        entity.setCreateTime(LocalDateTime.now());
        entity.setUpdateTime(LocalDateTime.now());
        return vehicleService.updateById(vehicleEntity) && save(entity);
    }

    @Override
    public boolean deleteOne(Long id) {
        var entity = getById(id);
        if (entity == null) {
            return false;
        }
        var vehicleEntity = vehicleService.getById(entity.getVehicleId());
        if (vehicleEntity == null || vehicleEntity.getDrivingPermitId() == null) {
            return false;
        }
        vehicleEntity.setDrivingPermitId(-1L);
        vehicleEntity.setUpdateTime(LocalDateTime.now());
        return removeById(id) && vehicleService.updateById(vehicleEntity);
    }

    @Override
    public boolean updateOne(VehicleDrivingPermitEntity entity) {
        entity.setUpdateTime(LocalDateTime.now());
        return updateById(entity);
    }
}
