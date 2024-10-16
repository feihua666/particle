package com.particle.openplatform.infrastructure.app.structmapping;

import com.particle.openplatform.infrastructure.app.dos.OpenplatformAppQuotaDO;
import com.particle.openplatform.domain.app.OpenplatformAppQuota;
import com.particle.openplatform.domain.app.OpenplatformAppQuotaId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 开放平台应用额度 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-10-16 10:38:41
 */
@Mapper
public abstract class OpenplatformAppQuotaInfrastructureStructMapping {
	public static OpenplatformAppQuotaInfrastructureStructMapping instance = Mappers.getMapper( OpenplatformAppQuotaInfrastructureStructMapping.class );

	protected OpenplatformAppQuotaId map(Long id){
		if (id == null) {
			return null;
		}
		return OpenplatformAppQuotaId.of(id);
	}
	protected Long map(OpenplatformAppQuotaId openplatformAppQuotaId){
		if (openplatformAppQuotaId == null) {
			return null;
		}
		return openplatformAppQuotaId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformAppQuotaInfrastructureStructMapping#map(java.lang.Long)}
	 * @param openplatformAppQuotaDO
	 * @return
	 */
	public abstract OpenplatformAppQuota openplatformAppQuotaDOToOpenplatformAppQuota(@MappingTarget OpenplatformAppQuota openplatformAppQuota,OpenplatformAppQuotaDO openplatformAppQuotaDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformAppQuotaInfrastructureStructMapping#map(OpenplatformAppQuotaId)}
	 * @param openplatformAppQuota
	 * @return
	 */
	public abstract OpenplatformAppQuotaDO openplatformAppQuotaToOpenplatformAppQuotaDO(OpenplatformAppQuota openplatformAppQuota);

}
