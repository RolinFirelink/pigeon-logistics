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
public class LgCarRelevance implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一主键
     */
    private Long id;

    /**
     * 司机id
     */
    private Long driverId;

    /**
     * 车辆id
     */
    private Long carId;

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
