package com.pigeon.logistics.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pigeon.logistics.entity.ReleaseApplicationFormEntity;
import com.pigeon.logistics.entity.ReleaseApplicationFormItemEntity;
import com.pigeon.logistics.entity.VO.SearchCondition;

import java.util.List;

/**
 * 旅行放行条服务类
 *
 * @author dxy
 * @date 2022年2月6日
 */
public interface ReleaseApplicationService extends IService<ReleaseApplicationFormEntity> {
    /**
     * 创建一个旅行放行表
     *
     * @param form  旅行放行表实体类对象
     * @param items 放行条实体类对象的明细列表
     * @return 是否添加成功
     */
    Boolean addOne(ReleaseApplicationFormEntity form, List<ReleaseApplicationFormItemEntity> items);

    /**
     * 更新一个旅行放行表
     *
     * @param form 旅行放行表实体类对象
     * @return 该旅行放行表实体类对象
     */
    boolean updateOne(ReleaseApplicationFormEntity form);

    /**
     * 删除一个放行条
     *
     * @param formId 放行条id
     * @return 是否删除成功
     */
    boolean deleteOne(Long formId);


    /**
     * 根据放行表编号列表批量删除
     *
     * @param formIds 放行表ids
     * @return 批量删除是否成功
     */
    boolean deleteBatchByIds(List<Long> formIds);

    /**
     * (根据搜索条件)分页查询
     *
     * @param page            页码
     * @param size            大小
     * @param searchCondition 查询条件
     * @return 分页结果
     */
    Page<ReleaseApplicationFormEntity> page(Integer page, Integer size, SearchCondition searchCondition);

}
