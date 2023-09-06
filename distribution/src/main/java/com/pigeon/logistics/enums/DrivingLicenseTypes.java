package com.pigeon.logistics.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * @author Rolin
 */
public enum DrivingLicenseTypes {
    /**
     * C1驾驶证
     */
    C1(0),

    /**
     * B2驾驶证
     */
    B2(1),

    /**
     * A2驾驶证
     */
    A2(2);

    @EnumValue
    private final Integer value;

    DrivingLicenseTypes(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }
}
