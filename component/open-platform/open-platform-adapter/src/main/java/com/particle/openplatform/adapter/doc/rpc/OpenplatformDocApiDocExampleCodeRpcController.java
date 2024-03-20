package com.particle.openplatform.adapter.doc.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.openplatform.client.doc.api.IOpenplatformDocApiDocExampleCodeApplicationService;
import com.particle.openplatform.adapter.feign.client.doc.rpc.OpenplatformDocApiDocExampleCodeRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放接口文档示例代码远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-03-18 17:04:11
 */
@Tag(name = "开放接口文档示例代码远程调用相关接口")
@RestController
@RequestMapping("/rpc/openplatform_doc_api_doc_example_code")
public class OpenplatformDocApiDocExampleCodeRpcController extends AbstractBaseRpcAdapter implements OpenplatformDocApiDocExampleCodeRpcFeignClient  {

	@Autowired
	private IOpenplatformDocApiDocExampleCodeApplicationService iOpenplatformDocApiDocExampleCodeApplicationService;


}