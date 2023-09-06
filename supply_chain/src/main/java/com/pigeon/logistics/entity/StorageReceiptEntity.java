package com.pigeon.logistics.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 入库单主表
 *
 * @author dxy cza
 * @date 2023年3月7日
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("lg_storage_receipt_order")
public class StorageReceiptEntity {

    /**
     * 主键ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 入库单号
     */
    private String code;

    /*
    计划ID
     */
    private Long planId;

    /**
     * 仓库ID
     */
    private Long warehouseId;

    /**
     * 仓库名称
     */
    private String warehouseName;

    /**
     * 发货人ID
     */
    private Long senderId;

    /**
     * 发货人姓名
     */
    private String senderName;

    /**
     * 发货人联系电话
     */
    private String senderPhone;

    /**
     * 发货人详细地址
     */
    private String senderAddress;

    /**
     * 四级地址编码
     */
    private String senderAddressCode;

    /**
     * 总金额
     */
    private BigDecimal totalAmount;

    /**
     * 收货单位及经手人
     */
    private String receiptHandler;

    /**
     * 送货单位及经手人
     */
    private String sendHandler;

    /**
     * 录单日期
     */
    private LocalDateTime editDate;

    /**
     * 备注，客户须知（收货须知）
     */
    private String remark;

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

}
