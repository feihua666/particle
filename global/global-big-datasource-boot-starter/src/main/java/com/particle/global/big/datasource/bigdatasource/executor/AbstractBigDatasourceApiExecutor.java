package com.particle.global.big.datasource.bigdatasource.executor;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.particle.global.big.datasource.bigdatasource.api.BigDatasourceApi;
import com.particle.global.big.datasource.bigdatasource.api.config.BigDatasourceApiCommandValidateConfig;
import com.particle.global.big.datasource.bigdatasource.api.config.PageableAdapterConfig;
import com.particle.global.big.datasource.bigdatasource.enums.BigDatasourceApiResponseWrapType;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.RawResponse;
import com.particle.global.dto.response.SingleResponse;

import java.util.Collection;

/**
 * <p>
 * 大数据源接口执行器抽象父类
 * </p>
 *
 * @author yangwei
 * @since 2023-03-10 17:06
 */
public abstract class AbstractBigDatasourceApiExecutor implements BigDatasourceApiExecutor{

	@Override
	public Object execute(BigDatasourceApi bigDatasourceApi, Object command) {
		preExe(bigDatasourceApi, command);
		Object o = doExecute(bigDatasourceApi, command);
		postExe(bigDatasourceApi, command, o);
		boolean success = isSuccess(bigDatasourceApi,o);
		Object resultData = resultData(bigDatasourceApi, command, o);
		Object resultDataConverted = resultDataConvert(bigDatasourceApi, command,resultData, o);
		return resultDataConverted;
	}
	public abstract Object doExecute(BigDatasourceApi bigDatasourceApi, Object command);

	/**
	 * 执行前调用，做一些参数处理
	 * @param bigDatasourceApi
	 * @param command
	 * @return
	 */
	protected void preExe(BigDatasourceApi bigDatasourceApi, Object command) {
		commandValidate(bigDatasourceApi,command);
	}
	/**
	 * 执行前调用，做一些参数处理
	 * @param bigDatasourceApi
	 * @param command
	 * @param o
	 * @return
	 */
	protected void postExe(BigDatasourceApi bigDatasourceApi, Object command,Object o) {

	}
	/**
	 * 参数校验
	 * 一般是是否必填，正则匹配等
	 * 暂不考虑远程调用校验
	 * @param bigDatasourceApi
	 * @param command
	 */
	protected void commandValidate(BigDatasourceApi bigDatasourceApi,Object command) {
		BigDatasourceApiCommandValidateConfig bigDatasourceApiCommandValidateConfig = bigDatasourceApi.commandValidateConfig();
		if (bigDatasourceApiCommandValidateConfig != null) {
			bigDatasourceApiCommandValidateConfig.doValidate(command, true);
		}
	}

	/**
	 * 参数转换
	 * 将参数转换为可被执行器被别的数据对象，包括数据字符映射等
	 * @param command 远端调用的标识文档数据对象
	 * @return
	 */
	//protected QueryCommand commandConvert(Object command) {
	//	return command;
	//}

	/**
	 * 返回结果数据
	 * 一般可能会有获取的数据带有包装，如：带有{code: 200,msg: '成功',data: xxx}这里只取最终要的数据
	 * @param bigDatasourceApi
	 * @param command
	 * @param rawResultData
	 * @return 这里只取data的数据
	 */
	protected Object resultData(BigDatasourceApi bigDatasourceApi, Object command,Object rawResultData) {
		return rawResultData;
	}

	/**
	 * 结果数据转换
	 * @param bigDatasourceApi
	 * @param command
	 * @param resultData 原生经历过处理的数据 参见 {@link AbstractBigDatasourceApiExecutor#resultData(com.particle.global.big.datasource.bigdatasource.api.BigDatasourceApi, java.lang.Object, java.lang.Object)}
	 * @param rawResultData 原生返回的数据
	 * @return 按文档说明返回的数据结构数据
	 */
	protected Object resultDataConvert(BigDatasourceApi bigDatasourceApi, Object command,Object resultData,Object rawResultData) {
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
					return PageResponse.of(resultDataPage.getRecords(), (int)resultDataPage.getTotal(), (int)resultDataPage.getCurrent(), (int)resultDataPage.getSize());
				}
				PageableAdapterConfig pageableAdapterConfig = bigDatasourceApi.pageableAdapterConfig();
				if (pageableAdapterConfig == null) {
					throw new RuntimeException("current BigDatasourceApiResponseWrapType is page mode, pageableAdapterConfig should be set!");
				}
				PageResponse pageResponse = pageableAdapterConfig.obtainResponsePageInfo(rawResultData);
				if (pageResponse == null) {
					throw new RuntimeException("current BigDatasourceApiResponseWrapType is page mode, pageableAdapterConfig#obtainResponsePageInfo method should be set!");
				}
				// 这里应该提取分页结果数据
				return PageResponse.of((Collection)resultData,pageResponse.getTotalCount(), pageResponse.getPageNo(), pageResponse.getPageSize());
			}
			if (bigDatasourceApi.responseWrapType() == BigDatasourceApiResponseWrapType.raw) {
				if (resultData instanceof RawResponse) {
					return resultData;
				}
				return RawResponse.of(resultData);
			}
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