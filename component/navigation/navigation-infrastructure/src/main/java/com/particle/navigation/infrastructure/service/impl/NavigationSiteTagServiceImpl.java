package com.particle.navigation.infrastructure.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.navigation.infrastructure.dos.NavigationSiteTagDO;
import com.particle.navigation.infrastructure.mapper.NavigationSiteTagMapper;
import com.particle.navigation.infrastructure.service.INavigationSiteTagRelService;
import com.particle.navigation.infrastructure.service.INavigationSiteTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 导航网站标签 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-11-07 09:38:23
 */
@Component
public class NavigationSiteTagServiceImpl extends IBaseServiceImpl<NavigationSiteTagMapper, NavigationSiteTagDO> implements INavigationSiteTagService {
	private IBaseQueryCommandMapStruct<NavigationSiteTagDO> queryCommandMapStruct;
	private INavigationSiteTagRelService iNavigationSiteTagRelService;

	@Override
	protected NavigationSiteTagDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}


	@Override
	protected void preAdd(NavigationSiteTagDO po) {
		if (StrUtil.isNotEmpty(po.getCode())) {
			// 标签编码 已存在不能添加
			assertByColumn(po.getCode(),NavigationSiteTagDO::getCode,false);
		}

		if (StrUtil.isNotEmpty(po.getName())) {
			// 标签名称 已存在不能添加
			assertByColumn(po.getName(),NavigationSiteTagDO::getName,false);
		}

	}

	@Override
	protected void preUpdate(NavigationSiteTagDO po) {
	    NavigationSiteTagDO byId = null;
	    if (StrUtil.isNotEmpty(po.getCode())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果标签编码有改动
	        if (!po.getCode().equals(byId.getCode())) {
	            // 标签编码已存在不能修改
	            assertByColumn(po.getCode(),NavigationSiteTagDO::getCode,false);
	        }
	    }

	    if (StrUtil.isNotEmpty(po.getName())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果标签名称有改动
	        if (!po.getName().equals(byId.getName())) {
	            // 标签名称已存在不能修改
	            assertByColumn(po.getName(),NavigationSiteTagDO::getName,false);
	        }
	    }


	}

	@Override
	protected void postDeleteById(Long id, NavigationSiteTagDO DO) {
		iNavigationSiteTagRelService.removeByTagId(id);
	}

	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<NavigationSiteTagDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}
	@Autowired
	public void setiNavigationSiteTagRelService(INavigationSiteTagRelService iNavigationSiteTagRelService) {
		this.iNavigationSiteTagRelService = iNavigationSiteTagRelService;
	}
}
