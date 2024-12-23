package com.particle.navigation.infrastructure.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.navigation.infrastructure.dos.NavigationStaticDeployDO;
import com.particle.navigation.infrastructure.mapper.NavigationStaticDeployMapper;
import com.particle.navigation.infrastructure.service.INavigationStaticDeployService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 导航网站静态部署 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-11-01 10:02:52
 */
@Component
public class NavigationStaticDeployServiceImpl extends IBaseServiceImpl<NavigationStaticDeployMapper, NavigationStaticDeployDO> implements INavigationStaticDeployService {
	private IBaseQueryCommandMapStruct<NavigationStaticDeployDO> queryCommandMapStruct;

	@Override
	protected NavigationStaticDeployDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<NavigationStaticDeployDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(NavigationStaticDeployDO po) {
	    // 部署编码 已存在不能添加
	    assertByColumn(po.getCode(),NavigationStaticDeployDO::getCode,false);

	}

	@Override
	protected void preUpdate(NavigationStaticDeployDO po) {
	    NavigationStaticDeployDO byId = null;
	    if (StrUtil.isNotEmpty(po.getCode())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果部署编码有改动
	        if (!po.getCode().equals(byId.getCode())) {
	            // 部署编码已存在不能修改
	            assertByColumn(po.getCode(),NavigationStaticDeployDO::getCode,false);
	        }
	    }


	}
}
