package com.particle.audit.infrastructure.auditrecord.structmapping;

import com.particle.audit.infrastructure.auditrecord.dos.AuditRecordDO;
import com.particle.audit.domain.auditrecord.AuditRecord;
import com.particle.audit.domain.auditrecord.AuditRecordId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 审核记录 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:57:45
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AuditRecordInfrastructureStructMapping {
	public static AuditRecordInfrastructureStructMapping instance = Mappers.getMapper( AuditRecordInfrastructureStructMapping.class );

	protected AuditRecordId map(Long id){
		if (id == null) {
			return null;
		}
		return AuditRecordId.of(id);
	}
	protected Long map(AuditRecordId auditRecordId){
		if (auditRecordId == null) {
			return null;
		}
		return auditRecordId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AuditRecordInfrastructureStructMapping#map(java.lang.Long)}
	 * @param auditRecordDO
	 * @return
	 */
	public abstract AuditRecord auditRecordDOToAuditRecord(@MappingTarget AuditRecord auditRecord,AuditRecordDO auditRecordDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AuditRecordInfrastructureStructMapping#map(AuditRecordId)}
	 * @param auditRecord
	 * @return
	 */
	public abstract AuditRecordDO auditRecordToAuditRecordDO(AuditRecord auditRecord);

}
