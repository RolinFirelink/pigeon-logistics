package com.pigeon.logistics.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pigeon.logistics.entity.AddressEntity;
import com.pigeon.logistics.enums.AddressStates;
import com.pigeon.logistics.mapper.AddressMapper;
import com.pigeon.logistics.service.AddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Rolin
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, AddressEntity> implements AddressService {

    @Override
    public boolean addOne(AddressEntity address) {
        address.setCreateTime(LocalDateTime.now());
        address.setUpdateTime(LocalDateTime.now());
        return save(address);
    }

    @Override
    public boolean deleteByIds(List<Long> ids) {
        return removeBatchByIds(ids);
    }

    @Override
    public boolean deleteOne(Long id) {
        return removeById(id);
    }

    @Override
    public boolean updateOne(AddressEntity address) {
        address.setUpdateTime(LocalDateTime.now());
        return updateById(address);
    }

    @Override
    public boolean setDefault(Long id) {
        var list = list();
        var collect = list.stream().peek((item) -> {
            if (!item.getId().equals(id)) {
                item.setIsDefault(AddressStates.NORMAL);
            } else {
                item.setIsDefault(AddressStates.DEFAULT);
            }
        }).collect(Collectors.toList());
        return updateBatchById(collect);
    }
}
