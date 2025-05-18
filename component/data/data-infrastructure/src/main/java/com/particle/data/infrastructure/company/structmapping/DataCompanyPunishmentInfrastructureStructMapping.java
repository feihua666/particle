package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyPunishmentDO;
import com.particle.data.domain.company.DataCompanyPunishment;
import com.particle.data.domain.company.DataCompanyPunishmentId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业行政处罚 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:37
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyPunishmentInfrastructureStructMapping {
	public static DataCompanyPunishmentInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyPunishmentInfrastructureStructMapping.class );

	protected DataCompanyPunishmentId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyPunishmentId.of(id);
	}
	protected Long map(DataCompanyPunishmentId dataCompanyPunishmentId){
		if (dataCompanyPunishmentId == null) {
			return null;
		}
		return dataCompanyPunishmentId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyPunishmentInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyPunishmentDO
	 * @return
	 */
	public abstract DataCompanyPunishment dataCompanyPunishmentDOToDataCompanyPunishment(@MappingTarget DataCompanyPunishment dataCompanyPunishment,DataCompanyPunishmentDO dataCompanyPunishmentDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyPunishmentInfrastructureStructMapping#map(DataCompanyPunishmentId)}
	 * @param dataCompanyPunishment
	 * @return
	 */
	public abstract DataCompanyPunishmentDO dataCompanyPunishmentToDataCompanyPunishmentDO(DataCompanyPunishment dataCompanyPunishment);

}
