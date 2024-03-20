package com.particle.openplatform.infrastructure.doc.structmapping;

import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocDirNameDO;
import com.particle.openplatform.domain.doc.OpenplatformDocDirName;
import com.particle.openplatform.domain.doc.OpenplatformDocDirNameId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 开放接口目录名称 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:53:48
 */
@Mapper
public abstract class OpenplatformDocDirNameInfrastructureStructMapping {
	public static OpenplatformDocDirNameInfrastructureStructMapping instance = Mappers.getMapper( OpenplatformDocDirNameInfrastructureStructMapping.class );

	protected OpenplatformDocDirNameId map(Long id){
		if (id == null) {
			return null;
		}
		return OpenplatformDocDirNameId.of(id);
	}
	protected Long map(OpenplatformDocDirNameId openplatformDocDirNameId){
		if (openplatformDocDirNameId == null) {
			return null;
		}
		return openplatformDocDirNameId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformDocDirNameInfrastructureStructMapping#map(java.lang.Long)}
	 * @param openplatformDocDirNameDO
	 * @return
	 */
	public abstract OpenplatformDocDirName openplatformDocDirNameDOToOpenplatformDocDirName(@MappingTarget OpenplatformDocDirName openplatformDocDirName,OpenplatformDocDirNameDO openplatformDocDirNameDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformDocDirNameInfrastructureStructMapping#map(OpenplatformDocDirNameId)}
	 * @param openplatformDocDirName
	 * @return
	 */
	public abstract OpenplatformDocDirNameDO openplatformDocDirNameToOpenplatformDocDirNameDO(OpenplatformDocDirName openplatformDocDirName);

}
