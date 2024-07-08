package com.particle.dict.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.dict.adapter.feign.client.rpc.DictTransRpcFeignClient;
import com.particle.dict.client.dto.data.DictTransVO;
import com.particle.global.trans.result.TransResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 字典翻译远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-07-08 11:45:37
 */
@Tag(name = "字典翻译远程调用相关接口")
@RestController
@RequestMapping("/rpc/dict")
public class DictTransRpcController extends AbstractBaseRpcAdapter implements DictTransRpcFeignClient {

	@Autowired
	private DictTransServiceImpl dictTransService;

	@Override
	public boolean supportBatch(String type) {
		return dictTransService.supportBatch(type);
	}


	@Override
	public List<TransResult<DictTransVO, Long>> transBatch(String type, Set<Long> keys) {
		return dictTransService.transBatch(type, keys);
	}

}