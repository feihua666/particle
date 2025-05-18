package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcFinancingInvestInstitutionRelPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcFinancingInvestInstitutionRelQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcFinancingInvestInstitutionRelVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcFinancingInvestInstitutionRelExWarehouseVO;
import com.particle.data.domain.company.DataCompanyVcFinancingInvestInstitutionRel;
import com.particle.data.domain.company.DataCompanyVcFinancingInvestInstitutionRelId;
import com.particle.data.infrastructure.company.dos.DataCompanyVcFinancingInvestInstitutionRelDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业融资历史投资机构关系 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:28
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyVcFinancingInvestInstitutionRelAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyVcFinancingInvestInstitutionRelDO>{
	public static DataCompanyVcFinancingInvestInstitutionRelAppStructMapping instance = Mappers.getMapper( DataCompanyVcFinancingInvestInstitutionRelAppStructMapping.class );

	protected Long map(DataCompanyVcFinancingInvestInstitutionRelId dataCompanyVcFinancingInvestInstitutionRelId){
		if (dataCompanyVcFinancingInvestInstitutionRelId == null) {
			return null;
		}
		return dataCompanyVcFinancingInvestInstitutionRelId.getId();
	}
	/**
	 * 企业融资历史投资机构关系领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyVcFinancingInvestInstitutionRelAppStructMapping#map(DataCompanyVcFinancingInvestInstitutionRelId)}
	 * @param dataCompanyVcFinancingInvestInstitutionRel
	 * @return
	 */
	public abstract DataCompanyVcFinancingInvestInstitutionRelVO toDataCompanyVcFinancingInvestInstitutionRelVO(DataCompanyVcFinancingInvestInstitutionRel dataCompanyVcFinancingInvestInstitutionRel);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyVcFinancingInvestInstitutionRelDO
	 * @return
	 */
	public abstract DataCompanyVcFinancingInvestInstitutionRelVO dataCompanyVcFinancingInvestInstitutionRelDOToDataCompanyVcFinancingInvestInstitutionRelVO(DataCompanyVcFinancingInvestInstitutionRelDO dataCompanyVcFinancingInvestInstitutionRelDO);

	/**
	 * 批量转换
	 * @param dataCompanyVcFinancingInvestInstitutionRelDOs
	 * @return
	 */
	public abstract List<DataCompanyVcFinancingInvestInstitutionRelVO> dataCompanyVcFinancingInvestInstitutionRelDOsToDataCompanyVcFinancingInvestInstitutionRelVOs(List<DataCompanyVcFinancingInvestInstitutionRelDO> dataCompanyVcFinancingInvestInstitutionRelDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyVcFinancingInvestInstitutionRelVO> infrastructurePageToPageResponse(Page<DataCompanyVcFinancingInvestInstitutionRelDO> page) {
		return PageResponse.of(dataCompanyVcFinancingInvestInstitutionRelDOsToDataCompanyVcFinancingInvestInstitutionRelVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyVcFinancingInvestInstitutionRelDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyVcFinancingInvestInstitutionRelPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyVcFinancingInvestInstitutionRelPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyVcFinancingInvestInstitutionRelQueryListCommand) {
			return queryListCommandToDO(((DataCompanyVcFinancingInvestInstitutionRelQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyVcFinancingInvestInstitutionRelDO pageQueryCommandToDO(DataCompanyVcFinancingInvestInstitutionRelPageQueryCommand dataCompanyVcFinancingInvestInstitutionRelPageQueryCommand);

	public abstract DataCompanyVcFinancingInvestInstitutionRelDO queryListCommandToDO(DataCompanyVcFinancingInvestInstitutionRelQueryListCommand dataCompanyVcFinancingInvestInstitutionRelQueryListCommand);
    public abstract DataCompanyVcFinancingInvestInstitutionRelExWarehouseVO dataCompanyVcFinancingInvestInstitutionRelDOToDataCompanyVcFinancingInvestInstitutionRelExWarehouseVO(DataCompanyVcFinancingInvestInstitutionRelDO dataCompanyVcFinancingInvestInstitutionRelDO);
    public abstract List<DataCompanyVcFinancingInvestInstitutionRelExWarehouseVO> dataCompanyVcFinancingInvestInstitutionRelDOsToDataCompanyVcFinancingInvestInstitutionRelExWarehouseVOs(List<DataCompanyVcFinancingInvestInstitutionRelDO> dataCompanyVcFinancingInvestInstitutionRelDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyVcFinancingInvestInstitutionRelExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyVcFinancingInvestInstitutionRelDO> page) {
		return PageResponse.of(dataCompanyVcFinancingInvestInstitutionRelDOsToDataCompanyVcFinancingInvestInstitutionRelExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
