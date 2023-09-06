package com.pigeon.logistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pigeon.logistics.entity.StorageReceiptEntity;

/**
 * 入库单服务类
 *
 * @author dxy cza
 */
public interface StorageReceiptService extends IService<StorageReceiptEntity> {
    /**
     * 创建一个入库单
     *
     * @param order 入库单实体类对象
     * @return 是否添加成功
     */
    Boolean addOne(StorageReceiptEntity order);

    /**
     * 查询一个入库单
     *
     * @param code 出库单编号
     * @return 该入库单实体类对象
     */
    StorageReceiptEntity getOneByCode(String code);

    /**
     * TODO 编辑一个入库单
     *
     * @return 该入库单实体类对象
     */
    StorageReceiptEntity updateOne();

    /**
     * 删除一个入库单
     *
     * @param code 入库单编号
     * @return 是否删除成功
     */
    Boolean deleteOne(String code);

}
