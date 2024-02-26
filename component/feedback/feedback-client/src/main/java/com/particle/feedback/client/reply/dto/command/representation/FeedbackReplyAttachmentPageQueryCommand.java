package com.particle.feedback.client.reply.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 意见反馈回复附件 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:49:16
 */
@Data
@Schema
public class FeedbackReplyAttachmentPageQueryCommand extends AbstractBasePageQueryCommand {



    @Schema(description = "反馈回复id")
    private Long feedbackReplyId;


    @Schema(description = "附件名称")
    private String attachmentName;


    @Schema(description = "附件地址")
    private String attachmentUrl;









}
