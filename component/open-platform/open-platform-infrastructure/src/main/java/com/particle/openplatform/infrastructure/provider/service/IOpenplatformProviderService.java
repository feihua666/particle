package com.particle.openplatform.infrastructure.provider.service;

import com.particle.openplatform.infrastructure.provider.dos.OpenplatformProviderDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 开放平台开放接口供应商 服务类
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:15:58
 */
public interface IOpenplatformProviderService extends IBaseService<OpenplatformProviderDO> {

    /**
     * 根据编码查询
     * @param code
     * @return
     */
    default OpenplatformProviderDO getByCode(String code) {
        Assert.notEmpty(code,"code 不能为空");
        return getOne(Wrappers.<OpenplatformProviderDO>lambdaQuery().eq(OpenplatformProviderDO::getCode, code));
    }
    /**
     * 根据数据查询供应商id查询
     * @param dataQueryProviderId
     * @return
     */
    default OpenplatformProviderDO getBydataQueryProviderId(Long dataQueryProviderId) {
        Assert.notNull(dataQueryProviderId,"dataQueryProviderId 不能为空");
        return getOne(Wrappers.<OpenplatformProviderDO>lambdaQuery().eq(OpenplatformProviderDO::getDataQueryProviderId, dataQueryProviderId));
    }


    /**
     * 根据编码查询多个
     * @param codes
     * @return
     */
    default List<OpenplatformProviderDO> getByCodes(List<String> codes) {
        Assert.notEmpty(codes,"codes 不能为空");
        return list(Wrappers.<OpenplatformProviderDO>lambdaQuery().in(OpenplatformProviderDO::getCode, codes));
    }
            











}
