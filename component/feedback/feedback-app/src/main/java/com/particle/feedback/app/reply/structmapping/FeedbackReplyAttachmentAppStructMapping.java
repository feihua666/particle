package com.particle.feedback.app.reply.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.feedback.client.reply.dto.data.FeedbackReplyAttachmentVO;
import com.particle.feedback.domain.reply.FeedbackReplyAttachment;
import com.particle.feedback.domain.reply.FeedbackReplyAttachmentId;
import com.particle.feedback.infrastructure.reply.dos.FeedbackReplyAttachmentDO;
import com.particle.feedback.client.reply.dto.command.representation.FeedbackReplyAttachmentPageQueryCommand;
import com.particle.feedback.client.reply.dto.command.representation.FeedbackReplyAttachmentQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 意见反馈回复附件 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:49:16
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class FeedbackReplyAttachmentAppStructMapping  implements IBaseQueryCommandMapStruct<FeedbackReplyAttachmentDO>{
	public static FeedbackReplyAttachmentAppStructMapping instance = Mappers.getMapper( FeedbackReplyAttachmentAppStructMapping.class );

	protected Long map(FeedbackReplyAttachmentId feedbackReplyAttachmentId){
		if (feedbackReplyAttachmentId == null) {
			return null;
		}
		return feedbackReplyAttachmentId.getId();
	}
	/**
	 * 意见反馈回复附件领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link FeedbackReplyAttachmentAppStructMapping#map(FeedbackReplyAttachmentId)}
	 * @param feedbackReplyAttachment
	 * @return
	 */
	public abstract FeedbackReplyAttachmentVO toFeedbackReplyAttachmentVO(FeedbackReplyAttachment feedbackReplyAttachment);


	/**
	 * 数据对象转视图对象
	 * @param feedbackReplyAttachmentDO
	 * @return
	 */
	public abstract FeedbackReplyAttachmentVO feedbackReplyAttachmentDOToFeedbackReplyAttachmentVO(FeedbackReplyAttachmentDO feedbackReplyAttachmentDO);

	/**
	 * 批量转换
	 * @param feedbackReplyAttachmentDOs
	 * @return
	 */
	public abstract List<FeedbackReplyAttachmentVO> feedbackReplyAttachmentDOsToFeedbackReplyAttachmentVOs(List<FeedbackReplyAttachmentDO> feedbackReplyAttachmentDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<FeedbackReplyAttachmentVO> infrastructurePageToPageResponse(Page<FeedbackReplyAttachmentDO> page) {
		return PageResponse.of(feedbackReplyAttachmentDOsToFeedbackReplyAttachmentVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public FeedbackReplyAttachmentDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof FeedbackReplyAttachmentPageQueryCommand) {
			return pageQueryCommandToDO((FeedbackReplyAttachmentPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof FeedbackReplyAttachmentQueryListCommand) {
			return queryListCommandToDO(((FeedbackReplyAttachmentQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract FeedbackReplyAttachmentDO pageQueryCommandToDO(FeedbackReplyAttachmentPageQueryCommand feedbackReplyAttachmentPageQueryCommand);

	public abstract FeedbackReplyAttachmentDO queryListCommandToDO(FeedbackReplyAttachmentQueryListCommand feedbackReplyAttachmentQueryListCommand);
}
