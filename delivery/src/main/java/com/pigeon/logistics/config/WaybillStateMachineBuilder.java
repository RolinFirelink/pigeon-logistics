package com.pigeon.logistics.config;

import com.pigeon.logistics.enums.WaybillEvents;
import com.pigeon.logistics.enums.WaybillStates;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.stereotype.Component;

import java.util.EnumSet;

/**
 * 一个请求就新增一个状态机太好资源。应该是和业务对象一一对应才行，
 * 比如一个运单就一个状态机，而不是每次运单变化就build一个。
 * TODO 状态机的持久化
 *
 * @author dxy
 * @date 2023年02月14日
 */
@Component
public class WaybillStateMachineBuilder {
    private final static String MACHINEID = "waybillMachine";

    public StateMachine<WaybillStates, WaybillEvents> build(BeanFactory beanFactory, WaybillStates initState) throws Exception {
        StateMachineBuilder.Builder<WaybillStates, WaybillEvents> builder = StateMachineBuilder.builder();

        builder.configureConfiguration()
                .withConfiguration()
                .machineId(MACHINEID)
                .beanFactory(beanFactory);

        builder.configureStates()
                .withStates()
                .initial(initState)
                .states(EnumSet.allOf(WaybillStates.class));

        builder.configureTransitions()
                // 有意向  ————创建————>  待支付
                .withExternal()
                .source(WaybillStates.INTENTION).target(WaybillStates.PENDING_PAYMENT)
                .event(WaybillEvents.CREATED)
                // 待支付  ————支付————>  待发货
                .and().withExternal()
                .source(WaybillStates.PENDING_PAYMENT).target(WaybillStates.PENDING_DELIVER)
                .event(WaybillEvents.PAYED)
                // 待发货  ————发货————>  待收货
                .and().withExternal()
                .source(WaybillStates.PENDING_DELIVER).target(WaybillStates.PENDING_RECEIVE)
                .event(WaybillEvents.DELIVERY)
                // 待收货  ————签收————>  已完成
                .and().withExternal()
                .source(WaybillStates.PENDING_RECEIVE).target(WaybillStates.FINISH)
                .event(WaybillEvents.RECEIVE)
                // 待收货  ————拒签————>  已完成
                .and().withExternal()
                .source(WaybillStates.PENDING_RECEIVE).target(WaybillStates.FINISH)
                .event(WaybillEvents.REFUSED);

        return builder.build();
    }
}
