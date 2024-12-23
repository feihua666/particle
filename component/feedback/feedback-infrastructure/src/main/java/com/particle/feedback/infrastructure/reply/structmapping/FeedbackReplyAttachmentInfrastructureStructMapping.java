package com.particle.feedback.infrastructure.reply.structmapping;

import com.particle.feedback.domain.reply.FeedbackReplyAttachment;
import com.particle.feedback.domain.reply.FeedbackReplyAttachmentId;
import com.particle.feedback.infrastructure.reply.dos.FeedbackReplyAttachmentDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 意见反馈回复附件 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:49:16
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class FeedbackReplyAttachmentInfrastructureStructMapping {
	public static FeedbackReplyAttachmentInfrastructureStructMapping instance = Mappers.getMapper( FeedbackReplyAttachmentInfrastructureStructMapping.class );

	protected FeedbackReplyAttachmentId map(Long id){
		if (id == null) {
			return null;
		}
		return FeedbackReplyAttachmentId.of(id);
	}
	protected Long map(FeedbackReplyAttachmentId feedbackReplyAttachmentId){
		if (feedbackReplyAttachmentId == null) {
			return null;
		}
		return feedbackReplyAttachmentId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link FeedbackReplyAttachmentInfrastructureStructMapping#map(java.lang.Long)}
	 * @param feedbackReplyAttachmentDO
	 * @return
	 */
	public abstract FeedbackReplyAttachment feedbackReplyAttachmentDOToFeedbackReplyAttachment(@MappingTarget FeedbackReplyAttachment feedbackReplyAttachment,FeedbackReplyAttachmentDO feedbackReplyAttachmentDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link FeedbackReplyAttachmentInfrastructureStructMapping#map(FeedbackReplyAttachmentId)}
	 * @param feedbackReplyAttachment
	 * @return
	 */
	public abstract FeedbackReplyAttachmentDO feedbackReplyAttachmentToFeedbackReplyAttachmentDO(FeedbackReplyAttachment feedbackReplyAttachment);

}
