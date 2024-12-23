package com.particle.feedback.infrastructure.reply.structmapping;

import com.particle.feedback.domain.reply.FeedbackReply;
import com.particle.feedback.domain.reply.FeedbackReplyId;
import com.particle.feedback.infrastructure.reply.dos.FeedbackReplyDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 意见反馈回复 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:43:38
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class FeedbackReplyInfrastructureStructMapping {
	public static FeedbackReplyInfrastructureStructMapping instance = Mappers.getMapper( FeedbackReplyInfrastructureStructMapping.class );

	protected FeedbackReplyId map(Long id){
		if (id == null) {
			return null;
		}
		return FeedbackReplyId.of(id);
	}
	protected Long map(FeedbackReplyId feedbackReplyId){
		if (feedbackReplyId == null) {
			return null;
		}
		return feedbackReplyId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link FeedbackReplyInfrastructureStructMapping#map(java.lang.Long)}
	 * @param feedbackReplyDO
	 * @return
	 */
	public abstract FeedbackReply feedbackReplyDOToFeedbackReply(@MappingTarget FeedbackReply feedbackReply,FeedbackReplyDO feedbackReplyDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link FeedbackReplyInfrastructureStructMapping#map(FeedbackReplyId)}
	 * @param feedbackReply
	 * @return
	 */
	public abstract FeedbackReplyDO feedbackReplyToFeedbackReplyDO(FeedbackReply feedbackReply);

}
