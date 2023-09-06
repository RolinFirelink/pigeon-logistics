package com.pigeon.logistics.entity.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @param code
 * @param deliveringWarehouseId
 * @param deliveringWarehouseName
 * @param customerName
 * @param contract
 * @param contractPhone
 * @param contractAddress
 * @param summary
 * @param totalAmount
 * @param editor
 * @param handler
 * @param editDate
 * @param remark
 * @param items
 * @param createTime
 * @author dxy
 * @date 2023年3月13日
 */
@Schema(description = "出库单视图类")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record OutboundOrderVO(
        @Schema(description = "出库单号", accessMode = Schema.AccessMode.READ_ONLY)
        String code,
        @Schema(description = "发货仓库ID", type = "string(int64)")
        @JsonSerialize(using = ToStringSerializer.class)
        Long deliveringWarehouseId,
        @Schema(description = "发货仓库名称")
        String deliveringWarehouseName,
        @Schema(description = "客户名称/提货部门")
        String customerName,
        @Schema(description = "联系人")
        String contract,
        @Schema(description = "联系电话")
        String contractPhone,
        @Schema(description = "联系地址")
        String contractAddress,
        @Schema(description = "摘要")
        String summary,
        @Schema(description = "总金额")
        BigDecimal totalAmount,
        @Schema(description = "制单人")
        String editor,
        @Schema(description = "经手人")
        String handler,
        @Schema(description = "录单日期")
        LocalDateTime editDate,
        @Schema(description = "备注，客户须知（收货须知）")
        String remark,
        @Schema(description = "出库单明细")
        List<OutboundOrderItemVO> items,
        @Schema(description = "创建时间", accessMode = Schema.AccessMode.READ_ONLY)
        LocalDateTime createTime
) {

    public OutboundOrderVO(List<OutboundOrderItemVO> items) {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, items, null);
    }

}
