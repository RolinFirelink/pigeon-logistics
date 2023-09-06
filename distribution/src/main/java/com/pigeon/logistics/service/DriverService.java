package com.pigeon.logistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pigeon.logistics.entity.DriverEntity;

import java.util.List;

/**
 * @author Rolin
 * 司机服务类
 */
public interface DriverService extends IService<DriverEntity> {

    /**
     * 新增一位司机
     *
     * @param entity 司机实体类对象
     * @return 是否添加成功
     */
    boolean addOne(DriverEntity entity);

    /**
     * 删除一位司机
     *
     * @param id 司机id
     * @return 是否删除成功
     */
    boolean deleteOne(Long id);

    /**
     * 根据地址id表批量删除司机
     *
     * @param ids 地址id表
     * @return 批量删除是否成功
     */
    boolean deleteByIds(List<Long> ids);

    /**
     * 更新司机信息
     *
     * @param entity 司机实体类对象
     * @return 是否更新成功
     */
    boolean updateOne(DriverEntity entity);
}
