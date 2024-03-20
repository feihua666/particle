package com.particle.openplatform.adapter.doc.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.openplatform.client.doc.api.IOpenplatformDocApiDocTemplateResponseCodeApplicationService;
import com.particle.openplatform.adapter.feign.client.doc.rpc.OpenplatformDocApiDocTemplateResponseCodeRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放接口文档模板响应码远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:49:10
 */
@Tag(name = "开放接口文档模板响应码远程调用相关接口")
@RestController
@RequestMapping("/rpc/openplatform_doc_api_doc_template_response_code")
public class OpenplatformDocApiDocTemplateResponseCodeRpcController extends AbstractBaseRpcAdapter implements OpenplatformDocApiDocTemplateResponseCodeRpcFeignClient  {

	@Autowired
	private IOpenplatformDocApiDocTemplateResponseCodeApplicationService iOpenplatformDocApiDocTemplateResponseCodeApplicationService;


}