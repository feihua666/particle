package com.particle.dept.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.dept.adapter.feign.client.rpc.DeptTreeTransRpcFeignClient;
import com.particle.dept.client.api.IDeptTreeApplicationService;
import com.particle.dept.adapter.feign.client.rpc.DeptTreeRpcFeignClient;
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
 * 部门树远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-04-12 11:41:43
 */
@Api(tags = "部门树远程调用相关接口")
@RestController
@RequestMapping("/rpc/dept_tree")
public class DeptTreeRpcController extends AbstractBaseRpcAdapter implements DeptTreeRpcFeignClient, DeptTreeTransRpcFeignClient {

	@Autowired
	private IDeptTreeApplicationService iDeptTreeApplicationService;

	@Autowired
	private DeptTreeTransServiceImpl deptTreeTransService;

	@Override
	public boolean supportBatch(String type) {
		return deptTreeTransService.supportBatch(type);
	}

	@Override
	public List<TransResult<DeptTreeTransVO, Long>> transBatch(String type, Set<Long> keys) {
		return deptTreeTransService.transBatch(type, keys);
	}
}