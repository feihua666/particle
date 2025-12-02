package com.particle.data.adapter.dynamictable.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.data.client.dynamictable.api.IDynamicTableApplicationService;
import com.particle.data.adapter.feign.client.dynamictable.rpc.DynamicTableRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 动态数据表格远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:29:35
 */
@Tag(name = "动态数据表格远程调用相关接口")
@RestController
@RequestMapping("/rpc/dynamic_table")
public class DynamicTableRpcController extends AbstractBaseRpcAdapter implements DynamicTableRpcFeignClient  {

	@Autowired
	private IDynamicTableApplicationService iDynamicTableApplicationService;


}