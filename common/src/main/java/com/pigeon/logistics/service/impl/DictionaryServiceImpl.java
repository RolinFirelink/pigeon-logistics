package com.pigeon.logistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pigeon.logistics.entity.DictionaryEntity;
import com.pigeon.logistics.mapper.DictionaryMapper;
import com.pigeon.logistics.service.DictionaryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Rolin
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, DictionaryEntity> implements DictionaryService {

    @Override
    public boolean addHead(DictionaryEntity entity) {
        return save(entity);
    }

    @Override
    public boolean addNode(String name, String headName) {
        var queryWrapper = new LambdaQueryWrapper<DictionaryEntity>();
        queryWrapper.eq(DictionaryEntity::getName, headName);
        var entity = getOne(queryWrapper);
        if (entity == null) {
            return false;
        } else {
            return save(new DictionaryEntity(null, name, entity.getId(), -1));
        }
    }

    @Override
    public boolean updateOne(DictionaryEntity node) {
        return updateById(node);
    }

    @Override
    public boolean deleteHead(Long id) {
        var entity = getById(id);
        if (entity == null) {
            return false;
        }
        var queryWrapper = new LambdaQueryWrapper<DictionaryEntity>();
        queryWrapper.eq(DictionaryEntity::getParentId, entity.getId());
        return removeById(id) && remove(queryWrapper);
    }

    @Override
    public boolean deleteSon(Long id) {
        var entity = getById(id);
        if (entity == null || entity.getParentId() == -1) {
            return false;
        }
        return removeById(id);
    }
}
