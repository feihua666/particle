package com.particle.role.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.global.trans.result.TransResult;
import com.particle.role.adapter.feign.client.rpc.RoleTransRpcFeignClient;
import com.particle.role.client.dto.data.RoleTransVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色翻译远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-07-08 11:51:11
 */
@Tag(name = "角色翻译远程调用相关接口")
@RestController
@RequestMapping("/rpc/role")
public class RoleTransRpcController extends AbstractBaseRpcAdapter implements RoleTransRpcFeignClient {

	@Autowired
	private RoleTransServiceImpl roleTransService;

	@Override
	public boolean supportBatch(String type) {
		return roleTransService.supportBatch(type);
	}

	@Override
	public List<TransResult<RoleTransVO, Long>> transBatch(String type, Set<Long> keys) {
		return roleTransService.transBatch(type, keys);
	}



}