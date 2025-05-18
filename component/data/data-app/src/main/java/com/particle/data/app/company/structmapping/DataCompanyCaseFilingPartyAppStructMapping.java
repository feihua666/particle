package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyCaseFilingPartyPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyCaseFilingPartyQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyCaseFilingPartyVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCaseFilingPartyExWarehouseVO;
import com.particle.data.domain.company.DataCompanyCaseFilingParty;
import com.particle.data.domain.company.DataCompanyCaseFilingPartyId;
import com.particle.data.infrastructure.company.dos.DataCompanyCaseFilingPartyDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业立案信息当事人 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:50
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyCaseFilingPartyAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyCaseFilingPartyDO>{
	public static DataCompanyCaseFilingPartyAppStructMapping instance = Mappers.getMapper( DataCompanyCaseFilingPartyAppStructMapping.class );

	protected Long map(DataCompanyCaseFilingPartyId dataCompanyCaseFilingPartyId){
		if (dataCompanyCaseFilingPartyId == null) {
			return null;
		}
		return dataCompanyCaseFilingPartyId.getId();
	}
	/**
	 * 企业立案信息当事人领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyCaseFilingPartyAppStructMapping#map(DataCompanyCaseFilingPartyId)}
	 * @param dataCompanyCaseFilingParty
	 * @return
	 */
	public abstract DataCompanyCaseFilingPartyVO toDataCompanyCaseFilingPartyVO(DataCompanyCaseFilingParty dataCompanyCaseFilingParty);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyCaseFilingPartyDO
	 * @return
	 */
	public abstract DataCompanyCaseFilingPartyVO dataCompanyCaseFilingPartyDOToDataCompanyCaseFilingPartyVO(DataCompanyCaseFilingPartyDO dataCompanyCaseFilingPartyDO);

	/**
	 * 批量转换
	 * @param dataCompanyCaseFilingPartyDOs
	 * @return
	 */
	public abstract List<DataCompanyCaseFilingPartyVO> dataCompanyCaseFilingPartyDOsToDataCompanyCaseFilingPartyVOs(List<DataCompanyCaseFilingPartyDO> dataCompanyCaseFilingPartyDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyCaseFilingPartyVO> infrastructurePageToPageResponse(Page<DataCompanyCaseFilingPartyDO> page) {
		return PageResponse.of(dataCompanyCaseFilingPartyDOsToDataCompanyCaseFilingPartyVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyCaseFilingPartyDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyCaseFilingPartyPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyCaseFilingPartyPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyCaseFilingPartyQueryListCommand) {
			return queryListCommandToDO(((DataCompanyCaseFilingPartyQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyCaseFilingPartyDO pageQueryCommandToDO(DataCompanyCaseFilingPartyPageQueryCommand dataCompanyCaseFilingPartyPageQueryCommand);

	public abstract DataCompanyCaseFilingPartyDO queryListCommandToDO(DataCompanyCaseFilingPartyQueryListCommand dataCompanyCaseFilingPartyQueryListCommand);
    public abstract DataCompanyCaseFilingPartyExWarehouseVO dataCompanyCaseFilingPartyDOToDataCompanyCaseFilingPartyExWarehouseVO(DataCompanyCaseFilingPartyDO dataCompanyCaseFilingPartyDO);
    public abstract List<DataCompanyCaseFilingPartyExWarehouseVO> dataCompanyCaseFilingPartyDOsToDataCompanyCaseFilingPartyExWarehouseVOs(List<DataCompanyCaseFilingPartyDO> dataCompanyCaseFilingPartyDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyCaseFilingPartyExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyCaseFilingPartyDO> page) {
		return PageResponse.of(dataCompanyCaseFilingPartyDOsToDataCompanyCaseFilingPartyExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
