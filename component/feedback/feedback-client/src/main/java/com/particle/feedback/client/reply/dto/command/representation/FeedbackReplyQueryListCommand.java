package com.particle.feedback.client.reply.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 意见反馈回复 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:43:38
 */
@Data
@Schema
public class FeedbackReplyQueryListCommand extends AbstractBaseQueryCommand {

	@Schema(description = "用户评价时间")
	private LocalDateTime feedbackUserRateAt;



    @Schema(description = "意见反馈id")
    private Long feedbackId;



    @Schema(description = "回复时间")
    private LocalDateTime replyAt;
    

    @Schema(description = "回复用户id")
    private Long replyUserId;


    @Schema(description = "是否提问题建议用户已读")
    private Boolean isFeedbackUserRead;

	@Schema(description = "用户已读时间")
	private LocalDateTime feedbackUserReadAt;


    @Schema(description = "提问题建议用户评价")
    private Long feedbackUserRateDictId;



    @Schema(description = "第一条意见反馈id")
    private Long firstFeedbackId;










}