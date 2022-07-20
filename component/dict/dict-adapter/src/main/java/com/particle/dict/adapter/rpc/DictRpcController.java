package com.particle.dict.adapter.rpc;

import com.particle.dict.client.api.IDictApplicationService;
import com.particle.dict.adapter.feign.client.rpc.DictRpcFeignClient;
import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 字典远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Api(tags = "字典远程调用相关接口")
@RestController
@RequestMapping("/rpc/dict")
public class DictRpcController extends AbstractBaseRpcAdapter implements DictRpcFeignClient {

	@Autowired
	private IDictApplicationService iDictApplicationService;









}