package com.pigeon.logistics.entity.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

/**
 * @param id                 驾驶证id
 * @param vehicleId          车辆id
 * @param vehicleNumber      号牌号码
 * @param vehicleType        车辆类型
 * @param owner              车辆所属人
 * @param address            所属人地址
 * @param useNature          使用性质
 * @param brandModel         品牌型号
 * @param identificationCode 车辆识别代号
 * @param engineNumber       发动机号码
 * @param registerTime       注册日期
 * @param issueTime          发证日期
 * @param maxCapacity        核定最大载人数
 * @param totalMass          总质量
 * @param maintenanceQuality 整备质量
 * @param approvedMass       核定载质量
 * @param dimensionExterior  外廓尺寸
 * @param tractionMass       准牵引总质量
 * @param remarks            备注
 * @param inspectionRecord   检验记录
 * @param picture            行驶证照片
 */
@Schema(description = "前端驾驶证数据传入类")
public record VehicleDrivingPermitVo(

        @Schema(description = "驾驶证id")
        Long id,


        @Schema(description = "车辆id")
        Long vehicleId,

        @Schema(description = "号牌号码")
        String vehicleNumber,

        @Schema(description = "车辆类型")
        String vehicleType,

        @Schema(description = "车辆所属人")
        String owner,

        @Schema(description = "所属人地址")
        String address,

        @Schema(description = "使用性质")
        String useNature,

        @Schema(description = "品牌型号")
        String brandModel,

        @Schema(description = "车辆识别代号")
        Long identificationCode,

        @Schema(description = "发动机号码")
        String engineNumber,

        @Schema(description = "注册日期")
        @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss")
        LocalDateTime registerTime,

        @Schema(description = "发证日期")
        @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss")
        LocalDateTime issueTime,

        @Schema(description = "核定最大载人数")
        Integer maxCapacity,

        @Schema(description = "总质量")
        Integer totalMass,

        @Schema(description = "整备质量")
        Integer maintenanceQuality,

        @Schema(description = "核定载质量")
        Integer approvedMass,

        @Schema(description = "外廓尺寸 " +
                "(按照 (长×宽×高)+厘米 的格式存入) " +
                "示例: 1000×1000×5000厘米")
        String dimensionExterior,

        @Schema(description = "准牵引总质量")
        Integer tractionMass,

        @Schema(description = "备注")
        String remarks,

        @Schema(description = "检验记录 按照一下格式存入 " +
                "检验有效期至xxxx年xx月+(车牌归属地+第一位车牌号)+(到期月份) " +
                "示例: 检验有效期至2018年11月冀J(01)")
        String inspectionRecord,

        @Schema(description = "行驶证照片")
        String picture
) {
}
