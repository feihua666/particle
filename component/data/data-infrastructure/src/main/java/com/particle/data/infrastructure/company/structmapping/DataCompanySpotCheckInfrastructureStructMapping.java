package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanySpotCheckDO;
import com.particle.data.domain.company.DataCompanySpotCheck;
import com.particle.data.domain.company.DataCompanySpotCheckId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业抽查检查 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:39
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanySpotCheckInfrastructureStructMapping {
	public static DataCompanySpotCheckInfrastructureStructMapping instance = Mappers.getMapper( DataCompanySpotCheckInfrastructureStructMapping.class );

	protected DataCompanySpotCheckId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanySpotCheckId.of(id);
	}
	protected Long map(DataCompanySpotCheckId dataCompanySpotCheckId){
		if (dataCompanySpotCheckId == null) {
			return null;
		}
		return dataCompanySpotCheckId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanySpotCheckInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanySpotCheckDO
	 * @return
	 */
	public abstract DataCompanySpotCheck dataCompanySpotCheckDOToDataCompanySpotCheck(@MappingTarget DataCompanySpotCheck dataCompanySpotCheck,DataCompanySpotCheckDO dataCompanySpotCheckDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanySpotCheckInfrastructureStructMapping#map(DataCompanySpotCheckId)}
	 * @param dataCompanySpotCheck
	 * @return
	 */
	public abstract DataCompanySpotCheckDO dataCompanySpotCheckToDataCompanySpotCheckDO(DataCompanySpotCheck dataCompanySpotCheck);

}
