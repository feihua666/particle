package com.particle.cms.infrastructure.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.cms.infrastructure.dos.CmsContentDO;
import com.particle.cms.infrastructure.dos.CmsContentDO;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.util.List;

/**
 * <p>
 * 内容 服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:16
 */
public interface ICmsContentService extends IBaseService<CmsContentDO> {


    /**
     * 根据内容 id 和 isPublic 查询
     * @param id
     * @return
     */
    default CmsContentDO getByIdAndIsPublic(Long id, Boolean isPublic) {
        Assert.notNull(id,"id 不能为空");
        return getOne(Wrappers.<CmsContentDO>lambdaQuery().eq(CmsContentDO::getId, id).eq(isPublic != null,CmsContentDO::getIsPublic, isPublic));
    }

    /**
     * 根据内容 ids 和 isPublic 查询
     * @param ids
     * @return
     */
    default List<CmsContentDO> listByIdsAndIsPublic(List<Long> ids, Boolean isPublic) {
        Assert.notEmpty(ids,"ids 不能为空");
        return list(Wrappers.<CmsContentDO>lambdaQuery().in(CmsContentDO::getId, ids).eq(isPublic != null,CmsContentDO::getIsPublic, isPublic));
    }

}
