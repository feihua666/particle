package com.particle.oauth2authorization.infrastructure.client.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.oauth2authorization.infrastructure.client.dos.Oauth2RegisteredClientDO;
import com.particle.oauth2authorization.infrastructure.client.mapper.Oauth2RegisteredClientMapper;
import com.particle.oauth2authorization.infrastructure.client.service.IOauth2RegisteredClientService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * oauth2客户端 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-07-25 17:03:38
 */
@Component
public class Oauth2RegisteredClientServiceImpl extends IBaseServiceImpl<Oauth2RegisteredClientMapper, Oauth2RegisteredClientDO> implements IOauth2RegisteredClientService {
	private IBaseQueryCommandMapStruct<Oauth2RegisteredClientDO> queryCommandMapStruct;

	@Override
	protected Oauth2RegisteredClientDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<Oauth2RegisteredClientDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(Oauth2RegisteredClientDO po) {
	    // 客户端ID 已存在不能添加
	    assertByColumn(po.getClientId(),Oauth2RegisteredClientDO::getClientId,false);

	}

	@Override
	protected void preUpdate(Oauth2RegisteredClientDO po) {

	    Oauth2RegisteredClientDO byId = null;
	    if (StrUtil.isNotEmpty(po.getClientId())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果客户端ID有改动
	        if (!po.getClientId().equals(byId.getClientId())) {
	            // 客户端ID已存在不能修改
	            assertByColumn(po.getClientId(),Oauth2RegisteredClientDO::getClientId,false);
	        }
	    }

    
	}
}
