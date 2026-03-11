package com.particle.audit.app.auditrecord.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.audit.client.auditrecord.dto.data.AuditRecordSnapshotDataVO;
import com.particle.audit.domain.auditrecord.AuditRecordSnapshotData;
import com.particle.audit.domain.auditrecord.AuditRecordSnapshotDataId;
import com.particle.audit.infrastructure.auditrecord.dos.AuditRecordSnapshotDataDO;
import com.particle.audit.client.auditrecord.dto.command.representation.AuditRecordSnapshotDataPageQueryCommand;
import com.particle.audit.client.auditrecord.dto.command.representation.AuditRecordSnapshotDataQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 审核记录数据快照 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:58:02
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AuditRecordSnapshotDataAppStructMapping  implements IBaseQueryCommandMapStruct<AuditRecordSnapshotDataDO>{
	public static AuditRecordSnapshotDataAppStructMapping instance = Mappers.getMapper( AuditRecordSnapshotDataAppStructMapping.class );

	protected Long map(AuditRecordSnapshotDataId auditRecordSnapshotDataId){
		if (auditRecordSnapshotDataId == null) {
			return null;
		}
		return auditRecordSnapshotDataId.getId();
	}
	/**
	 * 审核记录数据快照领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AuditRecordSnapshotDataAppStructMapping#map(AuditRecordSnapshotDataId)}
	 * @param auditRecordSnapshotData
	 * @return
	 */
	public abstract AuditRecordSnapshotDataVO toAuditRecordSnapshotDataVO(AuditRecordSnapshotData auditRecordSnapshotData);


	/**
	 * 数据对象转视图对象
	 * @param auditRecordSnapshotDataDO
	 * @return
	 */
	public abstract AuditRecordSnapshotDataVO auditRecordSnapshotDataDOToAuditRecordSnapshotDataVO(AuditRecordSnapshotDataDO auditRecordSnapshotDataDO);

	/**
	 * 批量转换
	 * @param auditRecordSnapshotDataDOs
	 * @return
	 */
	public abstract List<AuditRecordSnapshotDataVO> auditRecordSnapshotDataDOsToAuditRecordSnapshotDataVOs(List<AuditRecordSnapshotDataDO> auditRecordSnapshotDataDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<AuditRecordSnapshotDataVO> infrastructurePageToPageResponse(Page<AuditRecordSnapshotDataDO> page) {
		return PageResponse.of(auditRecordSnapshotDataDOsToAuditRecordSnapshotDataVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public AuditRecordSnapshotDataDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof AuditRecordSnapshotDataPageQueryCommand) {
			return pageQueryCommandToDO((AuditRecordSnapshotDataPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof AuditRecordSnapshotDataQueryListCommand) {
			return queryListCommandToDO(((AuditRecordSnapshotDataQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract AuditRecordSnapshotDataDO pageQueryCommandToDO(AuditRecordSnapshotDataPageQueryCommand auditRecordSnapshotDataPageQueryCommand);

	public abstract AuditRecordSnapshotDataDO queryListCommandToDO(AuditRecordSnapshotDataQueryListCommand auditRecordSnapshotDataQueryListCommand);
}
