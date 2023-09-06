package com.pigeon.logistics.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * 地址状态
 *
 * @author dxy
 * @date 2023年3月28日
 */
public enum AddressStates {

    /**
     * 非默认地址
     */
    NORMAL(0),

    /**
     * 默认地址
     */
    DEFAULT(1);

    @EnumValue
    private final Integer value;

    AddressStates(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }
}
