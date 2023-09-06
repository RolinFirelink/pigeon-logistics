package com.pigeon.logistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pigeon.logistics.entity.OutboundOrderEntity;
import com.pigeon.logistics.entity.OutboundOrderItemEntity;
import com.pigeon.logistics.entity.VO.SearchCondition;
import com.pigeon.logistics.mapper.OutboundOrderMapper;
import com.pigeon.logistics.service.OutboundOrderItemService;
import com.pigeon.logistics.service.OutboundOrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author dxy
 * @date 2023年3月22日
 */
@Service
@AllArgsConstructor
public class OutboundServiceImpl extends ServiceImpl<OutboundOrderMapper, OutboundOrderEntity> implements OutboundOrderService {

    private final OutboundOrderItemService outboundItemService;


    @Override
    @Transactional
    public boolean addOne(OutboundOrderEntity order, List<OutboundOrderItemEntity> items) {
        // 数据安全检查
        // TODO 编号规则:<平台><时间戳> 临时的版本，注意并发问题
        order.init(null, "OB" + System.currentTimeMillis());
        boolean res = save(order);

        for (int i = 0; i < items.size(); i++) {
            var item = items.get(i);
            item.init(null, "OBI" + System.currentTimeMillis() + i);
            item.setOutboundOrderCode(order.getCode());
        }
        outboundItemService.saveBatch(items);
        return res;

    }

    @Override
    public OutboundOrderEntity getOneByCode(String code) {
        var queryWrapper = new LambdaQueryWrapper<OutboundOrderEntity>();
        queryWrapper.eq(OutboundOrderEntity::getCode, code);
        return getOne(queryWrapper);
    }

    @Override
    @Transactional
    public boolean updateByOrderCode(OutboundOrderEntity entity) {
        var queryWrapper = new LambdaQueryWrapper<OutboundOrderEntity>();
        queryWrapper.eq(OutboundOrderEntity::getCode, entity.getCode());
        var originRecord = getOne(queryWrapper);
        entity.init(originRecord.getId(), originRecord.getCode(), originRecord.getOccVersion());
        return updateById(entity);
    }

    @Override
    @Transactional
    public boolean deleteOne(String code) {

        var itemQueryWrapper = new LambdaQueryWrapper<OutboundOrderItemEntity>();
        itemQueryWrapper.eq(OutboundOrderItemEntity::getOutboundOrderCode, code);
        outboundItemService.remove(itemQueryWrapper);

        var orderQueryWrapper = new LambdaQueryWrapper<OutboundOrderEntity>();
        orderQueryWrapper.eq(OutboundOrderEntity::getCode, code);

        return remove(orderQueryWrapper);
    }

    @Override
    public Page<OutboundOrderEntity> page(Integer page, Integer size, SearchCondition searchCondition) {
        var pageConfig = new Page<OutboundOrderEntity>(page, size);
        Page<OutboundOrderEntity> entitiesPage;

        if (searchCondition == null) {
            entitiesPage = page(pageConfig);
        } else {
            var wrapper = new LambdaQueryWrapper<OutboundOrderEntity>();

            if (searchCondition.start() != null) {
                wrapper.and(item -> item.ge(OutboundOrderEntity::getCreateTime, searchCondition.start())
                );
            }

            if (searchCondition.end() != null) {
                wrapper.and(item -> item.le(OutboundOrderEntity::getCreateTime, searchCondition.end()));
            }

            if (StringUtils.hasText(searchCondition.string())) {
                var searchString = searchCondition.string();
                wrapper.and(item ->
                        item.or().like(OutboundOrderEntity::getCode, searchString)
                                .or().like(OutboundOrderEntity::getDeliveringWarehouseName, searchString)
                                .or().like(OutboundOrderEntity::getCustomerName, searchString)
                                .or().like(OutboundOrderEntity::getContract, searchString)
                                .or().like(OutboundOrderEntity::getContractPhone, searchString)
                                .or().like(OutboundOrderEntity::getContractAddress, searchString)
                                .or().like(OutboundOrderEntity::getSummary, searchString)
                                .or().like(OutboundOrderEntity::getEditor, searchString)
                                .or().like(OutboundOrderEntity::getHandler, searchString)
                                .or().like(OutboundOrderEntity::getRemark, searchString)
                );
            }

            entitiesPage = page(pageConfig, wrapper);
        }

        return entitiesPage;
    }

    @Override
    public boolean deleteBatchByIds(List<String> orderCodes) {
        var queryWrapper = new LambdaQueryWrapper<OutboundOrderEntity>();
        queryWrapper.in(OutboundOrderEntity::getCode, orderCodes);
        return remove(queryWrapper);
    }

}
