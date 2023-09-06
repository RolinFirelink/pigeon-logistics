package com.pigeon.logistics.distribution.domain;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Rolin
 * @since 2023-04-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LgCarDrivingPermit implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一主键
     */
    private Long id;

    /**
     * 司机id
     */
    private Long carId;

    /**
     * 号牌号码
     */
    private String carNumber;

    /**
     * 车辆类型
     */
    private String carType;

    /**
     * 车辆所有人
     */
    private String owner;

    /**
     * 地址
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
    private Long engineNumber;

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
     * 总质量(单位kg)
     */
    private Integer totalMass;

    /**
     * 整备质量(单位kg)
     */
    private Integer maintenanceQuality;

    /**
     * 核定载质量
     */
    private Integer approvedMass;

    /**
     * 外廓尺寸(按照 (长×宽×高)+厘米 的格式存入)	示例: 1000×1000×5000厘米 
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
     * 检验记录按照以下格式存入 检验有效期至xxxx年xx月+(车牌归属地+第一位车牌号)+(到期月份)	示例:检验有效期至2018年11月冀J(01) 
     */
    private String inspectionRecord;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 乐观锁并发控制
     */
    private Integer occVersion;

    /**
     * 逻辑删除
     */
    private Integer deleted;


}
