package com.particle.openplatform.infrastructure.app.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.openplatform.infrastructure.app.dos.OpenplatformAppDO;
import com.particle.openplatform.infrastructure.app.mapper.OpenplatformAppMapper;
import com.particle.openplatform.infrastructure.app.service.IOpenplatformAppService;
import com.particle.openplatform.infrastructure.openapi.service.IOpenplatformOpenapiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * <p>
 * 开放平台应用 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:40:15
 */
@Component
public class OpenplatformAppServiceImpl extends IBaseServiceImpl<OpenplatformAppMapper, OpenplatformAppDO> implements IOpenplatformAppService {


	private IBaseQueryCommandMapStruct<OpenplatformAppDO> queryCommandMapStruct;

	private IOpenplatformOpenapiService iOpenplatformOpenapiService;

	@Override
	protected OpenplatformAppDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<OpenplatformAppDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Autowired
	public void setiOpenplatformOpenapiService(IOpenplatformOpenapiService iOpenplatformOpenapiService) {
		this.iOpenplatformOpenapiService = iOpenplatformOpenapiService;
	}

	@Override
	protected void preAdd(OpenplatformAppDO po) {
	    // 名称 已存在不能添加
	    assertByColumn(po.getName(),OpenplatformAppDO::getName,false);

	    // appId 已存在不能添加
	    assertByColumn(po.getAppId(),OpenplatformAppDO::getAppId,false);

	}

	@Override
	protected void preUpdate(OpenplatformAppDO po) {

	    OpenplatformAppDO byId = null;
	    if (StrUtil.isNotEmpty(po.getName())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果名称有改动
	        if (!po.getName().equals(byId.getName())) {
	            // 名称已存在不能修改
	            assertByColumn(po.getName(),OpenplatformAppDO::getName,false);
	        }
	    }


	    if (StrUtil.isNotEmpty(po.getAppId())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果appId有改动
	        if (!po.getAppId().equals(byId.getAppId())) {
	            // appId已存在不能修改
	            assertByColumn(po.getAppId(),OpenplatformAppDO::getAppId,false);
	        }
	    }


	}

	@Override
	public List<String> getPermissionsByAppId(String appId) {
		OpenplatformAppDO byAppId = getByAppId(appId);
		if (byAppId == null) {
			return null;
		}
		List<String> result = new ArrayList<>();
		// 将app的权限添加上
		if (StrUtil.isNotEmpty(byAppId.getScopes())) {
			result.addAll(Arrays.stream(byAppId.getScopes().split(",")).collect(Collectors.toList()));
		}
		// app分配的接口权限
		List<String> permissionsByAppId = iOpenplatformOpenapiService.getPermissionsByAppId(byAppId.getId());
		if (CollectionUtil.isNotEmpty(permissionsByAppId)) {
			for (String permission : permissionsByAppId) {
				result.addAll(Arrays.stream(permission.split(",")).collect(Collectors.toList()));
			}
		}
		return result;
	}
}
