package com.pigeon.logistics.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * @author Rolin
 */
public enum InsurancePay {
    /**
     * 已经交车强险
     */
    YES(0),

    /**
     * 没有交车强险
     */
    NO(1);

    @EnumValue
    private final Integer value;

    InsurancePay(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }
}
