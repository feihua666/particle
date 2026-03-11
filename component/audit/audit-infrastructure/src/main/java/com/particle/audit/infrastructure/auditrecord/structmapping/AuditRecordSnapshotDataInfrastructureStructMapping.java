package com.particle.audit.infrastructure.auditrecord.structmapping;

import com.particle.audit.infrastructure.auditrecord.dos.AuditRecordSnapshotDataDO;
import com.particle.audit.domain.auditrecord.AuditRecordSnapshotData;
import com.particle.audit.domain.auditrecord.AuditRecordSnapshotDataId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 审核记录数据快照 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:58:02
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AuditRecordSnapshotDataInfrastructureStructMapping {
	public static AuditRecordSnapshotDataInfrastructureStructMapping instance = Mappers.getMapper( AuditRecordSnapshotDataInfrastructureStructMapping.class );

	protected AuditRecordSnapshotDataId map(Long id){
		if (id == null) {
			return null;
		}
		return AuditRecordSnapshotDataId.of(id);
	}
	protected Long map(AuditRecordSnapshotDataId auditRecordSnapshotDataId){
		if (auditRecordSnapshotDataId == null) {
			return null;
		}
		return auditRecordSnapshotDataId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AuditRecordSnapshotDataInfrastructureStructMapping#map(java.lang.Long)}
	 * @param auditRecordSnapshotDataDO
	 * @return
	 */
	public abstract AuditRecordSnapshotData auditRecordSnapshotDataDOToAuditRecordSnapshotData(@MappingTarget AuditRecordSnapshotData auditRecordSnapshotData,AuditRecordSnapshotDataDO auditRecordSnapshotDataDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AuditRecordSnapshotDataInfrastructureStructMapping#map(AuditRecordSnapshotDataId)}
	 * @param auditRecordSnapshotData
	 * @return
	 */
	public abstract AuditRecordSnapshotDataDO auditRecordSnapshotDataToAuditRecordSnapshotDataDO(AuditRecordSnapshotData auditRecordSnapshotData);

}
