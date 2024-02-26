package com.particle.feedback.infrastructure.reply.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
/**
 * <p>
 * 意见反馈回复附件表
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:49:16
 */
@Data
@TableName("component_feedback_reply_attachment")
public class FeedbackReplyAttachmentDO extends BaseDO {

    /**
    * 反馈回复id
    */
    private Long feedbackReplyId;

    /**
    * 附件名称
    */
    private String attachmentName;

    /**
    * 附件地址
    */
    private String attachmentUrl;


}
