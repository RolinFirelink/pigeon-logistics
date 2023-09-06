package com.pigeon.logistics.entity.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

/**
 * @author dxy
 * @date 2023年3月9日
 */
@Schema(description = "出库单明细")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record OutboundOrderItemVO(
        @Schema(description = "出库单号")
        String outboundOrderCode,
        @Schema(description = "商品编号")
        String code,
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
        // TODO 金额
        // BigDecimal sum,
        @Schema(description = "签名图片地址")
        String signImgUrl,
        @Schema(description = "备注")
        String remark
) {
}
