package com.particle.feedback.domain.reply;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 意见反馈回复附件 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:49:16
 */
public class FeedbackReplyAttachmentId extends Id {

	public FeedbackReplyAttachmentId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 意见反馈回复附件 领域模型id
	 * @param id
	 * @return
	 */
	public static FeedbackReplyAttachmentId of(Long id){
		return new FeedbackReplyAttachmentId(id);
	}
}
