package com.pigeon.logistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pigeon.logistics.entity.ReleaseApplicationFormEntity;
import com.pigeon.logistics.entity.ReleaseApplicationFormItemEntity;
import com.pigeon.logistics.entity.VO.SearchCondition;
import com.pigeon.logistics.mapper.ReleaseApplicationMapper;
import com.pigeon.logistics.service.ReleaseApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author dxy
 * @date 2023年03月21日
 */
@Service
@AllArgsConstructor
public class ReleaseApplicationServiceImpl extends ServiceImpl<ReleaseApplicationMapper, ReleaseApplicationFormEntity> implements ReleaseApplicationService {

    ReleaseApplicationItemServiceImpl releaseApplicationItemService;

    @Override
    @Transactional
    public Boolean addOne(ReleaseApplicationFormEntity form, List<ReleaseApplicationFormItemEntity> items) {
        // 数据安全检查
        form.init(null);
        form.setApplicationDate(LocalDateTime.now());
        boolean res = save(form);

        items.forEach(item -> {
            item.init(null);
            item.setReleaseApplicationFormId(form.getId());
        });
        releaseApplicationItemService.saveBatch(items);
        return res;
    }

    @Override
    @Transactional
    public boolean updateOne(ReleaseApplicationFormEntity form) {
        var queryWrapper = new LambdaQueryWrapper<ReleaseApplicationFormEntity>();
        queryWrapper.eq(ReleaseApplicationFormEntity::getId, form.getId());
        var originRecord = getOne(queryWrapper);
        form.init(originRecord.getId(), originRecord.getOccVersion());
        return updateById(form);
    }

    @Override
    @Transactional
    public boolean deleteOne(Long formId) {

        // 不需要删除明细，因为主表已经被删除了，子表就可以不管了。之后也方便恢复。
//        var itemQueryWrapper = new LambdaQueryWrapper<ReleaseApplicationFormItemEntity>();
//        itemQueryWrapper.eq(ReleaseApplicationFormItemEntity::getReleaseApplicationFormId, formId);
//        releaseApplicationItemService.remove(itemQueryWrapper);

        var orderQueryWrapper = new LambdaQueryWrapper<ReleaseApplicationFormEntity>();
        orderQueryWrapper.eq(ReleaseApplicationFormEntity::getId, formId);

        return remove(orderQueryWrapper);

    }

    @Override
    @Transactional
    public boolean deleteBatchByIds(List<Long> formIds) {
        return removeByIds(formIds);
    }

    @Override
    public Page<ReleaseApplicationFormEntity> page(Integer page, Integer size, SearchCondition searchCondition) {
        var pageConfig = new Page<ReleaseApplicationFormEntity>(page, size);
        Page<ReleaseApplicationFormEntity> entitiesPage;

        if (searchCondition == null) {
            entitiesPage = page(pageConfig);
        } else {
            var wrapper = new LambdaQueryWrapper<ReleaseApplicationFormEntity>();

            if (searchCondition.start() != null) {
                wrapper.and(item -> item.ge(ReleaseApplicationFormEntity::getApplicationDate, searchCondition.start()));
            }

            if (searchCondition.end() != null) {
                wrapper.and(item -> item.le(ReleaseApplicationFormEntity::getApplicationDate, searchCondition.end()));
            }
            if (StringUtils.hasText(searchCondition.string())) {
                var searchString = searchCondition.string();
                wrapper.and(item -> item.or().like(ReleaseApplicationFormEntity::getApplicationReason, searchString)
                        .or().like(ReleaseApplicationFormEntity::getDeptAdvice, searchString)
                        .or().like(ReleaseApplicationFormEntity::getDeptAdviceSign, searchString)
                        .or().like(ReleaseApplicationFormEntity::getApprovalBy, searchString)
                        .or().like(ReleaseApplicationFormEntity::getDrawnUpBy, searchString)
                        .or().like(ReleaseApplicationFormEntity::getReviewBy, searchString)
                );
            }

            entitiesPage = page(pageConfig, wrapper);

        }

        return entitiesPage;
    }

}
