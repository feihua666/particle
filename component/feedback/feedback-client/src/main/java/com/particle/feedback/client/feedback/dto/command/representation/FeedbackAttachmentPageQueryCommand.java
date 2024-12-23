package com.particle.feedback.client.feedback.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 意见反馈附件 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:43:09
 */
@Data
@Schema
public class FeedbackAttachmentPageQueryCommand extends AbstractBasePageQueryCommand {



    @Schema(description = "反馈id")
    private Long feedbackId;


    @Schema(description = "附件名称")
    private String attachmentName;


    @Schema(description = "附件地址")
    private String attachmentUrl;









}
