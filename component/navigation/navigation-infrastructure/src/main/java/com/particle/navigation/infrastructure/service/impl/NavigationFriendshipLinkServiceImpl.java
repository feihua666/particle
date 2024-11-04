package com.particle.navigation.infrastructure.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.navigation.infrastructure.dos.NavigationFriendshipLinkDO;
import com.particle.navigation.infrastructure.mapper.NavigationFriendshipLinkMapper;
import com.particle.navigation.infrastructure.service.INavigationFriendshipLinkService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 导航友情链接 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-11-03 11:09:01
 */
@Component
public class NavigationFriendshipLinkServiceImpl extends IBaseServiceImpl<NavigationFriendshipLinkMapper, NavigationFriendshipLinkDO> implements INavigationFriendshipLinkService {
	private IBaseQueryCommandMapStruct<NavigationFriendshipLinkDO> queryCommandMapStruct;

	@Override
	protected NavigationFriendshipLinkDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<NavigationFriendshipLinkDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(NavigationFriendshipLinkDO po) {
	    // 网站地址 已存在不能添加
	    assertByColumn(po.getUrl(),NavigationFriendshipLinkDO::getUrl,false);

	}

	@Override
	protected void preUpdate(NavigationFriendshipLinkDO po) {
	    NavigationFriendshipLinkDO byId = null;
	    if (StrUtil.isNotEmpty(po.getUrl())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果网站地址有改动
	        if (!po.getUrl().equals(byId.getUrl())) {
	            // 网站地址已存在不能修改
	            assertByColumn(po.getUrl(),NavigationFriendshipLinkDO::getUrl,false);
	        }
	    }

    
	}
}
