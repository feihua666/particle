package com.particle.global.openapi.api.portal.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.ExceptionFactory;
import com.particle.global.exception.biz.BizException;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.global.openapi.GlobalOpenapiAutoConfiguration;
import com.particle.global.openapi.api.portal.OpenapiExecuteProvider;
import com.particle.global.openapi.api.portal.OpenapiExecuteProviderResult;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.data.ApiInfo;
import com.particle.global.openapi.data.ApiLogicRuleInfo;
import com.particle.global.openapi.endpoint.command.OpenapiCommand;
import com.particle.global.openapi.endpoint.command.OpenapiWarehouseCommand;
import com.particle.global.openapi.exception.ErrorCodeOpenapiEnum;
import com.particle.global.tool.script.GroovyTool;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.script.Bindings;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

/**
 * <p>
 * 按指定的供应商处理,可以指定多个供应商，按顺序逻辑处理
 * </p>
 *
 * @author yangwei
 * @since 2023-08-18 11:38
 */
@Slf4j
public class OpenapiSpecifyExecuteProviderLoadBalancerImpl extends AbstractOpenapiExecuteProviderLoadBalancerImpl {


	@Qualifier(GlobalOpenapiAutoConfiguration.global_openapi_executor)
	@Autowired
	private ExecutorService globalOpenapiExecutor;

	@Override
	public boolean support(OpenapiCommand openapiCommand, OpenapiContext openapiContext) {
		ApiInfo apiInfo = openapiContext.getApiInfo();
		ApiLogicRuleInfo apiLogicRuleInfo = apiInfo.getApiLogicRuleInfo();
		return apiLogicRuleInfo != null && CollectionUtil.isNotEmpty(apiLogicRuleInfo.getSpecifyProviderConfigs());
	}

	@Override
	public <T> T doExecute(OpenapiCommand openapiCommand, OpenapiContext openapiContext) {
		// 接口信息
		ApiInfo apiInfo = openapiContext.getApiInfo();
		if (apiInfo == null) {
			throw ExceptionFactory.bizException(ErrorCodeGlobalEnum.URL_NOT_FOUND);
		}

		// 接口标识
		String apiCode = apiInfo.getApiCode();
		// 接口逻辑规则
		ApiLogicRuleInfo apiLogicRuleInfo = apiInfo.getApiLogicRuleInfo();
		if (apiLogicRuleInfo == null || CollectionUtil.isEmpty(apiLogicRuleInfo.getSpecifyProviderConfigs())) {
			throw ExceptionFactory.bizException(ErrorCodeOpenapiEnum.OPENAPI_EXECUTE_PROVIDER_NOT_CONFIG);
		}
		// 根据指定的供应商配置，选择执行器供应商
		List<ApiLogicRuleInfo.SpecifyProviderConfig> specifyProviderConfigs = apiLogicRuleInfo.getSpecifyProviderConfigs();
		List<ApiLogicRuleInfo.AvailableProviderConfig> availableProviderConfigs = apiLogicRuleInfo.getAvailableProviderConfigs();
		List<OpenapiExecuteProvider> allOpenapiExecuteProviders = fetchOpenapiExecuteProviders();

		List<OpenapiExecuteProvider> specifyOpenapiExecuteProviderList = null;
		List<OpenapiExecuteProvider> availableOpenapiExecuteProviderList = null;
		if (CollectionUtil.isNotEmpty(allOpenapiExecuteProviders)) {
			specifyOpenapiExecuteProviderList = allOpenapiExecuteProviders.stream()
					.filter(item -> {
						return specifyProviderConfigs.stream().anyMatch(specifyProviderConfig -> {
							return item.supportApi(apiCode, specifyProviderConfig.getProviderApiVersion())
									&& item.supportProvider(specifyProviderConfig.getProviderCode());
						});
					})
					.collect(Collectors.toList());

			availableOpenapiExecuteProviderList = allOpenapiExecuteProviders.stream()
					.filter(item -> {
						return availableProviderConfigs.stream().anyMatch(availableProviderConfig -> {
							return item.supportApi(apiCode, availableProviderConfig.getProviderApiVersion())
									&& item.supportProvider(availableProviderConfig.getProviderCode());
						});
					})
					.collect(Collectors.toList());
		}
		if (CollectionUtil.isEmpty(specifyOpenapiExecuteProviderList)) {
			throw ExceptionFactory.bizException(ErrorCodeGlobalEnum.URL_NOT_FOUND);
		}
		// 按顺序处理逻辑
		int size = apiLogicRuleInfo.getSpecifyProviderConfigs().size();
		ApiLogicRuleInfo.SpecifyProviderConfig specifyProviderConfig = null;
		for (int i = 0; i < size; i++) {
			boolean isLast = i == size - 1;
			specifyProviderConfig = apiLogicRuleInfo.getSpecifyProviderConfigs().get(i);
			OpenapiExecuteProvider openapiExecuteProvider = select(specifyProviderConfig, specifyOpenapiExecuteProviderList, apiCode);
			if (openapiExecuteProvider == null) {
				throw ExceptionFactory.bizException(ErrorCodeOpenapiEnum.OPENAPI_EXECUTE_PROVIDER_NOT_EXIST);
			}
			boolean isLocalData = openapiExecuteProvider.supportProvider(AbstractLocalDataBaseDataOpenapiExecuteProvider.provider_code);
			Object result = null;
			boolean hasError = false;
			BizException bizException = null;
			try {
				result = executeWidthProvider(specifyProviderConfig, openapiExecuteProvider, openapiCommand, openapiContext);
			} catch (Exception e) {
				if((e instanceof BizException)){
					bizException = ((BizException) e);
					hasError = true;
				}else{
					log.error("执行供应商出错",e);
					hasError = true;
				}

			}
			OpenapiExecuteProviderResult<Object> wrapResult = wrapResult(result,isLocalData,specifyProviderConfig.getIsWhenDataLagNext(),specifyProviderConfig.getDataLagGroovyScript());
			// 数据滞后，继续处理
			if (wrapResult.getIsDataLag() && specifyProviderConfig.getIsWhenDataLagNext()) {
				if (!isLast) {
					continue;
				}
			}
			// 数据不存在，继续处理
			if (!wrapResult.getIsDataExist() && specifyProviderConfig.getIsWhenDataNotFoundNext()) {
				if (!isLast) {
					continue;
				}
			}
			// 数据存在，考虑入库
			if (wrapResult.getIsDataExist()) {
				// 不是从本地供应商查出来的，再入库
				if (!isLocalData) {
					warehouse(specifyProviderConfig, availableOpenapiExecuteProviderList, apiCode, wrapResult, openapiCommand, openapiContext);
				}
				// 数据存在，继续处理
				if(specifyProviderConfig.getIsWhenDataExistNext()){
					if (!isLast) {
						continue;
					}
				}
			}
			// 有错误，继续处理
			if ((wrapResult.getIsHasError() || hasError) && specifyProviderConfig.getIsWhenErrorNext()) {
				if (!isLast) {
					continue;
				}
			}

			T finalResult = (T) wrapResult.getResult();
			if (bizException != null) {
				throw bizException;
			}
			if (finalResult == null) {
				throw ExceptionFactory.bizException(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
			}
			return finalResult;
		}

		// 可能是bug
		throw ExceptionFactory.bizException(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
	}

	/**
	 * 入库处理
	 * @param specifyProviderConfig
	 * @param openapiExecuteProviderList
	 * @param apiCode
	 * @param wrapResult
	 * @param openapiCommand
	 * @param openapiContext
	 */
	private void warehouse(ApiLogicRuleInfo.SpecifyProviderConfig specifyProviderConfig,
						   List<OpenapiExecuteProvider> openapiExecuteProviderList,
						   String apiCode,OpenapiExecuteProviderResult<Object> wrapResult,
						   OpenapiCommand openapiCommand,
						   OpenapiContext openapiContext){
		if (specifyProviderConfig.getIsWarehouseResult()) {
			List<OpenapiExecuteProvider> openapiExecuteProvidersForWarehouse = selectWarehouseProviders(specifyProviderConfig, openapiExecuteProviderList, apiCode);
			if (specifyProviderConfig.getIsWarehouseAsync()) {
				globalOpenapiExecutor.execute(() -> {
					for (OpenapiExecuteProvider openapiExecuteProviderForWarehouse : openapiExecuteProvidersForWarehouse) {
						OpenapiWarehouseCommand<Object> objectOpenapiWarehouseCommand = OpenapiWarehouseCommand.create(wrapResult.getResult());
						openapiExecuteProviderForWarehouse.warehouse(objectOpenapiWarehouseCommand,openapiCommand, openapiContext);
					}
				});
			}else{
				for (OpenapiExecuteProvider openapiExecuteProviderForWarehouse : openapiExecuteProvidersForWarehouse) {
					OpenapiWarehouseCommand<Object> objectOpenapiWarehouseCommand = OpenapiWarehouseCommand.create(wrapResult.getResult());
					openapiExecuteProviderForWarehouse.warehouse(objectOpenapiWarehouseCommand,openapiCommand, openapiContext);
				}
			}
		}
	}
	/**
	 * 包装结果
	 * @param result
	 * @return
	 */
	@SneakyThrows
    private OpenapiExecuteProviderResult<Object> wrapResult(Object result, boolean isLocalData, boolean isCheckDataLag, String dataLagGroovyScript) {
        if (result instanceof OpenapiExecuteProviderResult) {
            return (OpenapiExecuteProviderResult<Object>) result;
        }
		boolean isDataLag = false;
		boolean isDataExist = false;
		boolean isHasError = false;
		if (isCheckDataLag) {
			if (StrUtil.isNotEmpty(dataLagGroovyScript)) {
				Bindings bindings = GroovyTool.createBindings();
				bindings.put("result", result);
				Object o = GroovyTool.compileAndEval(dataLagGroovyScript, bindings, true);
				if (o instanceof Boolean) {
					isDataLag = (Boolean) o;
				}
			}
		}
        if (result instanceof SingleResponse) {
			Object data = ((SingleResponse<?>) result).getData();
			if (data != null) {
				isDataExist = true;
			}
		}
		if (result instanceof PageResponse) {
			List data = ((PageResponse<?>) result).getData();
			if (CollectionUtil.isNotEmpty(data)) {
				isDataExist = true;
			}
		}
		if (result instanceof MultiResponse) {
			List data = ((MultiResponse<?>) result).getData();
			if (CollectionUtil.isNotEmpty(data)) {
				isDataExist = true;
			}
		}
		return OpenapiExecuteProviderResult.create(result,
				isDataLag,
				isDataExist,
				isHasError,
				isLocalData);
	}
	/**
	 * 选择执行器供应商
	 * @param specifyProviderConfig
	 * @param openapiExecuteProviderList
	 * @param apiCode
	 * @return
	 */
	private OpenapiExecuteProvider select(ApiLogicRuleInfo.SpecifyProviderConfig specifyProviderConfig,
										  List<OpenapiExecuteProvider> openapiExecuteProviderList, String apiCode) {
		for (OpenapiExecuteProvider openapiExecuteProvider : openapiExecuteProviderList) {
			if (openapiExecuteProvider.supportApi(apiCode, specifyProviderConfig.getProviderApiVersion())
					&& openapiExecuteProvider.supportProvider(specifyProviderConfig.getProviderCode())) {
				return openapiExecuteProvider;
			}
		}
		return null;
	}

	/**
	 * 选择支持入库的供应商
	 * @param specifyProviderConfig
	 * @param openapiExecuteProviderList
	 * @param apiCode
	 * @return
	 */
	private List<OpenapiExecuteProvider> selectWarehouseProviders(ApiLogicRuleInfo.SpecifyProviderConfig specifyProviderConfig,
																	List<OpenapiExecuteProvider> openapiExecuteProviderList,
																  String apiCode) {
		return openapiExecuteProviderList.stream()
				.filter(item -> {
					// 注意这里忽略接口版本
					return (item.supportApi(apiCode, specifyProviderConfig.getProviderApiVersion())
					|| item.supportApi(apiCode, null))
							&& item.supportWareHouse();
				})
				.collect(Collectors.toList());
																	}
	/**
	 * 执行执行器供应商
	 * @param specifyProviderConfig
	 * @param openapiExecuteProvider
	 * @param openapiCommand
	 * @param openapiContext
	 * @return
	 * @param <T>
	 */
	private <T> T executeWidthProvider(ApiLogicRuleInfo.SpecifyProviderConfig specifyProviderConfig,
									   OpenapiExecuteProvider openapiExecuteProvider,
									   OpenapiCommand openapiCommand,
									   OpenapiContext openapiContext) {
		Object result = openapiExecuteProvider.execute(openapiCommand, openapiContext);
		return (T) result;
	}
}
