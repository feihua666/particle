package com.particle.tenant.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.global.trans.result.TransResult;
import com.particle.tenant.adapter.feign.client.rpc.TenantTransRpcFeignClient;
import com.particle.tenant.client.dto.data.TenantTransVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 租户翻译远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-07-08 13:02:10
 */
@Tag(name = "租户翻译远程调用相关接口")
@RestController
@RequestMapping("/rpc/tenant")
public class TenantTransRpcController extends AbstractBaseRpcAdapter implements TenantTransRpcFeignClient {

	@Autowired
	private TenantTransServiceImpl tenantTransService;

	@Override
	public boolean supportBatch(String type) {
		return tenantTransService.supportBatch(type);
	}

	@Override
	public List<TransResult<TenantTransVO, Long>> transBatch(String type, Set<Long> keys) {
		return tenantTransService.transBatch(type, keys);
	}

}