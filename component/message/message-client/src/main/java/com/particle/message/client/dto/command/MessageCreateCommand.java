package com.particle.message.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * <p>
 * 消息 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:32:30
 */
@Data
@ApiModel
public class MessageCreateCommand extends AbstractBaseCommand {



    @NotEmpty(message = "消息标题 不能为空")
    @ApiModelProperty(value = "消息标题",required = true)
    private String title;


    @ApiModelProperty(value = "消息简短内容")
    private String shortContent;


    @NotEmpty(message = "消息内容 不能为空")
    @ApiModelProperty(value = "消息内容",required = true)
    private String content;

	@ApiModelProperty("内容是否为html，1=是，0=否")
	private Boolean isContentHtml;


    @ApiModelProperty(value = "业务内容json")
    private String businessDataJson;


    @ApiModelProperty(value = "业务数据id")
    private Long businessId;


    @ApiModelProperty(value = "消息分类")
    private Long typeDictId;


    @ApiModelProperty("是否为系统消息，1=是，0=否")
    private Boolean isSystem = false;

	@ApiModelProperty("消息模板id，用来追踪是哪个模板")
	private Long messageTemplateId;


}