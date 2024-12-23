package com.particle.data.infrastructure.temp.structmapping;

import com.particle.data.domain.temp.DataCompanyMd5Ids;
import com.particle.data.domain.temp.DataCompanyMd5IdsId;
import com.particle.data.infrastructure.temp.dos.DataCompanyMd5IdsDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业md5ids 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:24:11
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyMd5IdsInfrastructureStructMapping {
	public static DataCompanyMd5IdsInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyMd5IdsInfrastructureStructMapping.class );

	protected DataCompanyMd5IdsId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyMd5IdsId.of(id);
	}
	protected Long map(DataCompanyMd5IdsId dataCompanyMd5IdsId){
		if (dataCompanyMd5IdsId == null) {
			return null;
		}
		return dataCompanyMd5IdsId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyMd5IdsInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyMd5IdsDO
	 * @return
	 */
	public abstract DataCompanyMd5Ids dataCompanyMd5IdsDOToDataCompanyMd5Ids(@MappingTarget DataCompanyMd5Ids dataCompanyMd5Ids,DataCompanyMd5IdsDO dataCompanyMd5IdsDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyMd5IdsInfrastructureStructMapping#map(DataCompanyMd5IdsId)}
	 * @param dataCompanyMd5Ids
	 * @return
	 */
	public abstract DataCompanyMd5IdsDO dataCompanyMd5IdsToDataCompanyMd5IdsDO(DataCompanyMd5Ids dataCompanyMd5Ids);

}
