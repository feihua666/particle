package com.particle.openplatform.adapter.provider.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.adapter.feign.client.provider.rpc.OpenplatformProviderRpcFeignClient;
import com.particle.openplatform.app.provider.structmapping.OpenplatformProviderAppStructMapping;
import com.particle.openplatform.client.provider.api.IOpenplatformProviderApplicationService;
import com.particle.openplatform.client.provider.dto.data.OpenplatformProviderVO;
import com.particle.openplatform.infrastructure.provider.dos.OpenplatformProviderDO;
import com.particle.openplatform.infrastructure.provider.service.IOpenplatformProviderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放平台开放接口供应商远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:15:58
 */
@Tag(name = "开放平台开放接口供应商远程调用相关接口")
@RestController
@RequestMapping("/rpc/openplatform_provider")
public class OpenplatformProviderRpcController extends AbstractBaseRpcAdapter implements OpenplatformProviderRpcFeignClient  {

	@Autowired
	private IOpenplatformProviderApplicationService iOpenplatformProviderApplicationService;
	@Autowired
	private IOpenplatformProviderService  iOpenplatformProviderService;

	@Operation(summary = "根据id查询")
	@Override
	public SingleResponse<OpenplatformProviderVO> getById(Long openplatformProviderId) {
		OpenplatformProviderDO openplatformProviderDO = iOpenplatformProviderService.getById(openplatformProviderId);
		OpenplatformProviderVO openplatformProviderVO = OpenplatformProviderAppStructMapping.instance.openplatformProviderDOToOpenplatformProviderVO(openplatformProviderDO);
		return SingleResponse.of(openplatformProviderVO);
	}
	@Operation(summary = "根据数据查询供应商id查询")
	@Override
	public SingleResponse<OpenplatformProviderVO> getByDataQueryProviderId(Long dataQueryProviderId) {
		OpenplatformProviderDO openplatformProviderDO = iOpenplatformProviderService.getBydataQueryProviderId(dataQueryProviderId);
		OpenplatformProviderVO openplatformProviderVO = OpenplatformProviderAppStructMapping.instance.openplatformProviderDOToOpenplatformProviderVO(openplatformProviderDO);
		return SingleResponse.of(openplatformProviderVO);
	}
}
