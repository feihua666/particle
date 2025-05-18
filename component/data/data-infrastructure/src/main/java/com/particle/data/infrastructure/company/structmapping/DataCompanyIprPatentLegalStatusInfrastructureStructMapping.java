package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentLegalStatusDO;
import com.particle.data.domain.company.DataCompanyIprPatentLegalStatus;
import com.particle.data.domain.company.DataCompanyIprPatentLegalStatusId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业知识产权专利法律状态 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:03
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprPatentLegalStatusInfrastructureStructMapping {
	public static DataCompanyIprPatentLegalStatusInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyIprPatentLegalStatusInfrastructureStructMapping.class );

	protected DataCompanyIprPatentLegalStatusId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyIprPatentLegalStatusId.of(id);
	}
	protected Long map(DataCompanyIprPatentLegalStatusId dataCompanyIprPatentLegalStatusId){
		if (dataCompanyIprPatentLegalStatusId == null) {
			return null;
		}
		return dataCompanyIprPatentLegalStatusId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPatentLegalStatusInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyIprPatentLegalStatusDO
	 * @return
	 */
	public abstract DataCompanyIprPatentLegalStatus dataCompanyIprPatentLegalStatusDOToDataCompanyIprPatentLegalStatus(@MappingTarget DataCompanyIprPatentLegalStatus dataCompanyIprPatentLegalStatus,DataCompanyIprPatentLegalStatusDO dataCompanyIprPatentLegalStatusDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPatentLegalStatusInfrastructureStructMapping#map(DataCompanyIprPatentLegalStatusId)}
	 * @param dataCompanyIprPatentLegalStatus
	 * @return
	 */
	public abstract DataCompanyIprPatentLegalStatusDO dataCompanyIprPatentLegalStatusToDataCompanyIprPatentLegalStatusDO(DataCompanyIprPatentLegalStatus dataCompanyIprPatentLegalStatus);

}
