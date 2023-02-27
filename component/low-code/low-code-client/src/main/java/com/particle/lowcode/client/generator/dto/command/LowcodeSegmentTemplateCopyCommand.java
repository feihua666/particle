package com.particle.lowcode.client.generator.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.common.client.dto.command.IdCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 低代码片段模板复制指令
 * </p>
 *
 * @author yangwei
 * @since 2023-02-17
 */
@Data
@ApiModel
public class LowcodeSegmentTemplateCopyCommand extends IdCommand {

	@ApiModelProperty("父级id")
	private Long parentId;

	@ApiModelProperty("是否包含所有子孙，父级id不为空时有效")
	private Boolean isIncludeAllChildren;

	@ApiModelProperty(value = "关键字文本替换",example = "text=newText,text1=newText1")
	private String keyWordReplace;
}
