package com.particle.cms.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.cms.client.api.ICmsTemplateContentApplicationService;
import com.particle.cms.adapter.feign.client.rpc.CmsTemplateContentRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 模板内容远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:13:35
 */
@Tag(name = "模板内容远程调用相关接口")
@RestController
@RequestMapping("/rpc/cms_template_content")
public class CmsTemplateContentRpcController extends AbstractBaseRpcAdapter implements CmsTemplateContentRpcFeignClient  {

	@Autowired
	private ICmsTemplateContentApplicationService iCmsTemplateContentApplicationService;


}