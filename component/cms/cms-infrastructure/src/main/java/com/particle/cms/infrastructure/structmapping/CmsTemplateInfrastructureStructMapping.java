package com.particle.cms.infrastructure.structmapping;

import com.particle.cms.infrastructure.dos.CmsTemplateDO;
import com.particle.cms.domain.CmsTemplate;
import com.particle.cms.domain.CmsTemplateId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 模板 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2026-01-21 21:03:36
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CmsTemplateInfrastructureStructMapping {
	public static CmsTemplateInfrastructureStructMapping instance = Mappers.getMapper( CmsTemplateInfrastructureStructMapping.class );

	protected CmsTemplateId map(Long id){
		if (id == null) {
			return null;
		}
		return CmsTemplateId.of(id);
	}
	protected Long map(CmsTemplateId cmsTemplateId){
		if (cmsTemplateId == null) {
			return null;
		}
		return cmsTemplateId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CmsTemplateInfrastructureStructMapping#map(java.lang.Long)}
	 * @param cmsTemplateDO
	 * @return
	 */
	public abstract CmsTemplate cmsTemplateDOToCmsTemplate(@MappingTarget CmsTemplate cmsTemplate,CmsTemplateDO cmsTemplateDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CmsTemplateInfrastructureStructMapping#map(CmsTemplateId)}
	 * @param cmsTemplate
	 * @return
	 */
	public abstract CmsTemplateDO cmsTemplateToCmsTemplateDO(CmsTemplate cmsTemplate);

}
