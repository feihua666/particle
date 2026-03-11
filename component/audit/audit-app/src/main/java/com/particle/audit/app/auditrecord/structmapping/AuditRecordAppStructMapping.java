package com.particle.audit.app.auditrecord.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.audit.client.auditrecord.dto.data.AuditRecordVO;
import com.particle.audit.domain.auditrecord.AuditRecord;
import com.particle.audit.domain.auditrecord.AuditRecordId;
import com.particle.audit.infrastructure.auditrecord.dos.AuditRecordDO;
import com.particle.audit.client.auditrecord.dto.command.representation.AuditRecordPageQueryCommand;
import com.particle.audit.client.auditrecord.dto.command.representation.AuditRecordQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 审核记录 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:57:45
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AuditRecordAppStructMapping  implements IBaseQueryCommandMapStruct<AuditRecordDO>{
	public static AuditRecordAppStructMapping instance = Mappers.getMapper( AuditRecordAppStructMapping.class );

	protected Long map(AuditRecordId auditRecordId){
		if (auditRecordId == null) {
			return null;
		}
		return auditRecordId.getId();
	}
	/**
	 * 审核记录领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AuditRecordAppStructMapping#map(AuditRecordId)}
	 * @param auditRecord
	 * @return
	 */
	public abstract AuditRecordVO toAuditRecordVO(AuditRecord auditRecord);


	/**
	 * 数据对象转视图对象
	 * @param auditRecordDO
	 * @return
	 */
	public abstract AuditRecordVO auditRecordDOToAuditRecordVO(AuditRecordDO auditRecordDO);

	/**
	 * 批量转换
	 * @param auditRecordDOs
	 * @return
	 */
	public abstract List<AuditRecordVO> auditRecordDOsToAuditRecordVOs(List<AuditRecordDO> auditRecordDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<AuditRecordVO> infrastructurePageToPageResponse(Page<AuditRecordDO> page) {
		return PageResponse.of(auditRecordDOsToAuditRecordVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public AuditRecordDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof AuditRecordPageQueryCommand) {
			return pageQueryCommandToDO((AuditRecordPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof AuditRecordQueryListCommand) {
			return queryListCommandToDO(((AuditRecordQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract AuditRecordDO pageQueryCommandToDO(AuditRecordPageQueryCommand auditRecordPageQueryCommand);

	public abstract AuditRecordDO queryListCommandToDO(AuditRecordQueryListCommand auditRecordQueryListCommand);
}
