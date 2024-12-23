package com.particle.openplatform.infrastructure.doc.structmapping;

import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplateExampleCode;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplateExampleCodeId;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocTemplateExampleCodeDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 开放接口文档模板示例代码 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-03-18 17:04:26
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpenplatformDocApiDocTemplateExampleCodeInfrastructureStructMapping {
	public static OpenplatformDocApiDocTemplateExampleCodeInfrastructureStructMapping instance = Mappers.getMapper( OpenplatformDocApiDocTemplateExampleCodeInfrastructureStructMapping.class );

	protected OpenplatformDocApiDocTemplateExampleCodeId map(Long id){
		if (id == null) {
			return null;
		}
		return OpenplatformDocApiDocTemplateExampleCodeId.of(id);
	}
	protected Long map(OpenplatformDocApiDocTemplateExampleCodeId openplatformDocApiDocTemplateExampleCodeId){
		if (openplatformDocApiDocTemplateExampleCodeId == null) {
			return null;
		}
		return openplatformDocApiDocTemplateExampleCodeId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformDocApiDocTemplateExampleCodeInfrastructureStructMapping#map(java.lang.Long)}
	 * @param openplatformDocApiDocTemplateExampleCodeDO
	 * @return
	 */
	public abstract OpenplatformDocApiDocTemplateExampleCode openplatformDocApiDocTemplateExampleCodeDOToOpenplatformDocApiDocTemplateExampleCode(@MappingTarget OpenplatformDocApiDocTemplateExampleCode openplatformDocApiDocTemplateExampleCode,OpenplatformDocApiDocTemplateExampleCodeDO openplatformDocApiDocTemplateExampleCodeDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformDocApiDocTemplateExampleCodeInfrastructureStructMapping#map(OpenplatformDocApiDocTemplateExampleCodeId)}
	 * @param openplatformDocApiDocTemplateExampleCode
	 * @return
	 */
	public abstract OpenplatformDocApiDocTemplateExampleCodeDO openplatformDocApiDocTemplateExampleCodeToOpenplatformDocApiDocTemplateExampleCodeDO(OpenplatformDocApiDocTemplateExampleCode openplatformDocApiDocTemplateExampleCode);

}
