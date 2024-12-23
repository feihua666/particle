package com.particle.feedback.app.reply.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.feedback.client.reply.dto.command.representation.FeedbackReplyPageQueryCommand;
import com.particle.feedback.client.reply.dto.command.representation.FeedbackReplyQueryListCommand;
import com.particle.feedback.client.reply.dto.data.FeedbackReplyVO;
import com.particle.feedback.domain.reply.FeedbackReply;
import com.particle.feedback.domain.reply.FeedbackReplyId;
import com.particle.feedback.infrastructure.reply.dos.FeedbackReplyDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 意见反馈回复 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:43:38
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class FeedbackReplyAppStructMapping  implements IBaseQueryCommandMapStruct<FeedbackReplyDO>{
	public static FeedbackReplyAppStructMapping instance = Mappers.getMapper( FeedbackReplyAppStructMapping.class );

	protected Long map(FeedbackReplyId feedbackReplyId){
		if (feedbackReplyId == null) {
			return null;
		}
		return feedbackReplyId.getId();
	}
	/**
	 * 意见反馈回复领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link FeedbackReplyAppStructMapping#map(FeedbackReplyId)}
	 * @param feedbackReply
	 * @return
	 */
	public abstract FeedbackReplyVO toFeedbackReplyVO(FeedbackReply feedbackReply);


	/**
	 * 数据对象转视图对象
	 * @param feedbackReplyDO
	 * @return
	 */
	public abstract FeedbackReplyVO feedbackReplyDOToFeedbackReplyVO(FeedbackReplyDO feedbackReplyDO);

	/**
	 * 批量转换
	 * @param feedbackReplyDOs
	 * @return
	 */
	public abstract List<FeedbackReplyVO> feedbackReplyDOsToFeedbackReplyVOs(List<FeedbackReplyDO> feedbackReplyDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<FeedbackReplyVO> infrastructurePageToPageResponse(Page<FeedbackReplyDO> page) {
		return PageResponse.of(feedbackReplyDOsToFeedbackReplyVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public FeedbackReplyDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof FeedbackReplyPageQueryCommand) {
			return pageQueryCommandToDO((FeedbackReplyPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof FeedbackReplyQueryListCommand) {
			return queryListCommandToDO(((FeedbackReplyQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract FeedbackReplyDO pageQueryCommandToDO(FeedbackReplyPageQueryCommand feedbackReplyPageQueryCommand);

	public abstract FeedbackReplyDO queryListCommandToDO(FeedbackReplyQueryListCommand feedbackReplyQueryListCommand);
}
