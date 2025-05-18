package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyHonorQualificationDO;
import com.particle.data.domain.company.DataCompanyHonorQualification;
import com.particle.data.domain.company.DataCompanyHonorQualificationId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业荣誉资质 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:39:14
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyHonorQualificationInfrastructureStructMapping {
	public static DataCompanyHonorQualificationInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyHonorQualificationInfrastructureStructMapping.class );

	protected DataCompanyHonorQualificationId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyHonorQualificationId.of(id);
	}
	protected Long map(DataCompanyHonorQualificationId dataCompanyHonorQualificationId){
		if (dataCompanyHonorQualificationId == null) {
			return null;
		}
		return dataCompanyHonorQualificationId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyHonorQualificationInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyHonorQualificationDO
	 * @return
	 */
	public abstract DataCompanyHonorQualification dataCompanyHonorQualificationDOToDataCompanyHonorQualification(@MappingTarget DataCompanyHonorQualification dataCompanyHonorQualification,DataCompanyHonorQualificationDO dataCompanyHonorQualificationDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyHonorQualificationInfrastructureStructMapping#map(DataCompanyHonorQualificationId)}
	 * @param dataCompanyHonorQualification
	 * @return
	 */
	public abstract DataCompanyHonorQualificationDO dataCompanyHonorQualificationToDataCompanyHonorQualificationDO(DataCompanyHonorQualification dataCompanyHonorQualification);

}
