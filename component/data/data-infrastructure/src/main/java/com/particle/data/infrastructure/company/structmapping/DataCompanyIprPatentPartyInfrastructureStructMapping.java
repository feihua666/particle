package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentPartyDO;
import com.particle.data.domain.company.DataCompanyIprPatentParty;
import com.particle.data.domain.company.DataCompanyIprPatentPartyId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业知识产权当事人 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-27 18:00:12
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprPatentPartyInfrastructureStructMapping {
	public static DataCompanyIprPatentPartyInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyIprPatentPartyInfrastructureStructMapping.class );

	protected DataCompanyIprPatentPartyId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyIprPatentPartyId.of(id);
	}
	protected Long map(DataCompanyIprPatentPartyId dataCompanyIprPatentPartyId){
		if (dataCompanyIprPatentPartyId == null) {
			return null;
		}
		return dataCompanyIprPatentPartyId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPatentPartyInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyIprPatentPartyDO
	 * @return
	 */
	public abstract DataCompanyIprPatentParty dataCompanyIprPatentPartyDOToDataCompanyIprPatentParty(@MappingTarget DataCompanyIprPatentParty dataCompanyIprPatentParty,DataCompanyIprPatentPartyDO dataCompanyIprPatentPartyDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPatentPartyInfrastructureStructMapping#map(DataCompanyIprPatentPartyId)}
	 * @param dataCompanyIprPatentParty
	 * @return
	 */
	public abstract DataCompanyIprPatentPartyDO dataCompanyIprPatentPartyToDataCompanyIprPatentPartyDO(DataCompanyIprPatentParty dataCompanyIprPatentParty);

}
