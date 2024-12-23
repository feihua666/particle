package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.domain.company.DataCompany;
import com.particle.data.domain.company.DataCompanyId;
import com.particle.data.infrastructure.company.dos.DataCompanyDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:44
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyInfrastructureStructMapping {
	public static DataCompanyInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyInfrastructureStructMapping.class );

	protected DataCompanyId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyId.of(id);
	}
	protected Long map(DataCompanyId dataCompanyId){
		if (dataCompanyId == null) {
			return null;
		}
		return dataCompanyId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyDO
	 * @return
	 */
	public abstract DataCompany dataCompanyDOToDataCompany(@MappingTarget DataCompany dataCompany,DataCompanyDO dataCompanyDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyInfrastructureStructMapping#map(DataCompanyId)}
	 * @param dataCompany
	 * @return
	 */
	public abstract DataCompanyDO dataCompanyToDataCompanyDO(DataCompany dataCompany);

}
