package com.particle.dataquery.app.openapi;

import cn.hutool.core.util.StrUtil;
import com.particle.dataquery.app.dataapi.executor.representation.DataQueryDataApiDataApiQueryCommandExecutor;
import com.particle.dataquery.client.dataapi.dto.command.representation.DataQueryDataApiQueryCommand;
import com.particle.global.openapi.api.portal.OpenapiExecuteProvider;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.endpoint.command.OpenapiCommand;
import com.particle.global.openapi.endpoint.command.OpenapiWarehouseCommand;

/**
 * <p>
 * 数据查询数据接口 对开放平台执行器供应商实现
 * </p>
 *
 * @author yangwei
 * @since 2025/5/8 11:01
 */
public class DataQueryDataApiOpenapiExecuteProvider implements OpenapiExecuteProvider {

    private String providerCode;
    private String apiUrl;
    private String apiCode;
    private String apiVersion;
    private Boolean isSupportWarehouse;

    private DataQueryDataApiDataApiQueryCommandExecutor dataQueryDataApiDataApiQueryCommandExecutor;


    @Override
    public boolean supportApi(String apiCode, String apiVersion) {
        boolean apiVersionSupport = false;
        if (StrUtil.isEmpty(this.apiVersion)) {
            if (StrUtil.isEmpty(apiVersion)) {
                apiVersionSupport = true;
            }else{
                apiVersionSupport = false;
            }
        } else if (StrUtil.equals(this.apiVersion, apiVersion)) {
            apiVersionSupport = true;
        }else {
            apiVersionSupport = false;
        }
        return StrUtil.equals(this.apiCode,apiCode) && apiVersionSupport;
    }

    @Override
    public boolean supportProvider(String providerCode) {
        return StrUtil.equals(this.providerCode,providerCode);
    }

    @Override
    public <T> T execute(OpenapiCommand openapiCommand, OpenapiContext openapiContext) {
        DataQueryDataApiQueryCommand dataQueryDataApiQueryCommand = new DataQueryDataApiQueryCommand();
        dataQueryDataApiQueryCommand.setUrl(apiUrl);
        dataQueryDataApiQueryCommand.setParam(openapiCommand);
        dataQueryDataApiQueryCommand.setQueryString(openapiCommand.getQueryString());
        return (T) dataQueryDataApiDataApiQueryCommandExecutor.dataApiQuery(dataQueryDataApiQueryCommand);
    }

    @Override
    public boolean supportWareHouse() {
        return isSupportWarehouse != null  && isSupportWarehouse;
    }

    @Override
    public void warehouse(OpenapiWarehouseCommand warehouseCommand, OpenapiCommand openapiCommand, OpenapiContext openapiContext) {
        DataQueryDataApiQueryCommand dataQueryDataApiQueryCommand = new DataQueryDataApiQueryCommand();
        dataQueryDataApiQueryCommand.setUrl(apiUrl);
        dataQueryDataApiQueryCommand.setParam(warehouseCommand);
        dataQueryDataApiQueryCommand.setQueryString(null);
        dataQueryDataApiDataApiQueryCommandExecutor.dataApiQuery(dataQueryDataApiQueryCommand);
    }

    public void setDataQueryDataApiDataApiQueryCommandExecutor(DataQueryDataApiDataApiQueryCommandExecutor dataQueryDataApiDataApiQueryCommandExecutor) {
        this.dataQueryDataApiDataApiQueryCommandExecutor = dataQueryDataApiDataApiQueryCommandExecutor;
    }

    public static DataQueryDataApiOpenapiExecuteProvider create(String providerCode,
                                                                String apiUrl,
                                                                String apiCode,
                                                                String apiVersion,
                                                                Boolean isSupportWarehouse) {
        DataQueryDataApiOpenapiExecuteProvider dataQueryDataApiOpenapiExecuteProvider = new DataQueryDataApiOpenapiExecuteProvider();
        dataQueryDataApiOpenapiExecuteProvider.providerCode = providerCode;
        dataQueryDataApiOpenapiExecuteProvider.apiUrl = apiUrl;
        dataQueryDataApiOpenapiExecuteProvider.apiCode = apiCode;
        dataQueryDataApiOpenapiExecuteProvider.apiVersion = apiVersion;
        dataQueryDataApiOpenapiExecuteProvider.isSupportWarehouse = isSupportWarehouse;
        return dataQueryDataApiOpenapiExecuteProvider;
    }
}
