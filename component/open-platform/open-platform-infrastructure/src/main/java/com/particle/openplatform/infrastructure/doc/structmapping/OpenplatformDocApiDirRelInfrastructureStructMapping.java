package com.particle.openplatform.infrastructure.doc.structmapping;

import com.particle.openplatform.domain.doc.OpenplatformDocApiDirRel;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDirRelId;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDirRelDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 开放接口文档接口与目录关系 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:20
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpenplatformDocApiDirRelInfrastructureStructMapping {
	public static OpenplatformDocApiDirRelInfrastructureStructMapping instance = Mappers.getMapper( OpenplatformDocApiDirRelInfrastructureStructMapping.class );

	protected OpenplatformDocApiDirRelId map(Long id){
		if (id == null) {
			return null;
		}
		return OpenplatformDocApiDirRelId.of(id);
	}
	protected Long map(OpenplatformDocApiDirRelId openplatformDocApiDirRelId){
		if (openplatformDocApiDirRelId == null) {
			return null;
		}
		return openplatformDocApiDirRelId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformDocApiDirRelInfrastructureStructMapping#map(java.lang.Long)}
	 * @param openplatformDocApiDirRelDO
	 * @return
	 */
	public abstract OpenplatformDocApiDirRel openplatformDocApiDirRelDOToOpenplatformDocApiDirRel(@MappingTarget OpenplatformDocApiDirRel openplatformDocApiDirRel,OpenplatformDocApiDirRelDO openplatformDocApiDirRelDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformDocApiDirRelInfrastructureStructMapping#map(OpenplatformDocApiDirRelId)}
	 * @param openplatformDocApiDirRel
	 * @return
	 */
	public abstract OpenplatformDocApiDirRelDO openplatformDocApiDirRelToOpenplatformDocApiDirRelDO(OpenplatformDocApiDirRel openplatformDocApiDirRel);

}
