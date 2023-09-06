package com.pigeon.logistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pigeon.logistics.config.StateMachineConfig;
import com.pigeon.logistics.config.WaybillStateMachineBuilder;
import com.pigeon.logistics.entity.VO.SearchCondition;
import com.pigeon.logistics.entity.WaybillEntity;
import com.pigeon.logistics.enums.WaybillEvents;
import com.pigeon.logistics.enums.WaybillStates;
import com.pigeon.logistics.mapper.WaybillMapper;
import com.pigeon.logistics.service.WaybillService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author dxy
 * @date 2023年3月21日
 */
@Log4j2
@Service
public class WaybillServiceImpl extends ServiceImpl<WaybillMapper, WaybillEntity> implements WaybillService {

    private final WaybillStateMachineBuilder waybillStateMachineBuilder;

    private final BeanFactory beanFactory;

    public WaybillServiceImpl(WaybillStateMachineBuilder waybillStateMachineBuilder, BeanFactory beanFactory) {
        this.waybillStateMachineBuilder = waybillStateMachineBuilder;
        this.beanFactory = beanFactory;
    }

    @Override
    public WaybillEntity getOneByWaybillCode(String code) {
        var queryWrapper = new LambdaQueryWrapper<WaybillEntity>();
        queryWrapper.eq(WaybillEntity::getCode, code);
        return getOne(queryWrapper);
    }

    @Override
    public WaybillEntity updateOne() {
        // TODO 更新运单信息
        return null;
    }

    @Override
    public boolean deleteOne(String code) {
        var queryWrapper = new LambdaQueryWrapper<WaybillEntity>();
        queryWrapper.eq(WaybillEntity::getCode, code);
        return remove(queryWrapper);
    }

    @Override
    public boolean createOne(WaybillEntity waybill) throws Exception {
        // 数据安全检查
        waybill.setId(null);
        // TODO 编号规则:<平台><时间戳> 临时的版本，注意并发问题
        waybill.setCode("JL" + System.currentTimeMillis());
        waybill.setStatus(WaybillStates.PENDING_PAYMENT);
        waybill.setCreateTime(null);
        waybill.setUpdateTime(null);
        waybill.setReceiptTime(null);
        sendEvent(WaybillEvents.CREATED, waybill);
        return save(waybill);
    }

    @Override
    public boolean pay(Long id) throws Exception {
        var waybill = getById(id);
        log.info("线程名称：" + Thread.currentThread().getName() + "尝试支付");
        sendEvent(WaybillEvents.PAYED, waybill);
        log.info("PAYED");
        return true;
    }

    @Override
    public boolean pay(String code) throws Exception {
        var waybill = getOneByWaybillCode(code);
        log.info("线程名称：" + Thread.currentThread().getName() + "尝试支付运单号：" + code);
        sendEvent(WaybillEvents.PAYED, waybill);
        log.info("PAYED");
        return true;
    }

    @Override
    public boolean deliver(Long id) throws Exception {
        var waybill = getById(id);
        log.info("线程名称：" + Thread.currentThread().getName() + "尝试配送");
        sendEvent(WaybillEvents.DELIVERY, waybill);
        log.info("DELIVERY");
        return true;
    }

    @Override
    public boolean deliver(String code) throws Exception {
        var waybill = getOneByWaybillCode(code);
        log.info("线程名称：" + Thread.currentThread().getName() + "尝试配送运单号：" + code);
        sendEvent(WaybillEvents.DELIVERY, waybill);
        log.info("DELIVERY");
        return true;
    }

    @Override
    public boolean reveive(Long id) throws Exception {
        var waybill = getById(id);
        log.info("线程名称：" + Thread.currentThread().getName() + "尝试签收");
        sendEvent(WaybillEvents.RECEIVE, waybill);
        log.info("RECEIVE");
        return true;
    }

    @Override
    public boolean receive(String code) throws Exception {
        var waybill = getOneByWaybillCode(code);
        log.info("线程名称：" + Thread.currentThread().getName() + "尝试签收运单号：" + code);
        sendEvent(WaybillEvents.RECEIVE, waybill);
        log.info("RECEIVE");
        return true;
    }

    @Override
    public boolean refulse(Long id) throws Exception {
        var waybill = getById(id);
        log.info("线程名称：" + Thread.currentThread().getName() + "尝试拒签");
        sendEvent(WaybillEvents.REFUSED, waybill);
        log.info("REFUSED");
        return true;
    }

    @Override
    public boolean refulse(String code) throws Exception {
        var waybill = getOneByWaybillCode(code);
        log.info("线程名称：" + Thread.currentThread().getName() + "尝试拒签运单号：" + code);
        sendEvent(WaybillEvents.REFUSED, waybill);
        log.info("REFUSED");
        return true;
    }

    @Override
    public boolean deleteBatchByCodes(List<String> codes) {
        // TODO batch优化处理？
        var queryWrapper = new LambdaQueryWrapper<WaybillEntity>();
        queryWrapper.in(WaybillEntity::getCode, codes);
        return remove(queryWrapper);
    }

    @Override
    public Page<WaybillEntity> page(Integer page, Integer size, SearchCondition searchCondition) {
        var pageConfig = new Page<WaybillEntity>(page, size);
        Page<WaybillEntity> entitiesPage;

        if (searchCondition == null) {
            entitiesPage = page(pageConfig);
        } else {
            var wrapper = new LambdaQueryWrapper<WaybillEntity>();

            if (searchCondition.start() != null) {
                wrapper.and(item -> item.ge(WaybillEntity::getCreateTime, searchCondition.start())
                        .or().le(WaybillEntity::getReceiptTime, searchCondition.start())
                );
            }

            if (searchCondition.end() != null) {
                wrapper.and(item -> item.le(WaybillEntity::getCreateTime, searchCondition.end()));
            }

            if (StringUtils.hasText(searchCondition.string())) {
                var searchString = searchCondition.string();

                // TODO 搜索
                if (com.pigeon.logistics.util.StringUtils.isNumeric(searchString)) {
                    // FIXME 数字转型问题
                    if (!searchString.contains(".")) {
                        Long searchLong = null;
                        Integer searchInteger = null;
                        try {
                            searchLong = Long.valueOf(searchString);
                            searchInteger = Integer.valueOf(searchString);
                        } catch (NumberFormatException e) {
                            log.info("数字类型匹配转换错误");
                        }
                        Long finalSearchLong = searchLong;
                        Integer finalSearchInteger = searchInteger;
                        wrapper.or(item ->
                                item.or().like(WaybillEntity::getLogisticsOrderId, finalSearchLong)
                                        .or().eq(WaybillEntity::getEcp, finalSearchInteger)

                        );
                    }
                    wrapper.and(item ->
                            item.or().eq(WaybillEntity::getPrice, new BigDecimal(searchString))
                    );
                }

                wrapper.or(item ->
                        item.or().like(WaybillEntity::getCode, searchString)
                                .or().like(WaybillEntity::getLogisticsOrderCode, searchString)
                                .or().like(WaybillEntity::getSku, searchString)
                                .or().like(WaybillEntity::getReceiverName, searchString)
                                .or().like(WaybillEntity::getReceiverPhone, searchString)
                                .or().like(WaybillEntity::getReceiverEmail, searchString)
                                .or().like(WaybillEntity::getReceiverProvince, searchString)
                                .or().like(WaybillEntity::getReceiverCity, searchString)
                                .or().like(WaybillEntity::getReceiverDistrict, searchString)
                                .or().like(WaybillEntity::getReceiverStreet, searchString)
                                .or().like(WaybillEntity::getReceiverAddress, searchString)
                                .or().like(WaybillEntity::getReceiverAddressCode, searchString)
                                .or().like(WaybillEntity::getSenderName, searchString)
                                .or().like(WaybillEntity::getSenderPhone, searchString)
                                .or().like(WaybillEntity::getSenderEmail, searchString)
                                .or().like(WaybillEntity::getSenderProvince, searchString)
                                .or().like(WaybillEntity::getSenderCity, searchString)
                                .or().like(WaybillEntity::getSenderDistrict, searchString)
                                .or().like(WaybillEntity::getSenderStreet, searchString)
                                .or().like(WaybillEntity::getSenderAddress, searchString)
                                .or().like(WaybillEntity::getSenderAddressCode, searchString)
                                .or().like(WaybillEntity::getRemark, searchString)
                );
            }

            entitiesPage = page(pageConfig, wrapper);
        }

        return entitiesPage;
    }


    /**
     * 发送运单状态转换事件
     */
    private synchronized void sendEvent(
            WaybillEvents event,
            WaybillEntity body
    ) throws Exception {
        StateMachine<WaybillStates, WaybillEvents> stateMachine = waybillStateMachineBuilder.build(beanFactory, body.getStatus());
        stateMachine.startReactively().subscribe();
        stateMachine.sendEvent(Mono.just(
                MessageBuilder.withPayload(event).setHeader(StateMachineConfig.WAYBILL, body).build()
        )).subscribe();
    }

}
