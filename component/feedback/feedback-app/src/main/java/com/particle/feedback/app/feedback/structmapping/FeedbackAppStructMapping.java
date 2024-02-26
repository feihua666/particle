package com.particle.feedback.app.feedback.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.feedback.client.feedback.dto.data.FeedbackVO;
import com.particle.feedback.domain.feedback.Feedback;
import com.particle.feedback.domain.feedback.FeedbackId;
import com.particle.feedback.infrastructure.feedback.dos.FeedbackDO;
import com.particle.feedback.client.feedback.dto.command.representation.FeedbackPageQueryCommand;
import com.particle.feedback.client.feedback.dto.command.representation.FeedbackQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 意见反馈 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:42:51
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class FeedbackAppStructMapping  implements IBaseQueryCommandMapStruct<FeedbackDO>{
	public static FeedbackAppStructMapping instance = Mappers.getMapper( FeedbackAppStructMapping.class );

	protected Long map(FeedbackId feedbackId){
		if (feedbackId == null) {
			return null;
		}
		return feedbackId.getId();
	}
	/**
	 * 意见反馈领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link FeedbackAppStructMapping#map(FeedbackId)}
	 * @param feedback
	 * @return
	 */
	public abstract FeedbackVO toFeedbackVO(Feedback feedback);


	/**
	 * 数据对象转视图对象
	 * @param feedbackDO
	 * @return
	 */
	public abstract FeedbackVO feedbackDOToFeedbackVO(FeedbackDO feedbackDO);

	/**
	 * 批量转换
	 * @param feedbackDOs
	 * @return
	 */
	public abstract List<FeedbackVO> feedbackDOsToFeedbackVOs(List<FeedbackDO> feedbackDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<FeedbackVO> infrastructurePageToPageResponse(Page<FeedbackDO> page) {
		return PageResponse.of(feedbackDOsToFeedbackVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public FeedbackDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof FeedbackPageQueryCommand) {
			return pageQueryCommandToDO((FeedbackPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof FeedbackQueryListCommand) {
			return queryListCommandToDO(((FeedbackQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract FeedbackDO pageQueryCommandToDO(FeedbackPageQueryCommand feedbackPageQueryCommand);

	public abstract FeedbackDO queryListCommandToDO(FeedbackQueryListCommand feedbackQueryListCommand);
}
