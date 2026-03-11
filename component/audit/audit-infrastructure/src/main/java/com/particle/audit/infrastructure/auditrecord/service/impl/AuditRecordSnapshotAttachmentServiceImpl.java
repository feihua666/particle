package com.particle.audit.infrastructure.auditrecord.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.audit.infrastructure.auditrecord.dos.AuditRecordSnapshotAttachmentDO;
import com.particle.audit.infrastructure.auditrecord.mapper.AuditRecordSnapshotAttachmentMapper;
import com.particle.audit.infrastructure.auditrecord.service.IAuditRecordSnapshotAttachmentService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 审核记录附件快照 服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-01-19 15:37:34
 */
@Component
public class AuditRecordSnapshotAttachmentServiceImpl extends IBaseServiceImpl<AuditRecordSnapshotAttachmentMapper, AuditRecordSnapshotAttachmentDO> implements IAuditRecordSnapshotAttachmentService {
	private IBaseQueryCommandMapStruct<AuditRecordSnapshotAttachmentDO> queryCommandMapStruct;

	@Override
	protected AuditRecordSnapshotAttachmentDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<AuditRecordSnapshotAttachmentDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(AuditRecordSnapshotAttachmentDO po) {
	}

	@Override
	protected void preUpdate(AuditRecordSnapshotAttachmentDO po) {
    
	}
}
