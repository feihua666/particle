package com.particle.navigation.infrastructure.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.navigation.infrastructure.dos.NavigationSiteDO;
import com.particle.navigation.infrastructure.mapper.NavigationSiteMapper;
import com.particle.navigation.infrastructure.service.INavigationSiteCategoryRelService;
import com.particle.navigation.infrastructure.service.INavigationSiteService;
import com.particle.navigation.infrastructure.service.INavigationSiteTagRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 导航网站 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:34:56
 */
@Component
public class NavigationSiteServiceImpl extends IBaseServiceImpl<NavigationSiteMapper, NavigationSiteDO> implements INavigationSiteService {
	private IBaseQueryCommandMapStruct<NavigationSiteDO> queryCommandMapStruct;

	private INavigationSiteCategoryRelService iNavigationSiteCategoryRelService;
	private INavigationSiteTagRelService iNavigationSiteTagRelService;

	@Override
	protected NavigationSiteDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}


	@Override
	protected void preAdd(NavigationSiteDO po) {
		// url 已存在不能添加
		if (StrUtil.isNotEmpty(po.getUrl())) {
			assertByColumn(po.getUrl(), NavigationSiteDO::getUrl,false);
		}
	}

	@Override
	protected void preUpdate(NavigationSiteDO po) {
		NavigationSiteDO byId = null;
		if (StrUtil.isNotEmpty(po.getUrl())) {
			byId = byId == null ? getById(po.getId()) : byId;
			// 如果url有改动
			if (!po.getUrl().equals(byId.getUrl())) {
				// url已存在不能修改
				assertByColumn(po.getUrl(),NavigationSiteDO::getUrl,false);
			}
		}
	}

	@Override
	protected void postDeleteById(Long id, NavigationSiteDO DO) {
		iNavigationSiteCategoryRelService.removeBySiteId(id);
		iNavigationSiteTagRelService.removeBySiteId(id);
	}

	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<NavigationSiteDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}
	@Autowired
	public void setiNavigationSiteCategoryRelService(INavigationSiteCategoryRelService iNavigationSiteCategoryRelService) {
		this.iNavigationSiteCategoryRelService = iNavigationSiteCategoryRelService;
	}
	@Autowired
	public void setiNavigationSiteTagRelService(INavigationSiteTagRelService iNavigationSiteTagRelService) {
		this.iNavigationSiteTagRelService = iNavigationSiteTagRelService;
	}
}
