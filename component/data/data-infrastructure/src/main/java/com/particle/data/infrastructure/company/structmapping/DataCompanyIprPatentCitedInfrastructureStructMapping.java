package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentCitedDO;
import com.particle.data.domain.company.DataCompanyIprPatentCited;
import com.particle.data.domain.company.DataCompanyIprPatentCitedId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业知识产权专利被引证信息 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:15
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprPatentCitedInfrastructureStructMapping {
	public static DataCompanyIprPatentCitedInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyIprPatentCitedInfrastructureStructMapping.class );

	protected DataCompanyIprPatentCitedId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyIprPatentCitedId.of(id);
	}
	protected Long map(DataCompanyIprPatentCitedId dataCompanyIprPatentCitedId){
		if (dataCompanyIprPatentCitedId == null) {
			return null;
		}
		return dataCompanyIprPatentCitedId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPatentCitedInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyIprPatentCitedDO
	 * @return
	 */
	public abstract DataCompanyIprPatentCited dataCompanyIprPatentCitedDOToDataCompanyIprPatentCited(@MappingTarget DataCompanyIprPatentCited dataCompanyIprPatentCited,DataCompanyIprPatentCitedDO dataCompanyIprPatentCitedDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPatentCitedInfrastructureStructMapping#map(DataCompanyIprPatentCitedId)}
	 * @param dataCompanyIprPatentCited
	 * @return
	 */
	public abstract DataCompanyIprPatentCitedDO dataCompanyIprPatentCitedToDataCompanyIprPatentCitedDO(DataCompanyIprPatentCited dataCompanyIprPatentCited);

}
