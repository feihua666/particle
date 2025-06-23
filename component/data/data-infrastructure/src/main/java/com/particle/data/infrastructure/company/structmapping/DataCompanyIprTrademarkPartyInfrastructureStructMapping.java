package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkPartyDO;
import com.particle.data.domain.company.DataCompanyIprTrademarkParty;
import com.particle.data.domain.company.DataCompanyIprTrademarkPartyId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业知识产权商标当事人 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:34
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprTrademarkPartyInfrastructureStructMapping {
	public static DataCompanyIprTrademarkPartyInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyIprTrademarkPartyInfrastructureStructMapping.class );

	protected DataCompanyIprTrademarkPartyId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyIprTrademarkPartyId.of(id);
	}
	protected Long map(DataCompanyIprTrademarkPartyId dataCompanyIprTrademarkPartyId){
		if (dataCompanyIprTrademarkPartyId == null) {
			return null;
		}
		return dataCompanyIprTrademarkPartyId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprTrademarkPartyInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyIprTrademarkPartyDO
	 * @return
	 */
	public abstract DataCompanyIprTrademarkParty dataCompanyIprTrademarkPartyDOToDataCompanyIprTrademarkParty(@MappingTarget DataCompanyIprTrademarkParty dataCompanyIprTrademarkParty,DataCompanyIprTrademarkPartyDO dataCompanyIprTrademarkPartyDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprTrademarkPartyInfrastructureStructMapping#map(DataCompanyIprTrademarkPartyId)}
	 * @param dataCompanyIprTrademarkParty
	 * @return
	 */
	public abstract DataCompanyIprTrademarkPartyDO dataCompanyIprTrademarkPartyToDataCompanyIprTrademarkPartyDO(DataCompanyIprTrademarkParty dataCompanyIprTrademarkParty);

}
