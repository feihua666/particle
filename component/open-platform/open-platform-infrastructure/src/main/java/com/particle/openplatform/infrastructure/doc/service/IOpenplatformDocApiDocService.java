package com.particle.openplatform.infrastructure.doc.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocDO;
import com.particle.global.mybatis.plus.crud.IBaseService;

/**
 * <p>
 * 开放接口文档 服务类
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:37
 */
public interface IOpenplatformDocApiDocService extends IBaseService<OpenplatformDocApiDocDO> {


    /**
     * 根据接口id查询
     * @param openplatformDocApiId
     * @return
     */
    default OpenplatformDocApiDocDO getByOpenplatformDocApiId(Long openplatformDocApiId) {
        return getOne(Wrappers.<OpenplatformDocApiDocDO>lambdaQuery().eq(OpenplatformDocApiDocDO::getOpenplatformDocApiId,openplatformDocApiId));
    }

    /**
     * 根据接口 requestUrl 查询
     * @param requestUrl
     * @return
     */
    default OpenplatformDocApiDocDO getByRequestUrl(String requestUrl) {
        return getOne(Wrappers.<OpenplatformDocApiDocDO>lambdaQuery().eq(OpenplatformDocApiDocDO::getRequestUrl,requestUrl));
    }

}
