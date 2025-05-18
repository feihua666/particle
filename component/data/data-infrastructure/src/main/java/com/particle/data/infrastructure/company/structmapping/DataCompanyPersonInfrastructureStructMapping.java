package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyPersonDO;
import com.particle.data.domain.company.DataCompanyPerson;
import com.particle.data.domain.company.DataCompanyPersonId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业个人 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:50
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyPersonInfrastructureStructMapping {
	public static DataCompanyPersonInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyPersonInfrastructureStructMapping.class );

	protected DataCompanyPersonId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyPersonId.of(id);
	}
	protected Long map(DataCompanyPersonId dataCompanyPersonId){
		if (dataCompanyPersonId == null) {
			return null;
		}
		return dataCompanyPersonId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyPersonInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyPersonDO
	 * @return
	 */
	public abstract DataCompanyPerson dataCompanyPersonDOToDataCompanyPerson(@MappingTarget DataCompanyPerson dataCompanyPerson,DataCompanyPersonDO dataCompanyPersonDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyPersonInfrastructureStructMapping#map(DataCompanyPersonId)}
	 * @param dataCompanyPerson
	 * @return
	 */
	public abstract DataCompanyPersonDO dataCompanyPersonToDataCompanyPersonDO(DataCompanyPerson dataCompanyPerson);

}
