package com.particle.feedback.client.reply.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 意见反馈回复附件 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:49:16
 */
@Data
@Schema
public class FeedbackReplyAttachmentQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "反馈回复id")
    private Long feedbackReplyId;


    @Schema(description = "附件名称")
    private String attachmentName;


    @Schema(description = "附件地址")
    private String attachmentUrl;









}
