package com.pigeon.logistics.entity.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.pigeon.logistics.enums.WaybillStates;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @param code
 * @param logisticsOrderCode
 * @param receiverId
 * @param receiverName
 * @param receiverPhone
 * @param receiverProvince
 * @param receiverCity
 * @param receiverDistrict
 * @param receiverStreet
 * @param receiverAddress
 * @param senderId
 * @param senderName
 * @param senderPhone
 * @param senderProvince
 * @param senderCity
 * @param senderDistrict
 * @param senderStreet
 * @param senderAddress
 * @param status
 * @param logs
 * @param createTime
 * @param receiptTime
 * @author dxy
 * @date 2023年3月21日
 */
@Schema(description = "运单视图类")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record WaybillVO(
        @Schema(description = "运单号", accessMode = Schema.AccessMode.READ_ONLY)
        String code,
        @Schema(description = "物流订单号", example = "c1674898205000")
        String logisticsOrderCode,
        @Schema(description = "收货人ID", example = "347198745142394", type = "string(int64)")
        @JsonSerialize(using = ToStringSerializer.class)
        Long receiverId,
        @Schema(description = "收货人姓名", example = "锦诚明")
        String receiverName,
        @Schema(description = "收货人手机", example = "150xxxx6272")
        String receiverPhone,
        @Schema(description = "收货人省份", example = "广东省")
        String receiverProvince,
        @Schema(description = "收货人城市", example = "梅州市")
        String receiverCity,
        @Schema(description = "收货人地区", example = "丰顺县")
        String receiverDistrict,
        @Schema(description = "收货人街道")
        String receiverStreet,
        @Schema(description = "收货人详细地址")
        String receiverAddress,
        @Schema(description = "发货人ID", example = "361298752142372")
        @JsonSerialize(using = ToStringSerializer.class)
        Long senderId,
        @Schema(description = "发货人姓名", example = "月清疏")
        String senderName,
        @Schema(description = "发货人手机", example = "178xxxx7215")
        String senderPhone,
        @Schema(description = "发货人省份", example = "广东省")
        String senderProvince,
        @Schema(description = "发货人城市", example = "广州市")
        String senderCity,
        @Schema(description = "发货人地区", example = "海珠区")
        String senderDistrict,
        @Schema(description = "发货人街道", example = "滨江街道")
        String senderStreet,
        @Schema(description = "发货人详细地址", example = "郭墩街一巷7号")
        String senderAddress,
        @Schema(description = "状态")
        WaybillStates status,
        @Schema(description = "物流日志")
        List<LogisticsLogVO> logs,
        @Schema(description = "创建时间", accessMode = Schema.AccessMode.READ_ONLY)
        LocalDateTime createTime,
        @Schema(description = "签收时间", accessMode = Schema.AccessMode.READ_ONLY)
        LocalDateTime receiptTime
) {
    public WaybillVO(List<LogisticsLogVO> logs) {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, logs, null, null);
    }
}
