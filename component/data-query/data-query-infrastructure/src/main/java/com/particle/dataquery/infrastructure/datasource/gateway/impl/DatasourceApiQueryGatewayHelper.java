package com.particle.dataquery.infrastructure.datasource.gateway.impl;

import com.particle.dataquery.domain.datasource.DataQueryDatasourceApi;
import com.particle.dataquery.domain.datasource.value.DataQueryDatasourceApiInParamValidateConfig;
import com.particle.dataquery.domain.datasource.value.DataQueryDatasourceApiInSuccessValidateConfig;
import com.particle.dataquery.domain.datasource.value.DataQueryDatasourceApiJdbcBasicConfig;
import com.particle.dataquery.domain.gateway.DataQueryDictGateway;
import com.particle.global.big.datasource.bigdatasource.api.DefaultBigDatasourceApi;
import com.particle.global.big.datasource.bigdatasource.api.config.BigDatasourceApiCommandValidateConfig;
import com.particle.global.big.datasource.bigdatasource.api.config.BigDatasourceApiPageableAdapterConfig;
import com.particle.global.big.datasource.bigdatasource.api.config.BigDatasourceApiSuccessValidateConfig;
import com.particle.global.big.datasource.bigdatasource.enums.BigDatasourceApiPageableAdapterType;
import com.particle.global.big.datasource.bigdatasource.enums.BigDatasourceApiResponseWrapType;
import com.particle.global.big.datasource.bigdatasource.enums.ParamValidateType;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.api.config.JdbcBigDatasourceApiConfig;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.enums.JdbcBigDatasourceApiConfigDataType;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.enums.JdbcBigDatasourceApiConfigSqlTemplateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2023-03-23 23:44
 */
@Component
public class DatasourceApiQueryGatewayHelper {
	@Autowired
	private DataQueryDictGateway dataQueryDictGateway;

	public DefaultBigDatasourceApi createDefaultBigDatasourceApiByDataQueryDatasourceApi(DataQueryDatasourceApi datasourceApi){
		String datasourceApiResponseTypeDictIdValue = dataQueryDictGateway.getDictValueById(datasourceApi.getResponseTypeDictId());

		DefaultBigDatasourceApi defaultBigDatasourceApi = DefaultBigDatasourceApi.create(
				// 响应包装
				BigDatasourceApiResponseWrapType.valueOf(datasourceApiResponseTypeDictIdValue),
				jdbcBigDatasourceApiConfig(datasourceApi),
				null,
				pageableAdapterConfig(datasourceApi),
				commandValidateConfig(datasourceApi),
				successValidateConfig(datasourceApi)
		);
		return defaultBigDatasourceApi;
	}


	/**
	 * jdbc基础配置
	 * @param datasourceApi
	 * @return
	 */
	private JdbcBigDatasourceApiConfig jdbcBigDatasourceApiConfig(DataQueryDatasourceApi datasourceApi) {
		DataQueryDatasourceApiJdbcBasicConfig dataQueryDatasourceApiJdbcBasicConfig = datasourceApi.jdbcBasicConfig();

		return JdbcBigDatasourceApiConfig.create(
				JdbcBigDatasourceApiConfigSqlTemplateType.valueOf(dataQueryDatasourceApiJdbcBasicConfig.getSqlTemplateType().itemValue()),
				JdbcBigDatasourceApiConfigDataType.valueOf(dataQueryDatasourceApiJdbcBasicConfig.getDataType().itemValue()),
				dataQueryDatasourceApiJdbcBasicConfig.getSqlTemplate());
	}

	/**
	 * 分页解析信息配置
	 * @param datasourceApi
	 * @return
	 */
	private BigDatasourceApiPageableAdapterConfig pageableAdapterConfig(DataQueryDatasourceApi datasourceApi) {
		return Optional.ofNullable(datasourceApi.pageableAdapterConfig()).map(
				pageableAdapterConfig -> BigDatasourceApiPageableAdapterConfig.create(
						BigDatasourceApiPageableAdapterType.valueOf(pageableAdapterConfig.getInParamTemplateType().itemValue()),
						pageableAdapterConfig.getInParamTemplate(),
						BigDatasourceApiPageableAdapterType.valueOf(pageableAdapterConfig.getOutParamTemplateType().itemValue()),
						pageableAdapterConfig.getOutParamTemplate()
				)
		).orElse(null);
	}

	private BigDatasourceApiCommandValidateConfig commandValidateConfig(DataQueryDatasourceApi datasourceApi) {
		DataQueryDatasourceApiInParamValidateConfig dataQueryDatasourceApiInParamValidateConfig = datasourceApi.inParamValidateConfig();
		if (dataQueryDatasourceApiInParamValidateConfig != null) {
			BigDatasourceApiCommandValidateConfig bigDatasourceApiCommandValidateConfig = BigDatasourceApiCommandValidateConfig.create();

			for (DataQueryDatasourceApiInParamValidateConfig.ApiValidateItem inParamValidateItem : dataQueryDatasourceApiInParamValidateConfig.getInParamValidateItems()) {
				BigDatasourceApiCommandValidateConfig.ValidateItem validateItem = BigDatasourceApiCommandValidateConfig.ValidateItem.create(
						inParamValidateItem.getName(),
						ParamValidateType.valueOf(inParamValidateItem.getType().itemValue()),
						inParamValidateItem.getContentTemplate(),
						inParamValidateItem.getErrorMessage()
				);
				bigDatasourceApiCommandValidateConfig.addValidateItem(validateItem);
			}
			return bigDatasourceApiCommandValidateConfig;
		}
		return null;
	}

	private BigDatasourceApiSuccessValidateConfig successValidateConfig(DataQueryDatasourceApi datasourceApi) {
		DataQueryDatasourceApiInSuccessValidateConfig dataQueryDatasourceApiInSuccessValidateConfig = datasourceApi.outParamSuccessConfigJson();
		if (dataQueryDatasourceApiInSuccessValidateConfig != null) {
			BigDatasourceApiSuccessValidateConfig bigDatasourceApiSuccessValidateConfig = BigDatasourceApiSuccessValidateConfig.create();

			for (DataQueryDatasourceApiInSuccessValidateConfig.ApiValidateItem outParamValidateItem : dataQueryDatasourceApiInSuccessValidateConfig.getOutParamValidateItems()) {
				BigDatasourceApiSuccessValidateConfig.ValidateItem validateItem = BigDatasourceApiSuccessValidateConfig.ValidateItem.create(
						outParamValidateItem.getName(),
						ParamValidateType.valueOf(outParamValidateItem.getType().itemValue()),
						outParamValidateItem.getContentTemplate(),
						outParamValidateItem.getErrorMessage()
				);
				bigDatasourceApiSuccessValidateConfig.addValidateItem(validateItem);
			}
			return bigDatasourceApiSuccessValidateConfig;
		}
		return null;
	}
}
