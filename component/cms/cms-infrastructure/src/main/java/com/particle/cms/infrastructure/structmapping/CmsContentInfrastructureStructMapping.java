package com.particle.cms.infrastructure.structmapping;

import com.particle.cms.infrastructure.dos.CmsContentDO;
import com.particle.cms.domain.CmsContent;
import com.particle.cms.domain.CmsContentId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 内容 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:16
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CmsContentInfrastructureStructMapping {
	public static CmsContentInfrastructureStructMapping instance = Mappers.getMapper( CmsContentInfrastructureStructMapping.class );

	protected CmsContentId map(Long id){
		if (id == null) {
			return null;
		}
		return CmsContentId.of(id);
	}
	protected Long map(CmsContentId cmsContentId){
		if (cmsContentId == null) {
			return null;
		}
		return cmsContentId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CmsContentInfrastructureStructMapping#map(java.lang.Long)}
	 * @param cmsContentDO
	 * @return
	 */
	public abstract CmsContent cmsContentDOToCmsContent(@MappingTarget CmsContent cmsContent,CmsContentDO cmsContentDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CmsContentInfrastructureStructMapping#map(CmsContentId)}
	 * @param cmsContent
	 * @return
	 */
	public abstract CmsContentDO cmsContentToCmsContentDO(CmsContent cmsContent);

}
