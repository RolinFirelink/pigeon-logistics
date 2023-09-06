package com.pigeon.logistics.entity.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.pigeon.logistics.enums.DrivingLicenseTypes;
import com.pigeon.logistics.enums.Genders;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Min;
import java.time.LocalDateTime;

/**
 * @param name        司机名
 * @param phone       司机手机号
 * @param email       司机电子邮箱
 * @param age         司机年龄
 * @param entryTime   入职时间
 * @param licenseType 驾驶证类型
 * @param id          司机id
 * @param drivingAge  驾龄
 * @param address     居住地址
 * @param idNumber    身份证号
 * @param gender      性别
 * @param headPicture 司机头像
 */
@Schema(description = "前端司机数据传入类")
public record DriverVo(

        @Schema(description = "司机名")
        String name,

        @Schema(description = "司机手机号")
        String phone,

        @Schema(description = "司机电子邮箱")
        String email,

        @Schema(description = "司机年龄")
        @Min(value = 0, message = "年龄必须大于0")
        Integer age,

        @Schema(description = "入职时间", accessMode = Schema.AccessMode.READ_ONLY, example = "2023年1月12日 15:36:07")
        @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss")
        LocalDateTime entryTime,

        @Schema(description = "驾驶证类型")
        DrivingLicenseTypes licenseType,

        @Schema(description = "司机id")
        @JsonSerialize(using = ToStringSerializer.class)
        Long id,

        @Schema(description = "驾龄")
        Integer drivingAge,

        @Schema(description = "居住地址")
        String address,


        @Schema(description = "身份证号")
        // FIXME 该注解不能按预期进行校验 @Length(max = 18,min = 18,message = "身份证号必须是18位")
        String idNumber,

        @Schema(description = "性别")
        Genders gender,

        @Schema(description = "司机头像")
        String headPicture
) {
}
