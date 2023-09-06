package com.pigeon.logistics.util;

/**
 * 结果状态编号接口
 *
 * @author dxy
 * @date 2022年12月8日
 */
public interface ResultCode {
    /**
     * 获取结果状态编号
     *
     * @return 结果状态编号
     */
    default Integer getCode() {
        return 2000;
    }

    /**
     * 获取结果状态简短描述信息
     *
     * @return 状态描述
     */
    default String getDesc() {
        return "success";
    }
}
