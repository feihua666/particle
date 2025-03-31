package com.particle.agi.infrastructure.agent.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.agi.infrastructure.agent.dos.AgiAgentDO;
import com.particle.agi.infrastructure.agent.mapper.AgiAgentMapper;
import com.particle.agi.infrastructure.agent.service.IAgiAgentService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 智能体 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-02-06 15:50:10
 */
@Component
public class AgiAgentServiceImpl extends IBaseServiceImpl<AgiAgentMapper, AgiAgentDO> implements IAgiAgentService {
	private IBaseQueryCommandMapStruct<AgiAgentDO> queryCommandMapStruct;

	@Override
	protected AgiAgentDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<AgiAgentDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(AgiAgentDO po) {
	    // 智能体名称 已存在不能添加
	    assertByColumn(po.getName(),AgiAgentDO::getName,false);

	}

	@Override
	protected void preUpdate(AgiAgentDO po) {
	    AgiAgentDO byId = null;
	    if (StrUtil.isNotEmpty(po.getName())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果智能体名称有改动
	        if (!po.getName().equals(byId.getName())) {
	            // 智能体名称已存在不能修改
	            assertByColumn(po.getName(),AgiAgentDO::getName,false);
	        }
	    }

    
	}
}
