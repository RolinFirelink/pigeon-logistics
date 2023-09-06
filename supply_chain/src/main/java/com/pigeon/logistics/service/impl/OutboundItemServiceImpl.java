package com.pigeon.logistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pigeon.logistics.entity.OutboundOrderItemEntity;
import com.pigeon.logistics.mapper.OutboundOrderItemMapper;
import com.pigeon.logistics.service.OutboundOrderItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author dxy
 */
@Service
public class OutboundItemServiceImpl
        extends ServiceImpl<OutboundOrderItemMapper, OutboundOrderItemEntity>
        implements OutboundOrderItemService {

    @Override
    public List<OutboundOrderItemEntity> listByOrderCode(String orderCode) {
        var queryWrapper = new LambdaQueryWrapper<OutboundOrderItemEntity>();
        queryWrapper.eq(OutboundOrderItemEntity::getOutboundOrderCode, orderCode);
        return list(queryWrapper);
    }

    @Override
    public boolean addOneByOrderCode(String orderCode, OutboundOrderItemEntity item) {
        item.init(null, "OBI" + System.currentTimeMillis());
        item.setOutboundOrderCode(orderCode);
        return save(item);
    }

    @Override
    public boolean updateOneByOrderCode(String orderCode, OutboundOrderItemEntity item) {
        var queryWrapper = new LambdaQueryWrapper<OutboundOrderItemEntity>();
        queryWrapper.eq(OutboundOrderItemEntity::getOutboundOrderCode, orderCode);
        queryWrapper.eq(OutboundOrderItemEntity::getCode, item.getCode());
        var originRecord = getOne(queryWrapper);
        item.init(originRecord.getId(), originRecord.getCode(), originRecord.getOccVersion());
        return updateById(item);
    }

    @Override
    @Transactional
    public boolean deleteBatch(String orderCode, List<String> itemCodes) {
        var queryWrapper = new LambdaQueryWrapper<OutboundOrderItemEntity>();
        queryWrapper.eq(OutboundOrderItemEntity::getOutboundOrderCode, orderCode);
        queryWrapper.in(OutboundOrderItemEntity::getCode, itemCodes);
        return remove(queryWrapper);
    }
}
