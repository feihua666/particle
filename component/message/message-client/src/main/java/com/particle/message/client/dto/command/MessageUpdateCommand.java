package com.particle.message.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import com.particle.global.light.share.mybatis.anno.SetNullWhenNull;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * <p>
 * 消息 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:32:30
 */
@Data
@Schema
public class MessageUpdateCommand extends AbstractBaseUpdateCommand {



    @NotEmpty(message = "消息标题 不能为空")
    @Schema(description = "消息标题",requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;


    @Schema(description = "消息简短内容")
    private String shortContent;


    @NotEmpty(message = "消息内容 不能为空")
    @Schema(description = "消息内容",requiredMode = Schema.RequiredMode.REQUIRED)
    private String content;

	@Schema(description = "内容是否为html，1=是，0=否")
	private Boolean isContentHtml;


    @Schema(description = "业务内容json")
    private String businessDataJson;

	@SetNullWhenNull
    @Schema(description = "业务数据id")
    private Long businessId;

	@SetNullWhenNull
    @Schema(description = "消息分类")
    private Long typeDictId;


    @Schema(description = "是否为系统消息，1=是，0=否")
    private Boolean isSystem = false;

	@SetNullWhenNull
	@Schema(description = "消息模板id，用来追踪是哪个模板")
	private Long messageTemplateId;


}
