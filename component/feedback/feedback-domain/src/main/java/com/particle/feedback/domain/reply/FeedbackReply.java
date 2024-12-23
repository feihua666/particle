package com.particle.feedback.domain.reply;

import com.particle.common.domain.AggreateRoot;
import com.particle.feedback.domain.feedback.Feedback;
import com.particle.feedback.domain.feedback.FeedbackId;
import com.particle.feedback.domain.feedback.gateway.FeedbackGateway;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * <p>
 * 意见反馈回复 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:43:38
 */
@Data
@Entity
public class FeedbackReply extends AggreateRoot {

    private FeedbackReplyId id;

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


    public void initForAdd() {
        this.replyAt = LocalDateTime.now();
        this.isFeedbackUserRead = false;

        Feedback byId = feedbackGateway.getById(FeedbackId.of(this.feedbackId));
        this.firstFeedbackId = Optional.ofNullable(byId).map(Feedback::getFirstFeedbackId).orElse(this.feedbackId);
    }

    @Autowired
    private FeedbackGateway feedbackGateway;

    /**
     * 创建意见反馈回复领域模型对象
     * @return 意见反馈回复领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static FeedbackReply create(){
        return DomainFactory.create(FeedbackReply.class);
    }
}
