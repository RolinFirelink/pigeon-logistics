package com.pigeon.logistics.entity.VO;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.pigeon.logistics.enums.AddressStates;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @param companyName   公司名
 * @param countryName   国名
 * @param provinceName  省名
 * @param cityName      城市名
 * @param districtName  区/县名
 * @param streetName    街道名
 * @param detailAddress 详细地址
 * @param postalCode    邮政编码
 * @param phone         手机号
 * @param email         邮箱
 * @param companyId     公司id
 * @param id            地址id
 */
@Schema(description = "前端地址数据传入类")
public record AddressVO(
        @Schema(description = "公司名")
        String companyName,

        // TODO 校验
        // @Size(min = 12, max = 12)
        @Schema(description = "四级编码")
        String regionCode,

        @Schema(description = "国名")
        String countryName,

        @Schema(description = "省名")
        String provinceName,

        @Schema(description = "城市名")
        String cityName,

        @Schema(description = "区/县名")
        String districtName,

        @Schema(description = "街道名")
        String streetName,

        @Schema(description = "详细地址")
        String detailAddress,

        @Schema(description = "邮政编码")
        String postalCode,

        @Schema(description = "手机号")
        String phone,

        @Schema(description = "邮箱")
        String email,

        @Schema(description = "公司id")
        String companyId,

        @Schema(description = "地址id")
        @JsonSerialize(using = ToStringSerializer.class)
        Long id,

        @Schema(description = "是否默认")
        AddressStates isDefault
) {
}
