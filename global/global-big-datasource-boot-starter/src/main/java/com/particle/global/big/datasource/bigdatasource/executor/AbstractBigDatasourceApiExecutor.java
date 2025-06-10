package com.particle.global.big.datasource.bigdatasource.executor;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.particle.global.big.datasource.bigdatasource.api.BigDatasourceApi;
import com.particle.global.big.datasource.bigdatasource.api.config.*;
import com.particle.global.big.datasource.bigdatasource.enums.BigDatasourceApiResponseWrapType;
import com.particle.global.big.datasource.bigdatasource.exception.BigDatasourceException;
import com.particle.global.big.datasource.bigdatasource.trans.IBigDatasourceApiTransSupportService;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.RawResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.tool.json.JsonTool;
import com.particle.global.tool.spring.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * <p>
 * 大数据源接口执行器抽象父类
 * 定义的接口执行的基本框架及流程
 * 主要是根据{@link BigDatasourceApi}的配置信息执行具体的逻辑
 * </p>
 *
 * @author yangwei
 * @since 2023-03-10 17:06
 */
@Slf4j
public abstract class AbstractBigDatasourceApiExecutor implements BigDatasourceApiExecutor{

	protected List<ExecutorInfrastructureListener> executorInfrastructureListenerList;
	protected IBigDatasourceApiExecutorExeCache bigDatasourceApiExecutorExeCache;

	protected IBigDatasourceApiTransSupportService iBigDatasourceApiTransSupportService;

	@Override
	public Object execute(BigDatasourceApi bigDatasourceApi, Object command,String queryString) {

		long startAt = System.currentTimeMillis();
		String commandJsonStr = null;
		try {
			commandJsonStr = JsonTool.toJsonStr(command);
		} catch (Exception e) {
			if (command != null) {
				command.toString();
			}
		}
		String identifier = Optional.ofNullable(bigDatasourceApi).map(BigDatasourceApi::identifier).orElse(null);
		log.info("execute BigDatasourceApi start identifier={},command={},queryString={}",
				identifier,
				commandJsonStr,
				queryString
		);
		try {
			return handleExecute(bigDatasourceApi, command, queryString);
		} finally {
			log.info("execute BigDatasourceApi end identifier={},command={},queryString={}, duration={}ms",
					identifier,
					commandJsonStr,
					queryString,
					System.currentTimeMillis() - startAt);

		}
	}

	/**
	 * 处理执行
	 * @param bigDatasourceApi
	 * @param command
	 * @param queryString
	 * @return
	 */
	private Object handleExecute(BigDatasourceApi bigDatasourceApi, Object command,String queryString) {

		// 监听调用
		if (executorInfrastructureListenerList != null) {
			for (ExecutorInfrastructureListener executorInfrastructureListener : executorInfrastructureListenerList) {
				executorInfrastructureListener.beforeRequest(bigDatasourceApi,command,queryString);
			}
		}
		Map<String,Object> preExeResultHolder = new HashMap<>(2);

		// 接口执行前处理
		preExe(bigDatasourceApi, command, queryString, preExeResultHolder);

		// 如果有修改，使用返回的结果作为新的command
		if (preExeResultHolder.containsKey("command")) {
			command = preExeResultHolder.get("command");
		}
		Object finalCommand = command;
		Object o = null;
		boolean cacheHit = false;
		// 判断是否使用缓存，只缓存执行结果
		if (bigDatasourceApi.useCache()) {
			// 添加缓存支持
			IBigDatasourceApiExecutorExeCache.CacheValue cacheValue = Optional.ofNullable(bigDatasourceApiExecutorExeCache).map(iBigDatasourceApiExecutorExeCache -> iBigDatasourceApiExecutorExeCache.get(bigDatasourceApi, finalCommand, queryString)).orElse(null);
			o = Optional.ofNullable(cacheValue).map(cacheValue1 -> cacheValue1.getData()).orElse(null);
			cacheHit = Optional.ofNullable(cacheValue).map(cacheValue1 -> cacheValue1.getIsCacheHit()).orElse(false);
			// 没有命中获取
			if (!cacheHit) {
				o = doExecute(bigDatasourceApi, command,queryString);
				if (o != null) {
					Object finalO = o;

					Optional.ofNullable(bigDatasourceApiExecutorExeCache).ifPresent((iBigDatasourceApiExecutorExeCache)->{
						iBigDatasourceApiExecutorExeCache.put(bigDatasourceApi, finalCommand, queryString, finalO);
					});
				}
			}
		}else {
			o = doExecute(bigDatasourceApi, command,queryString);
		}

		Map<String,Object> postExeResultHolder = new HashMap<>(2);

		// 请求完处理，主要处理了出参扩展配置
		postExe(bigDatasourceApi, command,queryString, o,postExeResultHolder);

		// 如果有修改，使用返回的结果作为新的结果数据
		if (postExeResultHolder.containsKey("result")) {
			o = postExeResultHolder.get("result");
		}
		String responseBusinessStatus = null;
		if (postExeResultHolder.containsKey("responseBusinessStatus")) {
			Object responseBusinessStatusObj = postExeResultHolder.get("responseBusinessStatus");
			if (responseBusinessStatusObj != null) {
				responseBusinessStatus = responseBusinessStatusObj.toString();
			}
		}
		// 翻译
		transHandle(bigDatasourceApi, o);
		// 判断是否成功
		boolean success = isSuccess(bigDatasourceApi,o);
		// 收集是否成功的数据，主要用于开放接口使用
		bigDatasourceApi.apiContext().putData("executor.result.success",success);
		// 对执行结果进行一些处理
		Object resultData = handleResultData(bigDatasourceApi, command,queryString, o);
		// 尝试对结果进行包装
		Object resultDataConverted = resultDataWrapConvert(bigDatasourceApi, command,queryString,resultData, o);

		// 监听调用
		if (executorInfrastructureListenerList != null) {
			for (ExecutorInfrastructureListener executorInfrastructureListener : executorInfrastructureListenerList) {
				executorInfrastructureListener.afterResponse(bigDatasourceApi,command,queryString,success,responseBusinessStatus,resultData,resultDataConverted,cacheHit);
			}
		}
		return resultDataConverted;
	}

	/**
	 * 初始化
	 * @param executorInfrastructureListenerList
	 */
	protected void executorInfrastructureListenerInit(List<ExecutorInfrastructureListener> executorInfrastructureListenerList) {
		this.executorInfrastructureListenerList = executorInfrastructureListenerList;
	}

	/**
	 * 从spring容器初始化
	 */
	protected void executorInfrastructureListenerInitFromSpring() {
		try {
			this.executorInfrastructureListenerList = SpringContextHolder.getBeans(ExecutorInfrastructureListener.class);
		} catch (Exception e) {
			log.warn("can not init executorInfrastructureListenerList from spring because of exception",e);
		}
	}

	/**
	 * 初始化缓存
	 * @param bigDatasourceApiExecutorExeCache
	 */
	protected void bigDatasourceApiExecutorExeCacheInit(IBigDatasourceApiExecutorExeCache bigDatasourceApiExecutorExeCache) {
		this.bigDatasourceApiExecutorExeCache = bigDatasourceApiExecutorExeCache;
	}

	/**
	 * 从spring容器初始化缓存
	 */
	protected void bigDatasourceApiExecutorExeCacheInitFromSpring() {
		try {
			this.bigDatasourceApiExecutorExeCache = SpringContextHolder.getBean(IBigDatasourceApiExecutorExeCache.class);
		} catch (Exception e) {
			log.warn("can not init bigDatasourceApiExecutorExeCache from spring because of exception",e);
		}
	}

	/**
	 * 初始化翻译支持
	 * @param iBigDatasourceApiTransSupportService
	 */
	protected void bigDatasourceTransSupportServiceInit(IBigDatasourceApiTransSupportService iBigDatasourceApiTransSupportService) {
		this.iBigDatasourceApiTransSupportService = iBigDatasourceApiTransSupportService;
	}

	/**
	 * 从spring初始化翻译支持
	 */
	protected void bigDatasourceTransSupportServiceInitFromSpring() {
		try {
			this.iBigDatasourceApiTransSupportService = SpringContextHolder.getBean(IBigDatasourceApiTransSupportService.class);
		} catch (Exception e) {
			log.warn("can not init iBigDatasourceTransSupportService from spring because of exception",e);
		}
	}

	/**
	 * 真正执行
	 * @param bigDatasourceApi
	 * @param command
	 * @param queryString
	 * @return
	 */
	public abstract Object doExecute(BigDatasourceApi bigDatasourceApi, Object command,String queryString);

	/**
	 * 执行前调用，做一些参数处理
	 * @param bigDatasourceApi
	 * @param command
	 * @return
	 */
	protected void preExe(BigDatasourceApi bigDatasourceApi, Object command,String queryString,Map<String,Object> preExeResultHolder) {
		commandValidate(bigDatasourceApi,command,queryString);
		Object newCommand = commandExtConfigHandle(bigDatasourceApi, command, queryString);
		if (newCommand != null) {
			preExeResultHolder.put("command", newCommand);
		}
	}
	/**
	 * 执行前调用，做一些参数处理
	 * @param bigDatasourceApi
	 * @param command
	 * @param o
	 * @return
	 */
	protected void postExe(BigDatasourceApi bigDatasourceApi, Object command,String queryString,Object o,Map<String,Object> postExeResultHolder) {

		// 出参扩展配置执行
		Object newResult = resultExtConfigHandle(bigDatasourceApi, command, queryString, o,postExeResultHolder);
		Object result = postExeResultHolder.get("result");
		if (result != null) {
			return;
		}
		if (newResult != null) {
			postExeResultHolder.put("result", newResult);
		}
	}

	/**
	 * 入参数扩展处理
	 * @param bigDatasourceApi
	 * @param command
	 * @param queryString
	 * @return  如果返回结果有值将替换原始参数command
	 */
	protected Object commandExtConfigHandle(BigDatasourceApi bigDatasourceApi,Object command,String queryString) {
		BigDatasourceApiCommandExtConfig bigDatasourceApiCommandExtConfig = bigDatasourceApi.commandExtConfig();
		if (bigDatasourceApiCommandExtConfig != null) {
			Object handle = bigDatasourceApiCommandExtConfig.handle(command, queryString);
			return handle;
		}
		return null;
	}

	/**
	 * 出参数扩展处理
	 * @param bigDatasourceApi
	 * @param command
	 * @param queryString
	 * @return  如果返回结果有值将替换原始参数command
	 */
	protected Object resultExtConfigHandle(BigDatasourceApi bigDatasourceApi,Object command,String queryString, Object o,Map<String,Object> postExeResultHolder) {
		BigDatasourceApiResultExtConfig bigDatasourceApiResultExtConfig = bigDatasourceApi.resultExtConfig();
		if (bigDatasourceApiResultExtConfig != null) {
			// 扩展配置
			Map<String, Object> ext = new HashMap<>(1);
			ext.put("ext", postExeResultHolder);
			Object handle = bigDatasourceApiResultExtConfig.handle(o,command, queryString,ext);
			return handle;
		}
		return null;
	}
	/**
	 * 翻译处理
	 * @param bigDatasourceApi
	 */
	protected void transHandle(BigDatasourceApi bigDatasourceApi, Object o) {
		if (iBigDatasourceApiTransSupportService == null || o == null) {
			return;
		}
		BigDatasourceApiTransConfig bigDatasourceApiTransConfig = bigDatasourceApi.transConfig();
		if (bigDatasourceApiTransConfig == null) {
			return;
		}
		List<DictGroup> dictGroups = bigDatasourceApi.dictGroups();
		iBigDatasourceApiTransSupportService.trans(o, bigDatasourceApiTransConfig, dictGroups);
	}

	/**
	 * 参数校验
	 * 一般是是否必填，正则匹配等
	 * 暂不考虑远程调用校验
	 * @param bigDatasourceApi
	 * @param command
	 */
	protected void commandValidate(BigDatasourceApi bigDatasourceApi,Object command,String queryString) {
		BigDatasourceApiCommandValidateConfig bigDatasourceApiCommandValidateConfig = bigDatasourceApi.commandValidateConfig();
		if (bigDatasourceApiCommandValidateConfig != null) {
			bigDatasourceApiCommandValidateConfig.doValidate(command, true);
		}
	}

	/**
	 * 返回结果数据
	 * 一般可能会有获取的数据带有包装，如：带有{code: 200,msg: '成功',data: xxx}这里只取最终要的数据
	 * @param bigDatasourceApi
	 * @param command
	 * @param rawResultData
	 * @return 这里只取data的数据
	 */
	protected Object handleResultData(BigDatasourceApi bigDatasourceApi, Object command, String queryString, Object rawResultData) {
		return rawResultData;
	}

	/**
	 * 结果数据转换
	 * @param bigDatasourceApi
	 * @param command
	 * @param resultData 原生经历过处理的数据 参见 {@link AbstractBigDatasourceApiExecutor#handleResultData(com.particle.global.big.datasource.bigdatasource.api.BigDatasourceApi, java.lang.Object, java.lang.String, java.lang.Object)}
	 * @param rawResultData 原生返回的数据
	 * @return 按文档说明返回的数据结构数据
	 */
	protected Object resultDataWrapConvert(BigDatasourceApi bigDatasourceApi, Object command, String queryString, Object resultData, Object rawResultData) {
		if (bigDatasourceApi.responseWrapType() != null) {
			if (bigDatasourceApi.responseWrapType() == BigDatasourceApiResponseWrapType.single) {
				if (resultData instanceof SingleResponse) {
					return resultData;
				}
				return SingleResponse.of(resultData);
			}
			if (bigDatasourceApi.responseWrapType() == BigDatasourceApiResponseWrapType.multiple) {
				if (resultData instanceof MultiResponse) {
					return resultData;
				}
				return MultiResponse.of((Collection)resultData);
			}
			if (bigDatasourceApi.responseWrapType() == BigDatasourceApiResponseWrapType.page) {
				if (resultData instanceof PageResponse) {
					return resultData;
				}
				// 兼容一下数据库 mybatis plus 分页数据
				if (resultData instanceof IPage) {
					IPage<?> resultDataPage = (IPage<?>) resultData;
					return PageResponse.of(resultDataPage.getRecords(), (int)resultDataPage.getTotal(), (int)resultDataPage.getSize(), (int)resultDataPage.getCurrent());
				}
				PageableAdapterConfig pageableAdapterConfig = bigDatasourceApi.pageableAdapterConfig();
				if (pageableAdapterConfig == null) {
					throw new BigDatasourceException("current BigDatasourceApiResponseWrapType is page mode, pageableAdapterConfig should be set!");
				}
				PageResponse pageResponse = pageableAdapterConfig.obtainResponsePageInfo(rawResultData);
				if (pageResponse == null) {
					throw new BigDatasourceException("current BigDatasourceApiResponseWrapType is page mode, pageableAdapterConfig#obtainResponsePageInfo method should be set!");
				}
				// 这里应该提取分页结果数据
				return pageResponse;
			}
			if (bigDatasourceApi.responseWrapType() == BigDatasourceApiResponseWrapType.raw) {
				if (resultData instanceof RawResponse) {
					return resultData;
				}
				return RawResponse.of(resultData);
			}
			// 如果设置的为代理，意味着直接返回原始的数据结果
			if (bigDatasourceApi.responseWrapType() == BigDatasourceApiResponseWrapType.proxy) {
				return resultData;
			}
		}
		return resultData;
	}
	/**
	 * 是否调用成功
	 * @param rawResultData
	 * @return true=成功，false=失败
	 */
	public boolean isSuccess(BigDatasourceApi bigDatasourceApi,Object rawResultData) {
		if (bigDatasourceApi.successValidateConfig() != null) {
			return bigDatasourceApi.successValidateConfig().doValidate(rawResultData, false);
		}
		return true;
	}
}
