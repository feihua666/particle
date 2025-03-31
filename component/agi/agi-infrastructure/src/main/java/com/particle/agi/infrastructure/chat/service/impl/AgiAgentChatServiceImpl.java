package com.particle.agi.infrastructure.chat.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.particle.agi.infrastructure.chat.dos.AgiAgentChatDO;
import com.particle.agi.infrastructure.chat.dos.AgiAgentChatMessageDO;
import com.particle.agi.infrastructure.chat.mapper.AgiAgentChatMapper;
import com.particle.agi.infrastructure.chat.service.IAgiAgentChatMessageService;
import com.particle.agi.infrastructure.chat.service.IAgiAgentChatService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * <p>
 * 智能体对话 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:30:54
 */
@Component
public class AgiAgentChatServiceImpl extends IBaseServiceImpl<AgiAgentChatMapper, AgiAgentChatDO> implements IAgiAgentChatService {
	private IBaseQueryCommandMapStruct<AgiAgentChatDO> queryCommandMapStruct;

	private IAgiAgentChatMessageService agiAgentChatMessageService;

	@Override
	protected AgiAgentChatDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<AgiAgentChatDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}
	@Autowired
	public void setAgiAgentChatMessageService(IAgiAgentChatMessageService agiAgentChatMessageService) {
		this.agiAgentChatMessageService = agiAgentChatMessageService;
	}

	@Override
	protected void preAdd(AgiAgentChatDO po) {
	    // 对话id 已存在不能添加
	    assertByColumn(po.getChatId(),AgiAgentChatDO::getChatId,false);

	}

	@Override
	protected void preUpdate(AgiAgentChatDO po) {
	    AgiAgentChatDO byId = null;
	    if (StrUtil.isNotEmpty(po.getChatId())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果对话id有改动
	        if (!po.getChatId().equals(byId.getChatId())) {
	            // 对话id已存在不能修改
	            assertByColumn(po.getChatId(),AgiAgentChatDO::getChatId,false);
	        }
	    }


	}

	@Override
	protected void postDeleteById(Long id, AgiAgentChatDO DO) {
		agiAgentChatMessageService.deleteByColumn(id, AgiAgentChatMessageDO::getAgiAgentChatId);
	}

	@Override
	protected void postDeleteByColumn(Object columnId, SFunction<AgiAgentChatDO, ?> column, List<AgiAgentChatDO> DOS) {
		for (AgiAgentChatDO aDo : DOS) {
			agiAgentChatMessageService.deleteByColumn(aDo.getId(), AgiAgentChatMessageDO::getAgiAgentChatId);
		}
	}
}
