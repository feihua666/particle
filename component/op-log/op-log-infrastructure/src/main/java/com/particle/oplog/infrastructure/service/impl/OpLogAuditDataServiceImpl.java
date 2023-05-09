package com.particle.oplog.infrastructure.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.oplog.infrastructure.dos.OpLogAuditDataDO;
import com.particle.oplog.infrastructure.mapper.OpLogAuditDataMapper;
import com.particle.oplog.infrastructure.service.IOpLogAuditDataService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 操作日志审计数据 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-05-08 18:33:30
 */
@Component
public class OpLogAuditDataServiceImpl extends IBaseServiceImpl<OpLogAuditDataMapper, OpLogAuditDataDO> implements IOpLogAuditDataService {
	private IBaseQueryCommandMapStruct<OpLogAuditDataDO> queryCommandMapStruct;

	@Override
	protected OpLogAuditDataDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<OpLogAuditDataDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(OpLogAuditDataDO po) {
	}

	@Override
	protected void preUpdate(OpLogAuditDataDO po) {
    
	}
}
