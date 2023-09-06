package com.pigeon.logistics.entity.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;


/**
 * @param dateTime    操作日期时间
 * @param status      操作状态
 * @param operator    操作员
 * @param address     地址
 * @param operaDetail 操作细节
 * @param phone       联系电话
 */
@Schema(description = "物流日志类")
public record LogisticsLogVO(

        @Schema(description = "操作日期时间")
        @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss")
        LocalDateTime dateTime,

        @Schema(description = "操作状态")
        String status,

        @Schema(description = "操作员")
        String operator,

        @Schema(description = "地址")
        String address,

        @Schema(description = "操作细节")
        String operaDetail,

        @Schema(description = "联系电话")
        String phone
) {
}
