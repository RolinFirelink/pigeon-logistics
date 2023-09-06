package com.pigeon.logistics.entity.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

/**
 * @param itemId
 * @param itemType
 * @param itemName
 * @param itemWeight
 * @param itemVolume
 * @param marking
 * @param status
 * @param remark
 * @author dxy
 * @date 2023年3月9日
 */
// @ApiModel("物流订单明细视图类")
@Schema(description = "物流订单明细视图类")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record LogisticsOrderItemVO(

        @Schema(description = "商品明细id", type = "string(int64)")
        @JsonSerialize(using = ToStringSerializer.class)
        Long itemId,
        @Schema(description = "商品类型")
        Integer itemType,
        @Schema(description = "商品名称")
        String itemName,
        @Schema(description = "重量")
        BigDecimal itemWeight,
        @Schema(description = "体积")
        BigDecimal itemVolume,
        @Schema(description = "标记")
        String marking,
        @Schema(description = "状态")
        Integer status,
        @Schema(description = "备注")
        String remark
) {
}