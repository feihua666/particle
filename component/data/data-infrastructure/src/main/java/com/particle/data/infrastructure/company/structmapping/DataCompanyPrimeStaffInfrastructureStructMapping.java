package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyPrimeStaffDO;
import com.particle.data.domain.company.DataCompanyPrimeStaff;
import com.particle.data.domain.company.DataCompanyPrimeStaffId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业主要人员 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:44
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyPrimeStaffInfrastructureStructMapping {
	public static DataCompanyPrimeStaffInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyPrimeStaffInfrastructureStructMapping.class );

	protected DataCompanyPrimeStaffId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyPrimeStaffId.of(id);
	}
	protected Long map(DataCompanyPrimeStaffId dataCompanyPrimeStaffId){
		if (dataCompanyPrimeStaffId == null) {
			return null;
		}
		return dataCompanyPrimeStaffId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyPrimeStaffInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyPrimeStaffDO
	 * @return
	 */
	public abstract DataCompanyPrimeStaff dataCompanyPrimeStaffDOToDataCompanyPrimeStaff(@MappingTarget DataCompanyPrimeStaff dataCompanyPrimeStaff,DataCompanyPrimeStaffDO dataCompanyPrimeStaffDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyPrimeStaffInfrastructureStructMapping#map(DataCompanyPrimeStaffId)}
	 * @param dataCompanyPrimeStaff
	 * @return
	 */
	public abstract DataCompanyPrimeStaffDO dataCompanyPrimeStaffToDataCompanyPrimeStaffDO(DataCompanyPrimeStaff dataCompanyPrimeStaff);

}
