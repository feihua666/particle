package com.particle.agi.infrastructure.rag.structmapping;

import com.particle.agi.infrastructure.rag.dos.AgiVectorStoreRawDocumentDO;
import com.particle.agi.domain.rag.AgiVectorStoreRawDocument;
import com.particle.agi.domain.rag.AgiVectorStoreRawDocumentId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 知识存储原始文档 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-01-08 16:54:59
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AgiVectorStoreRawDocumentInfrastructureStructMapping {
	public static AgiVectorStoreRawDocumentInfrastructureStructMapping instance = Mappers.getMapper( AgiVectorStoreRawDocumentInfrastructureStructMapping.class );

	protected AgiVectorStoreRawDocumentId map(Long id){
		if (id == null) {
			return null;
		}
		return AgiVectorStoreRawDocumentId.of(id);
	}
	protected Long map(AgiVectorStoreRawDocumentId agiVectorStoreRawDocumentId){
		if (agiVectorStoreRawDocumentId == null) {
			return null;
		}
		return agiVectorStoreRawDocumentId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AgiVectorStoreRawDocumentInfrastructureStructMapping#map(java.lang.Long)}
	 * @param agiVectorStoreRawDocumentDO
	 * @return
	 */
	public abstract AgiVectorStoreRawDocument agiVectorStoreRawDocumentDOToAgiVectorStoreRawDocument(@MappingTarget AgiVectorStoreRawDocument agiVectorStoreRawDocument,AgiVectorStoreRawDocumentDO agiVectorStoreRawDocumentDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AgiVectorStoreRawDocumentInfrastructureStructMapping#map(AgiVectorStoreRawDocumentId)}
	 * @param agiVectorStoreRawDocument
	 * @return
	 */
	public abstract AgiVectorStoreRawDocumentDO agiVectorStoreRawDocumentToAgiVectorStoreRawDocumentDO(AgiVectorStoreRawDocument agiVectorStoreRawDocument);

}
