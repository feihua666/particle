package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyRestrictHighConsumeDO;
import com.particle.data.domain.company.DataCompanyRestrictHighConsume;
import com.particle.data.domain.company.DataCompanyRestrictHighConsumeId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业限制高消费 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:19
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyRestrictHighConsumeInfrastructureStructMapping {
	public static DataCompanyRestrictHighConsumeInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyRestrictHighConsumeInfrastructureStructMapping.class );

	protected DataCompanyRestrictHighConsumeId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyRestrictHighConsumeId.of(id);
	}
	protected Long map(DataCompanyRestrictHighConsumeId dataCompanyRestrictHighConsumeId){
		if (dataCompanyRestrictHighConsumeId == null) {
			return null;
		}
		return dataCompanyRestrictHighConsumeId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyRestrictHighConsumeInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyRestrictHighConsumeDO
	 * @return
	 */
	public abstract DataCompanyRestrictHighConsume dataCompanyRestrictHighConsumeDOToDataCompanyRestrictHighConsume(@MappingTarget DataCompanyRestrictHighConsume dataCompanyRestrictHighConsume,DataCompanyRestrictHighConsumeDO dataCompanyRestrictHighConsumeDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyRestrictHighConsumeInfrastructureStructMapping#map(DataCompanyRestrictHighConsumeId)}
	 * @param dataCompanyRestrictHighConsume
	 * @return
	 */
	public abstract DataCompanyRestrictHighConsumeDO dataCompanyRestrictHighConsumeToDataCompanyRestrictHighConsumeDO(DataCompanyRestrictHighConsume dataCompanyRestrictHighConsume);

}
