package com.pigeon.logistics.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.pigeon.logistics.entity.LogisticsOrderEntity;

/**
 * 物流订单服务类
 *
 * @author dingxinyu
 */
public interface LogisticsOrderService extends IService<LogisticsOrderEntity> {
    /**
     * 创建一个物流订单
     *
     * @param order 物流订单实体类对象
     * @return 是否添加成功
     */
    Boolean addOne(LogisticsOrderEntity order);

    /**
     * 查询一个物流订单
     *
     * @param code 物流订单编号
     * @return 该物流订单实体类对象
     */
    LogisticsOrderEntity getOneByLgCode(String code);

    /**
     * TODO 编辑一个物流订单
     *
     * @return 该物流订单实体类对象
     */
    LogisticsOrderEntity updateOne();

    /**
     * 删除一个物流订单
     *
     * @param code 物流订单编号
     * @return 是否删除成功
     */
    Boolean deleteOne(String code);

}
