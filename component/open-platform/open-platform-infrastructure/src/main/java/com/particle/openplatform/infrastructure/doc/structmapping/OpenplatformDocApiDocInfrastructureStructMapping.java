package com.particle.openplatform.infrastructure.doc.structmapping;

import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocDO;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDoc;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 开放接口文档 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:37
 */
@Mapper
public abstract class OpenplatformDocApiDocInfrastructureStructMapping {
	public static OpenplatformDocApiDocInfrastructureStructMapping instance = Mappers.getMapper( OpenplatformDocApiDocInfrastructureStructMapping.class );

	protected OpenplatformDocApiDocId map(Long id){
		if (id == null) {
			return null;
		}
		return OpenplatformDocApiDocId.of(id);
	}
	protected Long map(OpenplatformDocApiDocId openplatformDocApiDocId){
		if (openplatformDocApiDocId == null) {
			return null;
		}
		return openplatformDocApiDocId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformDocApiDocInfrastructureStructMapping#map(java.lang.Long)}
	 * @param openplatformDocApiDocDO
	 * @return
	 */
	public abstract OpenplatformDocApiDoc openplatformDocApiDocDOToOpenplatformDocApiDoc(@MappingTarget OpenplatformDocApiDoc openplatformDocApiDoc,OpenplatformDocApiDocDO openplatformDocApiDocDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformDocApiDocInfrastructureStructMapping#map(OpenplatformDocApiDocId)}
	 * @param openplatformDocApiDoc
	 * @return
	 */
	public abstract OpenplatformDocApiDocDO openplatformDocApiDocToOpenplatformDocApiDocDO(OpenplatformDocApiDoc openplatformDocApiDoc);

}
