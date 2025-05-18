package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyCourtAnnouncementPartyPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyCourtAnnouncementPartyQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyCourtAnnouncementPartyVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCourtAnnouncementPartyExWarehouseVO;
import com.particle.data.domain.company.DataCompanyCourtAnnouncementParty;
import com.particle.data.domain.company.DataCompanyCourtAnnouncementPartyId;
import com.particle.data.infrastructure.company.dos.DataCompanyCourtAnnouncementPartyDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业法院公告当事人 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:44
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyCourtAnnouncementPartyAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyCourtAnnouncementPartyDO>{
	public static DataCompanyCourtAnnouncementPartyAppStructMapping instance = Mappers.getMapper( DataCompanyCourtAnnouncementPartyAppStructMapping.class );

	protected Long map(DataCompanyCourtAnnouncementPartyId dataCompanyCourtAnnouncementPartyId){
		if (dataCompanyCourtAnnouncementPartyId == null) {
			return null;
		}
		return dataCompanyCourtAnnouncementPartyId.getId();
	}
	/**
	 * 企业法院公告当事人领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyCourtAnnouncementPartyAppStructMapping#map(DataCompanyCourtAnnouncementPartyId)}
	 * @param dataCompanyCourtAnnouncementParty
	 * @return
	 */
	public abstract DataCompanyCourtAnnouncementPartyVO toDataCompanyCourtAnnouncementPartyVO(DataCompanyCourtAnnouncementParty dataCompanyCourtAnnouncementParty);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyCourtAnnouncementPartyDO
	 * @return
	 */
	public abstract DataCompanyCourtAnnouncementPartyVO dataCompanyCourtAnnouncementPartyDOToDataCompanyCourtAnnouncementPartyVO(DataCompanyCourtAnnouncementPartyDO dataCompanyCourtAnnouncementPartyDO);

	/**
	 * 批量转换
	 * @param dataCompanyCourtAnnouncementPartyDOs
	 * @return
	 */
	public abstract List<DataCompanyCourtAnnouncementPartyVO> dataCompanyCourtAnnouncementPartyDOsToDataCompanyCourtAnnouncementPartyVOs(List<DataCompanyCourtAnnouncementPartyDO> dataCompanyCourtAnnouncementPartyDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyCourtAnnouncementPartyVO> infrastructurePageToPageResponse(Page<DataCompanyCourtAnnouncementPartyDO> page) {
		return PageResponse.of(dataCompanyCourtAnnouncementPartyDOsToDataCompanyCourtAnnouncementPartyVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyCourtAnnouncementPartyDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyCourtAnnouncementPartyPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyCourtAnnouncementPartyPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyCourtAnnouncementPartyQueryListCommand) {
			return queryListCommandToDO(((DataCompanyCourtAnnouncementPartyQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyCourtAnnouncementPartyDO pageQueryCommandToDO(DataCompanyCourtAnnouncementPartyPageQueryCommand dataCompanyCourtAnnouncementPartyPageQueryCommand);

	public abstract DataCompanyCourtAnnouncementPartyDO queryListCommandToDO(DataCompanyCourtAnnouncementPartyQueryListCommand dataCompanyCourtAnnouncementPartyQueryListCommand);
    public abstract DataCompanyCourtAnnouncementPartyExWarehouseVO dataCompanyCourtAnnouncementPartyDOToDataCompanyCourtAnnouncementPartyExWarehouseVO(DataCompanyCourtAnnouncementPartyDO dataCompanyCourtAnnouncementPartyDO);
    public abstract List<DataCompanyCourtAnnouncementPartyExWarehouseVO> dataCompanyCourtAnnouncementPartyDOsToDataCompanyCourtAnnouncementPartyExWarehouseVOs(List<DataCompanyCourtAnnouncementPartyDO> dataCompanyCourtAnnouncementPartyDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyCourtAnnouncementPartyExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyCourtAnnouncementPartyDO> page) {
		return PageResponse.of(dataCompanyCourtAnnouncementPartyDOsToDataCompanyCourtAnnouncementPartyExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
