package com.pigeon.logistics.listener;

import com.pigeon.logistics.entity.WaybillEntity;
import com.pigeon.logistics.enums.WaybillEvents;
import com.pigeon.logistics.enums.WaybillStates;
import com.pigeon.logistics.service.WaybillService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Component;

/**
 * 运单状态监听器，一旦状态发生变化，则出发对应的时间处理（观察者模式）
 *
 * @author dxy
 */
@Component
@Slf4j
@AllArgsConstructor
@WithStateMachine(id = "waybillMachine") // 绑定待监听的状态机
public class WaybillStateListener {

    final WaybillService waybillService;

    @OnTransition(source = "INTENTION", target = "PENDING_PAYMENT")
    public boolean createTransition(Message<WaybillEvents> message) {
        log.info(message.getHeaders().toString());
        var waybill = (WaybillEntity) message.getHeaders().get("waybill");
        if (waybill != null) {
            waybill.setStatus(WaybillStates.PENDING_PAYMENT);
        }
        return waybillService.updateById(waybill);
    }

    @OnTransition(source = "PENDING_PAYMENT", target = "PENDING_DELIVER")
    public boolean payTransition(Message<WaybillEvents> message) {
        log.info("pay:" + message.getHeaders());
        WaybillEntity waybill = (WaybillEntity) message.getHeaders().get("waybill");
        // todo 记录时间线索
        if (waybill != null) {
            waybill.setStatus(WaybillStates.PENDING_DELIVER);
        }
        return waybillService.updateById(waybill);
    }

    @OnTransition(source = "PENDING_DELIVER", target = "PENDING_RECEIVE")
    public boolean deliverTransition(Message<WaybillEvents> message) {
        log.info("deliver:" + message.getHeaders());
        WaybillEntity waybill = (WaybillEntity) message.getHeaders().get("waybill");
        // todo 记录时间线索
        if (waybill != null) {
            waybill.setStatus(WaybillStates.PENDING_RECEIVE);
        }
        return waybillService.updateById(waybill);
    }

    @OnTransition(source = "PENDING_RECEIVE", target = "FINISH")
    public boolean receiveTransition(Message<WaybillEvents> message) {
        log.info("receive:" + message.getHeaders());
        WaybillEntity waybill = (WaybillEntity) message.getHeaders().get("waybill");
        // todo 记录时间线索
        if (waybill != null) {
            waybill.setStatus(WaybillStates.FINISH);
        }
        return waybillService.updateById(waybill);
    }

    @OnTransition(source = "PENDING_RECEIVE", target = "FINISH")
    public boolean refuseTransition(Message<WaybillEvents> message) {
        log.info("refuse:" + message.getHeaders());
        WaybillEntity waybill = (WaybillEntity) message.getHeaders().get("waybill");
        // todo 记录时间线索
        if (waybill != null) {
            waybill.setStatus(WaybillStates.FINISH);
        }
        return waybillService.updateById(waybill);
    }

}
