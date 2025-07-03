package com.particle.cms.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.cms.client.api.ICmsContentViewRecordApplicationService;
import com.particle.cms.adapter.feign.client.rpc.CmsContentViewRecordRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 内容访问记录远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:38
 */
@Tag(name = "内容访问记录远程调用相关接口")
@RestController
@RequestMapping("/rpc/cms_content_view_record")
public class CmsContentViewRecordRpcController extends AbstractBaseRpcAdapter implements CmsContentViewRecordRpcFeignClient  {

	@Autowired
	private ICmsContentViewRecordApplicationService iCmsContentViewRecordApplicationService;


}