package com.pigeon.logistics.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.pigeon.logistics.enums.AddressStates;
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
@TableName("lg_address")
public class AddressEntity {
    /**
     * 唯一主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 四级编码
     */
    private String regionCode;

    /**
     * 公司id
     */
    private String companyId;

    /**
     * 公司名
     */
    private String companyName;

    /**
     * 国名
     */
    private String countryName;

    /**
     * 省名
     */
    private String provinceName;

    /**
     * 城市名
     */
    private String cityName;

    /**
     * 区/县名
     */
    private String districtName;

    /**
     * 街道名
     */
    private String streetName;

    /**
     * 详细地址
     */
    private String detailAddress;

    /**
     * 邮政编码
     */
    private String postalCode;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 是否默认,0代表不是默认,1代表是默认地址
     */
    @EnumValue
    private AddressStates isDefault;

    /**
     * 手机号码
     */
    private String phone;

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
