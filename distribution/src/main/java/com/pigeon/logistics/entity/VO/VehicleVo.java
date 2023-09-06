package com.pigeon.logistics.entity.VO;

import com.pigeon.logistics.enums.AnnualInspection;
import com.pigeon.logistics.enums.InsurancePay;
import com.pigeon.logistics.enums.Scrap;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

/**
 * @param id               车辆id
 * @param modelCode        车型代码
 * @param insuranceType    车辆购买过的保险
 * @param manufacturerName 生产厂商名称
 * @param price            购买价格
 * @param vehicleKind      车辆种类
 * @param vehicleType      车辆类型
 * @param seatNumber       核定座位数
 * @param maxWeight        最大载重量
 * @param listYear         上市年份
 * @param riskType         车辆风险类型
 * @param brand            车辆品牌
 * @param vehicleSeries    车系
 * @param annualAudit      是否年检
 * @param insurance        车辆是否交车强险
 * @param scrap            车辆是否报废
 */
@Schema(description = "前端车辆数据传入类")
public record VehicleVo(

        @Schema(description = "车辆id")
        Long id,

        @Schema(description = "车型代码")
        String modelCode,

        @Schema(description = "车辆购买过的保险(存储格式中各种保险用逗号隔开)")
        String insuranceType,

        @Schema(description = "生产厂商名称")
        String manufacturerName,

        @Schema(description = "购买价格")
        BigDecimal price,

        @Schema(description = "车辆种类")
        String vehicleKind,

        @Schema(description = "车辆类型")
        String vehicleType,

        @Schema(description = "核定座位数")
        Integer seatNumber,

        @Schema(description = "最大载重量(kg)")
        Integer maxWeight,

        @Schema(description = "上市年份(数字代表上市时间为几几年)")
        Integer listYear,

        @Schema(description = "车辆风险类型")
        String riskType,

        @Schema(description = "车辆品牌")
        String brand,

        @Schema(description = "车系")
        String vehicleSeries,

        @Schema(description = "是否年检(0代表已经年检,1代表还未年检)")
        AnnualInspection annualAudit,

        @Schema(description = "车辆是否交车强险(0代表已经交过,1代表没交)")
        InsurancePay insurance,

        @Schema(description = "车辆是否报废(0代表没有报废,1代表已经报废)")
        Scrap scrap
) {
}
