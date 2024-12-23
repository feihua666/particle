package com.particle.dataquery.domain.datasource.value;

import cn.hutool.json.JSONUtil;
import com.particle.dataquery.domain.datasource.enums.DataQueryDatasourceApiHttpBasicConfigContentType;
import com.particle.dataquery.domain.datasource.enums.DataQueryDatasourceApiHttpBasicConfigRequestMethod;
import com.particle.dataquery.domain.datasource.enums.DataQueryDatasourceApiHttpBasicConfigRequestUrlRenderType;
import com.particle.global.dto.basic.Value;
import com.particle.global.tool.script.GroovyTool;
import com.particle.global.tool.template.TemplateTool;
import lombok.Data;

import javax.script.ScriptException;

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

	/**
	 * 脚本预热
	 * @throws ScriptException
	 */
	public void warmUpLight() throws ScriptException {
		if (requestUrlRenderType != null && requestUrlTemplate != null) {
			switch (requestUrlRenderType) {
				case enjoy_template:
					TemplateTool.templateCompile(requestUrlTemplate);
					break;
				case groovy_script:
					GroovyTool.compile(requestUrlTemplate,true);
					break;
				default:
					break;
			}
		}
	}

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
