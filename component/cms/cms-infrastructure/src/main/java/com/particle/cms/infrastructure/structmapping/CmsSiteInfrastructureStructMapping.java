package com.particle.cms.infrastructure.structmapping;

import com.particle.cms.infrastructure.dos.CmsSiteDO;
import com.particle.cms.domain.CmsSite;
import com.particle.cms.domain.CmsSiteId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 站点 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:04
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CmsSiteInfrastructureStructMapping {
	public static CmsSiteInfrastructureStructMapping instance = Mappers.getMapper( CmsSiteInfrastructureStructMapping.class );

	protected CmsSiteId map(Long id){
		if (id == null) {
			return null;
		}
		return CmsSiteId.of(id);
	}
	protected Long map(CmsSiteId cmsSiteId){
		if (cmsSiteId == null) {
			return null;
		}
		return cmsSiteId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CmsSiteInfrastructureStructMapping#map(java.lang.Long)}
	 * @param cmsSiteDO
	 * @return
	 */
	public abstract CmsSite cmsSiteDOToCmsSite(@MappingTarget CmsSite cmsSite,CmsSiteDO cmsSiteDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CmsSiteInfrastructureStructMapping#map(CmsSiteId)}
	 * @param cmsSite
	 * @return
	 */
	public abstract CmsSiteDO cmsSiteToCmsSiteDO(CmsSite cmsSite);

}
