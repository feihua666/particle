package com.particle.global.big.datasource.bigdatasource.api;

import com.particle.global.big.datasource.bigdatasource.enums.BigDatasourceApiPageableAdapterType;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.dto.response.PageResponse;

/**
 * <p>
 * 大数据源接口分页适配提供者
 * </p>
 *
 * @author yangwei
 * @since 2023-03-19 18:12
 */
public interface BigDatasourceApiPageableAdapterConfigProvider {

	/**
	 * 配置 {@link BigDatasourceApiPageableAdapterConfigProvider#obtainCommandPageInfo(java.lang.Object,java.lang.String)} 使用
	 * @param apiPageableAdapterType
	 * @return
	 */
	boolean commandPageSupport(BigDatasourceApiPageableAdapterType apiPageableAdapterType);

	/**
	 * 提取分页参数信息
	 * @param command
	 * @return
	 */
	PageQueryCommand obtainCommandPageInfo(Object command,String queryString,String template);

	/**
	 * 配合 {@link BigDatasourceApiPageableAdapterConfigProvider#obtainResponsePageInfo(java.lang.Object,java.lang.String)} 使用
	 * @param apiPageableAdapterType
	 * @return
	 */
	boolean responsePageSupport(BigDatasourceApiPageableAdapterType apiPageableAdapterType);
	/**
	 * 提取分页响应参数信息
	 * @param rawResultData
	 * @return
	 */
	PageResponse obtainResponsePageInfo(Object rawResultData,String template);
}
