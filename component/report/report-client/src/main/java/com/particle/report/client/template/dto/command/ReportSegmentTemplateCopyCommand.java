package com.particle.report.client.template.dto.command;

import com.particle.common.client.dto.command.IdCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 报告片段模板复制指令
 * </p>
 *
 * @author yangwei
 * @since 2023-09-13 17:58:08
 */
@Data
@Schema
public class ReportSegmentTemplateCopyCommand extends IdCommand {

	@Schema(description = "父级id,可以重新指定一个父级id")
	private Long parentId;

	@Schema(description = "是否包含所有子孙")
	private Boolean isIncludeAllChildren;

	@Schema(description = "关键字文本替换",example = "text=newText,text1=newText1")
	private String keyWordReplace;
}