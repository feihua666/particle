package com.particle.agi.infrastructure.chat.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.agi.infrastructure.chat.dos.AgiAgentChatMessageMediaDO;
import com.particle.agi.infrastructure.chat.mapper.AgiAgentChatMessageMediaMapper;
import com.particle.agi.infrastructure.chat.service.IAgiAgentChatMessageMediaService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 智能体对话消息媒体 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:31:37
 */
@Component
public class AgiAgentChatMessageMediaServiceImpl extends IBaseServiceImpl<AgiAgentChatMessageMediaMapper, AgiAgentChatMessageMediaDO> implements IAgiAgentChatMessageMediaService {
	private IBaseQueryCommandMapStruct<AgiAgentChatMessageMediaDO> queryCommandMapStruct;

	@Override
	protected AgiAgentChatMessageMediaDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<AgiAgentChatMessageMediaDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(AgiAgentChatMessageMediaDO po) {
	    // 唯一id 已存在不能添加
	    assertByColumn(po.getUniqueId(),AgiAgentChatMessageMediaDO::getUniqueId,false);

	}

	@Override
	protected void preUpdate(AgiAgentChatMessageMediaDO po) {
	    AgiAgentChatMessageMediaDO byId = null;
	    if (StrUtil.isNotEmpty(po.getUniqueId())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果唯一id有改动
	        if (!po.getUniqueId().equals(byId.getUniqueId())) {
	            // 唯一id已存在不能修改
	            assertByColumn(po.getUniqueId(),AgiAgentChatMessageMediaDO::getUniqueId,false);
	        }
	    }

    
	}
}
