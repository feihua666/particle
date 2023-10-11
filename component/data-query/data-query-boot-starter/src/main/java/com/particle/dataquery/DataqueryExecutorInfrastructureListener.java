package com.particle.dataquery;

import com.particle.dataquery.infrastructure.datasource.gateway.impl.DatasourceApiQueryGatewayHelper;
import com.particle.global.big.datasource.bigdatasource.api.BigDatasourceApi;
import com.particle.global.big.datasource.bigdatasource.executor.ExecutorInfrastructureListener;
import com.particle.global.big.datasource.bigdatasource.impl.http.httpclient.impl.BigDatasourceHttpJoddClientImpl;
import com.particle.global.openapi.collect.OpenapiCollectTool;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.data.OpenapiCollectProviderDTO;

import java.util.Map;

/**
 * <p>
 * 支持开放接口的基础调用数据收集
 * </p>
 *
 * @author yangwei
 * @since 2023-08-23 14:07
 */
public class DataqueryExecutorInfrastructureListener implements ExecutorInfrastructureListener {
	@Override
	public void beforeRequest(BigDatasourceApi bigDatasourceApi, Object command, String queryString) {

	}

	@Override
	public void afterResponse(BigDatasourceApi bigDatasourceApi, Object command, String queryString, boolean success, Object resultData, Object resultDataConverted,Boolean isCacheHit) {
		if (OpenapiCollectTool.isStartOpenApi()) {
			OpenapiContext context = OpenapiCollectTool.getContext();
			if (context != null) {
				Object http = bigDatasourceApi.apiContext().getData(BigDatasourceHttpJoddClientImpl.apiContext_root_http);
				// 这里只取http的
				if (http != null) {
					Map httpData = (Map) http;
					/**
					 * 参见实现类：{@link BigDatasourceHttpJoddClientImpl}
					 */
					String requestUrl = (String) httpData.get(BigDatasourceHttpJoddClientImpl.apiContext_requestUrl);
					//httpData.put("headers",headers);
					//httpData.put("command",command);
					//httpData.put("commandJsonStr",commandJsonStr);
					//httpData.put("queryString",queryString);
					//httpData.put("contentType",contentType);
					//httpData.put("httpMethod",httpRequest.method());
					Object responseResult = httpData.get(BigDatasourceHttpJoddClientImpl.apiContext_responseResult);
					Integer responseStatus = (Integer)httpData.get(BigDatasourceHttpJoddClientImpl.apiContext_responseStatus);
					String responseBusinessStatus = (String)httpData.get(BigDatasourceHttpJoddClientImpl.apiContext_responseBusinessStatus);
					Integer handleDuration = (Integer)httpData.get(BigDatasourceHttpJoddClientImpl.apiContext_handleDuration);

					/**
					 * 参见：{@link DatasourceApiQueryGatewayHelper#createDefaultBigDatasourceApiByDataQueryDatasourceApi(com.particle.dataquery.domain.datasource.DataQueryDatasourceApi, com.particle.dataquery.domain.datasource.enums.DataQueryDatasourceType)}
					 */
					Long dataQueryProviderId = (Long)bigDatasourceApi.apiContext().getData(DatasourceApiQueryGatewayHelper.apiContext_dataQueryProviderId);

					OpenapiCollectProviderDTO openapiCollectProviderDTO = OpenapiCollectProviderDTO.create(requestUrl,
							handleDuration,
							success,
							responseStatus,
							responseBusinessStatus, command, responseResult, dataQueryProviderId.toString(),isCacheHit);
					context.addProviderDTO(openapiCollectProviderDTO);
				}

			}
		}
	}

}
