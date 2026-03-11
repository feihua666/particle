package com.particle.audit.infrastructure.auditrecord.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.audit.infrastructure.auditrecord.dos.AuditRecordSnapshotDataDO;
import com.particle.audit.infrastructure.auditrecord.mapper.AuditRecordSnapshotDataMapper;
import com.particle.audit.infrastructure.auditrecord.service.IAuditRecordSnapshotDataService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 审核记录数据快照 服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:58:02
 */
@Component
public class AuditRecordSnapshotDataServiceImpl extends IBaseServiceImpl<AuditRecordSnapshotDataMapper, AuditRecordSnapshotDataDO> implements IAuditRecordSnapshotDataService {
	private IBaseQueryCommandMapStruct<AuditRecordSnapshotDataDO> queryCommandMapStruct;

	@Override
	protected AuditRecordSnapshotDataDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<AuditRecordSnapshotDataDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(AuditRecordSnapshotDataDO po) {
	}

	@Override
	protected void preUpdate(AuditRecordSnapshotDataDO po) {
    
	}
}
