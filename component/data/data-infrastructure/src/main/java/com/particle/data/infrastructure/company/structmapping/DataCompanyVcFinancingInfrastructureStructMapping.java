package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyVcFinancingDO;
import com.particle.data.domain.company.DataCompanyVcFinancing;
import com.particle.data.domain.company.DataCompanyVcFinancingId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业融资 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:43
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyVcFinancingInfrastructureStructMapping {
	public static DataCompanyVcFinancingInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyVcFinancingInfrastructureStructMapping.class );

	protected DataCompanyVcFinancingId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyVcFinancingId.of(id);
	}
	protected Long map(DataCompanyVcFinancingId dataCompanyVcFinancingId){
		if (dataCompanyVcFinancingId == null) {
			return null;
		}
		return dataCompanyVcFinancingId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyVcFinancingInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyVcFinancingDO
	 * @return
	 */
	public abstract DataCompanyVcFinancing dataCompanyVcFinancingDOToDataCompanyVcFinancing(@MappingTarget DataCompanyVcFinancing dataCompanyVcFinancing,DataCompanyVcFinancingDO dataCompanyVcFinancingDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyVcFinancingInfrastructureStructMapping#map(DataCompanyVcFinancingId)}
	 * @param dataCompanyVcFinancing
	 * @return
	 */
	public abstract DataCompanyVcFinancingDO dataCompanyVcFinancingToDataCompanyVcFinancingDO(DataCompanyVcFinancing dataCompanyVcFinancing);

}
