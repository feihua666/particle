package com.particle.report.infrastructure.template.dto;

import com.particle.global.dto.basic.Value;
import com.particle.global.tool.template.templatetreerenderengine.render.RenderContext;
import lombok.Data;

import java.io.File;

/**
 * <p>
 * 报告片段模板渲染结果
 * </p>
 *
 * @author yangwei
 * @since 2023-09-07 14:47:59
 */
@Data
public class ReportSegmentTemplateRenderResult extends Value {


	/**
	 * 名称渲染结果文本
	 */
	private String templateNameContentResult;

	/**
	 * 内容渲染结果文本
	 */
	private String templateContentResult;

	/**
	 * 名称渲染结果文件句柄
	 */
	private File templateNameContentResultFile;


	/**
	 * 渲染上下文
	 */
	private RenderContext renderContext;

	public static ReportSegmentTemplateRenderResult create(String templateNameContentResult,
														   String templateContentResult,
														   File templateNameContentResultFile,RenderContext renderContext){
		ReportSegmentTemplateRenderResult reportSegmentTemplateRenderResult = new ReportSegmentTemplateRenderResult();
		reportSegmentTemplateRenderResult.setTemplateNameContentResult(templateNameContentResult);
		reportSegmentTemplateRenderResult.setTemplateContentResult(templateContentResult);
		reportSegmentTemplateRenderResult.setRenderContext(renderContext);

		reportSegmentTemplateRenderResult.setTemplateNameContentResultFile(templateNameContentResultFile);
		return reportSegmentTemplateRenderResult;
	}
}
