package com.particle.openplatform.infrastructure.doc.structmapping;

import com.particle.openplatform.domain.doc.OpenplatformDocDir;
import com.particle.openplatform.domain.doc.OpenplatformDocDirId;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocDirDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 开放接口目录 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:55:42
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpenplatformDocDirInfrastructureStructMapping {
	public static OpenplatformDocDirInfrastructureStructMapping instance = Mappers.getMapper( OpenplatformDocDirInfrastructureStructMapping.class );

	protected OpenplatformDocDirId map(Long id){
		if (id == null) {
			return null;
		}
		return OpenplatformDocDirId.of(id);
	}
	protected Long map(OpenplatformDocDirId openplatformDocDirId){
		if (openplatformDocDirId == null) {
			return null;
		}
		return openplatformDocDirId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformDocDirInfrastructureStructMapping#map(java.lang.Long)}
	 * @param openplatformDocDirDO
	 * @return
	 */
	public abstract OpenplatformDocDir openplatformDocDirDOToOpenplatformDocDir(@MappingTarget OpenplatformDocDir openplatformDocDir,OpenplatformDocDirDO openplatformDocDirDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformDocDirInfrastructureStructMapping#map(OpenplatformDocDirId)}
	 * @param openplatformDocDir
	 * @return
	 */
	public abstract OpenplatformDocDirDO openplatformDocDirToOpenplatformDocDirDO(OpenplatformDocDir openplatformDocDir);

}
