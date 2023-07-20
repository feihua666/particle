package com.particle.lowcode.client.generator.dto.data;

import com.particle.global.dto.basic.VO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.File;

/**
 * <p>
 * 低代码片段模板渲染结果
 * </p>
 *
 * @author yangwei
 * @since 2023-02-14 17:03
 */
@Data
@Schema
public class LowcodeSegmentTemplateRenderVO extends VO {

	/**
	 * 名称渲染结果文本
	 */
	@Schema(description = "名称渲染结果文本")
	private String templateNameContentResult;

	/**
	 * 渲染结果文本
	 */
	@Schema(description = "渲染结果文本")
	private String templateContentResult;

	/**
	 * 名称渲染结果文件句柄
	 */
	@Schema(description = "名称渲染结果文件句柄,应该为文件绝对路径")
	private String templateNameContentResultFile;
}
