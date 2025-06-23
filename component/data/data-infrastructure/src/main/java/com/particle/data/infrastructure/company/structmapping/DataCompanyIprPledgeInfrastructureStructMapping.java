package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyIprPledgeDO;
import com.particle.data.domain.company.DataCompanyIprPledge;
import com.particle.data.domain.company.DataCompanyIprPledgeId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业知识产权出质 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:21
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprPledgeInfrastructureStructMapping {
	public static DataCompanyIprPledgeInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyIprPledgeInfrastructureStructMapping.class );

	protected DataCompanyIprPledgeId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyIprPledgeId.of(id);
	}
	protected Long map(DataCompanyIprPledgeId dataCompanyIprPledgeId){
		if (dataCompanyIprPledgeId == null) {
			return null;
		}
		return dataCompanyIprPledgeId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPledgeInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyIprPledgeDO
	 * @return
	 */
	public abstract DataCompanyIprPledge dataCompanyIprPledgeDOToDataCompanyIprPledge(@MappingTarget DataCompanyIprPledge dataCompanyIprPledge,DataCompanyIprPledgeDO dataCompanyIprPledgeDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPledgeInfrastructureStructMapping#map(DataCompanyIprPledgeId)}
	 * @param dataCompanyIprPledge
	 * @return
	 */
	public abstract DataCompanyIprPledgeDO dataCompanyIprPledgeToDataCompanyIprPledgeDO(DataCompanyIprPledge dataCompanyIprPledge);

}
