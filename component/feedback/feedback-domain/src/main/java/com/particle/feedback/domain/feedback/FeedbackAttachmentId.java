package com.particle.feedback.domain.feedback;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 意见反馈附件 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:43:09
 */
public class FeedbackAttachmentId extends Id {

	public FeedbackAttachmentId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 意见反馈附件 领域模型id
	 * @param id
	 * @return
	 */
	public static FeedbackAttachmentId of(Long id){
		return new FeedbackAttachmentId(id);
	}
}
