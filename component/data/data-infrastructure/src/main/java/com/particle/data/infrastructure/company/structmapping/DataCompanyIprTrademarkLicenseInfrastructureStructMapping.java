package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkLicenseDO;
import com.particle.data.domain.company.DataCompanyIprTrademarkLicense;
import com.particle.data.domain.company.DataCompanyIprTrademarkLicenseId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业知识产权商标许可信息 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:10
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprTrademarkLicenseInfrastructureStructMapping {
	public static DataCompanyIprTrademarkLicenseInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyIprTrademarkLicenseInfrastructureStructMapping.class );

	protected DataCompanyIprTrademarkLicenseId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyIprTrademarkLicenseId.of(id);
	}
	protected Long map(DataCompanyIprTrademarkLicenseId dataCompanyIprTrademarkLicenseId){
		if (dataCompanyIprTrademarkLicenseId == null) {
			return null;
		}
		return dataCompanyIprTrademarkLicenseId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprTrademarkLicenseInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyIprTrademarkLicenseDO
	 * @return
	 */
	public abstract DataCompanyIprTrademarkLicense dataCompanyIprTrademarkLicenseDOToDataCompanyIprTrademarkLicense(@MappingTarget DataCompanyIprTrademarkLicense dataCompanyIprTrademarkLicense,DataCompanyIprTrademarkLicenseDO dataCompanyIprTrademarkLicenseDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprTrademarkLicenseInfrastructureStructMapping#map(DataCompanyIprTrademarkLicenseId)}
	 * @param dataCompanyIprTrademarkLicense
	 * @return
	 */
	public abstract DataCompanyIprTrademarkLicenseDO dataCompanyIprTrademarkLicenseToDataCompanyIprTrademarkLicenseDO(DataCompanyIprTrademarkLicense dataCompanyIprTrademarkLicense);

}
