package com.particle.tracking.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.tracking.client.dto.command.representation.TrackingPagePageQueryCommand;
import com.particle.tracking.client.dto.command.representation.TrackingPageQueryListCommand;
import com.particle.tracking.client.dto.data.TrackingPageVO;
import com.particle.tracking.domain.TrackingPage;
import com.particle.tracking.domain.TrackingPageId;
import com.particle.tracking.infrastructure.dos.TrackingPageDO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 埋点页面 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:39:06
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class TrackingPageAppStructMapping  implements IBaseQueryCommandMapStruct<TrackingPageDO>{
	public static TrackingPageAppStructMapping instance = Mappers.getMapper( TrackingPageAppStructMapping.class );

	protected Long map(TrackingPageId trackingPageId){
		if (trackingPageId == null) {
			return null;
		}
		return trackingPageId.getId();
	}
	/**
	 * 埋点页面领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link TrackingPageAppStructMapping#map(TrackingPageId)}
	 * @param trackingPage
	 * @return
	 */
	public abstract TrackingPageVO toTrackingPageVO(TrackingPage trackingPage);


	/**
	 * 数据对象转视图对象
	 * @param trackingPageDO
	 * @return
	 */
	public abstract TrackingPageVO trackingPageDOToTrackingPageVO(TrackingPageDO trackingPageDO);

	/**
	 * 批量转换
	 * @param trackingPageDOs
	 * @return
	 */
	public abstract List<TrackingPageVO> trackingPageDOsToTrackingPageVOs(List<TrackingPageDO> trackingPageDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<TrackingPageVO> infrastructurePageToPageResponse(Page<TrackingPageDO> page) {
		return PageResponse.of(trackingPageDOsToTrackingPageVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public TrackingPageDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof TrackingPagePageQueryCommand) {
			return pageQueryCommandToDO((TrackingPagePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof TrackingPageQueryListCommand) {
			return queryListCommandToDO(((TrackingPageQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract TrackingPageDO pageQueryCommandToDO(TrackingPagePageQueryCommand trackingPagePageQueryCommand);

	public abstract TrackingPageDO queryListCommandToDO(TrackingPageQueryListCommand trackingPageQueryListCommand);
}
