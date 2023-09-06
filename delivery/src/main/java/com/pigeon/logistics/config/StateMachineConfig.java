package com.pigeon.logistics.config;

import com.pigeon.logistics.enums.WaybillEvents;
import com.pigeon.logistics.enums.WaybillStates;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;


/**
 * FIXME 多个运单多个用户操作时会有问题。使用Builder：https://www.jianshu.com/p/ee8ecfacf6ed
 *
 * @author dxy
 * @date 2023年02月14日
 */
@Configuration
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<WaybillStates, WaybillEvents> {

    public static final String WAYBILL = "waybill";


    @Override
    public void configure(StateMachineConfigurationConfigurer<WaybillStates, WaybillEvents> config) throws Exception {
        config.withConfiguration()
                .autoStartup(true)
                .machineId("waybillMachine");
    }

    @Override
    public void configure(StateMachineStateConfigurer<WaybillStates, WaybillEvents> states) throws Exception {
        // 配置初始状态和状态集
        states.withStates()
                // 初始状态
                .initial(WaybillStates.PENDING_PAYMENT)
                .end(WaybillStates.FINISH)
                .states(EnumSet.allOf(WaybillStates.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<WaybillStates, WaybillEvents> transitions) throws Exception {
        // 配置时间的转换
        transitions
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
    }


}
