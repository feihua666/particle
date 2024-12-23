package com.particle.lowcode.client.generator.dto.command;

import com.particle.common.client.dto.command.IdCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * <p>
 * 低代码片段模板复制指令
 * </p>
 *
 * @author yangwei
 * @since 2023-02-17
 */
@Data
@Schema
public class LowcodeSegmentTemplateCopyCommand extends IdCommand {

	@Schema(description = "父级id,可以重新指定一个父级id")
	private Long parentId;

	@Schema(description = "是否包含所有子孙")
	private Boolean isIncludeAllChildren;

	@Schema(title = "关键字文本替换",description = "text=newText,text1=newText1")
	private String keyWordReplace;

	@NotEmpty(message = "组内分隔字符 不能为空")
	@Schema(title = "组内分隔字符",description = "如：=",example = "=")
	private String seperator;

	@NotEmpty(message = "组分隔字符 不能为空")
	@Schema(title = "组分隔字符",description = "如：,",example = ",")
	private String groupSeperator;
}
