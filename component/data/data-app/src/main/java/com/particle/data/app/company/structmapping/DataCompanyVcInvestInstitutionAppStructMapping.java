package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcInvestInstitutionPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcInvestInstitutionQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcInvestInstitutionVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcInvestInstitutionExWarehouseVO;
import com.particle.data.domain.company.DataCompanyVcInvestInstitution;
import com.particle.data.domain.company.DataCompanyVcInvestInstitutionId;
import com.particle.data.infrastructure.company.dos.DataCompanyVcInvestInstitutionDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业投资机构 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:13
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyVcInvestInstitutionAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyVcInvestInstitutionDO>{
	public static DataCompanyVcInvestInstitutionAppStructMapping instance = Mappers.getMapper( DataCompanyVcInvestInstitutionAppStructMapping.class );

	protected Long map(DataCompanyVcInvestInstitutionId dataCompanyVcInvestInstitutionId){
		if (dataCompanyVcInvestInstitutionId == null) {
			return null;
		}
		return dataCompanyVcInvestInstitutionId.getId();
	}
	/**
	 * 企业投资机构领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyVcInvestInstitutionAppStructMapping#map(DataCompanyVcInvestInstitutionId)}
	 * @param dataCompanyVcInvestInstitution
	 * @return
	 */
	public abstract DataCompanyVcInvestInstitutionVO toDataCompanyVcInvestInstitutionVO(DataCompanyVcInvestInstitution dataCompanyVcInvestInstitution);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyVcInvestInstitutionDO
	 * @return
	 */
	public abstract DataCompanyVcInvestInstitutionVO dataCompanyVcInvestInstitutionDOToDataCompanyVcInvestInstitutionVO(DataCompanyVcInvestInstitutionDO dataCompanyVcInvestInstitutionDO);

	/**
	 * 批量转换
	 * @param dataCompanyVcInvestInstitutionDOs
	 * @return
	 */
	public abstract List<DataCompanyVcInvestInstitutionVO> dataCompanyVcInvestInstitutionDOsToDataCompanyVcInvestInstitutionVOs(List<DataCompanyVcInvestInstitutionDO> dataCompanyVcInvestInstitutionDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyVcInvestInstitutionVO> infrastructurePageToPageResponse(Page<DataCompanyVcInvestInstitutionDO> page) {
		return PageResponse.of(dataCompanyVcInvestInstitutionDOsToDataCompanyVcInvestInstitutionVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyVcInvestInstitutionDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyVcInvestInstitutionPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyVcInvestInstitutionPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyVcInvestInstitutionQueryListCommand) {
			return queryListCommandToDO(((DataCompanyVcInvestInstitutionQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyVcInvestInstitutionDO pageQueryCommandToDO(DataCompanyVcInvestInstitutionPageQueryCommand dataCompanyVcInvestInstitutionPageQueryCommand);

	public abstract DataCompanyVcInvestInstitutionDO queryListCommandToDO(DataCompanyVcInvestInstitutionQueryListCommand dataCompanyVcInvestInstitutionQueryListCommand);
    public abstract DataCompanyVcInvestInstitutionExWarehouseVO dataCompanyVcInvestInstitutionDOToDataCompanyVcInvestInstitutionExWarehouseVO(DataCompanyVcInvestInstitutionDO dataCompanyVcInvestInstitutionDO);
    public abstract List<DataCompanyVcInvestInstitutionExWarehouseVO> dataCompanyVcInvestInstitutionDOsToDataCompanyVcInvestInstitutionExWarehouseVOs(List<DataCompanyVcInvestInstitutionDO> dataCompanyVcInvestInstitutionDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyVcInvestInstitutionExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyVcInvestInstitutionDO> page) {
		return PageResponse.of(dataCompanyVcInvestInstitutionDOsToDataCompanyVcInvestInstitutionExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
