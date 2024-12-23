package com.particle.message.infrastructure.service.impl;

import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.message.infrastructure.dos.MessageUserStateDO;
import com.particle.message.infrastructure.mapper.MessageUserStateMapper;
import com.particle.message.infrastructure.service.IMessageUserStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 用户消息读取状态 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:36:07
 */
@Component
public class MessageUserStateServiceImpl extends IBaseServiceImpl<MessageUserStateMapper, MessageUserStateDO> implements IMessageUserStateService {
	private IBaseQueryCommandMapStruct<MessageUserStateDO> queryCommandMapStruct;

	@Override
	protected MessageUserStateDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<MessageUserStateDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(MessageUserStateDO po) {
	}

	@Override
	protected void preUpdate(MessageUserStateDO po) {

	}
}
