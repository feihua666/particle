package com.particle.openplatform.infrastructure.doc.structmapping;

import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocResponseCodeDO;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocResponseCode;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocResponseCodeId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 开放接口文档响应码 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-03-18 14:44:43
 */
@Mapper
public abstract class OpenplatformDocApiDocResponseCodeInfrastructureStructMapping {
	public static OpenplatformDocApiDocResponseCodeInfrastructureStructMapping instance = Mappers.getMapper( OpenplatformDocApiDocResponseCodeInfrastructureStructMapping.class );

	protected OpenplatformDocApiDocResponseCodeId map(Long id){
		if (id == null) {
			return null;
		}
		return OpenplatformDocApiDocResponseCodeId.of(id);
	}
	protected Long map(OpenplatformDocApiDocResponseCodeId openplatformDocApiDocResponseCodeId){
		if (openplatformDocApiDocResponseCodeId == null) {
			return null;
		}
		return openplatformDocApiDocResponseCodeId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformDocApiDocResponseCodeInfrastructureStructMapping#map(java.lang.Long)}
	 * @param openplatformDocApiDocResponseCodeDO
	 * @return
	 */
	public abstract OpenplatformDocApiDocResponseCode openplatformDocApiDocResponseCodeDOToOpenplatformDocApiDocResponseCode(@MappingTarget OpenplatformDocApiDocResponseCode openplatformDocApiDocResponseCode,OpenplatformDocApiDocResponseCodeDO openplatformDocApiDocResponseCodeDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformDocApiDocResponseCodeInfrastructureStructMapping#map(OpenplatformDocApiDocResponseCodeId)}
	 * @param openplatformDocApiDocResponseCode
	 * @return
	 */
	public abstract OpenplatformDocApiDocResponseCodeDO openplatformDocApiDocResponseCodeToOpenplatformDocApiDocResponseCodeDO(OpenplatformDocApiDocResponseCode openplatformDocApiDocResponseCode);

}
