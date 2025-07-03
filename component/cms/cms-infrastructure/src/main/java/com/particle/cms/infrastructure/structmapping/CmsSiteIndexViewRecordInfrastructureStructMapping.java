package com.particle.cms.infrastructure.structmapping;

import com.particle.cms.infrastructure.dos.CmsSiteIndexViewRecordDO;
import com.particle.cms.domain.CmsSiteIndexViewRecord;
import com.particle.cms.domain.CmsSiteIndexViewRecordId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 站点首页访问记录 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:10
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CmsSiteIndexViewRecordInfrastructureStructMapping {
	public static CmsSiteIndexViewRecordInfrastructureStructMapping instance = Mappers.getMapper( CmsSiteIndexViewRecordInfrastructureStructMapping.class );

	protected CmsSiteIndexViewRecordId map(Long id){
		if (id == null) {
			return null;
		}
		return CmsSiteIndexViewRecordId.of(id);
	}
	protected Long map(CmsSiteIndexViewRecordId cmsSiteIndexViewRecordId){
		if (cmsSiteIndexViewRecordId == null) {
			return null;
		}
		return cmsSiteIndexViewRecordId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CmsSiteIndexViewRecordInfrastructureStructMapping#map(java.lang.Long)}
	 * @param cmsSiteIndexViewRecordDO
	 * @return
	 */
	public abstract CmsSiteIndexViewRecord cmsSiteIndexViewRecordDOToCmsSiteIndexViewRecord(@MappingTarget CmsSiteIndexViewRecord cmsSiteIndexViewRecord,CmsSiteIndexViewRecordDO cmsSiteIndexViewRecordDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CmsSiteIndexViewRecordInfrastructureStructMapping#map(CmsSiteIndexViewRecordId)}
	 * @param cmsSiteIndexViewRecord
	 * @return
	 */
	public abstract CmsSiteIndexViewRecordDO cmsSiteIndexViewRecordToCmsSiteIndexViewRecordDO(CmsSiteIndexViewRecord cmsSiteIndexViewRecord);

}
