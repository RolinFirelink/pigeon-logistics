package com.pigeon.logistics.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@TableName("lg_vehicle_driving_permit")
public class VehicleDrivingPermitEntity {

    /**
     * 唯一主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 车辆id
     */
    private Long vehicleId;

    /**
     * 号牌号码
     */
    private String vehicleNumber;

    /**
     * 车辆类型
     */
    private String vehicleType;

    /**
     * 车辆所属人
     */
    private String owner;

    /**
     * 所属人地址
     */
    private String address;

    /**
     * 使用性质
     */
    private String useNature;

    /**
     * 品牌型号
     */
    private String brandModel;

    /**
     * 车辆识别代号
     */
    private Long identificationCode;

    /**
     * 发动机号码
     */
    private String engineNumber;

    /**
     * 注册日期
     */
    private LocalDateTime registerTime;

    /**
     * 发证日期
     */
    private LocalDateTime issueTime;

    /**
     * 核定最大载人数
     */
    private Integer maxCapacity;

    /**
     * 总质量
     */
    private Integer totalMass;

    /**
     * 整备质量
     */
    private Integer maintenanceQuality;

    /**
     * 核定载质量
     */
    private Integer approvedMass;

    /**
     * 外廓尺寸(按照 (长×宽×高)+厘米 的格式存入)
     * 示例: 1000×1000×5000厘米
     */
    private String dimensionExterior;

    /**
     * 准牵引总质量
     */
    private Integer tractionMass;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 检验记录,按照以下格式存入
     * 检验有效期至xxxx年xx月+(车牌归属地+第一位车牌号)+(到期月份)
     * 示例: 检验有效期至2018年11月冀J(01)
     */
    private String inspectionRecord;

    /**
     * 行驶证照片
     */
    private String picture;

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
