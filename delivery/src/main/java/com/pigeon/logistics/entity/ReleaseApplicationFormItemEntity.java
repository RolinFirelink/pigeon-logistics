package com.pigeon.logistics.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 旅行放行表子表
 *
 * @author dxy
 * @date 2023年3月16日
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("lg_release_application_item")
public class ReleaseApplicationFormItemEntity {

    /**
     * 主键ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 放行条单号
     */
    private Long releaseApplicationFormId;

    /**
     * 物品名称
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

    public void init() {
        init(null, 0);
    }

    public void init(Long id) {
        init(id, 0);
    }

    public void init(Long id, Integer occVersion) {
        this.setId(id);
        this.setOccVersion(occVersion);
        this.setCreateTime(null);
        this.setUpdateTime(null);
    }

}
