package com.particle.navigation.infrastructure.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.navigation.infrastructure.dos.NavigationSiteCategoryRelDO;
import com.particle.navigation.infrastructure.mapper.NavigationSiteCategoryRelMapper;
import com.particle.navigation.infrastructure.service.INavigationSiteCategoryRelService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 导航网站分类关系 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:35:11
 */
@Component
public class NavigationSiteCategoryRelServiceImpl extends IBaseServiceImpl<NavigationSiteCategoryRelMapper, NavigationSiteCategoryRelDO> implements INavigationSiteCategoryRelService {
	private IBaseQueryCommandMapStruct<NavigationSiteCategoryRelDO> queryCommandMapStruct;

	@Override
	protected NavigationSiteCategoryRelDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<NavigationSiteCategoryRelDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(NavigationSiteCategoryRelDO po) {
	}

	@Override
	protected void preUpdate(NavigationSiteCategoryRelDO po) {
    
	}
}
