package com.particle.dept.adapter.deptuserrel.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.dept.adapter.feign.client.deptuserrel.rpc.DeptUserRelRpcFeignClient;
import com.particle.dept.client.deptuserrel.api.IDeptUserRelApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 部门用户关系远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-05-17 10:28:42
 */
@Tag(name = "部门用户关系远程调用相关接口")
@RestController
@RequestMapping("/rpc/dept_user_rel")
public class DeptUserRelRpcController extends AbstractBaseRpcAdapter implements DeptUserRelRpcFeignClient  {

	@Autowired
	private IDeptUserRelApplicationService iDeptUserRelApplicationService;


}
