package com.pigeon.logistics.entity;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.pigeon.logistics.enums.WaybillStates;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 运单表
 *
 * @author dxy
 * @date 2023年1月11日
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "lg_waybill", autoResultMap = true)
public class WaybillEntity {

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 运单号
     */
    private String code;


    /**
     * 物流订单ID
     */
    private Long logisticsOrderId;

    /**
     * 物流订单编号
     */
    private String logisticsOrderCode;

    /**
     * SKU 全称 Stock Keeping Unit ，库存进出计量的单位，SKU 是物理上不可分割的最小存货单元。
     * json格式
     */
    private String sku;

    /**
     * 质检员ID
     */
    private Long qaId;

    /**
     * 发货员ID
     */
    private Long deId;

    /**
     * 运费
     */
    private BigDecimal price;

    /**
     * 地址ID
     */
    private Long addressId;

    /**
     * 仓库ID
     */
    private Long warehouseId;

    /**
     * 快递公司编号
     */
    private Integer ecp;

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

    // TODO 运送动态 JSON

    /**
     * 状态
     */
    @EnumValue
    private WaybillStates status;

    @TableField(typeHandler = JacksonTypeHandler.class)
    private JSONArray logs;

    /**
     * 签收时间
     */
    private LocalDateTime receiptTime;

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
    private String remark;

}
