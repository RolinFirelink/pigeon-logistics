package com.pigeon.logistics.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * @author Rolin
 */
public enum Genders {
    /**
     * 男
     */
    MAN(0),

    /**
     * 女
     */
    WOMEN(1);

    @EnumValue
    private final Integer value;

    Genders(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }
}
