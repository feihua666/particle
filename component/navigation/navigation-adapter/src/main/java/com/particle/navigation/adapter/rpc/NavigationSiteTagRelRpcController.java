package com.particle.navigation.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.navigation.adapter.feign.client.rpc.NavigationSiteTagRelRpcFeignClient;
import com.particle.navigation.client.api.INavigationSiteTagRelApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 导航网站标签关系远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-11-07 09:39:14
 */
@Tag(name = "导航网站标签关系远程调用相关接口")
@RestController
@RequestMapping("/rpc/navigation_site_tag_rel")
public class NavigationSiteTagRelRpcController extends AbstractBaseRpcAdapter implements NavigationSiteTagRelRpcFeignClient  {

	@Autowired
	private INavigationSiteTagRelApplicationService iNavigationSiteTagRelApplicationService;


}
