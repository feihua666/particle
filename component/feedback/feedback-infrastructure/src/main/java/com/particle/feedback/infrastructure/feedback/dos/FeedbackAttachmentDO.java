package com.particle.feedback.infrastructure.feedback.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import lombok.Data;
/**
 * <p>
 * 意见反馈附件表
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:43:09
 */
@Data
@TableName("component_feedback_attachment")
public class FeedbackAttachmentDO extends BaseDO {

    /**
    * 反馈id
    */
    private Long feedbackId;

    /**
    * 附件名称
    */
    private String attachmentName;

    /**
    * 附件地址
    */
    private String attachmentUrl;


}
