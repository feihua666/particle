package com.particle.agi.infrastructure.rag.structmapping;

import com.particle.agi.infrastructure.rag.dos.AgiVectorStoreRawDocumentSegmentDO;
import com.particle.agi.domain.rag.AgiVectorStoreRawDocumentSegment;
import com.particle.agi.domain.rag.AgiVectorStoreRawDocumentSegmentId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 知识存储原始文档片段 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-01-08 16:55:48
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AgiVectorStoreRawDocumentSegmentInfrastructureStructMapping {
	public static AgiVectorStoreRawDocumentSegmentInfrastructureStructMapping instance = Mappers.getMapper( AgiVectorStoreRawDocumentSegmentInfrastructureStructMapping.class );

	protected AgiVectorStoreRawDocumentSegmentId map(Long id){
		if (id == null) {
			return null;
		}
		return AgiVectorStoreRawDocumentSegmentId.of(id);
	}
	protected Long map(AgiVectorStoreRawDocumentSegmentId agiVectorStoreRawDocumentSegmentId){
		if (agiVectorStoreRawDocumentSegmentId == null) {
			return null;
		}
		return agiVectorStoreRawDocumentSegmentId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AgiVectorStoreRawDocumentSegmentInfrastructureStructMapping#map(java.lang.Long)}
	 * @param agiVectorStoreRawDocumentSegmentDO
	 * @return
	 */
	public abstract AgiVectorStoreRawDocumentSegment agiVectorStoreRawDocumentSegmentDOToAgiVectorStoreRawDocumentSegment(@MappingTarget AgiVectorStoreRawDocumentSegment agiVectorStoreRawDocumentSegment,AgiVectorStoreRawDocumentSegmentDO agiVectorStoreRawDocumentSegmentDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AgiVectorStoreRawDocumentSegmentInfrastructureStructMapping#map(AgiVectorStoreRawDocumentSegmentId)}
	 * @param agiVectorStoreRawDocumentSegment
	 * @return
	 */
	public abstract AgiVectorStoreRawDocumentSegmentDO agiVectorStoreRawDocumentSegmentToAgiVectorStoreRawDocumentSegmentDO(AgiVectorStoreRawDocumentSegment agiVectorStoreRawDocumentSegment);

}
