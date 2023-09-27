package com.particle.global.tool.template.templatetreerenderengine.template;

/**
 * <p>
 * 模板渲染数据获取
 * </p>
 *
 * @author yangwei
 * @since 2023-02-13 22:32
 */
public interface ISegmentTemplateRenderDataResolver {


	/**
	 * 自定义单个模板渲染数据，在模板中引用可以使用 trd 开头的句柄引用。如：#{trd.xxx}
	 * @param resolveRenderDataParam
	 * @return 建议一般返回一个 map 或一个pojo
	 */
	Object resolveRenderData(ResolveRenderDataParam resolveRenderDataParam) throws Exception;
}
