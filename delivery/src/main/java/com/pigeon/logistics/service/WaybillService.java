package com.pigeon.logistics.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pigeon.logistics.entity.VO.SearchCondition;
import com.pigeon.logistics.entity.WaybillEntity;

import java.util.List;

/**
 * 运单服务类
 *
 * @author dxy
 * @date 2023年3月21日
 */
public interface WaybillService extends IService<WaybillEntity> {

    /**
     * 查询一个运单
     *
     * @param code 运单编号
     * @return 该运单实体类对象
     */
    WaybillEntity getOneByWaybillCode(String code);

    /**
     * TODO 编辑一个运单
     *
     * @return 该运单单实体类对象
     */
    WaybillEntity updateOne();

    /**
     * 删除一个物流订单
     *
     * @param code 物流订单编号
     * @return 是否删除成功
     */
    boolean deleteOne(String code);

    /**
     * 创建一个运单
     *
     * @param waybill 运单实体类对象
     * @return 是否添加成功
     */
    boolean createOne(WaybillEntity waybill) throws Exception;

    /**
     * 支付运单
     *
     * @param id 运单主键
     * @return 是否完成支付
     */
    boolean pay(Long id) throws Exception;

    /**
     * 支付运单
     *
     * @param code 运单号
     * @return 是否完成支付
     */
    boolean pay(String code) throws Exception;

    /**
     * 发货
     *
     * @param id 运单主键
     * @return 是否完成发货
     */
    boolean deliver(Long id) throws Exception;

    /**
     * 发货
     *
     * @param code 运单号
     * @return 是否完成发货
     */
    boolean deliver(String code) throws Exception;

    /**
     * 签收
     *
     * @param id 运单主键
     * @return 是否完成签收
     */
    boolean reveive(Long id) throws Exception;

    /**
     * 签收
     *
     * @param code 运单号
     * @return 是否完成签收
     */
    boolean receive(String code) throws Exception;

    /**
     * 拒签
     *
     * @param id 运单主键
     * @return 是否完成拒签
     */
    boolean refulse(Long id) throws Exception;

    /**
     * 拒签
     *
     * @param code 运单号
     * @return 是否完成拒签
     */
    boolean refulse(String code) throws Exception;

    /**
     * 根据运单号列表批量删除
     *
     * @param codes 运单号列表
     * @return 批量删除是否成功
     */
    boolean deleteBatchByCodes(List<String> codes);

    /**
     * (根据搜索条件)分页查询
     *
     * @param page            页码
     * @param size            大小
     * @param searchCondition 查询条件
     * @return 分页结果
     */
    Page<WaybillEntity> page(Integer page, Integer size, SearchCondition searchCondition);
}
