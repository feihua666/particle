package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentLicenseDO;
import com.particle.data.domain.company.DataCompanyIprPatentLicense;
import com.particle.data.domain.company.DataCompanyIprPatentLicenseId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业知识产权专利许可信息 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:59
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprPatentLicenseInfrastructureStructMapping {
	public static DataCompanyIprPatentLicenseInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyIprPatentLicenseInfrastructureStructMapping.class );

	protected DataCompanyIprPatentLicenseId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyIprPatentLicenseId.of(id);
	}
	protected Long map(DataCompanyIprPatentLicenseId dataCompanyIprPatentLicenseId){
		if (dataCompanyIprPatentLicenseId == null) {
			return null;
		}
		return dataCompanyIprPatentLicenseId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPatentLicenseInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyIprPatentLicenseDO
	 * @return
	 */
	public abstract DataCompanyIprPatentLicense dataCompanyIprPatentLicenseDOToDataCompanyIprPatentLicense(@MappingTarget DataCompanyIprPatentLicense dataCompanyIprPatentLicense,DataCompanyIprPatentLicenseDO dataCompanyIprPatentLicenseDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPatentLicenseInfrastructureStructMapping#map(DataCompanyIprPatentLicenseId)}
	 * @param dataCompanyIprPatentLicense
	 * @return
	 */
	public abstract DataCompanyIprPatentLicenseDO dataCompanyIprPatentLicenseToDataCompanyIprPatentLicenseDO(DataCompanyIprPatentLicense dataCompanyIprPatentLicense);

}
