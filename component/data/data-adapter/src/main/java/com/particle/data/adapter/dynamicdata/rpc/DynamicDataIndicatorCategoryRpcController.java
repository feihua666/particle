package com.particle.data.adapter.dynamicdata.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.data.client.dynamicdata.api.IDynamicDataIndicatorCategoryApplicationService;
import com.particle.data.adapter.feign.client.dynamicdata.rpc.DynamicDataIndicatorCategoryRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 动态数据指标分类远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:58
 */
@Tag(name = "动态数据指标分类远程调用相关接口")
@RestController
@RequestMapping("/rpc/dynamic_data_indicator_category")
public class DynamicDataIndicatorCategoryRpcController extends AbstractBaseRpcAdapter implements DynamicDataIndicatorCategoryRpcFeignClient  {

	@Autowired
	private IDynamicDataIndicatorCategoryApplicationService iDynamicDataIndicatorCategoryApplicationService;


}