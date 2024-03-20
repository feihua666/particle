package com.particle.openplatform.infrastructure.doc.structmapping;

import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocParamFieldDO;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocParamField;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocParamFieldId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 开放接口文档参数字段 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:55
 */
@Mapper
public abstract class OpenplatformDocApiDocParamFieldInfrastructureStructMapping {
	public static OpenplatformDocApiDocParamFieldInfrastructureStructMapping instance = Mappers.getMapper( OpenplatformDocApiDocParamFieldInfrastructureStructMapping.class );

	protected OpenplatformDocApiDocParamFieldId map(Long id){
		if (id == null) {
			return null;
		}
		return OpenplatformDocApiDocParamFieldId.of(id);
	}
	protected Long map(OpenplatformDocApiDocParamFieldId openplatformDocApiDocParamFieldId){
		if (openplatformDocApiDocParamFieldId == null) {
			return null;
		}
		return openplatformDocApiDocParamFieldId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformDocApiDocParamFieldInfrastructureStructMapping#map(java.lang.Long)}
	 * @param openplatformDocApiDocParamFieldDO
	 * @return
	 */
	public abstract OpenplatformDocApiDocParamField openplatformDocApiDocParamFieldDOToOpenplatformDocApiDocParamField(@MappingTarget OpenplatformDocApiDocParamField openplatformDocApiDocParamField,OpenplatformDocApiDocParamFieldDO openplatformDocApiDocParamFieldDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformDocApiDocParamFieldInfrastructureStructMapping#map(OpenplatformDocApiDocParamFieldId)}
	 * @param openplatformDocApiDocParamField
	 * @return
	 */
	public abstract OpenplatformDocApiDocParamFieldDO openplatformDocApiDocParamFieldToOpenplatformDocApiDocParamFieldDO(OpenplatformDocApiDocParamField openplatformDocApiDocParamField);

}
