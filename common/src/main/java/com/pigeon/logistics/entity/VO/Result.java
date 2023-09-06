package com.pigeon.logistics.entity.VO;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.pigeon.logistics.util.ResultCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * 统一消息结果类
 *
 * @author dxy
 * @date 2022年3月28日
 */
@Schema(description = "统一结果返回类")
@Data
public class Result<T> {

    public static Integer BUSINESS_SUCCESS = 2000;
    public static Integer BUSINESS_FAIL = 3000;
    public static Integer SERVICE_EXCEPTION = 4000;

    @Schema(description = "是否成功")
    private Boolean success;

    @Schema(description = "返回码（'2xxx'为业务成功，'3xxx'为业务失败，'4xxx'为服务器异常）")
    private Integer code;

    @Schema(description = "返回消息")
    private String message;

    @Schema(description = "返回数据")
    private T data;

    @Schema(description = "时间戳", type = "string(int64)")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long time = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();

    public Result() {
    }

    public Result<T> success() {
        Result<T> r = this;
        r.setSuccess(true);
        r.setCode(BUSINESS_SUCCESS);
        r.setMessage("访问成功");
        return r;
    }

    public Result<T> fail() {
        Result<T> r = this;
        r.setSuccess(false);
        r.setCode(BUSINESS_FAIL);
        r.setMessage("访问失败");
        return r;
    }

    public Result<T> fail(ResultCode code) {
        Result<T> r = this;
        r.setSuccess(false);
        r.setCode(code.getCode());
        r.setMessage(code.getDesc());
        return r;
    }

    public Result<T> error() {
        Result<T> r = this;
        r.setSuccess(false);
        r.setCode(SERVICE_EXCEPTION);
        r.setMessage("访问异常");
        return r;
    }

    public Result<T> error(ResultCode code) {
        Result<T> r = this;
        r.setSuccess(false);
        r.setCode(code.getCode());
        r.setMessage(code.getDesc());
        return r;
    }

    public Result<T> success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public Result<T> message(String message) {
        this.setMessage(message);
        return this;
    }

    public Result<T> code(ResultCode code) {
        this.setCode(code.getCode());
        return this;
    }

    public Result<T> data(T obj) {
        this.setData(obj);
        return this;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
