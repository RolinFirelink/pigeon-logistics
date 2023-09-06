package com.pigeon.logistics.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 放行申请表
 *
 * @author dxy
 * @date 2022年2月6日
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("lg_release_application")
public class ReleaseApplicationFormEntity {

    @TableId(type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 申请部门
     */
    @TableField("dept")
    private String applicationDept;

    /**
     * 申请日期
     */
    @TableField("date")
    private LocalDateTime applicationDate;

    /**
     * 申请理由
     */
    @TableField("reason")
    private String applicationReason;

    /**
     * 部门建议
     */
    private String deptAdvice;

    /**
     * 部门建议签名
     */
    private String deptAdviceSign;

    /**
     * 部门建议签名图片地址
     */
    private String deptAdviceSignImgUrl;

    /**
     * 部门签名日期
     */
    private LocalDateTime deptAdviceDate;

    /**
     * 批准
     */
    private String approvalBy;

    /**
     * 拟定
     */
    private String drawnUpBy;

    /**
     * 审核
     */
    private String reviewBy;

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
