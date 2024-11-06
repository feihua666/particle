package com.particle.navigation.infrastructure.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.navigation.infrastructure.dos.NavigationCategoryDO;
import com.particle.navigation.infrastructure.mapper.NavigationCategoryMapper;
import com.particle.navigation.infrastructure.service.INavigationCategoryService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.navigation.infrastructure.service.INavigationSiteCategoryRelService;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 导航分类 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:34:42
 */
@Component
public class NavigationCategoryServiceImpl extends IBaseServiceImpl<NavigationCategoryMapper, NavigationCategoryDO> implements INavigationCategoryService {
	private IBaseQueryCommandMapStruct<NavigationCategoryDO> queryCommandMapStruct;
	private INavigationSiteCategoryRelService iNavigationSiteCategoryRelService;

	@Override
	protected NavigationCategoryDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}


	@Override
	protected void preAdd(NavigationCategoryDO po) {
	    // 编码 已存在不能添加
		if (StrUtil.isNotEmpty(po.getCode())) {
			assertByColumn(po.getCode(),NavigationCategoryDO::getCode,false);
		}

	}

	@Override
	protected void preUpdate(NavigationCategoryDO po) {
	    NavigationCategoryDO byId = null;
	    if (StrUtil.isNotEmpty(po.getCode())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果编码有改动
	        if (!po.getCode().equals(byId.getCode())) {
	            // 编码已存在不能修改
	            assertByColumn(po.getCode(),NavigationCategoryDO::getCode,false);
	        }
	    }
	}

	@Override
	protected void postDeleteById(Long id, NavigationCategoryDO DO) {
		iNavigationSiteCategoryRelService.removeByCategoryId(id);
	}

	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<NavigationCategoryDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}
	@Autowired
	public void setiNavigationSiteCategoryRelService(INavigationSiteCategoryRelService iNavigationSiteCategoryRelService) {
		this.iNavigationSiteCategoryRelService = iNavigationSiteCategoryRelService;
	}
}
