package com.pigeon.logistics.entity.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @param code
 * @param planId
 * @param warehouseId
 * @param warehouseName
 * @param senderId
 * @param senderName
 * @param senderPhone
 * @param senderAddress
 * @param senderAddressCode
 * @param totalAmount
 * @param receiptHandler
 * @param sendHandler
 * @param editDate
 * @param remark
 * @param items
 * @param createTime
 * @author dxy cza
 * @date 2023年3月9日
 */
@Schema(description = "入库单视图类")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record StorageReceiptVO(
        @Schema(description = "入库单号", accessMode = Schema.AccessMode.READ_ONLY)
        String code,
        @Schema(description = "计划ID", type = "string(int64)")
        @JsonSerialize(using = ToStringSerializer.class)
        Long planId,
        @Schema(description = "仓库ID")
        Long warehouseId,
        @Schema(description = "仓库名称")
        String warehouseName,
        @Schema(description = "发货人ID")
        Long senderId,
        @Schema(description = "发货人姓名")
        String senderName,
        @Schema(description = "发货人联系电话")
        String senderPhone,
        @Schema(description = "发货人详细地址")
        String senderAddress,
        @Schema(description = "四级地址编码")
        String senderAddressCode,
        @Schema(description = "总金额")
        BigDecimal totalAmount,
        @Schema(description = "收货单位及经手人")
        String receiptHandler,
        @Schema(description = "送货单位及经手人")
        String sendHandler,
        @Schema(description = "录单日期")
        LocalDateTime editDate,
        @Schema(description = "备注，客户须知（收货须知）")
        String remark,
        @Schema(description = "入库单明细")
        List<StorageReceiptItemVO> items,
        @Schema(description = "创建时间")
        @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss")
        LocalDateTime createTime
) {
}
