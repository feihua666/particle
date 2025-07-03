package com.particle.cms.infrastructure.structmapping;

import com.particle.cms.infrastructure.dos.CmsContentMultimediaDO;
import com.particle.cms.domain.CmsContentMultimedia;
import com.particle.cms.domain.CmsContentMultimediaId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 内容多媒体 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:28
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CmsContentMultimediaInfrastructureStructMapping {
	public static CmsContentMultimediaInfrastructureStructMapping instance = Mappers.getMapper( CmsContentMultimediaInfrastructureStructMapping.class );

	protected CmsContentMultimediaId map(Long id){
		if (id == null) {
			return null;
		}
		return CmsContentMultimediaId.of(id);
	}
	protected Long map(CmsContentMultimediaId cmsContentMultimediaId){
		if (cmsContentMultimediaId == null) {
			return null;
		}
		return cmsContentMultimediaId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CmsContentMultimediaInfrastructureStructMapping#map(java.lang.Long)}
	 * @param cmsContentMultimediaDO
	 * @return
	 */
	public abstract CmsContentMultimedia cmsContentMultimediaDOToCmsContentMultimedia(@MappingTarget CmsContentMultimedia cmsContentMultimedia,CmsContentMultimediaDO cmsContentMultimediaDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CmsContentMultimediaInfrastructureStructMapping#map(CmsContentMultimediaId)}
	 * @param cmsContentMultimedia
	 * @return
	 */
	public abstract CmsContentMultimediaDO cmsContentMultimediaToCmsContentMultimediaDO(CmsContentMultimedia cmsContentMultimedia);

}
