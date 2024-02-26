package com.particle.feedback.client.reply.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 意见反馈回复 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:43:38
 */
@Data
@Schema
public class FeedbackReplyCreateCommand extends AbstractBaseCommand {

    @NotNull(message = "意见反馈id 不能为空")
    @Schema(description = "意见反馈id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long feedbackId;


    @NotEmpty(message = "回复内容 不能为空")
    @Schema(description = "回复内容",requiredMode = Schema.RequiredMode.REQUIRED)
    private String replyContent;

    /**
     * 前端传值无效
     */
    @Schema(description = "回复用户id",hidden = true)
    private Long replyUserId;


    @Schema(description = "描述")
    private String remark;

    @Valid
    @Schema(description = "附件信息")
    private List<FeedbackReplyAttachmentCreateCommand> attachmentCreateCommands;
}