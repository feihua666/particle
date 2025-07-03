package com.particle.cms.infrastructure.structmapping;

import com.particle.cms.infrastructure.dos.CmsContentCategoryDO;
import com.particle.cms.domain.CmsContentCategory;
import com.particle.cms.domain.CmsContentCategoryId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 内容分类 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:40
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CmsContentCategoryInfrastructureStructMapping {
	public static CmsContentCategoryInfrastructureStructMapping instance = Mappers.getMapper( CmsContentCategoryInfrastructureStructMapping.class );

	protected CmsContentCategoryId map(Long id){
		if (id == null) {
			return null;
		}
		return CmsContentCategoryId.of(id);
	}
	protected Long map(CmsContentCategoryId cmsContentCategoryId){
		if (cmsContentCategoryId == null) {
			return null;
		}
		return cmsContentCategoryId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CmsContentCategoryInfrastructureStructMapping#map(java.lang.Long)}
	 * @param cmsContentCategoryDO
	 * @return
	 */
	public abstract CmsContentCategory cmsContentCategoryDOToCmsContentCategory(@MappingTarget CmsContentCategory cmsContentCategory,CmsContentCategoryDO cmsContentCategoryDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CmsContentCategoryInfrastructureStructMapping#map(CmsContentCategoryId)}
	 * @param cmsContentCategory
	 * @return
	 */
	public abstract CmsContentCategoryDO cmsContentCategoryToCmsContentCategoryDO(CmsContentCategory cmsContentCategory);

}
