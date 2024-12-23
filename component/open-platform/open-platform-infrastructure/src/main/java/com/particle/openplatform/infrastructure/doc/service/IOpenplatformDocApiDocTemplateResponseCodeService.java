package com.particle.openplatform.infrastructure.doc.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocTemplateResponseCodeDO;

import java.util.List;

/**
 * <p>
 * 开放接口文档模板响应码 服务类
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:49:10
 */
public interface IOpenplatformDocApiDocTemplateResponseCodeService extends IBaseService<OpenplatformDocApiDocTemplateResponseCodeDO> {




    /**
     * 根据开放接口文档模板id查询
     * @param openplatformDocApiDocTemplateId
     * @return
     */
    default List<OpenplatformDocApiDocTemplateResponseCodeDO> getByOpenplatformDocApiDocTemplateId(Long openplatformDocApiDocTemplateId){
        return list(Wrappers.<OpenplatformDocApiDocTemplateResponseCodeDO>lambdaQuery().eq(OpenplatformDocApiDocTemplateResponseCodeDO::getOpenplatformDocApiDocTemplateId, openplatformDocApiDocTemplateId));
    }











}
