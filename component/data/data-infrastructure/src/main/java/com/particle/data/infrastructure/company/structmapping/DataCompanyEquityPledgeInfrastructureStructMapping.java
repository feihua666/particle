package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyEquityPledgeDO;
import com.particle.data.domain.company.DataCompanyEquityPledge;
import com.particle.data.domain.company.DataCompanyEquityPledgeId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业股权出质 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:10
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyEquityPledgeInfrastructureStructMapping {
	public static DataCompanyEquityPledgeInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyEquityPledgeInfrastructureStructMapping.class );

	protected DataCompanyEquityPledgeId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyEquityPledgeId.of(id);
	}
	protected Long map(DataCompanyEquityPledgeId dataCompanyEquityPledgeId){
		if (dataCompanyEquityPledgeId == null) {
			return null;
		}
		return dataCompanyEquityPledgeId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyEquityPledgeInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyEquityPledgeDO
	 * @return
	 */
	public abstract DataCompanyEquityPledge dataCompanyEquityPledgeDOToDataCompanyEquityPledge(@MappingTarget DataCompanyEquityPledge dataCompanyEquityPledge,DataCompanyEquityPledgeDO dataCompanyEquityPledgeDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyEquityPledgeInfrastructureStructMapping#map(DataCompanyEquityPledgeId)}
	 * @param dataCompanyEquityPledge
	 * @return
	 */
	public abstract DataCompanyEquityPledgeDO dataCompanyEquityPledgeToDataCompanyEquityPledgeDO(DataCompanyEquityPledge dataCompanyEquityPledge);

}
