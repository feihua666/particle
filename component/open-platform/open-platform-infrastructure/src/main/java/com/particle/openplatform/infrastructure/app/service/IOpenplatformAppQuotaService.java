package com.particle.openplatform.infrastructure.app.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.openplatform.infrastructure.app.dos.OpenplatformAppQuotaDO;

import java.util.List;

/**
 * <p>
 * 开放平台应用额度 服务类
 * </p>
 *
 * @author yw
 * @since 2024-10-16 10:38:41
 */
public interface IOpenplatformAppQuotaService extends IBaseService<OpenplatformAppQuotaDO> {

    /**
     * 根据开放平台应用id查询
     * @param openplatformAppId
     * @return
     */
    default OpenplatformAppQuotaDO getByOpenplatformAppId(Long openplatformAppId) {
        Assert.notNull(openplatformAppId,"openplatformAppId 不能为空");
        return getOne(Wrappers.<OpenplatformAppQuotaDO>lambdaQuery().eq(OpenplatformAppQuotaDO::getOpenplatformAppId, openplatformAppId));
    }



    /**
     * 根据开放平台应用id查询多个
     * @param openplatformAppIds
     * @return
     */
    default List<OpenplatformAppQuotaDO> getByOpenplatformAppIds(List<Long> openplatformAppIds) {
        Assert.notEmpty(openplatformAppIds,"openplatformAppIds 不能为空");
        return list(Wrappers.<OpenplatformAppQuotaDO>lambdaQuery().in(OpenplatformAppQuotaDO::getOpenplatformAppId, openplatformAppIds));
    }













}
