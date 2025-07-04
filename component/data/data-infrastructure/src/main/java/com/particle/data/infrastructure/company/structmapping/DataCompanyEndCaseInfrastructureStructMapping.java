package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyEndCaseDO;
import com.particle.data.domain.company.DataCompanyEndCase;
import com.particle.data.domain.company.DataCompanyEndCaseId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业终本案件 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:57
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyEndCaseInfrastructureStructMapping {
	public static DataCompanyEndCaseInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyEndCaseInfrastructureStructMapping.class );

	protected DataCompanyEndCaseId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyEndCaseId.of(id);
	}
	protected Long map(DataCompanyEndCaseId dataCompanyEndCaseId){
		if (dataCompanyEndCaseId == null) {
			return null;
		}
		return dataCompanyEndCaseId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyEndCaseInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyEndCaseDO
	 * @return
	 */
	public abstract DataCompanyEndCase dataCompanyEndCaseDOToDataCompanyEndCase(@MappingTarget DataCompanyEndCase dataCompanyEndCase,DataCompanyEndCaseDO dataCompanyEndCaseDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyEndCaseInfrastructureStructMapping#map(DataCompanyEndCaseId)}
	 * @param dataCompanyEndCase
	 * @return
	 */
	public abstract DataCompanyEndCaseDO dataCompanyEndCaseToDataCompanyEndCaseDO(DataCompanyEndCase dataCompanyEndCase);

}
