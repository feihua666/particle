package com.particle.global.big.datasource.bigdatasource.api.config;

import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.dto.response.PageResponse;

/**
 * <p>
 * 分页查询时数据配置
 * </p>
 *
 * @author yangwei
 * @since 2023-03-19 17:19
 */
public interface PageableAdapterConfig {

	/**
	 * 提取分页参数信息
	 * @param command
	 * @return
	 */
	PageQueryCommand obtainCommandPageInfo(Object command);

	/**
	 * 提取分页响应参数信息
	 * @param rawResultData
	 * @return
	 */
	PageResponse obtainResponsePageInfo(Object rawResultData);
}
