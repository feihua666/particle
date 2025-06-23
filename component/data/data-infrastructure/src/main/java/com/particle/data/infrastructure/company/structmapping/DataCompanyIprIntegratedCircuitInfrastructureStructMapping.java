package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyIprIntegratedCircuitDO;
import com.particle.data.domain.company.DataCompanyIprIntegratedCircuit;
import com.particle.data.domain.company.DataCompanyIprIntegratedCircuitId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业知识产权集成电路 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:28
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprIntegratedCircuitInfrastructureStructMapping {
	public static DataCompanyIprIntegratedCircuitInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyIprIntegratedCircuitInfrastructureStructMapping.class );

	protected DataCompanyIprIntegratedCircuitId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyIprIntegratedCircuitId.of(id);
	}
	protected Long map(DataCompanyIprIntegratedCircuitId dataCompanyIprIntegratedCircuitId){
		if (dataCompanyIprIntegratedCircuitId == null) {
			return null;
		}
		return dataCompanyIprIntegratedCircuitId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprIntegratedCircuitInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyIprIntegratedCircuitDO
	 * @return
	 */
	public abstract DataCompanyIprIntegratedCircuit dataCompanyIprIntegratedCircuitDOToDataCompanyIprIntegratedCircuit(@MappingTarget DataCompanyIprIntegratedCircuit dataCompanyIprIntegratedCircuit,DataCompanyIprIntegratedCircuitDO dataCompanyIprIntegratedCircuitDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprIntegratedCircuitInfrastructureStructMapping#map(DataCompanyIprIntegratedCircuitId)}
	 * @param dataCompanyIprIntegratedCircuit
	 * @return
	 */
	public abstract DataCompanyIprIntegratedCircuitDO dataCompanyIprIntegratedCircuitToDataCompanyIprIntegratedCircuitDO(DataCompanyIprIntegratedCircuit dataCompanyIprIntegratedCircuit);

}
