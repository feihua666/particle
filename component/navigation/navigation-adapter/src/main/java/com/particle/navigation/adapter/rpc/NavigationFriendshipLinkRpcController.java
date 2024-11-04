package com.particle.navigation.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.navigation.client.api.INavigationFriendshipLinkApplicationService;
import com.particle.navigation.adapter.feign.client.rpc.NavigationFriendshipLinkRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 导航友情链接远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-11-03 11:09:01
 */
@Tag(name = "导航友情链接远程调用相关接口")
@RestController
@RequestMapping("/rpc/navigation_friendship_link")
public class NavigationFriendshipLinkRpcController extends AbstractBaseRpcAdapter implements NavigationFriendshipLinkRpcFeignClient  {

	@Autowired
	private INavigationFriendshipLinkApplicationService iNavigationFriendshipLinkApplicationService;


}