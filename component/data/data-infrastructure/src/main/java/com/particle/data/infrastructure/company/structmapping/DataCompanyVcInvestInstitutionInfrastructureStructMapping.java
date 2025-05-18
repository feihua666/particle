package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyVcInvestInstitutionDO;
import com.particle.data.domain.company.DataCompanyVcInvestInstitution;
import com.particle.data.domain.company.DataCompanyVcInvestInstitutionId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业投资机构 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:13
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyVcInvestInstitutionInfrastructureStructMapping {
	public static DataCompanyVcInvestInstitutionInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyVcInvestInstitutionInfrastructureStructMapping.class );

	protected DataCompanyVcInvestInstitutionId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyVcInvestInstitutionId.of(id);
	}
	protected Long map(DataCompanyVcInvestInstitutionId dataCompanyVcInvestInstitutionId){
		if (dataCompanyVcInvestInstitutionId == null) {
			return null;
		}
		return dataCompanyVcInvestInstitutionId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyVcInvestInstitutionInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyVcInvestInstitutionDO
	 * @return
	 */
	public abstract DataCompanyVcInvestInstitution dataCompanyVcInvestInstitutionDOToDataCompanyVcInvestInstitution(@MappingTarget DataCompanyVcInvestInstitution dataCompanyVcInvestInstitution,DataCompanyVcInvestInstitutionDO dataCompanyVcInvestInstitutionDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyVcInvestInstitutionInfrastructureStructMapping#map(DataCompanyVcInvestInstitutionId)}
	 * @param dataCompanyVcInvestInstitution
	 * @return
	 */
	public abstract DataCompanyVcInvestInstitutionDO dataCompanyVcInvestInstitutionToDataCompanyVcInvestInstitutionDO(DataCompanyVcInvestInstitution dataCompanyVcInvestInstitution);

}
