package com.pigeon.logistics.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 物流订单主单表：订单基础字段、发货人信息字段、收货人信息字段、买家/卖家信息字段、快递信息字段、通用字段
 * 索引设计：
 * a)、主键id
 * b)、普通索引字段：lg_order_code、buyer_id
 *
 * @author dxy
 * @date 2022年12月22日
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("lg_order")
public class LogisticsOrderEntity {

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 物流单号
     */
    private String lgOrderCode;

    /**
     * 交易单号
     */
    private String tradeOrderCode;

    /**
     * 收货人ID
     */
    private Long receiverId;

    /**
     * 收货人姓名
     */
    private String receiverName;

    /**
     * 收货人手机
     */
    private String receiverPhone;

    /**
     * 收货人电子邮箱
     */
    private String receiverEmail;

    /**
     * 收货人省份
     */
    private String receiverProvince;

    /**
     * 收货人城市
     */
    private String receiverCity;

    /**
     * 收货人地区
     */
    private String receiverDistrict;

    /**
     * 收货人街道
     */
    private String receiverStreet;

    /**
     * 收货人详细地址
     */
    private String receiverAddress;

    /**
     * 收货人四级地址编码
     */
    private String receiverAddressCode;

    /**
     * 发货人ID
     */
    private Long senderId;

    /**
     * 发货人姓名
     */
    private String senderName;

    /**
     * 发货人手机
     */
    private String senderPhone;

    /**
     * 发货人电子邮箱
     */
    private String senderEmail;

    /**
     * 发货人省份
     */
    private String senderProvince;

    /**
     * 发货人城市
     */
    private String senderCity;

    /**
     * 发货人地区
     */
    private String senderDistrict;

    /**
     * 发货人街道
     */
    private String senderStreet;

    /**
     * 发货人详细地址
     */
    private String senderAddress;

    /**
     * 发货人四级地址编码
     */
    private String senderAddressCode;

    /**
     * 买家id
     */
    private Long buyerId;

    /**
     * 买家名称
     */
    private String buyerName;

    /**
     * 卖家id
     */
    private Long sellerId;

    /**
     * 卖家名称
     */
    private String sellerName;

    /**
     * 父物流单号
     */
    private String parentLgOrderCode;

    /**
     * 业务类型
     */
    private String bizType;

    /**
     * 订单来源
     */
    private Integer orderOrigin;

    /**
     * 订单类型
     */
    private Integer orderType;

    /**
     * 状态：已下单；已发货；运输中；已签收
     */
    private Integer status;

    /**
     * 运单ID
     */
    private Long waybillId;

    /**
     * 运单编号
     */
    private String waybillCode;

    /**
     * 快递公司编码
     */
    private String expressCode;

    /**
     * 快递公司名称
     */
    private String expressName;

    /**
     * 物品类型
     */
    private String goodsType;
    /**
     * 总重量(一般单位为KG，整车单位为吨)
     */
    private Integer weight;
    /**
     * 体积（可以直接填写体积，也可以填长宽高自动计算）
     */
    private BigDecimal volume;
    /**
     * 件数
     */
    private Integer itemsQuantity;

    /**
     * 文档：<a href="https://baomidou.com/pages/6b03c5">逻辑删除</a>
     */
    @TableLogic
    private Integer deleted;

    /**
     * Mybatis plus 乐观锁并发控制
     * 仅支持 updateById(id) 与 update(entity, wrapper) 方法
     * 在 update(entity, wrapper) 方法下, wrapper 不能复用!!!
     */
    @Version
    @TableField(fill = FieldFill.UPDATE)
    private Integer occVersion;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    /**
     * 给快递员捎话，备注
     */
    @TableField(exist = false)
    private String remark;

}
