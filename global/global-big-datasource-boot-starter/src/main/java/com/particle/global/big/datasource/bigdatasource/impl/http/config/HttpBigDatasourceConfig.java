package com.particle.global.big.datasource.bigdatasource.impl.http.config;

import cn.hutool.core.util.StrUtil;
import com.particle.global.big.datasource.bigdatasource.config.BigDatasourceAccountConfig;
import com.particle.global.big.datasource.bigdatasource.impl.http.enums.HttpBigDatasourceAuthScriptType;
import com.particle.global.tool.script.GroovyTool;
import com.particle.global.tool.template.TemplateRenderDataWrap;
import com.particle.global.tool.template.TemplateTool;
import lombok.Data;
import lombok.SneakyThrows;

import javax.script.Bindings;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * http大数据源配置
 * </p>
 *
 * @author yangwei
 * @since 2023-03-27 14:32
 */
@Data
public class HttpBigDatasourceConfig extends BigDatasourceAccountConfig {

	/**
	 * 域名地址，如：http://example.com
	 */
	private String domainUrl;

	/**
	 * 认证脚本类型
	 */
	private HttpBigDatasourceAuthScriptType authScriptType;
	/**
	 * 认证脚本内容，该脚本用来处理认证逻辑
	 * 一般http接口认证，需要对用户和密码处理一些逻辑，添加到请求头中
	 */
	private String authScriptTemplate;

	public static HttpBigDatasourceConfig create(String domainUrl){
		HttpBigDatasourceConfig httpBigDatasourceConfig = new HttpBigDatasourceConfig();
		httpBigDatasourceConfig.setDomainUrl(domainUrl);
		return httpBigDatasourceConfig;
	}

	public static HttpBigDatasourceConfig create(String domainUrl, String username, String password){
		HttpBigDatasourceConfig httpBigDatasourceConfig = create(domainUrl);
		httpBigDatasourceConfig.setUsername(username);
		httpBigDatasourceConfig.setPassword(password);
		return httpBigDatasourceConfig;
	}
	public static HttpBigDatasourceConfig create(String domainUrl,
												 HttpBigDatasourceAuthScriptType authScriptType,
												 String authScriptTemplate,
												 String username, String password){
		HttpBigDatasourceConfig httpBigDatasourceConfig = create(domainUrl, username, password);
		httpBigDatasourceConfig.setAuthScriptType(authScriptType);
		httpBigDatasourceConfig.setAuthScriptTemplate(authScriptTemplate);
		return httpBigDatasourceConfig;
	}
	public static HttpBigDatasourceConfig create(String domainUrl,
												 HttpBigDatasourceAuthScriptType authScriptType,
												 String authScriptTemplate,
												 String username,
												 String password,
												 String proxyAddress,
												 String proxyPort,
												 String proxyUsername,
												 String proxyPassword){
		HttpBigDatasourceConfig httpBigDatasourceConfig = create(domainUrl,authScriptType,authScriptTemplate, username, password);
		httpBigDatasourceConfig.setProxyAddress(proxyAddress);
		httpBigDatasourceConfig.setProxyPort(proxyPort);
		httpBigDatasourceConfig.setProxyUsername(proxyUsername);
		httpBigDatasourceConfig.setProxyPassword(proxyPassword);
		return httpBigDatasourceConfig;
	}

	/**
	 * 根据脚本渲染认证请求头
	 * @param command 原始请求参数，有可能为null，如果没有请求参数时
	 * @return 空或者null也是有可能返回的
	 */
	@SneakyThrows
	public Map<String, String> renderAuthHeaders(Object command,String commandJsonStr,String queryString) {

		Map<String, String> result = new HashMap<>();

		if (StrUtil.isEmpty(authScriptTemplate) || authScriptType == null) {
			return result;
		}
		TemplateRenderDataWrap<Object> objectTemplateRenderDataWrap = TemplateRenderDataWrap.create(command);
		Map<String, Object> objectMap = objectTemplateRenderDataWrap.toRenderMap();
		objectMap.put("headers", result);
		objectMap.put("username", getUsername());
		objectMap.put("password", getPassword());
		objectMap.put("dataJsonStr", commandJsonStr);
		if (authScriptType == HttpBigDatasourceAuthScriptType.enjoy_template) {
			// enjoy模板只支持在脚本中设置 headers
			TemplateTool.render(authScriptTemplate, objectMap);
			return result;
		}
		if (authScriptType == HttpBigDatasourceAuthScriptType.groovy_script) {
			Bindings bindings = GroovyTool.createBindings();
			bindings.putAll(objectMap);
			Object o = GroovyTool.compileAndEval(authScriptTemplate, bindings, true);
			if(!result.isEmpty()){
				return result;
			}
			// 如果为空可以使用返回值
			if (o != null && o instanceof Map) {
				return (Map<String, String>) o;
			}

		}

			return result;
	}

}
