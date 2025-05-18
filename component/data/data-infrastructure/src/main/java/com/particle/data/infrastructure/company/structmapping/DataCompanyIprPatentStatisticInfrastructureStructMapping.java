package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentStatisticDO;
import com.particle.data.domain.company.DataCompanyIprPatentStatistic;
import com.particle.data.domain.company.DataCompanyIprPatentStatisticId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业知识产权专利统计 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:36
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprPatentStatisticInfrastructureStructMapping {
	public static DataCompanyIprPatentStatisticInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyIprPatentStatisticInfrastructureStructMapping.class );

	protected DataCompanyIprPatentStatisticId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyIprPatentStatisticId.of(id);
	}
	protected Long map(DataCompanyIprPatentStatisticId dataCompanyIprPatentStatisticId){
		if (dataCompanyIprPatentStatisticId == null) {
			return null;
		}
		return dataCompanyIprPatentStatisticId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPatentStatisticInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyIprPatentStatisticDO
	 * @return
	 */
	public abstract DataCompanyIprPatentStatistic dataCompanyIprPatentStatisticDOToDataCompanyIprPatentStatistic(@MappingTarget DataCompanyIprPatentStatistic dataCompanyIprPatentStatistic,DataCompanyIprPatentStatisticDO dataCompanyIprPatentStatisticDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPatentStatisticInfrastructureStructMapping#map(DataCompanyIprPatentStatisticId)}
	 * @param dataCompanyIprPatentStatistic
	 * @return
	 */
	public abstract DataCompanyIprPatentStatisticDO dataCompanyIprPatentStatisticToDataCompanyIprPatentStatisticDO(DataCompanyIprPatentStatistic dataCompanyIprPatentStatistic);

}
