package com.particle.cms.infrastructure.structmapping;

import com.particle.cms.infrastructure.dos.CmsChannelDO;
import com.particle.cms.domain.CmsChannel;
import com.particle.cms.domain.CmsChannelId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 栏目 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:55
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CmsChannelInfrastructureStructMapping {
	public static CmsChannelInfrastructureStructMapping instance = Mappers.getMapper( CmsChannelInfrastructureStructMapping.class );

	protected CmsChannelId map(Long id){
		if (id == null) {
			return null;
		}
		return CmsChannelId.of(id);
	}
	protected Long map(CmsChannelId cmsChannelId){
		if (cmsChannelId == null) {
			return null;
		}
		return cmsChannelId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CmsChannelInfrastructureStructMapping#map(java.lang.Long)}
	 * @param cmsChannelDO
	 * @return
	 */
	public abstract CmsChannel cmsChannelDOToCmsChannel(@MappingTarget CmsChannel cmsChannel,CmsChannelDO cmsChannelDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CmsChannelInfrastructureStructMapping#map(CmsChannelId)}
	 * @param cmsChannel
	 * @return
	 */
	public abstract CmsChannelDO cmsChannelToCmsChannelDO(CmsChannel cmsChannel);

}
