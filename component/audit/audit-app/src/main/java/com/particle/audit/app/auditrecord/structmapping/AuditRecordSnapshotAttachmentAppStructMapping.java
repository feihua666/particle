package com.particle.audit.app.auditrecord.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.audit.client.auditrecord.dto.data.AuditRecordSnapshotAttachmentVO;
import com.particle.audit.domain.auditrecord.AuditRecordSnapshotAttachment;
import com.particle.audit.domain.auditrecord.AuditRecordSnapshotAttachmentId;
import com.particle.audit.infrastructure.auditrecord.dos.AuditRecordSnapshotAttachmentDO;
import com.particle.audit.client.auditrecord.dto.command.representation.AuditRecordSnapshotAttachmentPageQueryCommand;
import com.particle.audit.client.auditrecord.dto.command.representation.AuditRecordSnapshotAttachmentQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 审核记录附件快照 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2026-01-19 15:37:34
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AuditRecordSnapshotAttachmentAppStructMapping  implements IBaseQueryCommandMapStruct<AuditRecordSnapshotAttachmentDO>{
	public static AuditRecordSnapshotAttachmentAppStructMapping instance = Mappers.getMapper( AuditRecordSnapshotAttachmentAppStructMapping.class );

	protected Long map(AuditRecordSnapshotAttachmentId auditRecordSnapshotAttachmentId){
		if (auditRecordSnapshotAttachmentId == null) {
			return null;
		}
		return auditRecordSnapshotAttachmentId.getId();
	}
	/**
	 * 审核记录附件快照领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AuditRecordSnapshotAttachmentAppStructMapping#map(AuditRecordSnapshotAttachmentId)}
	 * @param auditRecordSnapshotAttachment
	 * @return
	 */
	public abstract AuditRecordSnapshotAttachmentVO toAuditRecordSnapshotAttachmentVO(AuditRecordSnapshotAttachment auditRecordSnapshotAttachment);


	/**
	 * 数据对象转视图对象
	 * @param auditRecordSnapshotAttachmentDO
	 * @return
	 */
	public abstract AuditRecordSnapshotAttachmentVO auditRecordSnapshotAttachmentDOToAuditRecordSnapshotAttachmentVO(AuditRecordSnapshotAttachmentDO auditRecordSnapshotAttachmentDO);

	/**
	 * 批量转换
	 * @param auditRecordSnapshotAttachmentDOs
	 * @return
	 */
	public abstract List<AuditRecordSnapshotAttachmentVO> auditRecordSnapshotAttachmentDOsToAuditRecordSnapshotAttachmentVOs(List<AuditRecordSnapshotAttachmentDO> auditRecordSnapshotAttachmentDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<AuditRecordSnapshotAttachmentVO> infrastructurePageToPageResponse(Page<AuditRecordSnapshotAttachmentDO> page) {
		return PageResponse.of(auditRecordSnapshotAttachmentDOsToAuditRecordSnapshotAttachmentVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public AuditRecordSnapshotAttachmentDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof AuditRecordSnapshotAttachmentPageQueryCommand) {
			return pageQueryCommandToDO((AuditRecordSnapshotAttachmentPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof AuditRecordSnapshotAttachmentQueryListCommand) {
			return queryListCommandToDO(((AuditRecordSnapshotAttachmentQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract AuditRecordSnapshotAttachmentDO pageQueryCommandToDO(AuditRecordSnapshotAttachmentPageQueryCommand auditRecordSnapshotAttachmentPageQueryCommand);

	public abstract AuditRecordSnapshotAttachmentDO queryListCommandToDO(AuditRecordSnapshotAttachmentQueryListCommand auditRecordSnapshotAttachmentQueryListCommand);
}
