package com.pigeon.logistics.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 出库单主表
 *
 * @author dxy
 * @date 2023年3月13日
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("lg_outbound_order")
public class OutboundOrderEntity {

    /**
     * 主键ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 单据编号
     */
    private String code;

    /**
     * 发货仓库ID
     */
    private Long deliveringWarehouseId;

    /**
     * 发货仓库名称
     */
    private String deliveringWarehouseName;

    /**
     * 客户名称/提货部门
     */
    private String customerName;

    /**
     * 联系人
     */
    private String contract;

    /**
     * 联系电话
     */
    private String contractPhone;

    /**
     * 联系地址
     */
    private String contractAddress;

    /**
     * 摘要
     */
    private String summary;

    /**
     * 总金额
     */
    private BigDecimal totalAmount;

    /**
     * 制单人
     */
    private String editor;

    /**
     * 经手人
     */
    private String handler;

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

    public void init(Long id, String code) {
        init(id, code, 0);
    }

    public void init(Long id, String code, Integer occVersion) {
        this.setId(id);
        this.setCode(code);
        this.setOccVersion(occVersion);
        this.setCreateTime(null);
        this.setUpdateTime(null);
    }

}
