package com.particle.agi.infrastructure.chat.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.agi.infrastructure.chat.dos.AgiAgentChatMessageToolDO;
import com.particle.agi.infrastructure.chat.mapper.AgiAgentChatMessageToolMapper;
import com.particle.agi.infrastructure.chat.service.IAgiAgentChatMessageToolService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 智能体对话消息工具 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-02-21 17:37:11
 */
@Component
public class AgiAgentChatMessageToolServiceImpl extends IBaseServiceImpl<AgiAgentChatMessageToolMapper, AgiAgentChatMessageToolDO> implements IAgiAgentChatMessageToolService {
	private IBaseQueryCommandMapStruct<AgiAgentChatMessageToolDO> queryCommandMapStruct;

	@Override
	protected AgiAgentChatMessageToolDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<AgiAgentChatMessageToolDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(AgiAgentChatMessageToolDO po) {
	    // 唯一id 已存在不能添加
	    assertByColumn(po.getUniqueId(),AgiAgentChatMessageToolDO::getUniqueId,false);

	}

	@Override
	protected void preUpdate(AgiAgentChatMessageToolDO po) {
	    AgiAgentChatMessageToolDO byId = null;
	    if (StrUtil.isNotEmpty(po.getUniqueId())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果唯一id有改动
	        if (!po.getUniqueId().equals(byId.getUniqueId())) {
	            // 唯一id已存在不能修改
	            assertByColumn(po.getUniqueId(),AgiAgentChatMessageToolDO::getUniqueId,false);
	        }
	    }

    
	}
}
