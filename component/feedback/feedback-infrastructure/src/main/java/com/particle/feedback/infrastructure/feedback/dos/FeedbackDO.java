package com.particle.feedback.infrastructure.feedback.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * <p>
 * 意见反馈表
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:42:51
 */
@Data
@TableName("component_feedback")
public class FeedbackDO extends BaseDO {

    /**
    * 问题建议内容
    */
    private String feedbackContent;

    /**
    * 问题建议时间
    */
    private LocalDateTime feedbackAt;

    /**
    * 问题建议用户id，如果存在登录用户，记录提建议的用户id
    */
    private Long feedbackUserId;

    /**
    * 联系姓名
    */
    private String contactUsername;

    /**
    * 联系电话
    */
    private String contactTelephone;

    /**
    * 联系邮箱
    */
    private String contactEmail;

    /**
    * 是否联系电话为手机号
    */
    private Boolean isContactTelephoneMobile;

    /**
    * 是否已处理
    */
    private Boolean isHandle;

    /**
    * 处理结果，描述文本
    */
    private String handleResult;

	/**
	 * 处理时间
	 */
	private LocalDateTime handleAt;

    /**
    * 第一条意见反馈id，主要串联起一个话题
    */
    private Long firstFeedbackId;

    /**
    * 描述
    */
    private String remark;


}
