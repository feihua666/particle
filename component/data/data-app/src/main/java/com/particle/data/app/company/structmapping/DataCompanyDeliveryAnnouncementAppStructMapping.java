package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyDeliveryAnnouncementPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyDeliveryAnnouncementQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyDeliveryAnnouncementVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyDeliveryAnnouncementExWarehouseVO;
import com.particle.data.domain.company.DataCompanyDeliveryAnnouncement;
import com.particle.data.domain.company.DataCompanyDeliveryAnnouncementId;
import com.particle.data.infrastructure.company.dos.DataCompanyDeliveryAnnouncementDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业送达公告 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:06
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyDeliveryAnnouncementAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyDeliveryAnnouncementDO>{
	public static DataCompanyDeliveryAnnouncementAppStructMapping instance = Mappers.getMapper( DataCompanyDeliveryAnnouncementAppStructMapping.class );

	protected Long map(DataCompanyDeliveryAnnouncementId dataCompanyDeliveryAnnouncementId){
		if (dataCompanyDeliveryAnnouncementId == null) {
			return null;
		}
		return dataCompanyDeliveryAnnouncementId.getId();
	}
	/**
	 * 企业送达公告领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyDeliveryAnnouncementAppStructMapping#map(DataCompanyDeliveryAnnouncementId)}
	 * @param dataCompanyDeliveryAnnouncement
	 * @return
	 */
	public abstract DataCompanyDeliveryAnnouncementVO toDataCompanyDeliveryAnnouncementVO(DataCompanyDeliveryAnnouncement dataCompanyDeliveryAnnouncement);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyDeliveryAnnouncementDO
	 * @return
	 */
	public abstract DataCompanyDeliveryAnnouncementVO dataCompanyDeliveryAnnouncementDOToDataCompanyDeliveryAnnouncementVO(DataCompanyDeliveryAnnouncementDO dataCompanyDeliveryAnnouncementDO);

	/**
	 * 批量转换
	 * @param dataCompanyDeliveryAnnouncementDOs
	 * @return
	 */
	public abstract List<DataCompanyDeliveryAnnouncementVO> dataCompanyDeliveryAnnouncementDOsToDataCompanyDeliveryAnnouncementVOs(List<DataCompanyDeliveryAnnouncementDO> dataCompanyDeliveryAnnouncementDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyDeliveryAnnouncementVO> infrastructurePageToPageResponse(Page<DataCompanyDeliveryAnnouncementDO> page) {
		return PageResponse.of(dataCompanyDeliveryAnnouncementDOsToDataCompanyDeliveryAnnouncementVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyDeliveryAnnouncementDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyDeliveryAnnouncementPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyDeliveryAnnouncementPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyDeliveryAnnouncementQueryListCommand) {
			return queryListCommandToDO(((DataCompanyDeliveryAnnouncementQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyDeliveryAnnouncementDO pageQueryCommandToDO(DataCompanyDeliveryAnnouncementPageQueryCommand dataCompanyDeliveryAnnouncementPageQueryCommand);

	public abstract DataCompanyDeliveryAnnouncementDO queryListCommandToDO(DataCompanyDeliveryAnnouncementQueryListCommand dataCompanyDeliveryAnnouncementQueryListCommand);
    public abstract DataCompanyDeliveryAnnouncementExWarehouseVO dataCompanyDeliveryAnnouncementDOToDataCompanyDeliveryAnnouncementExWarehouseVO(DataCompanyDeliveryAnnouncementDO dataCompanyDeliveryAnnouncementDO);
    public abstract List<DataCompanyDeliveryAnnouncementExWarehouseVO> dataCompanyDeliveryAnnouncementDOsToDataCompanyDeliveryAnnouncementExWarehouseVOs(List<DataCompanyDeliveryAnnouncementDO> dataCompanyDeliveryAnnouncementDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyDeliveryAnnouncementExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyDeliveryAnnouncementDO> page) {
		return PageResponse.of(dataCompanyDeliveryAnnouncementDOsToDataCompanyDeliveryAnnouncementExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
