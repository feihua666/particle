package com.particle.dept.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.dept.adapter.feign.client.rpc.DeptTreeRpcFeignClient;
import com.particle.dept.client.api.IDeptTreeApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 部门树远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-04-12 11:41:43
 */
@Tag(name = "部门树远程调用相关接口")
@RestController
@RequestMapping("/rpc/dept_tree")
public class DeptTreeRpcController extends AbstractBaseRpcAdapter implements DeptTreeRpcFeignClient {

	@Autowired
	private IDeptTreeApplicationService iDeptTreeApplicationService;

}