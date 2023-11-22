package com.particle.dataquery.domain.datasource.value;

import cn.hutool.json.JSONUtil;
import com.particle.dataquery.domain.datasource.enums.*;
import com.particle.global.dto.basic.Value;
import lombok.Data;

/**
 * <p>
 * http 数据源接口基础配置
 * 该配置大致与{@link com.particle.global.big.datasource.bigdatasource.impl.http.api.config.HttpBigDatasourceApiConfig} 相对应
 * </p>
 *
 * @author yangwei
 * @since 2023-03-27 13:38
 */
@Data
public class DataQueryDatasourceApiHttpBasicConfig extends Value {

	private DataQueryDatasourceApiHttpBasicConfigRequestMethod requestMethod;

	private DataQueryDatasourceApiHttpBasicConfigContentType requestContentType;

	private DataQueryDatasourceApiHttpBasicConfigContentType responseContentType;

	private DataQueryDatasourceApiHttpBasicConfigRequestUrlRenderType requestUrlRenderType;

	private String requestUrlTemplate;

	public static DataQueryDatasourceApiHttpBasicConfig createFromJsonStr(String jsonStr) {
		DataQueryDatasourceApiHttpBasicConfig config = JSONUtil.toBean(jsonStr, DataQueryDatasourceApiHttpBasicConfig.class);
		return config;
	}

	public static DataQueryDatasourceApiHttpBasicConfig create(
			DataQueryDatasourceApiHttpBasicConfigRequestMethod requestMethod,
			DataQueryDatasourceApiHttpBasicConfigContentType requestContentType,
			DataQueryDatasourceApiHttpBasicConfigContentType responseContentType,
			DataQueryDatasourceApiHttpBasicConfigRequestUrlRenderType requestUrlRenderType,
			String requestUrlTemplate
	) {
		DataQueryDatasourceApiHttpBasicConfig config = new DataQueryDatasourceApiHttpBasicConfig();
		config.setRequestMethod(requestMethod);
		config.setRequestContentType(requestContentType);
		config.setResponseContentType(responseContentType);
		config.setRequestUrlRenderType(requestUrlRenderType);
		config.setRequestUrlTemplate(requestUrlTemplate);
		return config;
	}
}
