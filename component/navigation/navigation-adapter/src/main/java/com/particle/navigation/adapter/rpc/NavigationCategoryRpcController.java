package com.particle.navigation.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.navigation.adapter.feign.client.rpc.NavigationCategoryRpcFeignClient;
import com.particle.navigation.client.api.INavigationCategoryApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 导航分类远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:34:42
 */
@Tag(name = "导航分类远程调用相关接口")
@RestController
@RequestMapping("/rpc/navigation_category")
public class NavigationCategoryRpcController extends AbstractBaseRpcAdapter implements NavigationCategoryRpcFeignClient  {

	@Autowired
	private INavigationCategoryApplicationService iNavigationCategoryApplicationService;


}
