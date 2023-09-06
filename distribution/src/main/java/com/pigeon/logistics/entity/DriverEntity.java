package com.pigeon.logistics.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.pigeon.logistics.enums.DrivingLicenseTypes;
import com.pigeon.logistics.enums.Genders;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Rolin
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("lg_driver")
public class DriverEntity {

    /**
     * 唯一主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 司机姓名
     */
    private String name;

    /**
     * 司机手机号
     */
    private String phone;

    /**
     * 司机电子邮箱
     */
    private String email;

    /**
     * 司机年龄
     */
    private Integer age;

    /**
     * 入职时间
     */
    private LocalDateTime entryTime;

    /**
     * 驾驶证类别
     */
    @EnumValue
    private DrivingLicenseTypes licenseType;

    /**
     * 驾龄
     */
    private Integer drivingAge;

    /**
     * 居住地址
     */
    private String address;

    /**
     * 身份证号
     */
    private String idNumber;

    /**
     * 性别(0代表男,1代表女)
     */
    @EnumValue
    private Genders gender;

    /**
     * 司机头像
     */
    private String headPicture;

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
     * Mybatis plus 乐观锁并发控制
     * 仅支持 updateById(id) 与 update(entity, wrapper) 方法
     * 在 update(entity, wrapper) 方法下, wrapper 不能复用!!!
     */
    @Version
    @TableField(fill = FieldFill.UPDATE)
    private Integer occVersion;

    /**
     * 逻辑删除(0代表未删除,1代表删除)
     */
    @TableLogic
    private Integer deleted;
}
