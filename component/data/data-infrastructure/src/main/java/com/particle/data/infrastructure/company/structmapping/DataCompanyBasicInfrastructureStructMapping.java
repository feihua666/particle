package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyBasicDO;
import com.particle.data.domain.company.DataCompanyBasic;
import com.particle.data.domain.company.DataCompanyBasicId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业基本信息 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyBasicInfrastructureStructMapping {
	public static DataCompanyBasicInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyBasicInfrastructureStructMapping.class );

	protected DataCompanyBasicId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyBasicId.of(id);
	}
	protected Long map(DataCompanyBasicId dataCompanyBasicId){
		if (dataCompanyBasicId == null) {
			return null;
		}
		return dataCompanyBasicId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyBasicInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyBasicDO
	 * @return
	 */
	public abstract DataCompanyBasic dataCompanyBasicDOToDataCompanyBasic(@MappingTarget DataCompanyBasic dataCompanyBasic,DataCompanyBasicDO dataCompanyBasicDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyBasicInfrastructureStructMapping#map(DataCompanyBasicId)}
	 * @param dataCompanyBasic
	 * @return
	 */
	public abstract DataCompanyBasicDO dataCompanyBasicToDataCompanyBasicDO(DataCompanyBasic dataCompanyBasic);

}
