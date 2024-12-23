package com.particle.openplatform.infrastructure.provider.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.openplatform.infrastructure.provider.dos.OpenplatformProviderApiDO;

import java.util.List;

/**
 * <p>
 * 开放平台供应商接口 服务类
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:53:52
 */
public interface IOpenplatformProviderApiService extends IBaseService<OpenplatformProviderApiDO> {

    /**
     * 根据编码查询
     * @param code
     * @return
     */
    default OpenplatformProviderApiDO getByCode(String code) {
        Assert.notNull(code,"code 不能为空");
        return getOne(Wrappers.<OpenplatformProviderApiDO>lambdaQuery().eq(OpenplatformProviderApiDO::getCode, code));
    }



    /**
     * 根据编码查询多个
     * @param codes
     * @return
     */
    default List<OpenplatformProviderApiDO> getByCodes(List<String> codes) {
        Assert.notEmpty(codes,"codes 不能为空");
        return list(Wrappers.<OpenplatformProviderApiDO>lambdaQuery().in(OpenplatformProviderApiDO::getCode, codes));
    }



    /**
     * 根据数据查询数据源接口id查询
     * @param dataQueryDatasourceApiId
     * @return
     */
    default OpenplatformProviderApiDO getByDataQueryDatasourceApiId(Long dataQueryDatasourceApiId) {
        Assert.notNull(dataQueryDatasourceApiId,"dataQueryDatasourceApiId 不能为空");
        return getOne(Wrappers.<OpenplatformProviderApiDO>lambdaQuery().eq(OpenplatformProviderApiDO::getDataQueryDatasourceApiId, dataQueryDatasourceApiId));
    }



    /**
     * 根据数据查询数据源接口id查询多个
     * @param dataQueryDatasourceApiIds
     * @return
     */
    default List<OpenplatformProviderApiDO> getByDataQueryDatasourceApiIds(List<Long> dataQueryDatasourceApiIds) {
        Assert.notEmpty(dataQueryDatasourceApiIds,"dataQueryDatasourceApiIds 不能为空");
        return list(Wrappers.<OpenplatformProviderApiDO>lambdaQuery().in(OpenplatformProviderApiDO::getDataQueryDatasourceApiId, dataQueryDatasourceApiIds));
    }
















}
