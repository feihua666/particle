package com.particle.lowcode.infrastructure.generator.service;

import com.particle.lowcode.infrastructure.generator.dto.LowcodeSegmentTemplateRenderParam;
import com.particle.lowcode.infrastructure.generator.dto.LowcodeSegmentTemplateRenderResult;

/**
 * <p>
 * 代码片段模板渲染服务
 * </p>
 *
 * @author yangwei
 * @since 2023-02-14 15:08
 */
public interface ILowcodeSegmentTemplateRenderService {


	/**
	 * 渲染模板片段
	 * @param lowcodeSegmentTemplateRenderParam
	 * @return
	 */
	LowcodeSegmentTemplateRenderResult render(LowcodeSegmentTemplateRenderParam lowcodeSegmentTemplateRenderParam);
}
