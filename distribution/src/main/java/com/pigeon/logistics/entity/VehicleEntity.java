package com.pigeon.logistics.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.pigeon.logistics.enums.AnnualInspection;
import com.pigeon.logistics.enums.InsurancePay;
import com.pigeon.logistics.enums.Scrap;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author Rolin
 * @since 2023-04-08
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("lg_vehicle")
public class VehicleEntity {

    /**
     * 唯一主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 车型代码(一般是17位)
     */
    private String modelCode;

    /**
     * 保险分类 	存储格式中各种保险用逗号隔开
     * 示例: 保险1,保险2,保险3
     */
    private String insuranceType;

    /**
     * 车型名称
     * 基本形式为（车辆品牌+车辆型号+车辆种类）
     * 示例:红旗 CA7220AE 轿车
     */
    private String modelName;

    /**
     * 生产厂商名称
     */
    private String manufacturerName;

    /**
     * 购买价格
     */
    private BigDecimal price;

    /**
     * 车辆种类
     */
    private String vehicleKind;

    /**
     * 车辆类型
     */
    private String vehicleType;

    /**
     * 核定座位数
     */
    private Integer seatNumber;

    /**
     * 最大载重量(单位kg)
     */
    private Integer maxWeight;

    /**
     * 上市年份(数字代表上市时间为几几年)
     */
    private Integer listYear;

    /**
     * 车型风险分类
     */
    private String riskType;

    /**
     * 车辆品牌
     */
    private String brand;

    /**
     * 车系
     */
    private String vehicleSeries;

    /**
     * 汽车行驶证表id (-1代表没有绑定行驶证)
     */
    private Long drivingPermitId;

    /**
     * 是否年检(0代表已经年检,1代表还未年检)
     */
    @EnumValue
    private AnnualInspection annualAudit;

    /**
     * 车辆是否交车强险(0代表已经交过,1代表没交)
     */
    @EnumValue
    private InsurancePay insurance;

    /**
     * 车辆是否报废(0代表没有报废,1代表已经报废)
     */
    @EnumValue
    private Scrap scrap;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
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
     * 逻辑删除
     */
    @TableLogic
    private Integer deleted;
}
