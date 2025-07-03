package com.particle.cms.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.cms.client.api.ICmsContentApplicationService;
import com.particle.cms.adapter.feign.client.rpc.CmsContentRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 内容远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:16
 */
@Tag(name = "内容远程调用相关接口")
@RestController
@RequestMapping("/rpc/cms_content")
public class CmsContentRpcController extends AbstractBaseRpcAdapter implements CmsContentRpcFeignClient  {

	@Autowired
	private ICmsContentApplicationService iCmsContentApplicationService;


}