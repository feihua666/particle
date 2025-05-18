package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentCertificateDO;
import com.particle.data.domain.company.DataCompanyIprPatentCertificate;
import com.particle.data.domain.company.DataCompanyIprPatentCertificateId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业知识产权专利证书信息 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:02
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprPatentCertificateInfrastructureStructMapping {
	public static DataCompanyIprPatentCertificateInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyIprPatentCertificateInfrastructureStructMapping.class );

	protected DataCompanyIprPatentCertificateId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyIprPatentCertificateId.of(id);
	}
	protected Long map(DataCompanyIprPatentCertificateId dataCompanyIprPatentCertificateId){
		if (dataCompanyIprPatentCertificateId == null) {
			return null;
		}
		return dataCompanyIprPatentCertificateId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPatentCertificateInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyIprPatentCertificateDO
	 * @return
	 */
	public abstract DataCompanyIprPatentCertificate dataCompanyIprPatentCertificateDOToDataCompanyIprPatentCertificate(@MappingTarget DataCompanyIprPatentCertificate dataCompanyIprPatentCertificate,DataCompanyIprPatentCertificateDO dataCompanyIprPatentCertificateDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPatentCertificateInfrastructureStructMapping#map(DataCompanyIprPatentCertificateId)}
	 * @param dataCompanyIprPatentCertificate
	 * @return
	 */
	public abstract DataCompanyIprPatentCertificateDO dataCompanyIprPatentCertificateToDataCompanyIprPatentCertificateDO(DataCompanyIprPatentCertificate dataCompanyIprPatentCertificate);

}
