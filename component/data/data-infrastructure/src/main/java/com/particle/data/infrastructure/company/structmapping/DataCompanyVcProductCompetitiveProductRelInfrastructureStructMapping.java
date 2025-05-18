package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyVcProductCompetitiveProductRelDO;
import com.particle.data.domain.company.DataCompanyVcProductCompetitiveProductRel;
import com.particle.data.domain.company.DataCompanyVcProductCompetitiveProductRelId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业融资产品竞品关系 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:47:00
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyVcProductCompetitiveProductRelInfrastructureStructMapping {
	public static DataCompanyVcProductCompetitiveProductRelInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyVcProductCompetitiveProductRelInfrastructureStructMapping.class );

	protected DataCompanyVcProductCompetitiveProductRelId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyVcProductCompetitiveProductRelId.of(id);
	}
	protected Long map(DataCompanyVcProductCompetitiveProductRelId dataCompanyVcProductCompetitiveProductRelId){
		if (dataCompanyVcProductCompetitiveProductRelId == null) {
			return null;
		}
		return dataCompanyVcProductCompetitiveProductRelId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyVcProductCompetitiveProductRelInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyVcProductCompetitiveProductRelDO
	 * @return
	 */
	public abstract DataCompanyVcProductCompetitiveProductRel dataCompanyVcProductCompetitiveProductRelDOToDataCompanyVcProductCompetitiveProductRel(@MappingTarget DataCompanyVcProductCompetitiveProductRel dataCompanyVcProductCompetitiveProductRel,DataCompanyVcProductCompetitiveProductRelDO dataCompanyVcProductCompetitiveProductRelDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyVcProductCompetitiveProductRelInfrastructureStructMapping#map(DataCompanyVcProductCompetitiveProductRelId)}
	 * @param dataCompanyVcProductCompetitiveProductRel
	 * @return
	 */
	public abstract DataCompanyVcProductCompetitiveProductRelDO dataCompanyVcProductCompetitiveProductRelToDataCompanyVcProductCompetitiveProductRelDO(DataCompanyVcProductCompetitiveProductRel dataCompanyVcProductCompetitiveProductRel);

}
