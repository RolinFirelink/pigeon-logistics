package com.pigeon.logistics.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * @author dxy
 * @date 2023年2月9日
 */

public enum BusinessTypeEnum {

    /**
     * 其它
     */
    OTHER(0, "其它"),

    /**
     * 新增
     */
    INSERT(1, "新增"),

    /**
     * 修改
     */
    UPDATE(2, "修改"),

    /**
     * 删除
     */
    DELETE(3, "删除");


    /**
     * 代码
     */
    @EnumValue // @EnumValue 标记数据库存的值是 value
    private final Integer code;

    /**
     * 信息
     */
    private final String message;

    BusinessTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
