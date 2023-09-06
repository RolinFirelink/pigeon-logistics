package com.pigeon.logistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pigeon.logistics.entity.LogisticsOrderEntity;
import com.pigeon.logistics.mapper.LogisticsOrderMapper;
import com.pigeon.logistics.service.LogisticsOrderService;
import org.springframework.stereotype.Service;

/**
 * @author dxy
 * @date 2023年01月12日
 */
@Service
public class LogisticsOrderServiceImpl extends ServiceImpl<LogisticsOrderMapper, LogisticsOrderEntity> implements LogisticsOrderService {

    @Override
    public Boolean addOne(LogisticsOrderEntity order) {
        // 数据安全检查
        order.setId(null);
        // TODO 编号规则:<平台><时间戳> 临时的版本，注意并发问题
        order.setLgOrderCode("C" + System.currentTimeMillis());
        order.setCreateTime(null);
        order.setUpdateTime(null);
        return save(order);
    }

    @Override
    public LogisticsOrderEntity getOneByLgCode(String code) {
        var queryWrapper = new LambdaQueryWrapper<LogisticsOrderEntity>();
        queryWrapper.eq(LogisticsOrderEntity::getLgOrderCode, code);
        return getOne(queryWrapper);
    }

    @Override
    public LogisticsOrderEntity updateOne() {
        return null;
    }

    @Override
    public Boolean deleteOne(String code) {
        var queryWrapper = new LambdaQueryWrapper<LogisticsOrderEntity>();
        queryWrapper.eq(LogisticsOrderEntity::getLgOrderCode, code);
        return remove(queryWrapper);
    }

}
