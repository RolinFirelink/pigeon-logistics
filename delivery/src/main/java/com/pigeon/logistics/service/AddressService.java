package com.pigeon.logistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pigeon.logistics.entity.AddressEntity;

import java.util.List;

/**
 * @author Rolin
 * 地址服务类
 */
public interface AddressService extends IService<AddressEntity> {
    /**
     * 创建一个企业地址
     *
     * @param address 地址实体类对象
     * @return 是否添加成功
     */
    boolean addOne(AddressEntity address);

    /**
     * 根据地址id表批量删除
     *
     * @param ids 地址id表
     * @return 批量删除是否成功
     */
    boolean deleteByIds(List<Long> ids);

    /**
     * 删除一个地址
     *
     * @param id 地址id
     * @return 是否删除成功
     */
    boolean deleteOne(Long id);


    /**
     * 更新一个地址
     *
     * @param address 地址实体类对象
     * @return 是否更新成功
     */
    boolean updateOne(AddressEntity address);

    /**
     * 设置一个地址为默认地址
     *
     * @param id 地址id
     * @return 是否设置成功
     */
    boolean setDefault(Long id);
}
