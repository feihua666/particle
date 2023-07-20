package com.particle.func.adapter.funcapplicationfuncrel.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.func.adapter.feign.client.funcapplicationfuncrel.rpc.FuncApplicationFuncRelRpcFeignClient;
import com.particle.func.client.funcapplicationfuncrel.api.IFuncApplicationFuncRelApplicationService;
import com.particle.func.infrastructure.funcapplicationfuncrel.dos.FuncApplicationFuncRelDO;
import com.particle.func.infrastructure.funcapplicationfuncrel.service.IFuncApplicationFuncRelService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 功能应用功能关系远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:15:29
 */
@Tag(name = "功能应用功能关系远程调用相关接口")
@RestController
@RequestMapping("/rpc/func_application_func_rel")
public class FuncApplicationFuncRelRpcController extends AbstractBaseRpcAdapter implements FuncApplicationFuncRelRpcFeignClient  {

	@Autowired
	private IFuncApplicationFuncRelApplicationService iFuncApplicationFuncRelApplicationService;

	@Autowired
	private IFuncApplicationFuncRelService iFuncApplicationFuncRelService;

	@Override
	@Operation(summary = "菜单功能更新详情")
	@GetMapping("/getFuncIdsByFuncApplicationId")
	public List<Long> getFuncIdsByFuncApplicationId(Long funcApplicationId) {
		List<FuncApplicationFuncRelDO> funcApplicationFuncRelDOS = iFuncApplicationFuncRelService.listByColumn(funcApplicationId, FuncApplicationFuncRelDO::getFuncApplicationId);
		List<Long> collect = funcApplicationFuncRelDOS.stream().map(FuncApplicationFuncRelDO::getFuncId).collect(Collectors.toList());

		return collect;
	}
}