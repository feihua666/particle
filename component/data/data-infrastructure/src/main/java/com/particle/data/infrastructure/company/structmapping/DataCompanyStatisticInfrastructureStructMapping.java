package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyStatisticDO;
import com.particle.data.domain.company.DataCompanyStatistic;
import com.particle.data.domain.company.DataCompanyStatisticId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业统计 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-04 15:53:01
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyStatisticInfrastructureStructMapping {
	public static DataCompanyStatisticInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyStatisticInfrastructureStructMapping.class );

	protected DataCompanyStatisticId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyStatisticId.of(id);
	}
	protected Long map(DataCompanyStatisticId dataCompanyStatisticId){
		if (dataCompanyStatisticId == null) {
			return null;
		}
		return dataCompanyStatisticId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyStatisticInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyStatisticDO
	 * @return
	 */
	public abstract DataCompanyStatistic dataCompanyStatisticDOToDataCompanyStatistic(@MappingTarget DataCompanyStatistic dataCompanyStatistic,DataCompanyStatisticDO dataCompanyStatisticDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyStatisticInfrastructureStructMapping#map(DataCompanyStatisticId)}
	 * @param dataCompanyStatistic
	 * @return
	 */
	public abstract DataCompanyStatisticDO dataCompanyStatisticToDataCompanyStatisticDO(DataCompanyStatistic dataCompanyStatistic);

}
