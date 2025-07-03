package com.particle.cms.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.cms.client.api.ICmsChannelViewRecordApplicationService;
import com.particle.cms.adapter.feign.client.rpc.CmsChannelViewRecordRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 栏目访问记录远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:22
 */
@Tag(name = "栏目访问记录远程调用相关接口")
@RestController
@RequestMapping("/rpc/cms_channel_view_record")
public class CmsChannelViewRecordRpcController extends AbstractBaseRpcAdapter implements CmsChannelViewRecordRpcFeignClient  {

	@Autowired
	private ICmsChannelViewRecordApplicationService iCmsChannelViewRecordApplicationService;


}