package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyCaseFilingDO;
import com.particle.data.domain.company.DataCompanyCaseFiling;
import com.particle.data.domain.company.DataCompanyCaseFilingId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业立案信息 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:36
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyCaseFilingInfrastructureStructMapping {
	public static DataCompanyCaseFilingInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyCaseFilingInfrastructureStructMapping.class );

	protected DataCompanyCaseFilingId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyCaseFilingId.of(id);
	}
	protected Long map(DataCompanyCaseFilingId dataCompanyCaseFilingId){
		if (dataCompanyCaseFilingId == null) {
			return null;
		}
		return dataCompanyCaseFilingId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyCaseFilingInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyCaseFilingDO
	 * @return
	 */
	public abstract DataCompanyCaseFiling dataCompanyCaseFilingDOToDataCompanyCaseFiling(@MappingTarget DataCompanyCaseFiling dataCompanyCaseFiling,DataCompanyCaseFilingDO dataCompanyCaseFilingDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyCaseFilingInfrastructureStructMapping#map(DataCompanyCaseFilingId)}
	 * @param dataCompanyCaseFiling
	 * @return
	 */
	public abstract DataCompanyCaseFilingDO dataCompanyCaseFilingToDataCompanyCaseFilingDO(DataCompanyCaseFiling dataCompanyCaseFiling);

}
