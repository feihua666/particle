package com.particle.global.big.datasource.bigdatasource.api.config;

import com.particle.global.big.datasource.bigdatasource.api.BigDatasourceApiPageableAdapterConfigProvider;
import com.particle.global.big.datasource.bigdatasource.enums.BigDatasourceApiPageableAdapterType;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.tool.template.TemplateRenderDataWrap;
import com.particle.global.tool.template.TemplateTool;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * <p>
 * 基于 enjoy 模板
 * </p>
 *
 * @author yangwei
 * @since 2023-03-19 18:19
 */
@Setter
@Getter
public class EnjoyBigDatasourceApiPageableAdapterConfigProvider implements BigDatasourceApiPageableAdapterConfigProvider {


	@Override
	public boolean commandPageSupport(BigDatasourceApiPageableAdapterType apiPageableAdapterType) {
		return apiPageableAdapterType == BigDatasourceApiPageableAdapterType.enjoy_template;
	}

	@Override
	public PageQueryCommand obtainCommandPageInfo(Object command,String commandPageEnjoyTemplate) {
		PageQueryCommand pageQueryCommand = new PageQueryCommand();
		TemplateRenderDataWrap<Object> objectTemplateRenderDataWrap = TemplateRenderDataWrap.create(command);
		Map<String, Object> objectMap = objectTemplateRenderDataWrap.toRenderMap();
		objectMap.put("pageQueryCommand", pageQueryCommand);
		TemplateTool.render(commandPageEnjoyTemplate, objectMap);
		return pageQueryCommand;
	}

	@Override
	public boolean responsePageSupport(BigDatasourceApiPageableAdapterType apiPageableAdapterType) {
		return commandPageSupport(apiPageableAdapterType);
	}

	@Override
	public PageResponse obtainResponsePageInfo(Object rawResultData,String responsePageEnjoyTemplate) {
		PageResponse<Object> objectPageResponse = new PageResponse<>();
		TemplateRenderDataWrap<Object> objectTemplateRenderDataWrap = TemplateRenderDataWrap.create(rawResultData);
		Map<String, Object> objectMap = objectTemplateRenderDataWrap.toRenderMap();
		objectMap.put("pageResponse", objectPageResponse);
		TemplateTool.render(responsePageEnjoyTemplate, objectMap);
		return objectPageResponse;
	}


	public static EnjoyBigDatasourceApiPageableAdapterConfigProvider create() {
		EnjoyBigDatasourceApiPageableAdapterConfigProvider enjoyBigDatasourceApiPageableAdapterConfigProvider = new EnjoyBigDatasourceApiPageableAdapterConfigProvider();
		return enjoyBigDatasourceApiPageableAdapterConfigProvider;
	}
}
