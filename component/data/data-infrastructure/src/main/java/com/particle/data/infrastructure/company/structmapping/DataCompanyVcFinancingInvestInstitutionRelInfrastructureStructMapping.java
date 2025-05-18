package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyVcFinancingInvestInstitutionRelDO;
import com.particle.data.domain.company.DataCompanyVcFinancingInvestInstitutionRel;
import com.particle.data.domain.company.DataCompanyVcFinancingInvestInstitutionRelId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业融资历史投资机构关系 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:28
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyVcFinancingInvestInstitutionRelInfrastructureStructMapping {
	public static DataCompanyVcFinancingInvestInstitutionRelInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyVcFinancingInvestInstitutionRelInfrastructureStructMapping.class );

	protected DataCompanyVcFinancingInvestInstitutionRelId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyVcFinancingInvestInstitutionRelId.of(id);
	}
	protected Long map(DataCompanyVcFinancingInvestInstitutionRelId dataCompanyVcFinancingInvestInstitutionRelId){
		if (dataCompanyVcFinancingInvestInstitutionRelId == null) {
			return null;
		}
		return dataCompanyVcFinancingInvestInstitutionRelId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyVcFinancingInvestInstitutionRelInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyVcFinancingInvestInstitutionRelDO
	 * @return
	 */
	public abstract DataCompanyVcFinancingInvestInstitutionRel dataCompanyVcFinancingInvestInstitutionRelDOToDataCompanyVcFinancingInvestInstitutionRel(@MappingTarget DataCompanyVcFinancingInvestInstitutionRel dataCompanyVcFinancingInvestInstitutionRel,DataCompanyVcFinancingInvestInstitutionRelDO dataCompanyVcFinancingInvestInstitutionRelDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyVcFinancingInvestInstitutionRelInfrastructureStructMapping#map(DataCompanyVcFinancingInvestInstitutionRelId)}
	 * @param dataCompanyVcFinancingInvestInstitutionRel
	 * @return
	 */
	public abstract DataCompanyVcFinancingInvestInstitutionRelDO dataCompanyVcFinancingInvestInstitutionRelToDataCompanyVcFinancingInvestInstitutionRelDO(DataCompanyVcFinancingInvestInstitutionRel dataCompanyVcFinancingInvestInstitutionRel);

}
