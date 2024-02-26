package com.particle.feedback.client.reply.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 意见反馈回复附件 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:49:16
 */
@Data
@Schema
public class FeedbackReplyAttachmentCreateCommand extends AbstractBaseCommand {



    @NotNull(message = "反馈回复id 不能为空")
        @Schema(description = "反馈回复id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long feedbackReplyId;


    @NotEmpty(message = "附件名称 不能为空")
        @Schema(description = "附件名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String attachmentName;


    @NotEmpty(message = "附件地址 不能为空")
        @Schema(description = "附件地址",requiredMode = Schema.RequiredMode.REQUIRED)
    private String attachmentUrl;









}
