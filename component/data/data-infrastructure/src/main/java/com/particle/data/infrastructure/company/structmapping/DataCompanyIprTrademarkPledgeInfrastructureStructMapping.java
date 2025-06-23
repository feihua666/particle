package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkPledgeDO;
import com.particle.data.domain.company.DataCompanyIprTrademarkPledge;
import com.particle.data.domain.company.DataCompanyIprTrademarkPledgeId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业知识产权商标质押信息 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:53
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprTrademarkPledgeInfrastructureStructMapping {
	public static DataCompanyIprTrademarkPledgeInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyIprTrademarkPledgeInfrastructureStructMapping.class );

	protected DataCompanyIprTrademarkPledgeId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyIprTrademarkPledgeId.of(id);
	}
	protected Long map(DataCompanyIprTrademarkPledgeId dataCompanyIprTrademarkPledgeId){
		if (dataCompanyIprTrademarkPledgeId == null) {
			return null;
		}
		return dataCompanyIprTrademarkPledgeId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprTrademarkPledgeInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyIprTrademarkPledgeDO
	 * @return
	 */
	public abstract DataCompanyIprTrademarkPledge dataCompanyIprTrademarkPledgeDOToDataCompanyIprTrademarkPledge(@MappingTarget DataCompanyIprTrademarkPledge dataCompanyIprTrademarkPledge,DataCompanyIprTrademarkPledgeDO dataCompanyIprTrademarkPledgeDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprTrademarkPledgeInfrastructureStructMapping#map(DataCompanyIprTrademarkPledgeId)}
	 * @param dataCompanyIprTrademarkPledge
	 * @return
	 */
	public abstract DataCompanyIprTrademarkPledgeDO dataCompanyIprTrademarkPledgeToDataCompanyIprTrademarkPledgeDO(DataCompanyIprTrademarkPledge dataCompanyIprTrademarkPledge);

}
