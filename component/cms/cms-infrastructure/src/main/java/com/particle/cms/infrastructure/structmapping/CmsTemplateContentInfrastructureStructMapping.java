package com.particle.cms.infrastructure.structmapping;

import com.particle.cms.infrastructure.dos.CmsTemplateContentDO;
import com.particle.cms.domain.CmsTemplateContent;
import com.particle.cms.domain.CmsTemplateContentId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 模板内容 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:13:35
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CmsTemplateContentInfrastructureStructMapping {
	public static CmsTemplateContentInfrastructureStructMapping instance = Mappers.getMapper( CmsTemplateContentInfrastructureStructMapping.class );

	protected CmsTemplateContentId map(Long id){
		if (id == null) {
			return null;
		}
		return CmsTemplateContentId.of(id);
	}
	protected Long map(CmsTemplateContentId cmsTemplateContentId){
		if (cmsTemplateContentId == null) {
			return null;
		}
		return cmsTemplateContentId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CmsTemplateContentInfrastructureStructMapping#map(java.lang.Long)}
	 * @param cmsTemplateContentDO
	 * @return
	 */
	public abstract CmsTemplateContent cmsTemplateContentDOToCmsTemplateContent(@MappingTarget CmsTemplateContent cmsTemplateContent,CmsTemplateContentDO cmsTemplateContentDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CmsTemplateContentInfrastructureStructMapping#map(CmsTemplateContentId)}
	 * @param cmsTemplateContent
	 * @return
	 */
	public abstract CmsTemplateContentDO cmsTemplateContentToCmsTemplateContentDO(CmsTemplateContent cmsTemplateContent);

}
