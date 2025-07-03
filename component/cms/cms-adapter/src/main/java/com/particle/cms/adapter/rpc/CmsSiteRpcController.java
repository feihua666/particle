package com.particle.cms.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.cms.client.api.ICmsSiteApplicationService;
import com.particle.cms.adapter.feign.client.rpc.CmsSiteRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 站点远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:04
 */
@Tag(name = "站点远程调用相关接口")
@RestController
@RequestMapping("/rpc/cms_site")
public class CmsSiteRpcController extends AbstractBaseRpcAdapter implements CmsSiteRpcFeignClient  {

	@Autowired
	private ICmsSiteApplicationService iCmsSiteApplicationService;


}