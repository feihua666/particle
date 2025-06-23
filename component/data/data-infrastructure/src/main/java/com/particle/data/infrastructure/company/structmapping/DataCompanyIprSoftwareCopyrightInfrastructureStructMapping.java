package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyIprSoftwareCopyrightDO;
import com.particle.data.domain.company.DataCompanyIprSoftwareCopyright;
import com.particle.data.domain.company.DataCompanyIprSoftwareCopyrightId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业知识产权软件著作 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:01
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprSoftwareCopyrightInfrastructureStructMapping {
	public static DataCompanyIprSoftwareCopyrightInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyIprSoftwareCopyrightInfrastructureStructMapping.class );

	protected DataCompanyIprSoftwareCopyrightId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyIprSoftwareCopyrightId.of(id);
	}
	protected Long map(DataCompanyIprSoftwareCopyrightId dataCompanyIprSoftwareCopyrightId){
		if (dataCompanyIprSoftwareCopyrightId == null) {
			return null;
		}
		return dataCompanyIprSoftwareCopyrightId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprSoftwareCopyrightInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyIprSoftwareCopyrightDO
	 * @return
	 */
	public abstract DataCompanyIprSoftwareCopyright dataCompanyIprSoftwareCopyrightDOToDataCompanyIprSoftwareCopyright(@MappingTarget DataCompanyIprSoftwareCopyright dataCompanyIprSoftwareCopyright,DataCompanyIprSoftwareCopyrightDO dataCompanyIprSoftwareCopyrightDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprSoftwareCopyrightInfrastructureStructMapping#map(DataCompanyIprSoftwareCopyrightId)}
	 * @param dataCompanyIprSoftwareCopyright
	 * @return
	 */
	public abstract DataCompanyIprSoftwareCopyrightDO dataCompanyIprSoftwareCopyrightToDataCompanyIprSoftwareCopyrightDO(DataCompanyIprSoftwareCopyright dataCompanyIprSoftwareCopyright);

}
