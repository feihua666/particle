package com.particle.global.big.datasource.bigdatasource.impl.http.api.config;

import com.particle.global.big.datasource.bigdatasource.api.config.AbstractBigDatasourceApiConfig;
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




	@SneakyThrows
	public String renderRequestUrl(Object command,String queryString){

		if(requestUrlRenderType == HttpBigDatasourceApiConfigRequestUrlRenderType.groovy_script){

			Map<String, Object> renderMap = TemplateRenderDataWrap.create(command).toRenderMap();

			Bindings bindings = GroovyTool.createBindings();
			bindings.putAll(renderMap);
			Object evalResult = GroovyTool.compileAndEval(requestUrlTemplate,bindings,true);
			boolean b = evalResult instanceof String;
			if (!b) {
				throw new RuntimeException("renderRequestUrl use groovy script must return a String value");
			}

			return evalResult.toString();

		}
		if (requestUrlRenderType == HttpBigDatasourceApiConfigRequestUrlRenderType.enjoy_template) {
			String result =  TemplateTool.render(requestUrlTemplate, TemplateRenderDataWrap.create(command));
			return result;
		}
		/**
		 * 原生支持，无需渲染
		 */
		if (requestUrlRenderType == HttpBigDatasourceApiConfigRequestUrlRenderType.raw) {
			return requestUrlTemplate;
		}

		throw new RuntimeException("not support renderRequestUrl for type " + requestUrlRenderType.name());
	}
}