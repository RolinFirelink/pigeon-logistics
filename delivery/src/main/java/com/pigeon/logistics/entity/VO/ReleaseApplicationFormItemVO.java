package com.pigeon.logistics.entity.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "放行条")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ReleaseApplicationFormItemVO(
        @Schema(description = "放行条单号", type = "string(int64)")
        @JsonSerialize(using = ToStringSerializer.class)
        Long releaseApplicationFormId,
        @Schema(description = "放行条明细编号", type = "string(int64)")
        @JsonSerialize(using = ToStringSerializer.class)
        Long id,
        @Schema(description = "物品名称")
        String name,
        @Schema(description = "规格")
        String specifications,
        @Schema(description = "单位")
        String unit,
        @Schema(description = "数量")
        Double count,
        @Schema(description = "单价：单位数量的价格")
        BigDecimal price,
        @Schema(description = "签名图片地址")
        String signImgUrl,
        @Schema(description = "备注")
        String remark
) {
}
