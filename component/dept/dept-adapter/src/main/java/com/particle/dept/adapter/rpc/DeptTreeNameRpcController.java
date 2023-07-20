package com.particle.dept.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.dept.client.api.IDeptTreeNameApplicationService;
import com.particle.dept.adapter.feign.client.rpc.DeptTreeNameRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 部门树名称远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-04-12 11:42:10
 */
@Tag(name = "部门树名称远程调用相关接口")
@RestController
@RequestMapping("/rpc/dept_tree_name")
public class DeptTreeNameRpcController extends AbstractBaseRpcAdapter implements DeptTreeNameRpcFeignClient  {

	@Autowired
	private IDeptTreeNameApplicationService iDeptTreeNameApplicationService;


}