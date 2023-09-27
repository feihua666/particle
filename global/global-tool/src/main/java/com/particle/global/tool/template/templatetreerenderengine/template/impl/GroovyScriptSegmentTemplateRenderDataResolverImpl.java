package com.particle.global.tool.template.templatetreerenderengine.template.impl;

import com.particle.global.tool.script.GroovyTool;
import com.particle.global.tool.template.templatetreerenderengine.template.ISegmentTemplateRenderDataResolver;
import com.particle.global.tool.template.templatetreerenderengine.template.ResolveRenderDataParam;
import lombok.extern.slf4j.Slf4j;

import javax.script.Bindings;
import java.util.Map;
import java.util.Optional;

/**
 * <p>
 * 基于groovy脚本的实现
 * </p>
 *
 * @author yangwei
 * @since 2023-09-08 14:45
 */
@Slf4j
public class GroovyScriptSegmentTemplateRenderDataResolverImpl implements ISegmentTemplateRenderDataResolver {

	private Map<String,Object> addtionalBindings;

	@Override
	public Object resolveRenderData(ResolveRenderDataParam resolveRenderDataParam) throws Exception {

		String script = Optional.ofNullable(resolveRenderDataParam.getSegmentTemplate().getExtConfig())
				.map(item -> item.getDataGroovyScript()).orElse(null);

		if (script == null) {
			log.warn("no groovy script found. null will be returned");
			return null;
		}

		Bindings bindings = GroovyTool.createBindings();
		bindings.putAll(resolveRenderDataParam.getRenderDataMap());
		if (addtionalBindings != null) {
			bindings.putAll(addtionalBindings);
		}
		Object o = GroovyTool.compileAndEval(resolveRenderDataParam.getSegmentTemplate().getExtConfig().getDataGroovyScript(), bindings, true);
		return o;
	}

	public void setAddtionalBindings(Map<String, Object> addtionalBindings) {
		this.addtionalBindings = addtionalBindings;
	}
}
