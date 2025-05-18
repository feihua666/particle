package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentContentDO;
import com.particle.data.domain.company.DataCompanyIprPatentContent;
import com.particle.data.domain.company.DataCompanyIprPatentContentId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业知识产权专利内容 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:27
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprPatentContentInfrastructureStructMapping {
	public static DataCompanyIprPatentContentInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyIprPatentContentInfrastructureStructMapping.class );

	protected DataCompanyIprPatentContentId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyIprPatentContentId.of(id);
	}
	protected Long map(DataCompanyIprPatentContentId dataCompanyIprPatentContentId){
		if (dataCompanyIprPatentContentId == null) {
			return null;
		}
		return dataCompanyIprPatentContentId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPatentContentInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyIprPatentContentDO
	 * @return
	 */
	public abstract DataCompanyIprPatentContent dataCompanyIprPatentContentDOToDataCompanyIprPatentContent(@MappingTarget DataCompanyIprPatentContent dataCompanyIprPatentContent,DataCompanyIprPatentContentDO dataCompanyIprPatentContentDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPatentContentInfrastructureStructMapping#map(DataCompanyIprPatentContentId)}
	 * @param dataCompanyIprPatentContent
	 * @return
	 */
	public abstract DataCompanyIprPatentContentDO dataCompanyIprPatentContentToDataCompanyIprPatentContentDO(DataCompanyIprPatentContent dataCompanyIprPatentContent);

}
