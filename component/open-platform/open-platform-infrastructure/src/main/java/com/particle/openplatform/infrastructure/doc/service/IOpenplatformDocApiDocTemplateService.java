package com.particle.openplatform.infrastructure.doc.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocTemplateDO;

import java.util.List;

/**
 * <p>
 * 开放接口文档模板 服务类
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:48:39
 */
public interface IOpenplatformDocApiDocTemplateService extends IBaseService<OpenplatformDocApiDocTemplateDO> {

    /**
     * 根据模板名称查询
     * @param name
     * @return
     */
    default OpenplatformDocApiDocTemplateDO getByName(String name) {
        Assert.notNull(name,"name 不能为空");
        return getOne(Wrappers.<OpenplatformDocApiDocTemplateDO>lambdaQuery().eq(OpenplatformDocApiDocTemplateDO::getName, name));
    }



    /**
     * 根据模板名称查询多个
     * @param names
     * @return
     */
    default List<OpenplatformDocApiDocTemplateDO> getByNames(List<String> names) {
        Assert.notEmpty(names,"names 不能为空");
        return list(Wrappers.<OpenplatformDocApiDocTemplateDO>lambdaQuery().in(OpenplatformDocApiDocTemplateDO::getName, names));
    }
















}
