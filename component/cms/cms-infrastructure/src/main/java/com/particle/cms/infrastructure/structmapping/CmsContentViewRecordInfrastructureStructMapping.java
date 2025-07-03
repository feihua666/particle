package com.particle.cms.infrastructure.structmapping;

import com.particle.cms.infrastructure.dos.CmsContentViewRecordDO;
import com.particle.cms.domain.CmsContentViewRecord;
import com.particle.cms.domain.CmsContentViewRecordId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 内容访问记录 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:38
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CmsContentViewRecordInfrastructureStructMapping {
	public static CmsContentViewRecordInfrastructureStructMapping instance = Mappers.getMapper( CmsContentViewRecordInfrastructureStructMapping.class );

	protected CmsContentViewRecordId map(Long id){
		if (id == null) {
			return null;
		}
		return CmsContentViewRecordId.of(id);
	}
	protected Long map(CmsContentViewRecordId cmsContentViewRecordId){
		if (cmsContentViewRecordId == null) {
			return null;
		}
		return cmsContentViewRecordId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CmsContentViewRecordInfrastructureStructMapping#map(java.lang.Long)}
	 * @param cmsContentViewRecordDO
	 * @return
	 */
	public abstract CmsContentViewRecord cmsContentViewRecordDOToCmsContentViewRecord(@MappingTarget CmsContentViewRecord cmsContentViewRecord,CmsContentViewRecordDO cmsContentViewRecordDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CmsContentViewRecordInfrastructureStructMapping#map(CmsContentViewRecordId)}
	 * @param cmsContentViewRecord
	 * @return
	 */
	public abstract CmsContentViewRecordDO cmsContentViewRecordToCmsContentViewRecordDO(CmsContentViewRecord cmsContentViewRecord);

}
