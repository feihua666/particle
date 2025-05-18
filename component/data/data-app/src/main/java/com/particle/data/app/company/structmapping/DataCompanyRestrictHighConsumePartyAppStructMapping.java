package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyRestrictHighConsumePartyPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyRestrictHighConsumePartyQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyRestrictHighConsumePartyVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyRestrictHighConsumePartyExWarehouseVO;
import com.particle.data.domain.company.DataCompanyRestrictHighConsumeParty;
import com.particle.data.domain.company.DataCompanyRestrictHighConsumePartyId;
import com.particle.data.infrastructure.company.dos.DataCompanyRestrictHighConsumePartyDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业限制高消费当事人 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:32
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyRestrictHighConsumePartyAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyRestrictHighConsumePartyDO>{
	public static DataCompanyRestrictHighConsumePartyAppStructMapping instance = Mappers.getMapper( DataCompanyRestrictHighConsumePartyAppStructMapping.class );

	protected Long map(DataCompanyRestrictHighConsumePartyId dataCompanyRestrictHighConsumePartyId){
		if (dataCompanyRestrictHighConsumePartyId == null) {
			return null;
		}
		return dataCompanyRestrictHighConsumePartyId.getId();
	}
	/**
	 * 企业限制高消费当事人领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyRestrictHighConsumePartyAppStructMapping#map(DataCompanyRestrictHighConsumePartyId)}
	 * @param dataCompanyRestrictHighConsumeParty
	 * @return
	 */
	public abstract DataCompanyRestrictHighConsumePartyVO toDataCompanyRestrictHighConsumePartyVO(DataCompanyRestrictHighConsumeParty dataCompanyRestrictHighConsumeParty);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyRestrictHighConsumePartyDO
	 * @return
	 */
	public abstract DataCompanyRestrictHighConsumePartyVO dataCompanyRestrictHighConsumePartyDOToDataCompanyRestrictHighConsumePartyVO(DataCompanyRestrictHighConsumePartyDO dataCompanyRestrictHighConsumePartyDO);

	/**
	 * 批量转换
	 * @param dataCompanyRestrictHighConsumePartyDOs
	 * @return
	 */
	public abstract List<DataCompanyRestrictHighConsumePartyVO> dataCompanyRestrictHighConsumePartyDOsToDataCompanyRestrictHighConsumePartyVOs(List<DataCompanyRestrictHighConsumePartyDO> dataCompanyRestrictHighConsumePartyDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyRestrictHighConsumePartyVO> infrastructurePageToPageResponse(Page<DataCompanyRestrictHighConsumePartyDO> page) {
		return PageResponse.of(dataCompanyRestrictHighConsumePartyDOsToDataCompanyRestrictHighConsumePartyVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyRestrictHighConsumePartyDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyRestrictHighConsumePartyPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyRestrictHighConsumePartyPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyRestrictHighConsumePartyQueryListCommand) {
			return queryListCommandToDO(((DataCompanyRestrictHighConsumePartyQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyRestrictHighConsumePartyDO pageQueryCommandToDO(DataCompanyRestrictHighConsumePartyPageQueryCommand dataCompanyRestrictHighConsumePartyPageQueryCommand);

	public abstract DataCompanyRestrictHighConsumePartyDO queryListCommandToDO(DataCompanyRestrictHighConsumePartyQueryListCommand dataCompanyRestrictHighConsumePartyQueryListCommand);
    public abstract DataCompanyRestrictHighConsumePartyExWarehouseVO dataCompanyRestrictHighConsumePartyDOToDataCompanyRestrictHighConsumePartyExWarehouseVO(DataCompanyRestrictHighConsumePartyDO dataCompanyRestrictHighConsumePartyDO);
    public abstract List<DataCompanyRestrictHighConsumePartyExWarehouseVO> dataCompanyRestrictHighConsumePartyDOsToDataCompanyRestrictHighConsumePartyExWarehouseVOs(List<DataCompanyRestrictHighConsumePartyDO> dataCompanyRestrictHighConsumePartyDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyRestrictHighConsumePartyExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyRestrictHighConsumePartyDO> page) {
		return PageResponse.of(dataCompanyRestrictHighConsumePartyDOsToDataCompanyRestrictHighConsumePartyExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
