package com.particle.openplatform.infrastructure.doc.structmapping;

import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocTemplateDO;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplate;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplateId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 开放接口文档模板 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:48:39
 */
@Mapper
public abstract class OpenplatformDocApiDocTemplateInfrastructureStructMapping {
	public static OpenplatformDocApiDocTemplateInfrastructureStructMapping instance = Mappers.getMapper( OpenplatformDocApiDocTemplateInfrastructureStructMapping.class );

	protected OpenplatformDocApiDocTemplateId map(Long id){
		if (id == null) {
			return null;
		}
		return OpenplatformDocApiDocTemplateId.of(id);
	}
	protected Long map(OpenplatformDocApiDocTemplateId openplatformDocApiDocTemplateId){
		if (openplatformDocApiDocTemplateId == null) {
			return null;
		}
		return openplatformDocApiDocTemplateId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformDocApiDocTemplateInfrastructureStructMapping#map(java.lang.Long)}
	 * @param openplatformDocApiDocTemplateDO
	 * @return
	 */
	public abstract OpenplatformDocApiDocTemplate openplatformDocApiDocTemplateDOToOpenplatformDocApiDocTemplate(@MappingTarget OpenplatformDocApiDocTemplate openplatformDocApiDocTemplate,OpenplatformDocApiDocTemplateDO openplatformDocApiDocTemplateDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformDocApiDocTemplateInfrastructureStructMapping#map(OpenplatformDocApiDocTemplateId)}
	 * @param openplatformDocApiDocTemplate
	 * @return
	 */
	public abstract OpenplatformDocApiDocTemplateDO openplatformDocApiDocTemplateToOpenplatformDocApiDocTemplateDO(OpenplatformDocApiDocTemplate openplatformDocApiDocTemplate);

}
