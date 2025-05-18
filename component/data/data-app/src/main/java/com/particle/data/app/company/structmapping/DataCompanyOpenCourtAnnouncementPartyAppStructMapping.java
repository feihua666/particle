package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyOpenCourtAnnouncementPartyPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyOpenCourtAnnouncementPartyQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyOpenCourtAnnouncementPartyVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyOpenCourtAnnouncementPartyExWarehouseVO;
import com.particle.data.domain.company.DataCompanyOpenCourtAnnouncementParty;
import com.particle.data.domain.company.DataCompanyOpenCourtAnnouncementPartyId;
import com.particle.data.infrastructure.company.dos.DataCompanyOpenCourtAnnouncementPartyDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业开庭公告当事人 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:03
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyOpenCourtAnnouncementPartyAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyOpenCourtAnnouncementPartyDO>{
	public static DataCompanyOpenCourtAnnouncementPartyAppStructMapping instance = Mappers.getMapper( DataCompanyOpenCourtAnnouncementPartyAppStructMapping.class );

	protected Long map(DataCompanyOpenCourtAnnouncementPartyId dataCompanyOpenCourtAnnouncementPartyId){
		if (dataCompanyOpenCourtAnnouncementPartyId == null) {
			return null;
		}
		return dataCompanyOpenCourtAnnouncementPartyId.getId();
	}
	/**
	 * 企业开庭公告当事人领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyOpenCourtAnnouncementPartyAppStructMapping#map(DataCompanyOpenCourtAnnouncementPartyId)}
	 * @param dataCompanyOpenCourtAnnouncementParty
	 * @return
	 */
	public abstract DataCompanyOpenCourtAnnouncementPartyVO toDataCompanyOpenCourtAnnouncementPartyVO(DataCompanyOpenCourtAnnouncementParty dataCompanyOpenCourtAnnouncementParty);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyOpenCourtAnnouncementPartyDO
	 * @return
	 */
	public abstract DataCompanyOpenCourtAnnouncementPartyVO dataCompanyOpenCourtAnnouncementPartyDOToDataCompanyOpenCourtAnnouncementPartyVO(DataCompanyOpenCourtAnnouncementPartyDO dataCompanyOpenCourtAnnouncementPartyDO);

	/**
	 * 批量转换
	 * @param dataCompanyOpenCourtAnnouncementPartyDOs
	 * @return
	 */
	public abstract List<DataCompanyOpenCourtAnnouncementPartyVO> dataCompanyOpenCourtAnnouncementPartyDOsToDataCompanyOpenCourtAnnouncementPartyVOs(List<DataCompanyOpenCourtAnnouncementPartyDO> dataCompanyOpenCourtAnnouncementPartyDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyOpenCourtAnnouncementPartyVO> infrastructurePageToPageResponse(Page<DataCompanyOpenCourtAnnouncementPartyDO> page) {
		return PageResponse.of(dataCompanyOpenCourtAnnouncementPartyDOsToDataCompanyOpenCourtAnnouncementPartyVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyOpenCourtAnnouncementPartyDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyOpenCourtAnnouncementPartyPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyOpenCourtAnnouncementPartyPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyOpenCourtAnnouncementPartyQueryListCommand) {
			return queryListCommandToDO(((DataCompanyOpenCourtAnnouncementPartyQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyOpenCourtAnnouncementPartyDO pageQueryCommandToDO(DataCompanyOpenCourtAnnouncementPartyPageQueryCommand dataCompanyOpenCourtAnnouncementPartyPageQueryCommand);

	public abstract DataCompanyOpenCourtAnnouncementPartyDO queryListCommandToDO(DataCompanyOpenCourtAnnouncementPartyQueryListCommand dataCompanyOpenCourtAnnouncementPartyQueryListCommand);
    public abstract DataCompanyOpenCourtAnnouncementPartyExWarehouseVO dataCompanyOpenCourtAnnouncementPartyDOToDataCompanyOpenCourtAnnouncementPartyExWarehouseVO(DataCompanyOpenCourtAnnouncementPartyDO dataCompanyOpenCourtAnnouncementPartyDO);
    public abstract List<DataCompanyOpenCourtAnnouncementPartyExWarehouseVO> dataCompanyOpenCourtAnnouncementPartyDOsToDataCompanyOpenCourtAnnouncementPartyExWarehouseVOs(List<DataCompanyOpenCourtAnnouncementPartyDO> dataCompanyOpenCourtAnnouncementPartyDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyOpenCourtAnnouncementPartyExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyOpenCourtAnnouncementPartyDO> page) {
		return PageResponse.of(dataCompanyOpenCourtAnnouncementPartyDOsToDataCompanyOpenCourtAnnouncementPartyExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
