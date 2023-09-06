package distribution.domain;

import java.math.BigDecimal;
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
public class LgCar implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一主键
     */
    private Long id;

    /**
     * 车型代码
     */
    private Long modelCode;

    /**
     * 保险分类(不同的数字表示不同的保险)
     */
    private Integer insuranceType;

    /**
     * 车型名称
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
     * 车辆种类(不同的数字表示不同的种类)
     */
    private Integer carType;

    /**
     * 车辆类型(不同的数字表示不同的类型)
     */
    private Integer vehicleType;

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
     * 车型风险分类(不同的数字表示不同的风险类型)
     */
    private Integer riskType;

    /**
     * 汽车品牌(不同的数字表示不同的品牌)
     */
    private Integer brand;

    /**
     * 车系(不同的数字表示不同的车系)
     */
    private Integer carSeries;

    /**
     * 行驶证
     */
    private String drivingPermit;

    /**
     * 是否年检(0代表已经年检,1代表还未年检)
     */
    private Integer annualAudit;

    /**
     * 司机车辆中间表id
     */
    private Long relevance;

    /**
     * 车辆行驶证id
     */
    private Long drivingPermitId;

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
