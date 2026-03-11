package com.particle.cms.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.cms.client.api.ICmsTemplateApplicationService;
import com.particle.cms.adapter.feign.client.rpc.CmsTemplateRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 模板远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2026-01-21 21:03:36
 */
@Tag(name = "模板远程调用相关接口")
@RestController
@RequestMapping("/rpc/cms_template")
public class CmsTemplateRpcController extends AbstractBaseRpcAdapter implements CmsTemplateRpcFeignClient  {

	@Autowired
	private ICmsTemplateApplicationService iCmsTemplateApplicationService;


}