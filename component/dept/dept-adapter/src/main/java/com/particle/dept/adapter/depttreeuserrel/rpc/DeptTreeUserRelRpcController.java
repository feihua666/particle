package com.particle.dept.adapter.depttreeuserrel.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.dept.client.depttreeuserrel.api.IDeptTreeUserRelApplicationService;
import com.particle.dept.adapter.feign.client.depttreeuserrel.rpc.DeptTreeUserRelRpcFeignClient;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 部门树用户关系远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-04-12 17:28:43
 */
@Api(tags = "部门树用户关系远程调用相关接口")
@RestController
@RequestMapping("/rpc/dept_tree_user_rel")
public class DeptTreeUserRelRpcController extends AbstractBaseRpcAdapter implements DeptTreeUserRelRpcFeignClient  {

	@Autowired
	private IDeptTreeUserRelApplicationService iDeptTreeUserRelApplicationService;


}