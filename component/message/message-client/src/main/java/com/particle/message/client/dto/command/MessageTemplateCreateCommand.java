package com.particle.message.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.global.validation.props.PropValid;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 消息模板 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:34:49
 */
@PropValid
@Data
@Schema
public class MessageTemplateCreateCommand extends AbstractBaseCommand {



    @Schema(description = "编码")
    private String code;


    @NotEmpty(message = "模板名称 不能为空")
    @Schema(description = "模板名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @Schema(description = "消息标题模板")
    private String titleTpl;


    @Schema(description = "消息标题模板说明")
    private String titleTplMemo;


    @PropValid.DependCondition(message = "消息内容模板不能为空",dependProp = "isGroup",ifEqual = "false")
    @Schema(description = "消息内容模板,模板时必填")
    private String contentTpl;


    @Schema(description = "消息内容模板说明")
    private String contentTplMemo;

	@Schema(description = "详细配置，可根据不同的通知内容细化配置，如短信内容")
	private String contentDetailJson;

	@Schema(description = "内容是否为html，1=是，0=否")
	private Boolean isContentHtml = false;


    @Schema(description = "消息模板分类")
    private Long typeDictId;


    @NotNull(message = "是否为分组 不能为空")
    @Schema(description = "是否为分组，true=分组，false=模板",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isGroup;


    @Schema(description = "分组标识")
    private String groupFlag;


    @Schema(description = "分组标识备忘")
    private String groupFlagMemo;


    @Schema(description = "标签")
    private String tags;


    @Schema(description = "描述")
    private String remark;


    @NotNull(message = "排序 不能为空")
    @Schema(description = "排序",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer seq;

    @Schema(description = "父级id")
    private Long parentId;
}
