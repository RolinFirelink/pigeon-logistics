package com.pigeon.logistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pigeon.logistics.entity.VehicleDrivingPermitEntity;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Rolin
 * @since 2023-04-08
 */
public interface VehicleDrivingPermitService extends IService<VehicleDrivingPermitEntity> {

    /**
     * 添加行驶证
     *
     * @param entity 行驶证实体类
     * @return 是否添加成功
     */
    boolean addOne(VehicleDrivingPermitEntity entity);

    /**
     * 删除行驶证
     *
     * @param id 行驶证id
     * @return 是否删除成功
     */
    boolean deleteOne(Long id);

    /**
     * 更新行驶证
     *
     * @param entity 行驶证实体类
     * @return 是否更新成功
     */
    boolean updateOne(VehicleDrivingPermitEntity entity);
}
