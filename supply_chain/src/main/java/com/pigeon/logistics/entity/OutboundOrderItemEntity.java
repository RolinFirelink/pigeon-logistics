package com.pigeon.logistics.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 出库单子表
 *
 * @author dxy
 * @date 2023年3月13日
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("lg_outbound_item_order")
public class OutboundOrderItemEntity {

    /**
     * 主键ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 出库单编号
     */
    private String outboundOrderCode;

    /**
     * 商品编号
     */
    private String code;

    /**
     * 商品全名
     */
    private String name;

    /**
     * 规格
     */
    private String specifications;

    /**
     * 单位
     */
    private String unit;

    /**
     * 数量
     */
    private Double count;

    /**
     * 单价：单位数量的价格
     */
    private BigDecimal price;

    /**
     * 签名图片地址
     */
    private String signImgUrl;

    /**
     * 备注
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
