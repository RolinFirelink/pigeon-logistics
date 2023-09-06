package com.pigeon.logistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pigeon.logistics.entity.StorageReceiptEntity;
import com.pigeon.logistics.mapper.StorageReceiptMapper;
import com.pigeon.logistics.service.StorageReceiptService;
import org.springframework.stereotype.Service;

/**
 * @author dxy cza
 * @date 2023年3月7日
 */
@Service
public class StorageReceiptImpl extends ServiceImpl<StorageReceiptMapper, StorageReceiptEntity> implements StorageReceiptService {
    @Override
    public Boolean addOne(StorageReceiptEntity order) {
        // 数据安全检查
        order.setId(null);
        // TODO 编号规则:<平台><时间戳> 临时的版本，注意并发问题
        order.setCode("OB" + System.currentTimeMillis());
        order.setCreateTime(null);
        order.setUpdateTime(null);
        return save(order);
    }

    @Override
    public StorageReceiptEntity getOneByCode(String code) {
        var queryWrapper = new LambdaQueryWrapper<StorageReceiptEntity>();
        queryWrapper.eq(StorageReceiptEntity::getCode, code);
        return getOne(queryWrapper);
    }

    @Override
    public StorageReceiptEntity updateOne() {
        // TODO 更新运单信息
        return null;
    }

    @Override
    public Boolean deleteOne(String code) {
        var queryWrapper = new LambdaQueryWrapper<StorageReceiptEntity>();
        queryWrapper.eq(StorageReceiptEntity::getCode, code);
        return remove(queryWrapper);
    }
}
