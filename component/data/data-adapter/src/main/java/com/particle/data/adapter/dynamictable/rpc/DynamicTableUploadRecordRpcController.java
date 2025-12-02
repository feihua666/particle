package com.particle.data.adapter.dynamictable.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.data.client.dynamictable.api.IDynamicTableUploadRecordApplicationService;
import com.particle.data.adapter.feign.client.dynamictable.rpc.DynamicTableUploadRecordRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 动态数据表格上传记录远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2025-11-28 15:00:17
 */
@Tag(name = "动态数据表格上传记录远程调用相关接口")
@RestController
@RequestMapping("/rpc/dynamic_table_upload_record")
public class DynamicTableUploadRecordRpcController extends AbstractBaseRpcAdapter implements DynamicTableUploadRecordRpcFeignClient  {

	@Autowired
	private IDynamicTableUploadRecordApplicationService iDynamicTableUploadRecordApplicationService;


}
