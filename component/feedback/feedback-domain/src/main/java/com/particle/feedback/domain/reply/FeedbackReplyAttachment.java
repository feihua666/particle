package com.particle.feedback.domain.reply;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 意见反馈回复附件 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:49:16
 */
@Data
@Entity
public class FeedbackReplyAttachment extends AggreateRoot {

    private FeedbackReplyAttachmentId id;

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



    /**
     * 创建意见反馈回复附件领域模型对象
     * @return 意见反馈回复附件领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static FeedbackReplyAttachment create(){
        return DomainFactory.create(FeedbackReplyAttachment.class);
    }
}
