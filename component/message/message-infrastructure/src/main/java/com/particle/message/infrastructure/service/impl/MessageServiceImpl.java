package com.particle.message.infrastructure.service.impl;

import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.message.infrastructure.dos.MessageDO;
import com.particle.message.infrastructure.mapper.MessageMapper;
import com.particle.message.infrastructure.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 消息 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:32:30
 */
@Component
public class MessageServiceImpl extends IBaseServiceImpl<MessageMapper, MessageDO> implements IMessageService {
	private IBaseQueryCommandMapStruct<MessageDO> queryCommandMapStruct;

	@Override
	protected MessageDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<MessageDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(MessageDO po) {
	}

	@Override
	protected void preUpdate(MessageDO po) {

	}
}
