package com.particle.global.big.datasource.bigdatasource.api.config;

import com.particle.global.big.datasource.bigdatasource.api.BigDatasourceApiPageableAdapterConfigProvider;
import com.particle.global.big.datasource.bigdatasource.enums.BigDatasourceApiPageableAdapterType;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.tool.script.GroovyTool;
import com.particle.global.tool.template.TemplateRenderDataWrap;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.script.Bindings;
import java.util.Map;

/**
 * <p>
 * 基于 groovy 脚本
 * </p>
 *
 * @author yangwei
 * @since 2023-03-23 18:09:44
 */
@Slf4j
@Setter
@Getter
public class GroovyBigDatasourceApiPageableAdapterConfigProvider implements BigDatasourceApiPageableAdapterConfigProvider {


	@Override
	public boolean commandPageSupport(BigDatasourceApiPageableAdapterType apiPageableAdapterType) {
		return apiPageableAdapterType == BigDatasourceApiPageableAdapterType.groovy_template;
	}

	@SneakyThrows
	@Override
	public PageQueryCommand obtainCommandPageInfo(Object command,String commandPageEnjoyTemplate) {
		PageQueryCommand pageQueryCommand = new PageQueryCommand();
		TemplateRenderDataWrap<Object> objectTemplateRenderDataWrap = TemplateRenderDataWrap.create(command);
		Map<String, Object> objectMap = objectTemplateRenderDataWrap.toRenderMap();
		objectMap.put("pageQueryCommand", pageQueryCommand);
		Bindings bindings = GroovyTool.createBindings();
		bindings.putAll(objectMap);

		Object o = GroovyTool.compileAndEval(commandPageEnjoyTemplate, bindings, true);
		boolean b = o instanceof PageQueryCommand;
		if (!b) {
			throw new RuntimeException("groovy template must return a " + PageQueryCommand.class.getName() + " instance");
		}

		return pageQueryCommand;
	}

	@Override
	public boolean responsePageSupport(BigDatasourceApiPageableAdapterType apiPageableAdapterType) {
		return commandPageSupport(apiPageableAdapterType);
	}

	@SneakyThrows
	@Override
	public PageResponse obtainResponsePageInfo(Object rawResultData,String responsePageEnjoyTemplate) {
		PageResponse<Object> objectPageResponse = new PageResponse<>();
		TemplateRenderDataWrap<Object> objectTemplateRenderDataWrap = TemplateRenderDataWrap.create(rawResultData);
		Map<String, Object> objectMap = objectTemplateRenderDataWrap.toRenderMap();
		objectMap.put("pageResponse", objectPageResponse);
		Bindings bindings = GroovyTool.createBindings();
		bindings.putAll(objectMap);

		Object o = GroovyTool.compileAndEval(responsePageEnjoyTemplate, bindings, true);
		boolean b = o instanceof PageResponse;
		if (!b) {
			throw new RuntimeException("groovy template must return a " + PageResponse.class.getName() + " instance");
		}
		return objectPageResponse;
	}


	public static GroovyBigDatasourceApiPageableAdapterConfigProvider create() {
		GroovyBigDatasourceApiPageableAdapterConfigProvider enjoyBigDatasourceApiPageableAdapterConfigProvider = new GroovyBigDatasourceApiPageableAdapterConfigProvider();
		return enjoyBigDatasourceApiPageableAdapterConfigProvider;
	}
}
