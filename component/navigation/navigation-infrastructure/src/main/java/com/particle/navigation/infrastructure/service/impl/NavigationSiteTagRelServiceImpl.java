package com.particle.navigation.infrastructure.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.navigation.infrastructure.dos.NavigationSiteTagRelDO;
import com.particle.navigation.infrastructure.mapper.NavigationSiteTagRelMapper;
import com.particle.navigation.infrastructure.service.INavigationSiteTagRelService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 导航网站标签关系 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-11-07 09:39:14
 */
@Component
public class NavigationSiteTagRelServiceImpl extends IBaseServiceImpl<NavigationSiteTagRelMapper, NavigationSiteTagRelDO> implements INavigationSiteTagRelService {
	private IBaseQueryCommandMapStruct<NavigationSiteTagRelDO> queryCommandMapStruct;

	@Override
	protected NavigationSiteTagRelDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<NavigationSiteTagRelDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(NavigationSiteTagRelDO po) {
	}

	@Override
	protected void preUpdate(NavigationSiteTagRelDO po) {
    
	}
}
