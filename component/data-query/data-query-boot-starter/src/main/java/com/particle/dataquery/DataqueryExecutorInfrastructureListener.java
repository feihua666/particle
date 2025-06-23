package com.particle.dataquery;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.dataquery.domain.datasource.DataQueryDatasourceApi;
import com.particle.dataquery.domain.datasource.enums.DataQueryDatasourceType;
import com.particle.dataquery.infrastructure.datasource.gateway.impl.DatasourceApiQueryGatewayHelper;
import com.particle.global.big.datasource.bigdatasource.api.BigDatasourceApi;
import com.particle.global.big.datasource.bigdatasource.api.config.IBigDatasourceApiConfig;
import com.particle.global.big.datasource.bigdatasource.executor.ExecutorInfrastructureListener;
import com.particle.global.big.datasource.bigdatasource.impl.http.api.config.HttpBigDatasourceApiConfig;
import com.particle.global.big.datasource.bigdatasource.impl.http.executor.HttpBigDatasourceApiExecutor;
import com.particle.global.big.datasource.bigdatasource.impl.http.httpclient.impl.BigDatasourceHttpJoddClientImpl;
import com.particle.global.openapi.api.GlobalOpenapiCollectPersistentService;
import com.particle.global.openapi.collect.OpenapiCollectTool;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.data.OpenapiCollectProviderDTO;
import com.particle.global.security.security.login.LoginTool;
import com.particle.global.security.security.login.LoginUserTool;
import com.particle.global.tool.log.TraceTool;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
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

	@Autowired(required = false)
	private GlobalOpenapiCollectPersistentService globalOpenapiCollectPersistentService;

	@Override
	public void beforeRequest(BigDatasourceApi bigDatasourceApi, Object command, String queryString) {

	}

	@Override
	public void afterResponse(BigDatasourceApi bigDatasourceApi, Object command, String queryString, boolean success,String responseBusinessStatus, Object resultData, Object resultDataConverted,Boolean isCacheHit) {
		if (globalOpenapiCollectPersistentService != null) {

			OpenapiContext context = new OpenapiContext();
			context.setTraceId(TraceTool.getTraceId());
			OpenapiContext openapiContext = OpenapiCollectTool.getContext();
			Long userId = LoginUserTool.getLoginUserId();
			Long customerId = null;
			if (openapiContext != null) {
				context.setId(openapiContext.getId());
				customerId = context.getOpenapiClient().getOwnerCustomerId();
				userId = context.getOpenapiClient().getOwnerUserId();
			}


			OpenapiCollectProviderDTO openapiCollectProviderDTO = http(bigDatasourceApi,
					command, queryString, success, responseBusinessStatus, isCacheHit,userId,customerId);
            if (openapiCollectProviderDTO != null) {
				context.addProviderDTO(openapiCollectProviderDTO);
            }else{
				openapiCollectProviderDTO = other(bigDatasourceApi,
						command, queryString, success, responseBusinessStatus, resultData, resultDataConverted, isCacheHit,userId,customerId);
				if (openapiCollectProviderDTO != null) {
					context.addProviderDTO(openapiCollectProviderDTO);
				}
			}
			globalOpenapiCollectPersistentService.saveProvider(context);
		}
	}

	/**
	 * 其它情况
	 * @param bigDatasourceApi
	 * @param command
	 * @param queryString
	 * @param success
	 * @param responseBusinessStatus
	 * @param resultData
	 * @param resultDataConverted
	 * @param isCacheHit
	 * @return
	 */
	private OpenapiCollectProviderDTO other(BigDatasourceApi bigDatasourceApi,
											Object command,
											String queryString,
											boolean success,
											String responseBusinessStatus,
											Object resultData,
											Object resultDataConverted,
											Boolean isCacheHit,Long userId,Long customerId) {

		IBigDatasourceApiConfig config = bigDatasourceApi.config();
		String requestName = bigDatasourceApi.name();
		String requestUrl = null;

		Object responseResult = resultData;
		Integer responseStatus = null;
		String responseBusinessStatusFromHttp = null;
		if (StrUtil.isNotEmpty(responseBusinessStatusFromHttp)) {
			responseBusinessStatus = responseBusinessStatusFromHttp;
		}

		LocalDateTime requestStartAt = (LocalDateTime)bigDatasourceApi.apiContext().getData("requestStartAt");
		LocalDateTime requestEndAt = (LocalDateTime)bigDatasourceApi.apiContext().getData("requestEndAt");
		Integer handleDuration = (int) LocalDateTimeUtil.between(requestStartAt,requestEndAt).toMillis();

		/**
		 * 参见：{@link DatasourceApiQueryGatewayHelper#createDefaultBigDatasourceApiByDataQueryDatasourceApi(DataQueryDatasourceApi, DataQueryDatasourceType, boolean)}
		 */
		Long dataQueryProviderId = (Long)bigDatasourceApi.apiContext().getData(DatasourceApiQueryGatewayHelper.apiContext_dataQueryProviderId);

		OpenapiCollectProviderDTO openapiCollectProviderDTO = OpenapiCollectProviderDTO.create(requestStartAt,requestEndAt,requestName,requestUrl,
				handleDuration,
				success,
				responseStatus,
				responseBusinessStatus, command,
				queryString, responseResult, dataQueryProviderId.toString(),isCacheHit,userId,customerId);
		return openapiCollectProviderDTO;
	}
	/**
	 * 收集 http 请求数据
	 * @param bigDatasourceApi
	 * @param command
	 * @param queryString
	 * @param success
	 * @param responseBusinessStatus
	 * @param isCacheHit
	 * @return
	 */
	private OpenapiCollectProviderDTO http(BigDatasourceApi bigDatasourceApi,
										   Object command,
										   String queryString,
										   boolean success,
										   String responseBusinessStatus,
										   Boolean isCacheHit,Long userId,Long customerId) {

		Object http = bigDatasourceApi.apiContext().getData(BigDatasourceHttpJoddClientImpl.apiContext_root_http);
		// 这里只取http的
		if (http != null) {
			Map httpData = (Map) http;
			/**
			 * 参见实现类：{@link BigDatasourceHttpJoddClientImpl}
			 */
			String requestName = bigDatasourceApi.name();
			String requestUrl = (String) httpData.get(BigDatasourceHttpJoddClientImpl.apiContext_requestUrl);
			//httpData.put("headers",headers);
			//httpData.put("command",command);
			//httpData.put("commandJsonStr",commandJsonStr);
			//httpData.put("queryString",queryString);
			//httpData.put("contentType",contentType);
			//httpData.put("httpMethod",httpRequest.method());
			Object responseResult = httpData.get(BigDatasourceHttpJoddClientImpl.apiContext_responseResult);
			Integer responseStatus = (Integer)httpData.get(BigDatasourceHttpJoddClientImpl.apiContext_responseStatus);
			String responseBusinessStatusFromHttp = (String)httpData.get(BigDatasourceHttpJoddClientImpl.apiContext_responseBusinessStatus);
			if (StrUtil.isNotEmpty(responseBusinessStatusFromHttp)) {
				responseBusinessStatus = responseBusinessStatusFromHttp;
			}

			LocalDateTime requestStartAt = (LocalDateTime)httpData.get(BigDatasourceHttpJoddClientImpl.apiContext_requestStartAt);
			LocalDateTime requestEndAt = (LocalDateTime)httpData.get(BigDatasourceHttpJoddClientImpl.apiContext_requestEndAt);
			Integer handleDuration = (Integer)httpData.get(BigDatasourceHttpJoddClientImpl.apiContext_handleDuration);

			if (requestStartAt == null || requestEndAt == null) {
				requestStartAt = (LocalDateTime)bigDatasourceApi.apiContext().getData("requestStartAt");
				requestEndAt = (LocalDateTime)bigDatasourceApi.apiContext().getData("requestEndAt");
				handleDuration = (int) LocalDateTimeUtil.between(requestStartAt,requestEndAt).toMillis();
			}

			/**
			 * 参见：{@link DatasourceApiQueryGatewayHelper#createDefaultBigDatasourceApiByDataQueryDatasourceApi(DataQueryDatasourceApi, DataQueryDatasourceType, boolean)}
			 */
			Long dataQueryProviderId = (Long)bigDatasourceApi.apiContext().getData(DatasourceApiQueryGatewayHelper.apiContext_dataQueryProviderId);

			OpenapiCollectProviderDTO openapiCollectProviderDTO = OpenapiCollectProviderDTO.create(requestStartAt,requestEndAt,requestName,requestUrl,
					handleDuration,
					success,
					responseStatus,
					responseBusinessStatus, command,
					queryString, responseResult, dataQueryProviderId.toString(),isCacheHit,userId,customerId);
			return openapiCollectProviderDTO;

		}
		return null;
	}

}
