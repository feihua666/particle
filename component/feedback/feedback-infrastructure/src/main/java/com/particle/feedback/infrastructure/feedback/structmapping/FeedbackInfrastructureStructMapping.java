package com.particle.feedback.infrastructure.feedback.structmapping;

import com.particle.feedback.infrastructure.feedback.dos.FeedbackDO;
import com.particle.feedback.domain.feedback.Feedback;
import com.particle.feedback.domain.feedback.FeedbackId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 意见反馈 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:42:51
 */
@Mapper
public abstract class FeedbackInfrastructureStructMapping {
	public static FeedbackInfrastructureStructMapping instance = Mappers.getMapper( FeedbackInfrastructureStructMapping.class );

	protected FeedbackId map(Long id){
		if (id == null) {
			return null;
		}
		return FeedbackId.of(id);
	}
	protected Long map(FeedbackId feedbackId){
		if (feedbackId == null) {
			return null;
		}
		return feedbackId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link FeedbackInfrastructureStructMapping#map(java.lang.Long)}
	 * @param feedbackDO
	 * @return
	 */
	public abstract Feedback feedbackDOToFeedback(@MappingTarget Feedback feedback,FeedbackDO feedbackDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link FeedbackInfrastructureStructMapping#map(FeedbackId)}
	 * @param feedback
	 * @return
	 */
	public abstract FeedbackDO feedbackToFeedbackDO(Feedback feedback);

}
