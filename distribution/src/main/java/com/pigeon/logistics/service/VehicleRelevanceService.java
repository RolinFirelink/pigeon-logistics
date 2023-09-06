package com.pigeon.logistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pigeon.logistics.entity.VehicleDriverEntity;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Rolin
 * @since 2023-04-08
 */
public interface VehicleRelevanceService extends IService<VehicleDriverEntity> {

    /**
     * 添加一条司机车辆关联数据
     *
     * @param entity 关联数据实体类
     * @return 是否添加成功
     */
    boolean addOne(VehicleDriverEntity entity);

    /**
     * 删除一条司机车辆关联数据
     *
     * @param id 关联数据id
     * @return 是否删除成功
     */
    boolean deleteOne(Long id);

    /**
     * 更新一条司机车辆关联数据
     *
     * @param entity 关联数据实体类
     * @return 是否更新成功
     */
    boolean updateOne(VehicleDriverEntity entity);

}
