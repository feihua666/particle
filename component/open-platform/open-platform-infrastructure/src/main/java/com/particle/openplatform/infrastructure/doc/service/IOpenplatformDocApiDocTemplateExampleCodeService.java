package com.particle.openplatform.infrastructure.doc.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocTemplateExampleCodeDO;

import java.util.List;

/**
 * <p>
 * 开放接口文档模板示例代码 服务类
 * </p>
 *
 * @author yw
 * @since 2024-03-18 17:04:26
 */
public interface IOpenplatformDocApiDocTemplateExampleCodeService extends IBaseService<OpenplatformDocApiDocTemplateExampleCodeDO> {

    /**
     * 根据开放接口文档模板id查询
     * @param openplatformDocApiDocTemplateId
     * @return
     */
    default List<OpenplatformDocApiDocTemplateExampleCodeDO> getByOpenplatformDocApiDocTemplateId(Long openplatformDocApiDocTemplateId){
        return list(Wrappers.<OpenplatformDocApiDocTemplateExampleCodeDO>lambdaQuery().eq(OpenplatformDocApiDocTemplateExampleCodeDO::getOpenplatformDocApiDocTemplateId, openplatformDocApiDocTemplateId));
    }









}
