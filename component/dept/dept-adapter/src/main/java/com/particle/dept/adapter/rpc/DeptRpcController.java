package com.particle.dept.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.dept.adapter.feign.client.rpc.DeptRpcFeignClient;
import com.particle.dept.adapter.feign.client.rpc.DeptTransRpcFeignClient;
import com.particle.dept.client.api.IDeptApplicationService;
import com.particle.dept.client.dto.data.DeptTransVO;
import com.particle.global.trans.result.TransResult;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "部门远程调用相关接口")
@RestController
@RequestMapping("/rpc/dept")
public class DeptRpcController extends AbstractBaseRpcAdapter implements DeptRpcFeignClient, DeptTransRpcFeignClient {

	@Autowired
	private IDeptApplicationService iDeptApplicationService;

	@Autowired
	private DeptTransServiceImpl deptTransService;

	@Override
	public boolean supportBatch(String type) {
		return deptTransService.supportBatch(type);
	}

	@Override
	public List<TransResult<DeptTransVO, Long>> transBatch(String type, Set<Long> keys) {
		return deptTransService.transBatch(type, keys);
	}
}