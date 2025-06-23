package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyIprPlantVarietyChangeDO;
import com.particle.data.domain.company.DataCompanyIprPlantVarietyChange;
import com.particle.data.domain.company.DataCompanyIprPlantVarietyChangeId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业知识产权植物新品种变更信息 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:52
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprPlantVarietyChangeInfrastructureStructMapping {
	public static DataCompanyIprPlantVarietyChangeInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyIprPlantVarietyChangeInfrastructureStructMapping.class );

	protected DataCompanyIprPlantVarietyChangeId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyIprPlantVarietyChangeId.of(id);
	}
	protected Long map(DataCompanyIprPlantVarietyChangeId dataCompanyIprPlantVarietyChangeId){
		if (dataCompanyIprPlantVarietyChangeId == null) {
			return null;
		}
		return dataCompanyIprPlantVarietyChangeId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPlantVarietyChangeInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyIprPlantVarietyChangeDO
	 * @return
	 */
	public abstract DataCompanyIprPlantVarietyChange dataCompanyIprPlantVarietyChangeDOToDataCompanyIprPlantVarietyChange(@MappingTarget DataCompanyIprPlantVarietyChange dataCompanyIprPlantVarietyChange,DataCompanyIprPlantVarietyChangeDO dataCompanyIprPlantVarietyChangeDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPlantVarietyChangeInfrastructureStructMapping#map(DataCompanyIprPlantVarietyChangeId)}
	 * @param dataCompanyIprPlantVarietyChange
	 * @return
	 */
	public abstract DataCompanyIprPlantVarietyChangeDO dataCompanyIprPlantVarietyChangeToDataCompanyIprPlantVarietyChangeDO(DataCompanyIprPlantVarietyChange dataCompanyIprPlantVarietyChange);

}
