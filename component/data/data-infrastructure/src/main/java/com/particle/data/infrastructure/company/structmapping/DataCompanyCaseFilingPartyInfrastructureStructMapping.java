package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyCaseFilingPartyDO;
import com.particle.data.domain.company.DataCompanyCaseFilingParty;
import com.particle.data.domain.company.DataCompanyCaseFilingPartyId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业立案信息当事人 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:50
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyCaseFilingPartyInfrastructureStructMapping {
	public static DataCompanyCaseFilingPartyInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyCaseFilingPartyInfrastructureStructMapping.class );

	protected DataCompanyCaseFilingPartyId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyCaseFilingPartyId.of(id);
	}
	protected Long map(DataCompanyCaseFilingPartyId dataCompanyCaseFilingPartyId){
		if (dataCompanyCaseFilingPartyId == null) {
			return null;
		}
		return dataCompanyCaseFilingPartyId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyCaseFilingPartyInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyCaseFilingPartyDO
	 * @return
	 */
	public abstract DataCompanyCaseFilingParty dataCompanyCaseFilingPartyDOToDataCompanyCaseFilingParty(@MappingTarget DataCompanyCaseFilingParty dataCompanyCaseFilingParty,DataCompanyCaseFilingPartyDO dataCompanyCaseFilingPartyDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyCaseFilingPartyInfrastructureStructMapping#map(DataCompanyCaseFilingPartyId)}
	 * @param dataCompanyCaseFilingParty
	 * @return
	 */
	public abstract DataCompanyCaseFilingPartyDO dataCompanyCaseFilingPartyToDataCompanyCaseFilingPartyDO(DataCompanyCaseFilingParty dataCompanyCaseFilingParty);

}
