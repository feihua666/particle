package com.particle.dream.adapter.ssq.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.dream.client.ssq.api.ISsqCodeApplicationService;
import com.particle.dream.adapter.feign.client.ssq.rpc.SsqCodeRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 双色球号码远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-05-16 09:47:01
 */
@Tag(name = "双色球号码远程调用相关接口")
@RestController
@RequestMapping("/rpc/ssq_code")
public class SsqCodeRpcController extends AbstractBaseRpcAdapter implements SsqCodeRpcFeignClient  {

	@Autowired
	private ISsqCodeApplicationService iSsqCodeApplicationService;


}