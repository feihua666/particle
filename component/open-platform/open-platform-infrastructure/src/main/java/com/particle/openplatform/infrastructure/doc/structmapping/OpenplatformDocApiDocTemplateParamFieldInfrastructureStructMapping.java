package com.particle.openplatform.infrastructure.doc.structmapping;

import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocTemplateParamFieldDO;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplateParamField;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplateParamFieldId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 开放接口文档模板参数字段 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:48:56
 */
@Mapper
public abstract class OpenplatformDocApiDocTemplateParamFieldInfrastructureStructMapping {
	public static OpenplatformDocApiDocTemplateParamFieldInfrastructureStructMapping instance = Mappers.getMapper( OpenplatformDocApiDocTemplateParamFieldInfrastructureStructMapping.class );

	protected OpenplatformDocApiDocTemplateParamFieldId map(Long id){
		if (id == null) {
			return null;
		}
		return OpenplatformDocApiDocTemplateParamFieldId.of(id);
	}
	protected Long map(OpenplatformDocApiDocTemplateParamFieldId openplatformDocApiDocTemplateParamFieldId){
		if (openplatformDocApiDocTemplateParamFieldId == null) {
			return null;
		}
		return openplatformDocApiDocTemplateParamFieldId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformDocApiDocTemplateParamFieldInfrastructureStructMapping#map(java.lang.Long)}
	 * @param openplatformDocApiDocTemplateParamFieldDO
	 * @return
	 */
	public abstract OpenplatformDocApiDocTemplateParamField openplatformDocApiDocTemplateParamFieldDOToOpenplatformDocApiDocTemplateParamField(@MappingTarget OpenplatformDocApiDocTemplateParamField openplatformDocApiDocTemplateParamField,OpenplatformDocApiDocTemplateParamFieldDO openplatformDocApiDocTemplateParamFieldDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformDocApiDocTemplateParamFieldInfrastructureStructMapping#map(OpenplatformDocApiDocTemplateParamFieldId)}
	 * @param openplatformDocApiDocTemplateParamField
	 * @return
	 */
	public abstract OpenplatformDocApiDocTemplateParamFieldDO openplatformDocApiDocTemplateParamFieldToOpenplatformDocApiDocTemplateParamFieldDO(OpenplatformDocApiDocTemplateParamField openplatformDocApiDocTemplateParamField);

}
