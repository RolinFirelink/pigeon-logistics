package com.pigeon.logistics.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pigeon.logistics.entity.DriverEntity;
import com.pigeon.logistics.mapper.DriverMapper;
import com.pigeon.logistics.service.DriverService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Rolin
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DriverServiceImpl extends ServiceImpl<DriverMapper, DriverEntity> implements DriverService {

    @Override
    public boolean addOne(DriverEntity driver) {
        driver.setCreateTime(LocalDateTime.now());
        driver.setUpdateTime(LocalDateTime.now());
        return save(driver);
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
    public boolean updateOne(DriverEntity entity) {
        entity.setUpdateTime(LocalDateTime.now());
        return updateById(entity);
    }
}
