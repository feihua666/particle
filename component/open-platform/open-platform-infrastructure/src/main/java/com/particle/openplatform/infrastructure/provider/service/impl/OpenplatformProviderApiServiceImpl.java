package com.particle.openplatform.infrastructure.provider.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.openplatform.infrastructure.provider.dos.OpenplatformProviderApiDO;
import com.particle.openplatform.infrastructure.provider.mapper.OpenplatformProviderApiMapper;
import com.particle.openplatform.infrastructure.provider.service.IOpenplatformProviderApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 开放平台供应商接口 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:53:52
 */
@Component
public class OpenplatformProviderApiServiceImpl extends IBaseServiceImpl<OpenplatformProviderApiMapper, OpenplatformProviderApiDO> implements IOpenplatformProviderApiService {
	private IBaseQueryCommandMapStruct<OpenplatformProviderApiDO> queryCommandMapStruct;

	@Override
	protected OpenplatformProviderApiDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<OpenplatformProviderApiDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(OpenplatformProviderApiDO po) {
	    // 编码 已存在不能添加
	    assertByColumn(po.getCode(),OpenplatformProviderApiDO::getCode,false);

	    // 数据查询数据源接口id 已存在不能添加
	    assertByColumn(po.getDataQueryDatasourceApiId(),OpenplatformProviderApiDO::getDataQueryDatasourceApiId,false);

	}

	@Override
	protected void preUpdate(OpenplatformProviderApiDO po) {
	    OpenplatformProviderApiDO byId = null;
	    if (StrUtil.isNotEmpty(po.getCode())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果编码有改动
	        if (!po.getCode().equals(byId.getCode())) {
	            // 编码已存在不能修改
	            assertByColumn(po.getCode(),OpenplatformProviderApiDO::getCode,false);
	        }
	    }

	    if (po.getDataQueryDatasourceApiId() != null) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果数据查询数据源接口id有改动
	        if (!po.getDataQueryDatasourceApiId().equals(byId.getDataQueryDatasourceApiId())) {
	            // 数据查询数据源接口id已存在不能修改
	            assertByColumn(po.getDataQueryDatasourceApiId(),OpenplatformProviderApiDO::getDataQueryDatasourceApiId,false);
	        }
	    }


	}
}
