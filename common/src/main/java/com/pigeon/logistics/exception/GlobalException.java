package com.pigeon.logistics.exception;

import com.pigeon.logistics.entity.VO.Result;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 全局异常类处理
 *
 * @author dxy
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GlobalException extends RuntimeException {

    /**
     * 统一结果类
     */
    private Result<Object> result;

    public GlobalException(Result<Object> result) {
        super(result.getMessage());
        this.result = result;
    }

}
