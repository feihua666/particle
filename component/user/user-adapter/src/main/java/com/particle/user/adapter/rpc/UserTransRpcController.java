package com.particle.user.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.global.trans.result.TransResult;
import com.particle.user.adapter.feign.client.rpc.UserTransRpcFeignClient;
import com.particle.user.client.dto.data.UserTransVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户翻译远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-07-08 11:52:32
 */
@Tag(name = "用户翻译远程调用相关接口")
@RestController
@RequestMapping("/rpc/user")
public class UserTransRpcController extends AbstractBaseRpcAdapter implements UserTransRpcFeignClient {


	@Autowired
	private UserTransServiceImpl userTransService;

	@Override
	public boolean supportBatch(String type) {
		return userTransService.supportBatch(type);
	}

	@Override
	public List<TransResult<UserTransVO, Long>> transBatch(String type, Set<Long> keys) {
		return userTransService.transBatch(type, keys);
	}
}