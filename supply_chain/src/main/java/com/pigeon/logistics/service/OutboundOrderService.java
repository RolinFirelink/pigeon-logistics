package com.pigeon.logistics.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pigeon.logistics.entity.OutboundOrderEntity;
import com.pigeon.logistics.entity.OutboundOrderItemEntity;
import com.pigeon.logistics.entity.VO.SearchCondition;

import java.util.List;

/**
 * 出库单服务类
 *
 * @author dxy
 */
public interface OutboundOrderService extends IService<OutboundOrderEntity> {
    /**
     * 创建一个出库单
     *
     * @param order 出库单实体类对象
     * @param items 出库单实体类对象的明细列表
     * @return 是否添加成功
     */
    boolean addOne(OutboundOrderEntity order, List<OutboundOrderItemEntity> items);

    /**
     * 查询一个出库单
     *
     * @param code 出库单编号
     * @return 该出库单实体类对象
     */
    OutboundOrderEntity getOneByCode(String code);

    /**
     * 更新一个出库单
     *
     * @param entity 出库单实体类对象
     * @return 是否更新成功
     */
    boolean updateByOrderCode(OutboundOrderEntity entity);

    /**
     * 删除一个出库单
     *
     * @param code 出库单编号
     * @return 是否删除成功
     */
    boolean deleteOne(String code);

    /**
     * (根据搜索条件)分页查询
     *
     * @param page            页码
     * @param size            大小
     * @param searchCondition 查询条件
     * @return 分页结果
     */
    Page<OutboundOrderEntity> page(Integer page, Integer size, SearchCondition searchCondition);

    /**
     * 根据出库表编号列表批量删除
     *
     * @param orderCodes 出库表编号列表
     * @return 批量删除是否成功
     */
    boolean deleteBatchByIds(List<String> orderCodes);
}
