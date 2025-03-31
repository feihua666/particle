package com.particle.agi.infrastructure.chat.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.particle.agi.infrastructure.chat.dos.AgiAgentChatMessageDO;
import com.particle.agi.infrastructure.chat.dos.AgiAgentChatMessageMediaDO;
import com.particle.agi.infrastructure.chat.dos.AgiAgentChatMessageToolDO;
import com.particle.agi.infrastructure.chat.dos.AgiAgentChatMessageToolcallDO;
import com.particle.agi.infrastructure.chat.mapper.AgiAgentChatMessageMapper;
import com.particle.agi.infrastructure.chat.service.IAgiAgentChatMessageMediaService;
import com.particle.agi.infrastructure.chat.service.IAgiAgentChatMessageService;
import com.particle.agi.infrastructure.chat.service.IAgiAgentChatMessageToolService;
import com.particle.agi.infrastructure.chat.service.IAgiAgentChatMessageToolcallService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * <p>
 * 智能体对话消息 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:31:19
 */
@Component
public class AgiAgentChatMessageServiceImpl extends IBaseServiceImpl<AgiAgentChatMessageMapper, AgiAgentChatMessageDO> implements IAgiAgentChatMessageService {
	private IBaseQueryCommandMapStruct<AgiAgentChatMessageDO> queryCommandMapStruct;
	private IAgiAgentChatMessageMediaService agiAgentChatMessageMediaService;
	private IAgiAgentChatMessageToolcallService agiAgentChatMessageToolcallService;
	private IAgiAgentChatMessageToolService agiAgentChatMessageToolService;

	@Override
	protected AgiAgentChatMessageDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<AgiAgentChatMessageDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}
	@Autowired
	public void setAgiAgentChatMessageMediaService(IAgiAgentChatMessageMediaService agiAgentChatMessageMediaService) {
		this.agiAgentChatMessageMediaService = agiAgentChatMessageMediaService;
	}
	@Autowired
	public void setAgiAgentChatMessageToolcallService(IAgiAgentChatMessageToolcallService agiAgentChatMessageToolcallService) {
		this.agiAgentChatMessageToolcallService = agiAgentChatMessageToolcallService;
	}
	@Autowired
	public void setAgiAgentChatMessageToolService(IAgiAgentChatMessageToolService agiAgentChatMessageToolService) {
		this.agiAgentChatMessageToolService = agiAgentChatMessageToolService;
	}

	@Override
	protected void preAdd(AgiAgentChatMessageDO po) {
	}

	@Override
	protected void preUpdate(AgiAgentChatMessageDO po) {

	}

	@Override
	protected void postDeleteById(Long id, AgiAgentChatMessageDO DO) {
		agiAgentChatMessageMediaService.deleteByColumn(id, AgiAgentChatMessageMediaDO::getAgiAgentChatMessageId);
		agiAgentChatMessageToolcallService.deleteByColumn(id, AgiAgentChatMessageToolcallDO::getAgiAgentChatMessageId);
		agiAgentChatMessageToolService.deleteByColumn(id, AgiAgentChatMessageToolDO::getAgiAgentChatMessageId);
	}

	@Override
	protected void postDeleteByColumn(Object columnId, SFunction<AgiAgentChatMessageDO, ?> column, List<AgiAgentChatMessageDO> DOS) {
		for (AgiAgentChatMessageDO aDo : DOS) {
			agiAgentChatMessageMediaService.deleteByColumn(aDo.getId(), AgiAgentChatMessageMediaDO::getAgiAgentChatMessageId);
			agiAgentChatMessageToolcallService.deleteByColumn(aDo.getId(), AgiAgentChatMessageToolcallDO::getAgiAgentChatMessageId);
			agiAgentChatMessageToolService.deleteByColumn(aDo.getId(), AgiAgentChatMessageToolDO::getAgiAgentChatMessageId);
		}
	}
}
