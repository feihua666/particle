package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyRestrictHighConsumePartyDO;
import com.particle.data.domain.company.DataCompanyRestrictHighConsumeParty;
import com.particle.data.domain.company.DataCompanyRestrictHighConsumePartyId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业限制高消费当事人 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:32
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyRestrictHighConsumePartyInfrastructureStructMapping {
	public static DataCompanyRestrictHighConsumePartyInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyRestrictHighConsumePartyInfrastructureStructMapping.class );

	protected DataCompanyRestrictHighConsumePartyId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyRestrictHighConsumePartyId.of(id);
	}
	protected Long map(DataCompanyRestrictHighConsumePartyId dataCompanyRestrictHighConsumePartyId){
		if (dataCompanyRestrictHighConsumePartyId == null) {
			return null;
		}
		return dataCompanyRestrictHighConsumePartyId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyRestrictHighConsumePartyInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyRestrictHighConsumePartyDO
	 * @return
	 */
	public abstract DataCompanyRestrictHighConsumeParty dataCompanyRestrictHighConsumePartyDOToDataCompanyRestrictHighConsumeParty(@MappingTarget DataCompanyRestrictHighConsumeParty dataCompanyRestrictHighConsumeParty,DataCompanyRestrictHighConsumePartyDO dataCompanyRestrictHighConsumePartyDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyRestrictHighConsumePartyInfrastructureStructMapping#map(DataCompanyRestrictHighConsumePartyId)}
	 * @param dataCompanyRestrictHighConsumeParty
	 * @return
	 */
	public abstract DataCompanyRestrictHighConsumePartyDO dataCompanyRestrictHighConsumePartyToDataCompanyRestrictHighConsumePartyDO(DataCompanyRestrictHighConsumeParty dataCompanyRestrictHighConsumeParty);

}
