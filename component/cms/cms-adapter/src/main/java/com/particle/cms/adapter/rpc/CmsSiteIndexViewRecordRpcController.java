package com.particle.cms.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.cms.client.api.ICmsSiteIndexViewRecordApplicationService;
import com.particle.cms.adapter.feign.client.rpc.CmsSiteIndexViewRecordRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 站点首页访问记录远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:10
 */
@Tag(name = "站点首页访问记录远程调用相关接口")
@RestController
@RequestMapping("/rpc/cms_site_index_view_record")
public class CmsSiteIndexViewRecordRpcController extends AbstractBaseRpcAdapter implements CmsSiteIndexViewRecordRpcFeignClient  {

	@Autowired
	private ICmsSiteIndexViewRecordApplicationService iCmsSiteIndexViewRecordApplicationService;


}