package com.particle.dept.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.dept.adapter.feign.client.rpc.DeptTreeTransRpcFeignClient;
import com.particle.dept.client.api.IDeptApplicationService;
import com.particle.dept.adapter.feign.client.rpc.DeptRpcFeignClient;
import com.particle.dept.client.dto.data.DeptTreeTransVO;
import com.particle.global.trans.result.TransResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 部门远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-04-12 14:19:42
 */
@Api(tags = "部门远程调用相关接口")
@RestController
@RequestMapping("/rpc/dept")
public class DeptRpcController extends AbstractBaseRpcAdapter implements DeptRpcFeignClient, DeptTreeTransRpcFeignClient {

	@Autowired
	private IDeptApplicationService iDeptApplicationService;

	@Autowired
	private DictTreeTransServiceImpl dictTreeTransService;

	@Override
	public boolean supportBatch(String type) {
		return dictTreeTransService.supportBatch(type);
	}

	@Override
	public List<TransResult<DeptTreeTransVO, Long>> transBatch(String type, Set<Long> keys) {
		return dictTreeTransService.transBatch(type, keys);
	}
}