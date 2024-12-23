package com.particle.openplatform.infrastructure.provider.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.openplatform.infrastructure.provider.dos.OpenplatformProviderDO;
import com.particle.openplatform.infrastructure.provider.mapper.OpenplatformProviderMapper;
import com.particle.openplatform.infrastructure.provider.service.IOpenplatformProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 开放平台开放接口供应商 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:15:58
 */
@Component
public class OpenplatformProviderServiceImpl extends IBaseServiceImpl<OpenplatformProviderMapper, OpenplatformProviderDO> implements IOpenplatformProviderService {
	private IBaseQueryCommandMapStruct<OpenplatformProviderDO> queryCommandMapStruct;

	@Override
	protected OpenplatformProviderDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<OpenplatformProviderDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(OpenplatformProviderDO po) {
	    // 编码 已存在不能添加
		if (StrUtil.isNotEmpty(po.getCode())) {
			assertByColumn(po.getCode(),OpenplatformProviderDO::getCode,false);
		}
		if (po.getDataQueryProviderId() != null) {
			assertByColumn(po.getDataQueryProviderId(),OpenplatformProviderDO::getDataQueryProviderId,false);
		}

	}

	@Override
	protected void preUpdate(OpenplatformProviderDO po) {
	    OpenplatformProviderDO byId = null;
	    if (StrUtil.isNotEmpty(po.getCode())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果编码有改动
	        if (!po.getCode().equals(byId.getCode())) {
	            // 编码已存在不能修改
	            assertByColumn(po.getCode(),OpenplatformProviderDO::getCode,false);
	        }
	    }
		if (po.getDataQueryProviderId() != null) {
			byId = byId == null ? getById(po.getId()) : byId;
			// 如果数据查询供应商有改动
			if (!po.getDataQueryProviderId().equals(byId.getDataQueryProviderId())) {
				// 数据查询供应商已存在不能修改
				assertByColumn(po.getDataQueryProviderId(),OpenplatformProviderDO::getDataQueryProviderId,false);
			}
		}

	}
}
