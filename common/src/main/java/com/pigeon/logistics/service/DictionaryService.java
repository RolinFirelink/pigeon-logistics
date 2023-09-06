package com.pigeon.logistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pigeon.logistics.entity.DictionaryEntity;

/**
 * 字典服务类
 *
 * @author Rolin
 */
public interface DictionaryService extends IService<DictionaryEntity> {

    /**
     * 往字典序表中添加头节点
     *
     * @param head 字典序节点实体类
     * @return 是否添加成功
     */
    boolean addHead(DictionaryEntity head);

    /**
     * 往字典序表中添加节点
     *
     * @param name     节点名
     * @param headName 头节点名
     * @return 添加节点
     */
    boolean addNode(String name, String headName);

    /**
     * 更新字典序中的结点信息
     *
     * @param node 用于更新的结点实体类
     * @return 是否更新成功
     */
    boolean updateOne(DictionaryEntity node);

    /**
     * 删除头结点
     *
     * @param id 头结点id
     * @return 删除头结点是否成功
     */
    boolean deleteHead(Long id);

    /**
     * 删除子节点
     *
     * @param id 子节点id
     * @return 删除子节点是否成功
     */
    boolean deleteSon(Long id);
}
