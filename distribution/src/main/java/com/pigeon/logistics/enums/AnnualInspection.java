package com.pigeon.logistics.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * @author Rolin
 */
public enum AnnualInspection {
    /**
     * 已经年检
     */
    YES(0),

    /**
     * 没有年检
     */
    NO(1);

    @EnumValue
    private final Integer value;

    AnnualInspection(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }
}
