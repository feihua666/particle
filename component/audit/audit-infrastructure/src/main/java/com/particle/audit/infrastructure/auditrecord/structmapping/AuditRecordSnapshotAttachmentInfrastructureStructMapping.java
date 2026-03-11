package com.particle.audit.infrastructure.auditrecord.structmapping;

import com.particle.audit.infrastructure.auditrecord.dos.AuditRecordSnapshotAttachmentDO;
import com.particle.audit.domain.auditrecord.AuditRecordSnapshotAttachment;
import com.particle.audit.domain.auditrecord.AuditRecordSnapshotAttachmentId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 审核记录附件快照 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2026-01-19 15:37:34
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AuditRecordSnapshotAttachmentInfrastructureStructMapping {
	public static AuditRecordSnapshotAttachmentInfrastructureStructMapping instance = Mappers.getMapper( AuditRecordSnapshotAttachmentInfrastructureStructMapping.class );

	protected AuditRecordSnapshotAttachmentId map(Long id){
		if (id == null) {
			return null;
		}
		return AuditRecordSnapshotAttachmentId.of(id);
	}
	protected Long map(AuditRecordSnapshotAttachmentId auditRecordSnapshotAttachmentId){
		if (auditRecordSnapshotAttachmentId == null) {
			return null;
		}
		return auditRecordSnapshotAttachmentId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AuditRecordSnapshotAttachmentInfrastructureStructMapping#map(java.lang.Long)}
	 * @param auditRecordSnapshotAttachmentDO
	 * @return
	 */
	public abstract AuditRecordSnapshotAttachment auditRecordSnapshotAttachmentDOToAuditRecordSnapshotAttachment(@MappingTarget AuditRecordSnapshotAttachment auditRecordSnapshotAttachment,AuditRecordSnapshotAttachmentDO auditRecordSnapshotAttachmentDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AuditRecordSnapshotAttachmentInfrastructureStructMapping#map(AuditRecordSnapshotAttachmentId)}
	 * @param auditRecordSnapshotAttachment
	 * @return
	 */
	public abstract AuditRecordSnapshotAttachmentDO auditRecordSnapshotAttachmentToAuditRecordSnapshotAttachmentDO(AuditRecordSnapshotAttachment auditRecordSnapshotAttachment);

}
