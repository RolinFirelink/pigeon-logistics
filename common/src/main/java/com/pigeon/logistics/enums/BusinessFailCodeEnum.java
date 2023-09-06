package com.pigeon.logistics.enums;

import com.pigeon.logistics.util.ResultCode;

/**
 * @author dxy
 * @date 2023年3月8日
 * 失败业务结果的编号枚举类
 */
public enum BusinessFailCodeEnum implements ResultCode {

    /**
     * 权限不足，无法访问
     */
    PERMISSION_DENIED(3001, "权限不足，无法访问"),

    /**
     * 重复操作
     */
    REPETITIVE_OPERATION(3002, "重复操作"),

    /**
     * 参数错误
     */
    PARAMETER_ERROR(3003, "参数错误"),

    /**
     * 请求方式错误
     */
    REQUEST_METHOD_NOT_SUPPORTED(3004, "请求方式错误");

    /**
     * 编号
     */
    private final Integer code;

    /**
     * 描述
     */
    private final String desc;

    BusinessFailCodeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
