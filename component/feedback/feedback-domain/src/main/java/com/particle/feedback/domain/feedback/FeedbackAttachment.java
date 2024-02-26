package com.particle.feedback.domain.feedback;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 意见反馈附件 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:43:09
 */
@Data
@Entity
public class FeedbackAttachment extends AggreateRoot {

    private FeedbackAttachmentId id;

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



    /**
     * 创建意见反馈附件领域模型对象
     * @return 意见反馈附件领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static FeedbackAttachment create(){
        return DomainFactory.create(FeedbackAttachment.class);
    }
}
