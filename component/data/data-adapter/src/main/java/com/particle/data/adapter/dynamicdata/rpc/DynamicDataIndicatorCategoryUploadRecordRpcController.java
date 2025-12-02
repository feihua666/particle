package com.particle.data.adapter.dynamicdata.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.data.client.dynamicdata.api.IDynamicDataIndicatorCategoryUploadRecordApplicationService;
import com.particle.data.adapter.feign.client.dynamicdata.rpc.DynamicDataIndicatorCategoryUploadRecordRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 动态数据指标分类上传记录远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2025-11-28 15:00:59
 */
@Tag(name = "动态数据指标分类上传记录远程调用相关接口")
@RestController
@RequestMapping("/rpc/dynamic_data_indicator_category_upload_record")
public class DynamicDataIndicatorCategoryUploadRecordRpcController extends AbstractBaseRpcAdapter implements DynamicDataIndicatorCategoryUploadRecordRpcFeignClient  {

	@Autowired
	private IDynamicDataIndicatorCategoryUploadRecordApplicationService iDynamicDataIndicatorCategoryUploadRecordApplicationService;


}