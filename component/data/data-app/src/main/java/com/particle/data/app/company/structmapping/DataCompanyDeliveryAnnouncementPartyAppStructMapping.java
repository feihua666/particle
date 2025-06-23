package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyDeliveryAnnouncementPartyPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyDeliveryAnnouncementPartyQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyDeliveryAnnouncementPartyVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyDeliveryAnnouncementPartyExWarehouseVO;
import com.particle.data.domain.company.DataCompanyDeliveryAnnouncementParty;
import com.particle.data.domain.company.DataCompanyDeliveryAnnouncementPartyId;
import com.particle.data.infrastructure.company.dos.DataCompanyDeliveryAnnouncementPartyDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业送达公告当事人 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:33
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyDeliveryAnnouncementPartyAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyDeliveryAnnouncementPartyDO>{
	public static DataCompanyDeliveryAnnouncementPartyAppStructMapping instance = Mappers.getMapper( DataCompanyDeliveryAnnouncementPartyAppStructMapping.class );

	protected Long map(DataCompanyDeliveryAnnouncementPartyId dataCompanyDeliveryAnnouncementPartyId){
		if (dataCompanyDeliveryAnnouncementPartyId == null) {
			return null;
		}
		return dataCompanyDeliveryAnnouncementPartyId.getId();
	}
	/**
	 * 企业送达公告当事人领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyDeliveryAnnouncementPartyAppStructMapping#map(DataCompanyDeliveryAnnouncementPartyId)}
	 * @param dataCompanyDeliveryAnnouncementParty
	 * @return
	 */
	public abstract DataCompanyDeliveryAnnouncementPartyVO toDataCompanyDeliveryAnnouncementPartyVO(DataCompanyDeliveryAnnouncementParty dataCompanyDeliveryAnnouncementParty);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyDeliveryAnnouncementPartyDO
	 * @return
	 */
	public abstract DataCompanyDeliveryAnnouncementPartyVO dataCompanyDeliveryAnnouncementPartyDOToDataCompanyDeliveryAnnouncementPartyVO(DataCompanyDeliveryAnnouncementPartyDO dataCompanyDeliveryAnnouncementPartyDO);

	/**
	 * 批量转换
	 * @param dataCompanyDeliveryAnnouncementPartyDOs
	 * @return
	 */
	public abstract List<DataCompanyDeliveryAnnouncementPartyVO> dataCompanyDeliveryAnnouncementPartyDOsToDataCompanyDeliveryAnnouncementPartyVOs(List<DataCompanyDeliveryAnnouncementPartyDO> dataCompanyDeliveryAnnouncementPartyDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyDeliveryAnnouncementPartyVO> infrastructurePageToPageResponse(Page<DataCompanyDeliveryAnnouncementPartyDO> page) {
		return PageResponse.of(dataCompanyDeliveryAnnouncementPartyDOsToDataCompanyDeliveryAnnouncementPartyVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyDeliveryAnnouncementPartyDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyDeliveryAnnouncementPartyPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyDeliveryAnnouncementPartyPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyDeliveryAnnouncementPartyQueryListCommand) {
			return queryListCommandToDO(((DataCompanyDeliveryAnnouncementPartyQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyDeliveryAnnouncementPartyDO pageQueryCommandToDO(DataCompanyDeliveryAnnouncementPartyPageQueryCommand dataCompanyDeliveryAnnouncementPartyPageQueryCommand);

	public abstract DataCompanyDeliveryAnnouncementPartyDO queryListCommandToDO(DataCompanyDeliveryAnnouncementPartyQueryListCommand dataCompanyDeliveryAnnouncementPartyQueryListCommand);
    public abstract DataCompanyDeliveryAnnouncementPartyExWarehouseVO dataCompanyDeliveryAnnouncementPartyDOToDataCompanyDeliveryAnnouncementPartyExWarehouseVO(DataCompanyDeliveryAnnouncementPartyDO dataCompanyDeliveryAnnouncementPartyDO);
    public abstract List<DataCompanyDeliveryAnnouncementPartyExWarehouseVO> dataCompanyDeliveryAnnouncementPartyDOsToDataCompanyDeliveryAnnouncementPartyExWarehouseVOs(List<DataCompanyDeliveryAnnouncementPartyDO> dataCompanyDeliveryAnnouncementPartyDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyDeliveryAnnouncementPartyExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyDeliveryAnnouncementPartyDO> page) {
		return PageResponse.of(dataCompanyDeliveryAnnouncementPartyDOsToDataCompanyDeliveryAnnouncementPartyExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
