package com.particle.feedback.domain.reply;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 意见反馈回复 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:43:38
 */
public class FeedbackReplyId extends Id {

	public FeedbackReplyId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 意见反馈回复 领域模型id
	 * @param id
	 * @return
	 */
	public static FeedbackReplyId of(Long id){
		return new FeedbackReplyId(id);
	}
}
