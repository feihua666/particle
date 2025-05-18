package com.particle.openplatform.infrastructure.app.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.openplatform.infrastructure.app.dos.OpenplatformAppDO;
import com.particle.openplatform.infrastructure.app.dos.OpenplatformAppOpenapiDO;
import com.particle.openplatform.infrastructure.app.mapper.OpenplatformAppMapper;
import com.particle.openplatform.infrastructure.app.mapper.OpenplatformAppOpenapiMapper;
import com.particle.openplatform.infrastructure.app.service.IOpenplatformAppOpenapiService;
import com.particle.openplatform.infrastructure.openapi.dos.OpenplatformOpenapiDO;
import com.particle.openplatform.infrastructure.openapi.mapper.OpenplatformOpenapiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;


/**
 * <p>
 * 开放平台应用与开放接口配置 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:49:06
 */
@Component
public class OpenplatformAppOpenapiServiceImpl extends IBaseServiceImpl<OpenplatformAppOpenapiMapper, OpenplatformAppOpenapiDO> implements IOpenplatformAppOpenapiService {
	private IBaseQueryCommandMapStruct<OpenplatformAppOpenapiDO> queryCommandMapStruct;

	private OpenplatformAppMapper openplatformAppMapper;
	private OpenplatformOpenapiMapper openplatformOpenapiMapper;
	@Override
	protected OpenplatformAppOpenapiDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}


	@Override
	protected void preAdd(OpenplatformAppOpenapiDO po) {
	}

	@Override
	protected void preUpdate(OpenplatformAppOpenapiDO po) {
	}


	@Override
	public OpenplatformAppOpenapiDO getByAppIdAndOpenplatformOpenapiId(String appId, Long openplatformOpenapiId) {
		OpenplatformAppDO idOnlyByAppId = openplatformAppMapper.getIdOnlyByAppId(appId);
		if (idOnlyByAppId == null) {
			return null;
		}
		return getByOpenplatformAppIdAndOpenplatformOpenapiId(idOnlyByAppId.getId(),openplatformOpenapiId);
	}

	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<OpenplatformAppOpenapiDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}
	@Autowired
	public void setOpenplatformAppMapper(OpenplatformAppMapper openplatformAppMapper) {
		this.openplatformAppMapper = openplatformAppMapper;
	}
	@Autowired
	public void setOpenplatformOpenapiMapper(OpenplatformOpenapiMapper openplatformOpenapiMapper) {
		this.openplatformOpenapiMapper = openplatformOpenapiMapper;
	}
}
