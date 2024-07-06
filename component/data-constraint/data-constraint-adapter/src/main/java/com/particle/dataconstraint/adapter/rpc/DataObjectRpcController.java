package com.particle.dataconstraint.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.dataconstraint.adapter.feign.client.rpc.DataObjectTransRpcFeignClient;
import com.particle.dataconstraint.app.structmapping.DataObjectAppStructMapping;
import com.particle.dataconstraint.client.api.IDataObjectApplicationService;
import com.particle.dataconstraint.adapter.feign.client.rpc.DataObjectRpcFeignClient;
import com.particle.dataconstraint.client.dto.data.DataObjectTransVO;
import com.particle.dataconstraint.client.dto.data.DataObjectVO;
import com.particle.dataconstraint.infrastructure.dos.DataObjectDO;
import com.particle.dataconstraint.infrastructure.service.IDataObjectService;
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
 * 数据对象远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:18
 */
@Tag(name = "数据对象远程调用相关接口")
@RestController
@RequestMapping("/rpc/data_object")
public class DataObjectRpcController extends AbstractBaseRpcAdapter implements DataObjectRpcFeignClient, DataObjectTransRpcFeignClient {

	@Autowired
	private IDataObjectApplicationService iDataObjectApplicationService;

	@Autowired
	private DataObjectTransServiceImpl dataObjectTransService;
	@Autowired
	private IDataObjectService iDataObjectService;

	@Override
	public boolean supportBatch(String type) {
		return dataObjectTransService.supportBatch(type);
	}

	@Override
	public List<TransResult<DataObjectTransVO, Long>> transBatch(String type, Set<Long> keys) {
		return dataObjectTransService.transBatch(type, keys);
	}

	@Override
	public MultiResponse<DataObjectVO> getByDataObjectIds(List<Long> dataObjectIds) {
		List<DataObjectDO> dataObjectDOS = iDataObjectService.listByIds(dataObjectIds);
		List<DataObjectVO> dataObjectVOS = DataObjectAppStructMapping.instance.dataObjectDOsToDataObjectVOs(dataObjectDOS);
		return MultiResponse.of(dataObjectVOS);
	}
}