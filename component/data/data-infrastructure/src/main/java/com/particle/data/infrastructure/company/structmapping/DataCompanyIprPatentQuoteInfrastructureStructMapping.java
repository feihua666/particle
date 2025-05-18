package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentQuoteDO;
import com.particle.data.domain.company.DataCompanyIprPatentQuote;
import com.particle.data.domain.company.DataCompanyIprPatentQuoteId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业知识产权专利引证信息 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:24
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprPatentQuoteInfrastructureStructMapping {
	public static DataCompanyIprPatentQuoteInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyIprPatentQuoteInfrastructureStructMapping.class );

	protected DataCompanyIprPatentQuoteId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyIprPatentQuoteId.of(id);
	}
	protected Long map(DataCompanyIprPatentQuoteId dataCompanyIprPatentQuoteId){
		if (dataCompanyIprPatentQuoteId == null) {
			return null;
		}
		return dataCompanyIprPatentQuoteId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPatentQuoteInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyIprPatentQuoteDO
	 * @return
	 */
	public abstract DataCompanyIprPatentQuote dataCompanyIprPatentQuoteDOToDataCompanyIprPatentQuote(@MappingTarget DataCompanyIprPatentQuote dataCompanyIprPatentQuote,DataCompanyIprPatentQuoteDO dataCompanyIprPatentQuoteDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPatentQuoteInfrastructureStructMapping#map(DataCompanyIprPatentQuoteId)}
	 * @param dataCompanyIprPatentQuote
	 * @return
	 */
	public abstract DataCompanyIprPatentQuoteDO dataCompanyIprPatentQuoteToDataCompanyIprPatentQuoteDO(DataCompanyIprPatentQuote dataCompanyIprPatentQuote);

}
