package com.pigeon.logistics.entity.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "条件查询")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record SearchCondition(

        @Schema(description = "字符串匹配条件", example = "锦诚明")
        String string,

        @Schema(description = "日期时间起始条件", example = "2023-03-08T00:00:00", type = "string")
        LocalDateTime start,

        @Schema(description = "日期时间结束条件", example = "2023-03-24T00:00:00", type = "string")
        LocalDateTime end
) {
}
