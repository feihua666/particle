package com.particle.global.big.datasource.bigdatasource.impl.http.api.config;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.global.big.datasource.bigdatasource.api.config.AbstractBigDatasourceApiConfig;
import com.particle.global.big.datasource.bigdatasource.exception.BigDatasourceException;
import com.particle.global.big.datasource.bigdatasource.impl.http.enums.HttpBigDatasourceApiConfigContentType;
import com.particle.global.big.datasource.bigdatasource.impl.http.enums.HttpBigDatasourceApiConfigRequestMethod;
import com.particle.global.big.datasource.bigdatasource.impl.http.enums.HttpBigDatasourceApiConfigRequestUrlRenderType;
import com.particle.global.tool.script.GroovyTool;
import com.particle.global.tool.template.TemplateRenderDataWrap;
import com.particle.global.tool.template.TemplateTool;
import lombok.Data;
import lombok.SneakyThrows;

import javax.script.Bindings;
import java.util.Map;

/**
 * <p>
 * http 大数据源接口配置
 * </p>
 *
 * @author yangwei
 * @since 2023-03-27 13:56
 */
@Data
public class HttpBigDatasourceApiConfig extends AbstractBigDatasourceApiConfig {
	
	private HttpBigDatasourceApiConfigRequestMethod requestMethod;
	
	private HttpBigDatasourceApiConfigContentType requestContentType;
	
	private HttpBigDatasourceApiConfigContentType responseContentType;
	
	private HttpBigDatasourceApiConfigRequestUrlRenderType requestUrlRenderType;
	
	private String requestUrlTemplate;


	/**
	 * 额外的数据绑定，主要用于在处理groovy脚本时，进行全局的一些脚本数据绑定
	 */
	protected Map<String, Object> extBindings;

	public static HttpBigDatasourceApiConfig create(
			HttpBigDatasourceApiConfigRequestMethod requestMethod,
			HttpBigDatasourceApiConfigContentType requestContentType,
			HttpBigDatasourceApiConfigContentType responseContentType,
			HttpBigDatasourceApiConfigRequestUrlRenderType requestUrlRenderType,
			String requestUrlTemplate
	){
		HttpBigDatasourceApiConfig httpBigDatasourceApiConfig = new HttpBigDatasourceApiConfig();

		httpBigDatasourceApiConfig.setRequestMethod(requestMethod);
		httpBigDatasourceApiConfig.setRequestContentType(requestContentType);
		httpBigDatasourceApiConfig.setResponseContentType(responseContentType);
		httpBigDatasourceApiConfig.setRequestUrlRenderType(requestUrlRenderType);
		httpBigDatasourceApiConfig.setRequestUrlTemplate(requestUrlTemplate);
		return httpBigDatasourceApiConfig;
	}


	/**
	 * 渲染请求地址
	 * @param command
	 * @param queryString
	 * @param objectMap 额外的扩展数据
	 * @return
	 */
	@SneakyThrows
	public String renderRequestUrl(Object command,String queryString,Map<String, Object> objectMap){

		Map<String, Object> renderMap = TemplateRenderDataWrap.create(command).toRenderMap();
		if (CollectionUtil.isNotEmpty(objectMap)) {
			renderMap.putAll(objectMap);
		}
		renderMap.put("queryString", queryString);


		if(requestUrlRenderType == HttpBigDatasourceApiConfigRequestUrlRenderType.groovy_script){

			Bindings bindings = GroovyTool.createBindings();
			if (extBindings != null) {
				bindings.putAll(extBindings);
			}
			bindings.putAll(renderMap);
			Object evalResult = GroovyTool.compileAndEval(requestUrlTemplate,bindings,true);
			boolean b = evalResult instanceof String;
			if (!b) {
				throw new BigDatasourceException("renderRequestUrl use groovy script must return a String value");
			}

			return evalResult.toString();

		}
		if (requestUrlRenderType == HttpBigDatasourceApiConfigRequestUrlRenderType.enjoy_template) {
			String result =  TemplateTool.render(requestUrlTemplate, renderMap);
			return result;
		}
		/**
		 * 原生支持，无需渲染
		 */
		if (requestUrlRenderType == HttpBigDatasourceApiConfigRequestUrlRenderType.raw) {
			return requestUrlTemplate;
		}

		throw new BigDatasourceException("not support renderRequestUrl for type " + requestUrlRenderType.name());
	}
}
