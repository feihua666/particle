package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentDO;
import com.particle.data.domain.company.DataCompanyIprPatent;
import com.particle.data.domain.company.DataCompanyIprPatentId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业知识产权专利 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:39:48
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprPatentInfrastructureStructMapping {
	public static DataCompanyIprPatentInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyIprPatentInfrastructureStructMapping.class );

	protected DataCompanyIprPatentId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyIprPatentId.of(id);
	}
	protected Long map(DataCompanyIprPatentId dataCompanyIprPatentId){
		if (dataCompanyIprPatentId == null) {
			return null;
		}
		return dataCompanyIprPatentId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPatentInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyIprPatentDO
	 * @return
	 */
	public abstract DataCompanyIprPatent dataCompanyIprPatentDOToDataCompanyIprPatent(@MappingTarget DataCompanyIprPatent dataCompanyIprPatent,DataCompanyIprPatentDO dataCompanyIprPatentDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPatentInfrastructureStructMapping#map(DataCompanyIprPatentId)}
	 * @param dataCompanyIprPatent
	 * @return
	 */
	public abstract DataCompanyIprPatentDO dataCompanyIprPatentToDataCompanyIprPatentDO(DataCompanyIprPatent dataCompanyIprPatent);

}
