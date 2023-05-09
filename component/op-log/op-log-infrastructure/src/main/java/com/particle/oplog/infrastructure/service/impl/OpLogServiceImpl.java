package com.particle.oplog.infrastructure.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.oplog.infrastructure.dos.OpLogDO;
import com.particle.oplog.infrastructure.mapper.OpLogMapper;
import com.particle.oplog.infrastructure.service.IOpLogService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 操作日志 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-05-08 18:32:34
 */
@Component
public class OpLogServiceImpl extends IBaseServiceImpl<OpLogMapper, OpLogDO> implements IOpLogService {
	private IBaseQueryCommandMapStruct<OpLogDO> queryCommandMapStruct;

	@Override
	protected OpLogDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<OpLogDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(OpLogDO po) {
	}

	@Override
	protected void preUpdate(OpLogDO po) {
    
	}
}
