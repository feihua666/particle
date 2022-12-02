package com.particle.dict.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.dict.adapter.feign.client.rpc.DictRpcFeignClient;
import com.particle.dict.client.api.IDictApplicationService;
import com.particle.global.trans.result.TransResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

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

	@Autowired
	private DictTransServiceImpl dictTransService;

	@Override
	public boolean supportBatch(String type) {
		return dictTransService.supportBatch(type);
	}


	@Override
	public List<TransResult<Object, Long>> transBatch(String type, Set<Long> keys) {
		return dictTransService.transBatch(type, keys);
	}
}