package com.particle.cms.infrastructure.structmapping;

import com.particle.cms.infrastructure.dos.CmsChannelViewRecordDO;
import com.particle.cms.domain.CmsChannelViewRecord;
import com.particle.cms.domain.CmsChannelViewRecordId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 栏目访问记录 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:22
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CmsChannelViewRecordInfrastructureStructMapping {
	public static CmsChannelViewRecordInfrastructureStructMapping instance = Mappers.getMapper( CmsChannelViewRecordInfrastructureStructMapping.class );

	protected CmsChannelViewRecordId map(Long id){
		if (id == null) {
			return null;
		}
		return CmsChannelViewRecordId.of(id);
	}
	protected Long map(CmsChannelViewRecordId cmsChannelViewRecordId){
		if (cmsChannelViewRecordId == null) {
			return null;
		}
		return cmsChannelViewRecordId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CmsChannelViewRecordInfrastructureStructMapping#map(java.lang.Long)}
	 * @param cmsChannelViewRecordDO
	 * @return
	 */
	public abstract CmsChannelViewRecord cmsChannelViewRecordDOToCmsChannelViewRecord(@MappingTarget CmsChannelViewRecord cmsChannelViewRecord,CmsChannelViewRecordDO cmsChannelViewRecordDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CmsChannelViewRecordInfrastructureStructMapping#map(CmsChannelViewRecordId)}
	 * @param cmsChannelViewRecord
	 * @return
	 */
	public abstract CmsChannelViewRecordDO cmsChannelViewRecordToCmsChannelViewRecordDO(CmsChannelViewRecord cmsChannelViewRecord);

}
