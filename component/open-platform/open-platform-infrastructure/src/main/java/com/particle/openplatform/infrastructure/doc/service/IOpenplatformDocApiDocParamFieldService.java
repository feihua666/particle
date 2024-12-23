package com.particle.openplatform.infrastructure.doc.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocParamFieldDO;

import java.util.List;

/**
 * <p>
 * 开放接口文档参数字段 服务类
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:55
 */
public interface IOpenplatformDocApiDocParamFieldService extends IBaseService<OpenplatformDocApiDocParamFieldDO> {


    /**
     * 根据文档内容id查询
     * @param openplatformDocApiDocId
     * @return
     */
    default List<OpenplatformDocApiDocParamFieldDO> getByOpenplatformDocApiDocId(Long openplatformDocApiDocId) {
        return list(Wrappers.<OpenplatformDocApiDocParamFieldDO>lambdaQuery().eq(OpenplatformDocApiDocParamFieldDO::getOpenplatformDocApiDocId, openplatformDocApiDocId));
    }

























}
