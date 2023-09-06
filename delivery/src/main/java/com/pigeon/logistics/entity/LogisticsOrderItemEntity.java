package com.pigeon.logistics.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 物流订单子单表：物流订单关联信息、商品/sku信息、买家/卖家信息、通用字段
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
@TableName("lg_order_item")
public class LogisticsOrderItemEntity {
    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 物流订单号
     */
    private String lgOrderCode;

    /**
     * 交易订单号
     */
    private String tradeOrderCode;

    /**
     * 交易子单号
     */
    private String tradeSubOrderCode;

    /**
     * 包裹ID
     */
    private Long packageId;

    /**
     * SKU ID
     */
    private Long skuId;

    /**
     * SKU名称
     */
    private String skuName;

    /**
     * 买家ID
     */
    private Long buyerId;

    /**
     * 卖家ID
     */
    private Long sellerId;

    /**
     * 店铺ID
     */
    private Long shopId;

    /**
     * 商品ID
     */
    private Long itemId;

    /**
     * 商品类型
     */
    private Integer itemType;

    /**
     * 商品名称
     */
    private String itemName;

    /**
     * 商品重量
     */
    private BigDecimal itemWeight;

    /**
     * 商品体积
     */
    private BigDecimal itemVolume;

    /**
     * 商品标签信息
     */
    private String marking;

    /**
     * 状态
     */
    private Integer status;


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
     * 临时字段，用来描述
     */
    @TableField(exist = false)
    private String remark;

}
