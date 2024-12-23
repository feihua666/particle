package com.particle.openplatform.infrastructure.doc.structmapping;

import com.particle.openplatform.domain.doc.OpenplatformDocApiDocExampleCode;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocExampleCodeId;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocExampleCodeDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 开放接口文档示例代码 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-03-18 17:04:11
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpenplatformDocApiDocExampleCodeInfrastructureStructMapping {
	public static OpenplatformDocApiDocExampleCodeInfrastructureStructMapping instance = Mappers.getMapper( OpenplatformDocApiDocExampleCodeInfrastructureStructMapping.class );

	protected OpenplatformDocApiDocExampleCodeId map(Long id){
		if (id == null) {
			return null;
		}
		return OpenplatformDocApiDocExampleCodeId.of(id);
	}
	protected Long map(OpenplatformDocApiDocExampleCodeId openplatformDocApiDocExampleCodeId){
		if (openplatformDocApiDocExampleCodeId == null) {
			return null;
		}
		return openplatformDocApiDocExampleCodeId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformDocApiDocExampleCodeInfrastructureStructMapping#map(java.lang.Long)}
	 * @param openplatformDocApiDocExampleCodeDO
	 * @return
	 */
	public abstract OpenplatformDocApiDocExampleCode openplatformDocApiDocExampleCodeDOToOpenplatformDocApiDocExampleCode(@MappingTarget OpenplatformDocApiDocExampleCode openplatformDocApiDocExampleCode,OpenplatformDocApiDocExampleCodeDO openplatformDocApiDocExampleCodeDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformDocApiDocExampleCodeInfrastructureStructMapping#map(OpenplatformDocApiDocExampleCodeId)}
	 * @param openplatformDocApiDocExampleCode
	 * @return
	 */
	public abstract OpenplatformDocApiDocExampleCodeDO openplatformDocApiDocExampleCodeToOpenplatformDocApiDocExampleCodeDO(OpenplatformDocApiDocExampleCode openplatformDocApiDocExampleCode);

}
