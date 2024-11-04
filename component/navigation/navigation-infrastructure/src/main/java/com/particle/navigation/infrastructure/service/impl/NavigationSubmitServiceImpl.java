package com.particle.navigation.infrastructure.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.navigation.infrastructure.dos.NavigationSubmitDO;
import com.particle.navigation.infrastructure.mapper.NavigationSubmitMapper;
import com.particle.navigation.infrastructure.service.INavigationSubmitService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 导航提交 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-11-03 11:09:19
 */
@Component
public class NavigationSubmitServiceImpl extends IBaseServiceImpl<NavigationSubmitMapper, NavigationSubmitDO> implements INavigationSubmitService {
	private IBaseQueryCommandMapStruct<NavigationSubmitDO> queryCommandMapStruct;

	@Override
	protected NavigationSubmitDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<NavigationSubmitDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(NavigationSubmitDO po) {
	    // 网站地址 已存在不能添加
	    assertByColumn(po.getUrl(),NavigationSubmitDO::getUrl,false);

	}

	@Override
	protected void preUpdate(NavigationSubmitDO po) {
	    NavigationSubmitDO byId = null;
	    if (StrUtil.isNotEmpty(po.getUrl())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果网站地址有改动
	        if (!po.getUrl().equals(byId.getUrl())) {
	            // 网站地址已存在不能修改
	            assertByColumn(po.getUrl(),NavigationSubmitDO::getUrl,false);
	        }
	    }

    
	}
}
