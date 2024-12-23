package com.particle.openplatform.infrastructure.doc.structmapping;

import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplateResponseCode;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplateResponseCodeId;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocTemplateResponseCodeDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 开放接口文档模板响应码 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:49:10
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpenplatformDocApiDocTemplateResponseCodeInfrastructureStructMapping {
	public static OpenplatformDocApiDocTemplateResponseCodeInfrastructureStructMapping instance = Mappers.getMapper( OpenplatformDocApiDocTemplateResponseCodeInfrastructureStructMapping.class );

	protected OpenplatformDocApiDocTemplateResponseCodeId map(Long id){
		if (id == null) {
			return null;
		}
		return OpenplatformDocApiDocTemplateResponseCodeId.of(id);
	}
	protected Long map(OpenplatformDocApiDocTemplateResponseCodeId openplatformDocApiDocTemplateResponseCodeId){
		if (openplatformDocApiDocTemplateResponseCodeId == null) {
			return null;
		}
		return openplatformDocApiDocTemplateResponseCodeId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformDocApiDocTemplateResponseCodeInfrastructureStructMapping#map(java.lang.Long)}
	 * @param openplatformDocApiDocTemplateResponseCodeDO
	 * @return
	 */
	public abstract OpenplatformDocApiDocTemplateResponseCode openplatformDocApiDocTemplateResponseCodeDOToOpenplatformDocApiDocTemplateResponseCode(@MappingTarget OpenplatformDocApiDocTemplateResponseCode openplatformDocApiDocTemplateResponseCode,OpenplatformDocApiDocTemplateResponseCodeDO openplatformDocApiDocTemplateResponseCodeDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformDocApiDocTemplateResponseCodeInfrastructureStructMapping#map(OpenplatformDocApiDocTemplateResponseCodeId)}
	 * @param openplatformDocApiDocTemplateResponseCode
	 * @return
	 */
	public abstract OpenplatformDocApiDocTemplateResponseCodeDO openplatformDocApiDocTemplateResponseCodeToOpenplatformDocApiDocTemplateResponseCodeDO(OpenplatformDocApiDocTemplateResponseCode openplatformDocApiDocTemplateResponseCode);

}
