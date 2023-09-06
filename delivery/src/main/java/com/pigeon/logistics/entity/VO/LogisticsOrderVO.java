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
 * 物流订单视图类
 *
 * @param lgOrderCode      物流订单编号
 * @param tradeOrderCode   交易单编号
 * @param receiverId       收货人ID
 * @param receiverName     收货人姓名
 * @param receiverPhone    收货人手机
 * @param receiverProvince 收货人省份
 * @param receiverCity     收货人城市
 * @param receiverDistrict 收货人地区
 * @param receiverStreet   收货人街道
 * @param receiverAddress  收货人详细地址
 * @param senderId         发货人ID
 * @param senderName       发货人姓名
 * @param senderPhone      发货人手机
 * @param senderProvince   发货人省份
 * @param senderCity       发货人城市
 * @param senderDistrict   发货人地区
 * @param senderStreet     发货人街道
 * @param senderAddress    发货人详细地址
 * @param status           状态
 * @param waybillCode      运单编号
 * @param expressCode      快递公司编号
 * @param expressName      快递公司名字
 * @param items            物流订单明细
 * @param remark           备注
 * @author dxy
 * @date 2023年3月9日
 */
@Schema(description = "物流订单视图类")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record LogisticsOrderVO(

        @Schema(description = "物流订单编号", accessMode = Schema.AccessMode.READ_ONLY, example = "JL0093529424183")
        String lgOrderCode,
        @Schema(description = "交易单编号", example = "JL0093529424183")
        String tradeOrderCode,
        @Schema(description = "收货人ID", type = "string(int64)")
        @JsonSerialize(using = ToStringSerializer.class)
        Long receiverId,
        @Schema(description = "收货人姓名")
        String receiverName,
        @Schema(description = "收货人手机")
        String receiverPhone,
        @Schema(description = "收货人电子邮箱")
        String receiverEmail,
        @Schema(description = "收货人省份")
        String receiverProvince,
        @Schema(description = "收货人城市")
        String receiverCity,
        @Schema(description = "收货人地区")
        String receiverDistrict,
        @Schema(description = "收货人街道")
        String receiverStreet,
        @Schema(description = "收货人详细地址")
        String receiverAddress,
        @Schema(description = "发货人ID", type = "string(int64)")
        @JsonSerialize(using = ToStringSerializer.class)
        Long senderId,
        @Schema(description = "发货人姓名")
        String senderName,
        @Schema(description = "发货人手机")
        String senderPhone,
        @Schema(description = "发货人电子邮箱")
        String senderEmail,
        @Schema(description = "发货人省份")
        String senderProvince,
        @Schema(description = "发货人城市")
        String senderCity,
        @Schema(description = "发货人地区")
        String senderDistrict,
        @Schema(description = "发货人街道")
        String senderStreet,
        @Schema(description = "发货人详细地址")
        String senderAddress,
        // 买家/卖家相关字段
        // 物品信息
        @Schema(description = "物品类型")
        String goodsType,
        @Schema(description = "总重量(单位KG)")
        Integer weight,
        @Schema(description = "体积（可以直接填写体积，也可以填长宽高自动计算）")
        BigDecimal volume,
        @Schema(description = "件数")
        Integer itemsQuantity,
        @Schema(description = "状态")
        Integer status,
        @Schema(description = "运单编号", example = "JD0093746583382")
        String waybillCode,
        @Schema(description = "快递公司编号", example = "JL")
        String expressCode,
        @Schema(description = "快递公司名字", example = "金绿食品")
        String expressName,
        @Schema(description = "物流订单明细")
        List<LogisticsOrderItemVO> items,
        @Schema(description = "备注，给快递员捎话", title = "给快递员捎话")
        String remark,
        @Schema(description = "下单时间", accessMode = Schema.AccessMode.READ_ONLY, example = "2023年1月12日 15:36:07")
        @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss")
        LocalDateTime createTime
) {
}
