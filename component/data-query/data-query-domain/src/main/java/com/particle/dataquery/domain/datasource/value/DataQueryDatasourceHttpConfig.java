package com.particle.dataquery.domain.datasource.value;

import cn.hutool.json.JSONUtil;
import com.particle.dataquery.domain.datasource.enums.DataQueryDatasourceHttpAuthScriptType;
import com.particle.global.tool.script.GroovyTool;
import com.particle.global.tool.template.TemplateTool;
import lombok.Data;

import javax.script.ScriptException;

/**
 * <p>
 * jdbc数据源配置
 * </p>
 *
 * @author yangwei
 * @since 2023-03-29 00:37:59
 */
@Data
public class DataQueryDatasourceHttpConfig extends DataQueryDatasourceAccountConfig {


	/**
	 * 域名地址，如：http://example.com
	 */
	private String domainUrl;

	/**
	 * 认证脚本类型
	 */
	private DataQueryDatasourceHttpAuthScriptType authScriptType;
	/**
	 * 认证脚本内容，该脚本用来处理认证逻辑
	 * 一般http接口认证，需要对用户和密码处理一些逻辑，添加到请求头中
	 */
	private String authScriptTemplate;

	/**
	 * 脚本预热
	 * @throws ScriptException
	 */
	public void warmUpLight() throws ScriptException {
		if (authScriptType != null && authScriptTemplate != null) {
			switch (authScriptType) {
				case enjoy_template:
					TemplateTool.templateCompile(authScriptTemplate);
					break;
				case groovy_script:
					GroovyTool.compile(authScriptTemplate,true);
					break;
				default:
					break;
			}
		}
	}
	public static DataQueryDatasourceHttpConfig createFromJsonStr(String jsonStr) {
		DataQueryDatasourceHttpConfig config = JSONUtil.toBean(jsonStr, DataQueryDatasourceHttpConfig.class);
		return config;
	}
}
