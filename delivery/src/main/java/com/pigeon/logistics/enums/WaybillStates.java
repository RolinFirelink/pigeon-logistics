package com.pigeon.logistics.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * 运单状态类
 *
 * @author dxy
 * @date 2023年2月13日
 */
public enum WaybillStates {

    /**
     * 有意向
     */
    INTENTION(0),

    /**
     * 待支付
     */
    PENDING_PAYMENT(1),

    /**
     * 待发货
     */
    PENDING_DELIVER(10),

    /**
     * 待收货
     */
    PENDING_RECEIVE(100),

    /**
     * 已完成
     */
    FINISH(200);

    @EnumValue
    private final Integer value;

    WaybillStates(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }
}
