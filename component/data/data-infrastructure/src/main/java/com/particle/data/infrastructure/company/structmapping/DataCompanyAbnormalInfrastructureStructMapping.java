package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyAbnormalDO;
import com.particle.data.domain.company.DataCompanyAbnormal;
import com.particle.data.domain.company.DataCompanyAbnormalId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业经营异常 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-05-29 10:47:31
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyAbnormalInfrastructureStructMapping {
	public static DataCompanyAbnormalInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyAbnormalInfrastructureStructMapping.class );

	protected DataCompanyAbnormalId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyAbnormalId.of(id);
	}
	protected Long map(DataCompanyAbnormalId dataCompanyAbnormalId){
		if (dataCompanyAbnormalId == null) {
			return null;
		}
		return dataCompanyAbnormalId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyAbnormalInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyAbnormalDO
	 * @return
	 */
	public abstract DataCompanyAbnormal dataCompanyAbnormalDOToDataCompanyAbnormal(@MappingTarget DataCompanyAbnormal dataCompanyAbnormal,DataCompanyAbnormalDO dataCompanyAbnormalDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyAbnormalInfrastructureStructMapping#map(DataCompanyAbnormalId)}
	 * @param dataCompanyAbnormal
	 * @return
	 */
	public abstract DataCompanyAbnormalDO dataCompanyAbnormalToDataCompanyAbnormalDO(DataCompanyAbnormal dataCompanyAbnormal);

}
