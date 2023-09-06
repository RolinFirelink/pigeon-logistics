package com.pigeon.logistics.entity.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

/**
 * @author dxy cza
 * @date 2023年3月9日
 */
@Schema(description = "入库单明细")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record StorageReceiptItemVO(
        @Schema(description = "入库单号", type = "string(int64)")
        @JsonSerialize(using = ToStringSerializer.class)
        Long storageReceiptCode,
        @Schema(description = "商品全名")
        String name,
        @Schema(description = "规格")
        String specifications,
        @Schema(description = "单位")
        String unit,
        @Schema(description = "数量")
        Double count,
        @Schema(description = "单价：单位数量的价格")
        BigDecimal price,
        @Schema(description = "金额")
        BigDecimal amount,
        @Schema(description = "备注")
        String remark
) {
}
