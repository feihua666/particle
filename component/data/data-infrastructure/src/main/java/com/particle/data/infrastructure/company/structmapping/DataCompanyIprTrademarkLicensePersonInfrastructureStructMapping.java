package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkLicensePersonDO;
import com.particle.data.domain.company.DataCompanyIprTrademarkLicensePerson;
import com.particle.data.domain.company.DataCompanyIprTrademarkLicensePersonId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业知识产权商标许可人 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:22
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprTrademarkLicensePersonInfrastructureStructMapping {
	public static DataCompanyIprTrademarkLicensePersonInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyIprTrademarkLicensePersonInfrastructureStructMapping.class );

	protected DataCompanyIprTrademarkLicensePersonId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyIprTrademarkLicensePersonId.of(id);
	}
	protected Long map(DataCompanyIprTrademarkLicensePersonId dataCompanyIprTrademarkLicensePersonId){
		if (dataCompanyIprTrademarkLicensePersonId == null) {
			return null;
		}
		return dataCompanyIprTrademarkLicensePersonId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprTrademarkLicensePersonInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyIprTrademarkLicensePersonDO
	 * @return
	 */
	public abstract DataCompanyIprTrademarkLicensePerson dataCompanyIprTrademarkLicensePersonDOToDataCompanyIprTrademarkLicensePerson(@MappingTarget DataCompanyIprTrademarkLicensePerson dataCompanyIprTrademarkLicensePerson,DataCompanyIprTrademarkLicensePersonDO dataCompanyIprTrademarkLicensePersonDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprTrademarkLicensePersonInfrastructureStructMapping#map(DataCompanyIprTrademarkLicensePersonId)}
	 * @param dataCompanyIprTrademarkLicensePerson
	 * @return
	 */
	public abstract DataCompanyIprTrademarkLicensePersonDO dataCompanyIprTrademarkLicensePersonToDataCompanyIprTrademarkLicensePersonDO(DataCompanyIprTrademarkLicensePerson dataCompanyIprTrademarkLicensePerson);

}
