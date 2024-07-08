package com.particle.dept.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.dept.adapter.feign.client.rpc.DeptTransRpcFeignClient;
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
 * 部门翻译远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-07-08 14:04:17
 */
@Tag(name = "部门翻译远程调用相关接口")
@RestController
@RequestMapping("/rpc/dept")
public class DeptTransRpcController extends AbstractBaseRpcAdapter implements DeptTransRpcFeignClient {

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