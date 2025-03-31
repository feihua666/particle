package com.particle.agi.infrastructure.chat.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.agi.infrastructure.chat.dos.AgiAgentChatMessageToolcallDO;
import com.particle.agi.infrastructure.chat.mapper.AgiAgentChatMessageToolcallMapper;
import com.particle.agi.infrastructure.chat.service.IAgiAgentChatMessageToolcallService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 智能体对话消息工具调用 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-02-21 17:37:24
 */
@Component
public class AgiAgentChatMessageToolcallServiceImpl extends IBaseServiceImpl<AgiAgentChatMessageToolcallMapper, AgiAgentChatMessageToolcallDO> implements IAgiAgentChatMessageToolcallService {
	private IBaseQueryCommandMapStruct<AgiAgentChatMessageToolcallDO> queryCommandMapStruct;

	@Override
	protected AgiAgentChatMessageToolcallDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<AgiAgentChatMessageToolcallDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(AgiAgentChatMessageToolcallDO po) {
	    // 唯一id 已存在不能添加
	    assertByColumn(po.getUniqueId(),AgiAgentChatMessageToolcallDO::getUniqueId,false);

	}

	@Override
	protected void preUpdate(AgiAgentChatMessageToolcallDO po) {
	    AgiAgentChatMessageToolcallDO byId = null;
	    if (StrUtil.isNotEmpty(po.getUniqueId())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果唯一id有改动
	        if (!po.getUniqueId().equals(byId.getUniqueId())) {
	            // 唯一id已存在不能修改
	            assertByColumn(po.getUniqueId(),AgiAgentChatMessageToolcallDO::getUniqueId,false);
	        }
	    }

    
	}
}
