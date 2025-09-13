package com.particle.role.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.global.trans.result.TransResult;
import com.particle.role.adapter.feign.client.rpc.RoleMultipleTransRpcFeignClient;
import com.particle.component.light.share.role.RoleTransVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 多角色翻译远程调用适配器
 * 主要用于OpenFeignClient远程调用的翻译场景，目前主要是根据用户id翻译
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Tag(name = "角色远程调用相关接口")
@RestController
@RequestMapping("/rpc/role/mt")
public class RoleMultipleTransRpcController extends AbstractBaseRpcAdapter implements RoleMultipleTransRpcFeignClient {


	@Autowired
	private RoleMultipleTransServiceImpl roleMultipleTransService;


	@Override
	public boolean supportBatch(String type) {
		return roleMultipleTransService.supportBatch(type);
	}

	@Override
	public List<TransResult<List<RoleTransVO>, Long>> transBatch(String type, Set<Long> keys) {
		return roleMultipleTransService.transBatch(type, keys);
	}

}
