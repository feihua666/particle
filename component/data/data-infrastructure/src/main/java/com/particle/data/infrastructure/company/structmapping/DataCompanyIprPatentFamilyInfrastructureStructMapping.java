package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentFamilyDO;
import com.particle.data.domain.company.DataCompanyIprPatentFamily;
import com.particle.data.domain.company.DataCompanyIprPatentFamilyId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业知识产权专利同族信息 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:43
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprPatentFamilyInfrastructureStructMapping {
	public static DataCompanyIprPatentFamilyInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyIprPatentFamilyInfrastructureStructMapping.class );

	protected DataCompanyIprPatentFamilyId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyIprPatentFamilyId.of(id);
	}
	protected Long map(DataCompanyIprPatentFamilyId dataCompanyIprPatentFamilyId){
		if (dataCompanyIprPatentFamilyId == null) {
			return null;
		}
		return dataCompanyIprPatentFamilyId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPatentFamilyInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyIprPatentFamilyDO
	 * @return
	 */
	public abstract DataCompanyIprPatentFamily dataCompanyIprPatentFamilyDOToDataCompanyIprPatentFamily(@MappingTarget DataCompanyIprPatentFamily dataCompanyIprPatentFamily,DataCompanyIprPatentFamilyDO dataCompanyIprPatentFamilyDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPatentFamilyInfrastructureStructMapping#map(DataCompanyIprPatentFamilyId)}
	 * @param dataCompanyIprPatentFamily
	 * @return
	 */
	public abstract DataCompanyIprPatentFamilyDO dataCompanyIprPatentFamilyToDataCompanyIprPatentFamilyDO(DataCompanyIprPatentFamily dataCompanyIprPatentFamily);

}
