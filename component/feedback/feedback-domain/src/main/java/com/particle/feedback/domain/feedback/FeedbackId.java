package com.particle.feedback.domain.feedback;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 意见反馈 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:42:51
 */
public class FeedbackId extends Id {

	public FeedbackId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 意见反馈 领域模型id
	 * @param id
	 * @return
	 */
	public static FeedbackId of(Long id){
		return new FeedbackId(id);
	}
}
