package com.particle.navigation.adapter.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.navigation.client.api.INavigationFriendshipLinkApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 导航友情链接后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-11-03 11:09:01
 */
@Tag(name = "导航友情链接wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/navigation_friendship_link")
public class NavigationFriendshipLinkAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private INavigationFriendshipLinkApplicationService iNavigationFriendshipLinkApplicationService;


}