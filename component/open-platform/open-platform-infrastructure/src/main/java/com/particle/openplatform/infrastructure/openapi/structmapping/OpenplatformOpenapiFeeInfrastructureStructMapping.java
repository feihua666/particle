package com.particle.openplatform.infrastructure.openapi.structmapping;

import com.particle.openplatform.infrastructure.openapi.dos.OpenplatformOpenapiFeeDO;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiFee;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiFeeId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 开放平台开放接口费用 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:59:32
 */
@Mapper
public abstract class OpenplatformOpenapiFeeInfrastructureStructMapping {
	public static OpenplatformOpenapiFeeInfrastructureStructMapping instance = Mappers.getMapper( OpenplatformOpenapiFeeInfrastructureStructMapping.class );

	protected OpenplatformOpenapiFeeId map(Long id){
		if (id == null) {
			return null;
		}
		return OpenplatformOpenapiFeeId.of(id);
	}
	protected Long map(OpenplatformOpenapiFeeId openplatformOpenapiFeeId){
		if (openplatformOpenapiFeeId == null) {
			return null;
		}
		return openplatformOpenapiFeeId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformOpenapiFeeInfrastructureStructMapping#map(java.lang.Long)}
	 * @param openplatformOpenapiFeeDO
	 * @return
	 */
	public abstract OpenplatformOpenapiFee openplatformOpenapiFeeDOToOpenplatformOpenapiFee(@MappingTarget OpenplatformOpenapiFee openplatformOpenapiFee,OpenplatformOpenapiFeeDO openplatformOpenapiFeeDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformOpenapiFeeInfrastructureStructMapping#map(OpenplatformOpenapiFeeId)}
	 * @param openplatformOpenapiFee
	 * @return
	 */
	public abstract OpenplatformOpenapiFeeDO openplatformOpenapiFeeToOpenplatformOpenapiFeeDO(OpenplatformOpenapiFee openplatformOpenapiFee);

}
