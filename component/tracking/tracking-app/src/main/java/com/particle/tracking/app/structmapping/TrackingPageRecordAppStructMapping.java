package com.particle.tracking.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.tracking.client.dto.command.TrackingPageRecordCreateCommand;
import com.particle.tracking.client.dto.command.TrackingPageRecordFrontCreateCommand;
import com.particle.tracking.client.dto.data.TrackingPageRecordVO;
import com.particle.tracking.domain.TrackingPageRecord;
import com.particle.tracking.domain.TrackingPageRecordId;
import com.particle.tracking.infrastructure.dos.TrackingPageRecordDO;
import com.particle.tracking.client.dto.command.representation.TrackingPageRecordPageQueryCommand;
import com.particle.tracking.client.dto.command.representation.TrackingPageRecordQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 页面埋点记录 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:44:01
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class TrackingPageRecordAppStructMapping  implements IBaseQueryCommandMapStruct<TrackingPageRecordDO>{
	public static TrackingPageRecordAppStructMapping instance = Mappers.getMapper( TrackingPageRecordAppStructMapping.class );

	protected Long map(TrackingPageRecordId trackingPageRecordId){
		if (trackingPageRecordId == null) {
			return null;
		}
		return trackingPageRecordId.getId();
	}
	/**
	 * 页面埋点记录领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link TrackingPageRecordAppStructMapping#map(TrackingPageRecordId)}
	 * @param trackingPageRecord
	 * @return
	 */
	public abstract TrackingPageRecordVO toTrackingPageRecordVO(TrackingPageRecord trackingPageRecord);


	/**
	 * 数据对象转视图对象
	 * @param trackingPageRecordDO
	 * @return
	 */
	public abstract TrackingPageRecordVO trackingPageRecordDOToTrackingPageRecordVO(TrackingPageRecordDO trackingPageRecordDO);

	/**
	 * 批量转换
	 * @param trackingPageRecordDOs
	 * @return
	 */
	public abstract List<TrackingPageRecordVO> trackingPageRecordDOsToTrackingPageRecordVOs(List<TrackingPageRecordDO> trackingPageRecordDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<TrackingPageRecordVO> infrastructurePageToPageResponse(Page<TrackingPageRecordDO> page) {
		return PageResponse.of(trackingPageRecordDOsToTrackingPageRecordVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public TrackingPageRecordDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof TrackingPageRecordPageQueryCommand) {
			return pageQueryCommandToDO((TrackingPageRecordPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof TrackingPageRecordQueryListCommand) {
			return queryListCommandToDO(((TrackingPageRecordQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract TrackingPageRecordDO pageQueryCommandToDO(TrackingPageRecordPageQueryCommand trackingPageRecordPageQueryCommand);

	public abstract TrackingPageRecordDO queryListCommandToDO(TrackingPageRecordQueryListCommand trackingPageRecordQueryListCommand);

	public abstract TrackingPageRecordCreateCommand trackingPageRecordFrontCreateCommandToTrackingPageRecordCreateCommand(TrackingPageRecordFrontCreateCommand trackingPageRecordFrontCreateCommand);
}
