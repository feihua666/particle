package com.particle.feedback.infrastructure.feedback.structmapping;

import com.particle.feedback.domain.feedback.FeedbackAttachment;
import com.particle.feedback.domain.feedback.FeedbackAttachmentId;
import com.particle.feedback.infrastructure.feedback.dos.FeedbackAttachmentDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 意见反馈附件 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:43:09
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class FeedbackAttachmentInfrastructureStructMapping {
	public static FeedbackAttachmentInfrastructureStructMapping instance = Mappers.getMapper( FeedbackAttachmentInfrastructureStructMapping.class );

	protected FeedbackAttachmentId map(Long id){
		if (id == null) {
			return null;
		}
		return FeedbackAttachmentId.of(id);
	}
	protected Long map(FeedbackAttachmentId feedbackAttachmentId){
		if (feedbackAttachmentId == null) {
			return null;
		}
		return feedbackAttachmentId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link FeedbackAttachmentInfrastructureStructMapping#map(java.lang.Long)}
	 * @param feedbackAttachmentDO
	 * @return
	 */
	public abstract FeedbackAttachment feedbackAttachmentDOToFeedbackAttachment(@MappingTarget FeedbackAttachment feedbackAttachment,FeedbackAttachmentDO feedbackAttachmentDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link FeedbackAttachmentInfrastructureStructMapping#map(FeedbackAttachmentId)}
	 * @param feedbackAttachment
	 * @return
	 */
	public abstract FeedbackAttachmentDO feedbackAttachmentToFeedbackAttachmentDO(FeedbackAttachment feedbackAttachment);

}
