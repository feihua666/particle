package com.particle.cms.infrastructure.service;

import com.particle.cms.infrastructure.dos.CmsTemplateDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 模板 服务类
 * </p>
 *
 * @author yw
 * @since 2026-01-21 21:03:36
 */
public interface ICmsTemplateService extends IBaseService<CmsTemplateDO> {

    /**
     * 根据唯一键查询
     * @param templateKey
     * @return
     */
    default CmsTemplateDO getByTemplateKey(String templateKey) {
        Assert.notNull(templateKey,"templateKey 不能为空");
        return getOne(Wrappers.<CmsTemplateDO>lambdaQuery().eq(CmsTemplateDO::getTemplateKey, templateKey));
    }



    /**
     * 根据唯一键查询多个
     * @param templateKeys
     * @return
     */
    default List<CmsTemplateDO> getByTemplateKeys(List<String> templateKeys) {
        Assert.notEmpty(templateKeys,"templateKeys 不能为空");
        return list(Wrappers.<CmsTemplateDO>lambdaQuery().in(CmsTemplateDO::getTemplateKey, templateKeys));
    }
            
























}
