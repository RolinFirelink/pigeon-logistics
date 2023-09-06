package com.pigeon.logistics.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author dxy
 * @date 2023年2月15日
 */
@Builder
@Data
public class LogisticsLogEntity {
    /**
     * 操作日期时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;

    /**
     * 操作状态
     */
    private String status;

    /**
     * 操作员
     */
    private String operator;

    /**
     * 地址
     */
    private String address;

    /**
     * 操作细节
     */
    private String operaDetail;

    /**
     * 联系电话
     */
    private String phone;

}
