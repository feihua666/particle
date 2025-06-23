package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyIprGeograDO;
import com.particle.data.domain.company.DataCompanyIprGeogra;
import com.particle.data.domain.company.DataCompanyIprGeograId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业知识产权地理标识 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:33
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprGeograInfrastructureStructMapping {
	public static DataCompanyIprGeograInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyIprGeograInfrastructureStructMapping.class );

	protected DataCompanyIprGeograId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyIprGeograId.of(id);
	}
	protected Long map(DataCompanyIprGeograId dataCompanyIprGeograId){
		if (dataCompanyIprGeograId == null) {
			return null;
		}
		return dataCompanyIprGeograId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprGeograInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyIprGeograDO
	 * @return
	 */
	public abstract DataCompanyIprGeogra dataCompanyIprGeograDOToDataCompanyIprGeogra(@MappingTarget DataCompanyIprGeogra dataCompanyIprGeogra,DataCompanyIprGeograDO dataCompanyIprGeograDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprGeograInfrastructureStructMapping#map(DataCompanyIprGeograId)}
	 * @param dataCompanyIprGeogra
	 * @return
	 */
	public abstract DataCompanyIprGeograDO dataCompanyIprGeograToDataCompanyIprGeograDO(DataCompanyIprGeogra dataCompanyIprGeogra);

}
