package com.particle.lowcode.infrastructure.generator.dto;

import com.particle.global.dto.basic.Value;
import lombok.Data;

import java.io.File;

/**
 * <p>
 * 低代码片段模板渲染结果
 * </p>
 *
 * @author yangwei
 * @since 2023-02-14 15:18
 */
@Data
public class LowcodeSegmentTemplateRenderResult extends Value {


	/**
	 * 名称渲染结果文本
	 */
	private String templateNameContentResult;

	/**
	 * 渲染结果文本
	 */
	private String templateContentResult;

	/**
	 * 名称渲染结果文件句柄
	 */
	private File templateNameContentResultFile;


	public static LowcodeSegmentTemplateRenderResult create( String templateNameContentResult,
															 String templateContentResult,
															 File templateNameContentResultFile){
		LowcodeSegmentTemplateRenderResult lowcodeSegmentTemplateRenderResult = new LowcodeSegmentTemplateRenderResult();
		lowcodeSegmentTemplateRenderResult.setTemplateNameContentResult(templateNameContentResult);
		lowcodeSegmentTemplateRenderResult.setTemplateContentResult(templateContentResult);

		lowcodeSegmentTemplateRenderResult.setTemplateNameContentResultFile(templateNameContentResultFile);
		return lowcodeSegmentTemplateRenderResult;
	}
}
