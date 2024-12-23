package com.particle.feedback.infrastructure.reply.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * <p>
 * 意见反馈回复表
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:43:38
 */
@Data
@TableName("component_feedback_reply")
public class FeedbackReplyDO extends BaseDO {

    /**
    * 意见反馈id
    */
    private Long feedbackId;

    /**
    * 回复内容
    */
    private String replyContent;

    /**
    * 回复时间
    */
    private LocalDateTime replyAt;

    /**
    * 回复用户id
    */
    private Long replyUserId;

    /**
    * 是否提问题建议用户已读
    */
    private Boolean isFeedbackUserRead;

	/**
	 * 用户已读时间
	 */
	private LocalDateTime feedbackUserReadAt;

    /**
    * 提问题建议用户评价，字典id
    */
    private Long feedbackUserRateDictId;

    /**
    * 提问题建议用户评价内容
    */
    private String feedbackUserRateMemo;

	/**
	 * 用户评价时间
	 */
	private LocalDateTime feedbackUserRateAt;

    /**
    * 第一条意见反馈id，主要串联起一个话题
    */
    private Long firstFeedbackId;

    /**
    * 描述
    */
    private String remark;


}
