package com.particle.feedback.app.feedback.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.feedback.client.feedback.dto.command.representation.FeedbackAttachmentPageQueryCommand;
import com.particle.feedback.client.feedback.dto.command.representation.FeedbackAttachmentQueryListCommand;
import com.particle.feedback.client.feedback.dto.data.FeedbackAttachmentVO;
import com.particle.feedback.domain.feedback.FeedbackAttachment;
import com.particle.feedback.domain.feedback.FeedbackAttachmentId;
import com.particle.feedback.infrastructure.feedback.dos.FeedbackAttachmentDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 意见反馈附件 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:43:09
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class FeedbackAttachmentAppStructMapping  implements IBaseQueryCommandMapStruct<FeedbackAttachmentDO>{
	public static FeedbackAttachmentAppStructMapping instance = Mappers.getMapper( FeedbackAttachmentAppStructMapping.class );

	protected Long map(FeedbackAttachmentId feedbackAttachmentId){
		if (feedbackAttachmentId == null) {
			return null;
		}
		return feedbackAttachmentId.getId();
	}
	/**
	 * 意见反馈附件领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link FeedbackAttachmentAppStructMapping#map(FeedbackAttachmentId)}
	 * @param feedbackAttachment
	 * @return
	 */
	public abstract FeedbackAttachmentVO toFeedbackAttachmentVO(FeedbackAttachment feedbackAttachment);


	/**
	 * 数据对象转视图对象
	 * @param feedbackAttachmentDO
	 * @return
	 */
	public abstract FeedbackAttachmentVO feedbackAttachmentDOToFeedbackAttachmentVO(FeedbackAttachmentDO feedbackAttachmentDO);

	/**
	 * 批量转换
	 * @param feedbackAttachmentDOs
	 * @return
	 */
	public abstract List<FeedbackAttachmentVO> feedbackAttachmentDOsToFeedbackAttachmentVOs(List<FeedbackAttachmentDO> feedbackAttachmentDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<FeedbackAttachmentVO> infrastructurePageToPageResponse(Page<FeedbackAttachmentDO> page) {
		return PageResponse.of(feedbackAttachmentDOsToFeedbackAttachmentVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public FeedbackAttachmentDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof FeedbackAttachmentPageQueryCommand) {
			return pageQueryCommandToDO((FeedbackAttachmentPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof FeedbackAttachmentQueryListCommand) {
			return queryListCommandToDO(((FeedbackAttachmentQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract FeedbackAttachmentDO pageQueryCommandToDO(FeedbackAttachmentPageQueryCommand feedbackAttachmentPageQueryCommand);

	public abstract FeedbackAttachmentDO queryListCommandToDO(FeedbackAttachmentQueryListCommand feedbackAttachmentQueryListCommand);
}
