package com.pigeon.logistics.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * @author Rolin
 */
public enum Scrap {
    /**
     * 没有报废
     */
    YES(0),

    /**
     * 已经报废
     */
    NO(1);

    @EnumValue
    private final Integer value;

    Scrap(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }
}
