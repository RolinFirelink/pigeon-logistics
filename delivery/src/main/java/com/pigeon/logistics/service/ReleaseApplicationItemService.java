package com.pigeon.logistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pigeon.logistics.entity.ReleaseApplicationFormItemEntity;

import java.util.List;

/**
 * @author dxy
 * @date 2023年03月16日
 */
public interface ReleaseApplicationItemService extends IService<ReleaseApplicationFormItemEntity> {

    /**
     * 在放行条中添加一个明细
     *
     * @param formId 放行条id
     * @param item   明细实体类对象
     * @return 是否添加成功
     */
    boolean addOneById(Long formId, ReleaseApplicationFormItemEntity item);

    /**
     * 根据放行条id和明细对象更新一条明细
     *
     * @param formId 放行条id
     * @param item   明细实体类对象
     * @return 是否更新成功
     */
    boolean updateOneById(Long formId, ReleaseApplicationFormItemEntity item);

    /**
     * 根据明细列表批量删除
     *
     * @param formId  放行条id
     * @param itemIds 明细id列表
     * @return 是否批量删除成功
     */
    boolean deleteBatch(Long formId, List<Long> itemIds);

    /**
     * 根据放行条id获取明细列表
     *
     * @param formId 放行条id
     * @return 该放行条明细列表
     */
    List<ReleaseApplicationFormItemEntity> listByFormId(Long formId);
}
