package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyAdministrativeLicenseDO;
import com.particle.data.domain.company.DataCompanyAdministrativeLicense;
import com.particle.data.domain.company.DataCompanyAdministrativeLicenseId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业行政许可 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:17:53
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyAdministrativeLicenseInfrastructureStructMapping {
	public static DataCompanyAdministrativeLicenseInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyAdministrativeLicenseInfrastructureStructMapping.class );

	protected DataCompanyAdministrativeLicenseId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyAdministrativeLicenseId.of(id);
	}
	protected Long map(DataCompanyAdministrativeLicenseId dataCompanyAdministrativeLicenseId){
		if (dataCompanyAdministrativeLicenseId == null) {
			return null;
		}
		return dataCompanyAdministrativeLicenseId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyAdministrativeLicenseInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyAdministrativeLicenseDO
	 * @return
	 */
	public abstract DataCompanyAdministrativeLicense dataCompanyAdministrativeLicenseDOToDataCompanyAdministrativeLicense(@MappingTarget DataCompanyAdministrativeLicense dataCompanyAdministrativeLicense,DataCompanyAdministrativeLicenseDO dataCompanyAdministrativeLicenseDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyAdministrativeLicenseInfrastructureStructMapping#map(DataCompanyAdministrativeLicenseId)}
	 * @param dataCompanyAdministrativeLicense
	 * @return
	 */
	public abstract DataCompanyAdministrativeLicenseDO dataCompanyAdministrativeLicenseToDataCompanyAdministrativeLicenseDO(DataCompanyAdministrativeLicense dataCompanyAdministrativeLicense);

}
