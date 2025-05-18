package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentPledgeDO;
import com.particle.data.domain.company.DataCompanyIprPatentPledge;
import com.particle.data.domain.company.DataCompanyIprPatentPledgeId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业知识产权专利质押信息 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:40
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprPatentPledgeInfrastructureStructMapping {
	public static DataCompanyIprPatentPledgeInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyIprPatentPledgeInfrastructureStructMapping.class );

	protected DataCompanyIprPatentPledgeId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyIprPatentPledgeId.of(id);
	}
	protected Long map(DataCompanyIprPatentPledgeId dataCompanyIprPatentPledgeId){
		if (dataCompanyIprPatentPledgeId == null) {
			return null;
		}
		return dataCompanyIprPatentPledgeId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPatentPledgeInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyIprPatentPledgeDO
	 * @return
	 */
	public abstract DataCompanyIprPatentPledge dataCompanyIprPatentPledgeDOToDataCompanyIprPatentPledge(@MappingTarget DataCompanyIprPatentPledge dataCompanyIprPatentPledge,DataCompanyIprPatentPledgeDO dataCompanyIprPatentPledgeDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPatentPledgeInfrastructureStructMapping#map(DataCompanyIprPatentPledgeId)}
	 * @param dataCompanyIprPatentPledge
	 * @return
	 */
	public abstract DataCompanyIprPatentPledgeDO dataCompanyIprPatentPledgeToDataCompanyIprPatentPledgeDO(DataCompanyIprPatentPledge dataCompanyIprPatentPledge);

}
