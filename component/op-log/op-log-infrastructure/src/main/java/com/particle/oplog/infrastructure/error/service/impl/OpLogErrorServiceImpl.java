package com.particle.oplog.infrastructure.error.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.oplog.infrastructure.error.dos.OpLogErrorDO;
import com.particle.oplog.infrastructure.error.mapper.OpLogErrorMapper;
import com.particle.oplog.infrastructure.error.service.IOpLogErrorService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 操作异常日志 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-08-09 14:19:09
 */
@Component
public class OpLogErrorServiceImpl extends IBaseServiceImpl<OpLogErrorMapper, OpLogErrorDO> implements IOpLogErrorService {
	private IBaseQueryCommandMapStruct<OpLogErrorDO> queryCommandMapStruct;

	@Override
	protected OpLogErrorDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<OpLogErrorDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(OpLogErrorDO po) {
	}

	@Override
	protected void preUpdate(OpLogErrorDO po) {
    
	}
}
