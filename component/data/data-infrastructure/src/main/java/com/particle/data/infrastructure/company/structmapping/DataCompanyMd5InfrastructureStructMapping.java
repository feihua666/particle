package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyMd5DO;
import com.particle.data.domain.company.DataCompanyMd5;
import com.particle.data.domain.company.DataCompanyMd5Id;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业md5 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:59
 */
@Mapper
public abstract class DataCompanyMd5InfrastructureStructMapping {
	public static DataCompanyMd5InfrastructureStructMapping instance = Mappers.getMapper( DataCompanyMd5InfrastructureStructMapping.class );

	protected DataCompanyMd5Id map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyMd5Id.of(id);
	}
	protected Long map(DataCompanyMd5Id dataCompanyMd5Id){
		if (dataCompanyMd5Id == null) {
			return null;
		}
		return dataCompanyMd5Id.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyMd5InfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyMd5DO
	 * @return
	 */
	public abstract DataCompanyMd5 dataCompanyMd5DOToDataCompanyMd5(@MappingTarget DataCompanyMd5 dataCompanyMd5,DataCompanyMd5DO dataCompanyMd5DO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyMd5InfrastructureStructMapping#map(DataCompanyMd5Id)}
	 * @param dataCompanyMd5
	 * @return
	 */
	public abstract DataCompanyMd5DO dataCompanyMd5ToDataCompanyMd5DO(DataCompanyMd5 dataCompanyMd5);

}
