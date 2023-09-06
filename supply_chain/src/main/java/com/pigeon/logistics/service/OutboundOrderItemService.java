package com.pigeon.logistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pigeon.logistics.entity.OutboundOrderItemEntity;

import java.util.List;

/**
 * @author dxy
 * @date 2023年02月03日
 */
public interface OutboundOrderItemService extends IService<OutboundOrderItemEntity> {
    /**
     * 根据出库单编号获取明细列表
     *
     * @param orderCode 出库单编号
     */
    List<OutboundOrderItemEntity> listByOrderCode(String orderCode);

    /**
     * 根据出库单编号添加一条明细
     *
     * @param orderCode 出库单编号
     * @param item      出库单明细
     * @return 是否添加成功
     */
    boolean addOneByOrderCode(String orderCode, OutboundOrderItemEntity item);

    /**
     * 根据出库单编号更新一条明细
     *
     * @param orderCode 出库单编号
     * @param item      出库单明细
     * @return 是否更新成功
     */
    boolean updateOneByOrderCode(String orderCode, OutboundOrderItemEntity item);

    /**
     * 根据明细编号列表删除明细
     *
     * @param orderCode 出库单编号
     * @param itemCodes 出库单明细编号列表
     * @return 是否删除成功
     */
    boolean deleteBatch(String orderCode, List<String> itemCodes);

}
