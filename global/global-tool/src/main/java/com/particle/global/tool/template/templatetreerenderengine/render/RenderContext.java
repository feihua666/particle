package com.particle.global.tool.template.templatetreerenderengine.render;

import com.particle.global.tool.template.templatetreerenderengine.config.ConfigData;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 渲染上下文本数据
 * </p>
 *
 * @author yangwei
 * @since 2023-02-10 16:43
 */
@Data
public class RenderContext {

	/**
	 * 配置数据
	 */
	private ConfigData configData;

	/**
	 * 模板渲染上下文
	 */
	private List<TemplateRenderContext> templateRenderContexts = new ArrayList<>();

	/**
	 * 添加模板渲染上下文
	 * @param templateRenderContext
	 */
	public void addTemplateRenderContext(TemplateRenderContext templateRenderContext) {
		templateRenderContexts.add(templateRenderContext);
	}
}
