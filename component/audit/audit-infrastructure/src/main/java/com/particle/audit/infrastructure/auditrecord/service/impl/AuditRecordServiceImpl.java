package com.particle.audit.infrastructure.auditrecord.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.audit.infrastructure.auditrecord.dos.AuditRecordDO;
import com.particle.audit.infrastructure.auditrecord.mapper.AuditRecordMapper;
import com.particle.audit.infrastructure.auditrecord.service.IAuditRecordService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 审核记录 服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:57:45
 */
@Component
public class AuditRecordServiceImpl extends IBaseServiceImpl<AuditRecordMapper, AuditRecordDO> implements IAuditRecordService {
	private IBaseQueryCommandMapStruct<AuditRecordDO> queryCommandMapStruct;

	@Override
	protected AuditRecordDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<AuditRecordDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(AuditRecordDO po) {
	}

	@Override
	protected void preUpdate(AuditRecordDO po) {
    
	}
}
