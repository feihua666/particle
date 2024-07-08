package com.particle.dataconstraint.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.dataconstraint.adapter.feign.client.rpc.DataObjectTransRpcFeignClient;
import com.particle.dataconstraint.client.dto.data.DataObjectTransVO;
import com.particle.global.trans.result.TransResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 数据对象翻译远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-07-08 12:12:18
 */
@Tag(name = "数据对象翻译远程调用相关接口")
@RestController
@RequestMapping("/rpc/data_object")
public class DataObjectTransRpcController extends AbstractBaseRpcAdapter implements DataObjectTransRpcFeignClient {

	@Autowired
	private DataObjectTransServiceImpl dataObjectTransService;


	@Override
	public boolean supportBatch(String type) {
		return dataObjectTransService.supportBatch(type);
	}

	@Override
	public List<TransResult<DataObjectTransVO, Long>> transBatch(String type, Set<Long> keys) {
		return dataObjectTransService.transBatch(type, keys);
	}
}