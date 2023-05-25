package com.particle.message.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.global.validation.props.PropValid;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
@ApiModel
public class MessageTemplateCreateCommand extends AbstractBaseCommand {



    @ApiModelProperty(value = "编码")
    private String code;


    @NotEmpty(message = "模板名称 不能为空")
    @ApiModelProperty(value = "模板名称",required = true)
    private String name;


    @ApiModelProperty(value = "消息标题模板")
    private String titleTpl;


    @ApiModelProperty(value = "消息标题模板说明")
    private String titleTplMemo;


    @PropValid.DependCondition(message = "消息内容模板不能为空",dependProp = "isGroup",ifEqual = "false")
    @ApiModelProperty(value = "消息内容模板,模板时必填")
    private String contentTpl;


    @ApiModelProperty(value = "消息内容模板说明")
    private String contentTplMemo;

	@ApiModelProperty("详细配置，可根据不同的通知内容细化配置，如短信内容")
	private String contentDetailJson;

	@ApiModelProperty("内容是否为html，1=是，0=否")
	private Boolean isContentHtml = false;


    @ApiModelProperty(value = "消息模板分类")
    private Long typeDictId;


    @NotNull(message = "是否为分组 不能为空")
    @ApiModelProperty(value = "是否为分组，true=分组，false=模板",required = true)
    private Boolean isGroup;


    @ApiModelProperty(value = "分组标识")
    private String groupFlag;


    @ApiModelProperty(value = "分组标识备忘")
    private String groupFlagMemo;


    @ApiModelProperty(value = "标签")
    private String tags;


    @ApiModelProperty(value = "描述")
    private String remark;


    @NotNull(message = "排序 不能为空")
    @ApiModelProperty(value = "排序",required = true)
    private Integer seq;

    @ApiModelProperty("父级id")
    private Long parentId;
}