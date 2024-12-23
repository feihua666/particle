package com.particle.global.tool.template.templatetreerenderengine.template;

import lombok.Data;

import java.util.Map;

/**
 * <p>
 * 模板渲染数据额外处理
 * </p>
 *
 * @author yangwei
 * @since 2023-09-08 10:42
 */
@Data
public class ResolveRenderDataParam {

	private SegmentTemplate segmentTemplate;
	private Map<String,Object> renderDataMap;

	public static ResolveRenderDataParam create(
			Map<String,Object> renderDataMap,
			SegmentTemplate segmentTemplate) {
		ResolveRenderDataParam resolveRenderDataParam = new ResolveRenderDataParam();
		resolveRenderDataParam.renderDataMap = renderDataMap;
		resolveRenderDataParam.segmentTemplate = segmentTemplate;
		return resolveRenderDataParam;
	}

}
