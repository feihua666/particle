package com.particle.dataconstraint.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.dataconstraint.adapter.feign.client.rpc.DataScopeTransRpcFeignClient;
import com.particle.dataconstraint.app.structmapping.DataScopeAppStructMapping;
import com.particle.dataconstraint.client.api.IDataScopeApplicationService;
import com.particle.dataconstraint.adapter.feign.client.rpc.DataScopeRpcFeignClient;
import com.particle.dataconstraint.client.dto.data.DataScopeTransVO;
import com.particle.dataconstraint.client.dto.data.DataScopeVO;
import com.particle.dataconstraint.infrastructure.dos.DataScopeDO;
import com.particle.dataconstraint.infrastructure.service.IDataScopeService;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.trans.result.TransResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 数据范围远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:38
 */
@Tag(name = "数据范围远程调用相关接口")
@RestController
@RequestMapping("/rpc/data_scope")
public class DataScopeRpcController extends AbstractBaseRpcAdapter implements DataScopeRpcFeignClient, DataScopeTransRpcFeignClient {

	@Autowired
	private IDataScopeApplicationService iDataScopeApplicationService;

	@Autowired
	private DataScopeTransServiceImpl dataScopeTransService;

	@Autowired
	private IDataScopeService iDataScopeService;
	@Override
	public boolean supportBatch(String type) {
		return dataScopeTransService.supportBatch(type);
	}

	@Override
	public List<TransResult<DataScopeTransVO, Long>> transBatch(String type, Set<Long> keys) {
		return dataScopeTransService.transBatch(type, keys);
	}

	@Override
	public MultiResponse<DataScopeVO> getByDataScopeIds(List<Long> dataScopeIds) {
		List<DataScopeDO> dataScopeDOS = iDataScopeService.listByIds(dataScopeIds);
		List<DataScopeVO> dataScopeVOS = DataScopeAppStructMapping.instance.dataScopeDOsToDataScopeVOs(dataScopeDOS);
		return MultiResponse.of(dataScopeVOS);
	}
}