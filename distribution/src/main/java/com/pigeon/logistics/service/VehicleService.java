package com.pigeon.logistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pigeon.logistics.entity.VehicleEntity;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Rolin
 * @since 2023-04-08
 */
public interface VehicleService extends IService<VehicleEntity> {

    /**
     * 添加一个车辆
     *
     * @param entity 车辆信息实体类
     * @return 添加是否成功
     */
    boolean addOne(VehicleEntity entity);

    /**
     * 删除一个车辆
     *
     * @param id 车辆id
     * @return 删除是否成功
     */
    boolean deleteOne(Long id);

    /**
     * 批量删除车辆
     *
     * @param ids 车辆id列表
     * @return 批量删除是否成功
     */
    boolean deleteByIds(List<Long> ids);

    /**
     * 更新车辆信息
     *
     * @param entity 车辆实体类
     * @return 是否更新成功
     */
    boolean updateOne(VehicleEntity entity);
}
