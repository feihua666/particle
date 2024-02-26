package com.particle.feedback.client.feedback.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 意见反馈附件 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:43:09
 */
@Data
@Schema
public class FeedbackAttachmentCreateCommand extends AbstractBaseCommand {



    @NotNull(message = "反馈id 不能为空")
    @Schema(description = "反馈id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long feedbackId;


    @NotEmpty(message = "附件名称 不能为空")
    @Schema(description = "附件名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String attachmentName;


    @NotEmpty(message = "附件地址 不能为空")
    @Schema(description = "附件地址",requiredMode = Schema.RequiredMode.REQUIRED)
    private String attachmentUrl;


}
