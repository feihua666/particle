package com.particle.openplatform.infrastructure.doc.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocTemplateParamFieldDO;

import java.util.List;

/**
 * <p>
 * 开放接口文档模板参数字段 服务类
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:48:56
 */
public interface IOpenplatformDocApiDocTemplateParamFieldService extends IBaseService<OpenplatformDocApiDocTemplateParamFieldDO> {


    /**
     * 根据文档模板id获取参数字段
     * @param openplatformDocApiDocTemplateId
     * @return
     */
    default List<OpenplatformDocApiDocTemplateParamFieldDO> getByOpenplatformDocApiDocTemplateId(Long openplatformDocApiDocTemplateId) {
        return list(Wrappers.<OpenplatformDocApiDocTemplateParamFieldDO>lambdaQuery().eq(OpenplatformDocApiDocTemplateParamFieldDO::getOpenplatformDocApiDocTemplateId, openplatformDocApiDocTemplateId));
    }

    /**
     * 根据文档模板id和参数类别字典id删除
     * @param openplatformDocApiDocTemplateId
     * @param categoryDictId
     * @return
     */
    default boolean removeByOpenplatformDocApiDocTemplateIdAndCategoryDictId(Long openplatformDocApiDocTemplateId,Long categoryDictId) {
        Assert.notNull(openplatformDocApiDocTemplateId,"openplatformDocApiDocTemplateId 不能为空");
        Assert.notNull(categoryDictId,"categoryDictId 不能为空");
        return remove(Wrappers.<OpenplatformDocApiDocTemplateParamFieldDO>lambdaQuery()
                .eq(OpenplatformDocApiDocTemplateParamFieldDO::getOpenplatformDocApiDocTemplateId, openplatformDocApiDocTemplateId)
                .eq(OpenplatformDocApiDocTemplateParamFieldDO::getCategoryDictId, categoryDictId)
        );
    }






















}
