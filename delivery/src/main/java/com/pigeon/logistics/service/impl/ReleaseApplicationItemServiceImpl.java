package com.pigeon.logistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pigeon.logistics.entity.ReleaseApplicationFormItemEntity;
import com.pigeon.logistics.mapper.ReleaseApplicationItemMapper;
import com.pigeon.logistics.service.ReleaseApplicationItemService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dxy
 * @date 2023年03月16日
 */
@Service
public class ReleaseApplicationItemServiceImpl
        extends ServiceImpl<ReleaseApplicationItemMapper, ReleaseApplicationFormItemEntity>
        implements ReleaseApplicationItemService {

    @Override
    public boolean addOneById(Long formId, ReleaseApplicationFormItemEntity item) {
        item.init();
        item.setReleaseApplicationFormId(formId);
        return save(item);
    }

    @Override
    public boolean updateOneById(Long formId, ReleaseApplicationFormItemEntity item) {
        var queryWrapper = new LambdaQueryWrapper<ReleaseApplicationFormItemEntity>();
        queryWrapper.eq(ReleaseApplicationFormItemEntity::getReleaseApplicationFormId, formId);
        queryWrapper.eq(ReleaseApplicationFormItemEntity::getId, item.getId());
        var originRecord = getOne(queryWrapper);
        item.init(originRecord.getId(), originRecord.getOccVersion());
        return updateById(item);
    }

    @Override
    public boolean deleteBatch(Long formId, List<Long> itemIds) {
        var queryWrapper = new LambdaQueryWrapper<ReleaseApplicationFormItemEntity>();
        queryWrapper.eq(ReleaseApplicationFormItemEntity::getReleaseApplicationFormId, formId);
        queryWrapper.in(ReleaseApplicationFormItemEntity::getId, itemIds);
        return remove(queryWrapper);
    }

    @Override
    public List<ReleaseApplicationFormItemEntity> listByFormId(Long formId) {
        var queryWrapper = new LambdaQueryWrapper<ReleaseApplicationFormItemEntity>();
        queryWrapper.eq(ReleaseApplicationFormItemEntity::getReleaseApplicationFormId, formId);
        return list(queryWrapper);
    }
}
