package com.particle.dept.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.dept.adapter.feign.client.rpc.DeptTreeTransRpcFeignClient;
import com.particle.dept.client.dto.data.DeptTreeTransVO;
import com.particle.global.trans.result.TransResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 部门树翻译远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-07-08 13:03:52
 */
@Tag(name = "部门树翻译远程调用相关接口")
@RestController
@RequestMapping("/rpc/dept_tree")
public class DeptTreeTransRpcController extends AbstractBaseRpcAdapter implements DeptTreeTransRpcFeignClient {

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