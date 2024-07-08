package com.particle.dataconstraint.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.dataconstraint.adapter.feign.client.rpc.DataScopeTransRpcFeignClient;
import com.particle.dataconstraint.client.dto.data.DataScopeTransVO;
import com.particle.global.trans.result.TransResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 数据范围翻译远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-07-08 12:15:00
 */
@Tag(name = "数据范围翻译远程调用相关接口")
@RestController
@RequestMapping("/rpc/data_scope")
public class DataScopeTransRpcController extends AbstractBaseRpcAdapter implements DataScopeTransRpcFeignClient {

	@Autowired
	private DataScopeTransServiceImpl dataScopeTransService;

	@Override
	public boolean supportBatch(String type) {
		return dataScopeTransService.supportBatch(type);
	}

	@Override
	public List<TransResult<DataScopeTransVO, Long>> transBatch(String type, Set<Long> keys) {
		return dataScopeTransService.transBatch(type, keys);
	}

}