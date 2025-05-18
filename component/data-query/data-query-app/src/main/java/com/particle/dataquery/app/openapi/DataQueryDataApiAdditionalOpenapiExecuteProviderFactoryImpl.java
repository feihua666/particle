package com.particle.dataquery.app.openapi;

import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.dataquery.app.dataapi.executor.representation.DataQueryDataApiDataApiQueryCommandExecutor;
import com.particle.dataquery.domain.gateway.DataQueryOpenplatformGateway;
import com.particle.dataquery.domain.value.DataQueryOpenplatformProvider;
import com.particle.dataquery.infrastructure.dataapi.dos.DataQueryDataApiDO;
import com.particle.dataquery.infrastructure.dataapi.service.IDataQueryDataApiService;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.openapi.api.portal.AdditionalOpenapiExecuteProviderFactory;
import com.particle.global.openapi.api.portal.OpenapiExecuteProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 数据查询数据接口执行提供者工厂
 * </p>
 *
 * @author yangwei
 * @since 2025/5/8 11:51
 */
@Slf4j
@Component
public class DataQueryDataApiAdditionalOpenapiExecuteProviderFactoryImpl implements AdditionalOpenapiExecuteProviderFactory {

    private IDataQueryDataApiService iDataQueryDataApiService;
    private DataQueryOpenplatformGateway dataQueryOpenplatformGateway;
    private DataQueryDataApiDataApiQueryCommandExecutor dataQueryDataApiDataApiQueryCommandExecutor;


    private List<OpenapiExecuteProvider> openapiExecuteProviders;

    @Override
    public List<OpenapiExecuteProvider> additionalOpenapiExecuteProviders() {
        if (openapiExecuteProviders != null) {
            return openapiExecuteProviders;
        }
        refreshOpenapiExecuteProviderCache();
        return openapiExecuteProviders;
    }

    /**
     * 刷新执行器供应商缓存
     * @return
     */
    public SingleResponse<String> refreshOpenapiExecuteProviderCache() {
        List<OpenapiExecuteProvider> result = doAdditionalOpenapiExecuteProviders();
        openapiExecuteProviders = result;
        return SingleResponse.of(NetUtil.getLocalhostStr());
    }
    /**
     * 获取数据查询接口执行提供者
     * @return
     */
    private List<OpenapiExecuteProvider> doAdditionalOpenapiExecuteProviders() {
        List<DataQueryDataApiDO> list = iDataQueryDataApiService.list();
        list = list.stream().filter(dataQueryDataApiDO ->
                        dataQueryDataApiDO.getDataQueryProviderId() != null
                                && StrUtil.isNotEmpty(dataQueryDataApiDO.getApiIdentifier()))
                .collect(Collectors.toList());
        List<OpenapiExecuteProvider> result = new ArrayList<>(list.size());
        for (DataQueryDataApiDO dataQueryDataApiDO : list) {
            DataQueryOpenplatformProvider dataQueryOpenplatformProvider = dataQueryOpenplatformGateway.getOpenplatformProviderByDataQueryProviderId(dataQueryDataApiDO.getDataQueryProviderId());
            if (dataQueryOpenplatformProvider == null) {
                log.warn("ignored! dataQueryOpenplatformProvider is null by dataQueryProviderId={},the dataQueryDataApi url is {}",dataQueryDataApiDO.getDataQueryProviderId(),dataQueryDataApiDO.getUrl());
                continue;
            }
            DataQueryDataApiOpenapiExecuteProvider dataQueryDataApiOpenapiExecuteProvider = DataQueryDataApiOpenapiExecuteProvider.create(
                    dataQueryOpenplatformProvider.getCode(),
                    dataQueryDataApiDO.getUrl(),
                    dataQueryDataApiDO.getApiIdentifier(),
                    dataQueryDataApiDO.getApiVersion(),
                    dataQueryDataApiDO.getIsSupportWarehouse()
            );
            dataQueryDataApiOpenapiExecuteProvider.setDataQueryDataApiDataApiQueryCommandExecutor(dataQueryDataApiDataApiQueryCommandExecutor);
            result.add(dataQueryDataApiOpenapiExecuteProvider);
        }
        return result;
    }


    @Autowired
    public void setiDataQueryDataApiService(IDataQueryDataApiService iDataQueryDataApiService) {
        this.iDataQueryDataApiService = iDataQueryDataApiService;
    }
    @Autowired
    public void setDataQueryOpenplatformGateway(DataQueryOpenplatformGateway dataQueryOpenplatformGateway) {
        this.dataQueryOpenplatformGateway = dataQueryOpenplatformGateway;
    }
    @Autowired
    public void setDataQueryDataApiDataApiQueryCommandExecutor(DataQueryDataApiDataApiQueryCommandExecutor dataQueryDataApiDataApiQueryCommandExecutor) {
        this.dataQueryDataApiDataApiQueryCommandExecutor = dataQueryDataApiDataApiQueryCommandExecutor;
    }
}
