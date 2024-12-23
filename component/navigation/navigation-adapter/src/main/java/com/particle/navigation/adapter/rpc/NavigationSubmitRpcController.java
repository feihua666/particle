package com.particle.navigation.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.navigation.adapter.feign.client.rpc.NavigationSubmitRpcFeignClient;
import com.particle.navigation.client.api.INavigationSubmitApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 导航提交远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-11-03 11:09:19
 */
@Tag(name = "导航提交远程调用相关接口")
@RestController
@RequestMapping("/rpc/navigation_submit")
public class NavigationSubmitRpcController extends AbstractBaseRpcAdapter implements NavigationSubmitRpcFeignClient  {

	@Autowired
	private INavigationSubmitApplicationService iNavigationSubmitApplicationService;


}
