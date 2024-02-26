package com.particle.feedback.client.feedback.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * <p>
 * 意见反馈 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:42:51
 */
@Data
@Schema
public class FeedbackCreateCommand extends AbstractBaseCommand {

    @NotEmpty(message = "问题建议内容 不能为空")
    @Schema(description = "问题建议内容",requiredMode = Schema.RequiredMode.REQUIRED)
    private String feedbackContent;

    /**
     * 后台添加，前端添加无效
     */
    @Schema(description = "问题建议用户id",hidden = true)
    private Long feedbackUserId;


    @Schema(description = "联系姓名")
    private String contactUsername;


    @Schema(description = "联系电话")
    private String contactTelephone;


    @Schema(description = "联系邮箱")
    private String contactEmail;


    /**
     * 如果继续上一话题，如果上一话题 firstFeedbackId不为空，则为上一话题的 firstFeedbackId，否则为上一话题的 id
     */
    @Schema(description = "第一条意见反馈id")
    private Long firstFeedbackId;

    @Schema(description = "描述")
    private String remark;


    @Valid
    @Schema(description = "附件信息")
    private List<FeedbackAttachmentCreateCommand> attachmentCreateCommands;

}