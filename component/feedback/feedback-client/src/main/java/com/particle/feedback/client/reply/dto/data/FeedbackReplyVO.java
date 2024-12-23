package com.particle.feedback.client.reply.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 意见反馈回复 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:43:38
 */
@Data
@Schema
public class FeedbackReplyVO extends AbstractBaseIdVO {

    @Schema(description = "意见反馈id")
    private Long feedbackId;

    @Schema(description = "回复内容")
    private String replyContent;

    @Schema(description = "回复时间")
    private LocalDateTime replyAt;

    @Schema(description = "回复用户id")
    private Long replyUserId;

    @TransBy(type = TransConstants.TRANS_USER_BY_ID,byFieldName = "replyUserId",mapValueField = "nickname")
    @Schema(description = "回复用户昵称")
    private String replyUserNickname;

    @TransBy(type = TransConstants.TRANS_USER_BY_ID,byFieldName = "replyUserId",mapValueField = "avatar")
    @Schema(description = "回复用户头像")
    private String replyUserAvatar;

    @Schema(description = "是否提问题建议用户已读")
    private Boolean isFeedbackUserRead;

	@Schema(description = "用户已读时间")
	private LocalDateTime feedbackUserReadAt;

    @Schema(description = "提问题建议用户评价")
    private Long feedbackUserRateDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "feedbackUserRateDictId",mapValueField = "name")
    @Schema(description = "提问题建议用户评价对应字典名称")
    private String feedbackUserRateDictName;

    @Schema(description = "提问题建议用户评价内容")
    private String feedbackUserRateMemo;

	@Schema(description = "用户评价时间")
	private LocalDateTime feedbackUserRateAt;

    @Schema(description = "第一条意见反馈id")
    private Long firstFeedbackId;

    @TransBy(type = TransConstants.TRANS_ATTACHMENT_BY_FEEDBACK_REPLY_ID,byFieldName = "id",mapValueCollectionJoin = false)
    @Schema(description = "附件信息")
    private List<FeedbackReplyAttachmentTransVO> attachmentVOs;

    @Schema(description = "描述")
    private String remark;



}
