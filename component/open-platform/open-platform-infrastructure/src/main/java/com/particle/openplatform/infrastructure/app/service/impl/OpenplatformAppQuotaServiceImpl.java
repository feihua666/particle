package com.particle.openplatform.infrastructure.app.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.openplatform.infrastructure.app.dos.OpenplatformAppQuotaDO;
import com.particle.openplatform.infrastructure.app.mapper.OpenplatformAppQuotaMapper;
import com.particle.openplatform.infrastructure.app.service.IOpenplatformAppQuotaService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 开放平台应用额度 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-10-16 10:38:41
 */
@Component
public class OpenplatformAppQuotaServiceImpl extends IBaseServiceImpl<OpenplatformAppQuotaMapper, OpenplatformAppQuotaDO> implements IOpenplatformAppQuotaService {
	private IBaseQueryCommandMapStruct<OpenplatformAppQuotaDO> queryCommandMapStruct;

	@Override
	protected OpenplatformAppQuotaDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<OpenplatformAppQuotaDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(OpenplatformAppQuotaDO po) {
	    // 开放平台应用id 已存在不能添加
	    assertByColumn(po.getOpenplatformAppId(),OpenplatformAppQuotaDO::getOpenplatformAppId,false);

	}

	@Override
	protected void preUpdate(OpenplatformAppQuotaDO po) {
	    OpenplatformAppQuotaDO byId = null;
	    if (po.getOpenplatformAppId() != null) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果开放平台应用id有改动
	        if (!po.getOpenplatformAppId().equals(byId.getOpenplatformAppId())) {
	            // 开放平台应用id已存在不能修改
	            assertByColumn(po.getOpenplatformAppId(),OpenplatformAppQuotaDO::getOpenplatformAppId,false);
	        }
	    }

    
	}
}
