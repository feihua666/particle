package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentPaymentDO;
import com.particle.data.domain.company.DataCompanyIprPatentPayment;
import com.particle.data.domain.company.DataCompanyIprPatentPaymentId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业知识产权专利缴费信息 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:27
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprPatentPaymentInfrastructureStructMapping {
	public static DataCompanyIprPatentPaymentInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyIprPatentPaymentInfrastructureStructMapping.class );

	protected DataCompanyIprPatentPaymentId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyIprPatentPaymentId.of(id);
	}
	protected Long map(DataCompanyIprPatentPaymentId dataCompanyIprPatentPaymentId){
		if (dataCompanyIprPatentPaymentId == null) {
			return null;
		}
		return dataCompanyIprPatentPaymentId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPatentPaymentInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyIprPatentPaymentDO
	 * @return
	 */
	public abstract DataCompanyIprPatentPayment dataCompanyIprPatentPaymentDOToDataCompanyIprPatentPayment(@MappingTarget DataCompanyIprPatentPayment dataCompanyIprPatentPayment,DataCompanyIprPatentPaymentDO dataCompanyIprPatentPaymentDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPatentPaymentInfrastructureStructMapping#map(DataCompanyIprPatentPaymentId)}
	 * @param dataCompanyIprPatentPayment
	 * @return
	 */
	public abstract DataCompanyIprPatentPaymentDO dataCompanyIprPatentPaymentToDataCompanyIprPatentPaymentDO(DataCompanyIprPatentPayment dataCompanyIprPatentPayment);

}
