package com.particle.global.big.datasource.bigdatasource.api.config;

import com.google.common.collect.Lists;
import com.particle.global.big.datasource.bigdatasource.api.BigDatasourceApiPageableAdapterConfigProvider;
import com.particle.global.big.datasource.bigdatasource.enums.BigDatasourceApiPageableAdapterType;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.dto.response.PageResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * <p>
 * 大数据源接口分页信息适配配置
 * </p>
 *
 * @author yangwei
 * @since 2023-03-19 18:10
 */
@Getter
@Setter
public class BigDatasourceApiPageableAdapterConfig implements PageableAdapterConfig{

	private static List<BigDatasourceApiPageableAdapterConfigProvider> defaultPageableAdapterConfigProviderList;

	private BigDatasourceApiPageableAdapterType commandPageType;

	private String commandPageTemplate;
	private BigDatasourceApiPageableAdapterType responsePageType;

	private String responsePageTemplate;

	protected List<BigDatasourceApiPageableAdapterConfigProvider> pageableAdapterConfigProviderList;

	@Override
	public PageQueryCommand obtainCommandPageInfo(Object command,String queryString) {
		for (BigDatasourceApiPageableAdapterConfigProvider bigDatasourceApiPageableAdapterConfigProvider : pageableAdapterConfigProviderList) {
			if (bigDatasourceApiPageableAdapterConfigProvider.commandPageSupport(commandPageType)) {
				return bigDatasourceApiPageableAdapterConfigProvider.obtainCommandPageInfo(command,queryString,commandPageTemplate);
			}
		}
		return null;
	}

	@Override
	public PageResponse obtainResponsePageInfo(Object rawResultData) {
		for (BigDatasourceApiPageableAdapterConfigProvider bigDatasourceApiPageableAdapterConfigProvider : pageableAdapterConfigProviderList) {
			if (bigDatasourceApiPageableAdapterConfigProvider.responsePageSupport(commandPageType)) {
				return bigDatasourceApiPageableAdapterConfigProvider.obtainResponsePageInfo(rawResultData,responsePageTemplate);
			}
		}
		return null;
	}

	public static BigDatasourceApiPageableAdapterConfig create (
			BigDatasourceApiPageableAdapterType commandPageType,
			String commandPageTemplate,
			BigDatasourceApiPageableAdapterType responsePageType,
			String responsePageTemplate
	){
		BigDatasourceApiPageableAdapterConfig bigDatasourceApiPageableAdapterConfig = new BigDatasourceApiPageableAdapterConfig();
		bigDatasourceApiPageableAdapterConfig.setCommandPageType(commandPageType);
		bigDatasourceApiPageableAdapterConfig.setCommandPageTemplate(commandPageTemplate);
		bigDatasourceApiPageableAdapterConfig.setResponsePageType(responsePageType);
		bigDatasourceApiPageableAdapterConfig.setResponsePageTemplate(responsePageTemplate);
		bigDatasourceApiPageableAdapterConfig.setPageableAdapterConfigProviderList(initDefaultPageableAdapterConfigProvider());

		return bigDatasourceApiPageableAdapterConfig;
	}

	private static List<BigDatasourceApiPageableAdapterConfigProvider> initDefaultPageableAdapterConfigProvider() {
		if (defaultPageableAdapterConfigProviderList != null) {
			defaultPageableAdapterConfigProviderList = Lists.newArrayList(EnjoyBigDatasourceApiPageableAdapterConfigProvider.create());
		}
		return defaultPageableAdapterConfigProviderList;
	}

}
