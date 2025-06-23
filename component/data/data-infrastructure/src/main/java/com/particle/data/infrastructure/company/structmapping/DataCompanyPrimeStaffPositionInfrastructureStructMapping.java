package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyPrimeStaffPositionDO;
import com.particle.data.domain.company.DataCompanyPrimeStaffPosition;
import com.particle.data.domain.company.DataCompanyPrimeStaffPositionId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业主要人员职位 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-22 15:07:33
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyPrimeStaffPositionInfrastructureStructMapping {
	public static DataCompanyPrimeStaffPositionInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyPrimeStaffPositionInfrastructureStructMapping.class );

	protected DataCompanyPrimeStaffPositionId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyPrimeStaffPositionId.of(id);
	}
	protected Long map(DataCompanyPrimeStaffPositionId dataCompanyPrimeStaffPositionId){
		if (dataCompanyPrimeStaffPositionId == null) {
			return null;
		}
		return dataCompanyPrimeStaffPositionId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyPrimeStaffPositionInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyPrimeStaffPositionDO
	 * @return
	 */
	public abstract DataCompanyPrimeStaffPosition dataCompanyPrimeStaffPositionDOToDataCompanyPrimeStaffPosition(@MappingTarget DataCompanyPrimeStaffPosition dataCompanyPrimeStaffPosition,DataCompanyPrimeStaffPositionDO dataCompanyPrimeStaffPositionDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyPrimeStaffPositionInfrastructureStructMapping#map(DataCompanyPrimeStaffPositionId)}
	 * @param dataCompanyPrimeStaffPosition
	 * @return
	 */
	public abstract DataCompanyPrimeStaffPositionDO dataCompanyPrimeStaffPositionToDataCompanyPrimeStaffPositionDO(DataCompanyPrimeStaffPosition dataCompanyPrimeStaffPosition);

}
