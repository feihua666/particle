package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyIprWorkCopyrightDO;
import com.particle.data.domain.company.DataCompanyIprWorkCopyright;
import com.particle.data.domain.company.DataCompanyIprWorkCopyrightId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业知识产权作品著作 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:45
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprWorkCopyrightInfrastructureStructMapping {
	public static DataCompanyIprWorkCopyrightInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyIprWorkCopyrightInfrastructureStructMapping.class );

	protected DataCompanyIprWorkCopyrightId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyIprWorkCopyrightId.of(id);
	}
	protected Long map(DataCompanyIprWorkCopyrightId dataCompanyIprWorkCopyrightId){
		if (dataCompanyIprWorkCopyrightId == null) {
			return null;
		}
		return dataCompanyIprWorkCopyrightId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprWorkCopyrightInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyIprWorkCopyrightDO
	 * @return
	 */
	public abstract DataCompanyIprWorkCopyright dataCompanyIprWorkCopyrightDOToDataCompanyIprWorkCopyright(@MappingTarget DataCompanyIprWorkCopyright dataCompanyIprWorkCopyright,DataCompanyIprWorkCopyrightDO dataCompanyIprWorkCopyrightDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprWorkCopyrightInfrastructureStructMapping#map(DataCompanyIprWorkCopyrightId)}
	 * @param dataCompanyIprWorkCopyright
	 * @return
	 */
	public abstract DataCompanyIprWorkCopyrightDO dataCompanyIprWorkCopyrightToDataCompanyIprWorkCopyrightDO(DataCompanyIprWorkCopyright dataCompanyIprWorkCopyright);

}
