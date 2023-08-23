package com.particle.global.openapi.endpoint.api;

import com.particle.global.openapi.api.portal.OpenapiExecutePortalService;
import com.particle.global.openapi.collect.OpenapiCollectTool;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.endpoint.command.OpenapiCommand;
import com.particle.global.openapi.filter.GlobalOpenApiFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 开放接口数据接口
 * 注：本接口肯定是经过了开放过滤器{@link GlobalOpenApiFilter}所有前置条件已经处理完成
 * </p>
 *
 * @author yw
 * @since 2023-08-16 14:35:48
 */
@Tag(name = "开放服务接口")
@RestController
@RequestMapping(OpenapiPortalApiController.API_REQUEST_MAPPING)
public class OpenapiPortalApiController {

	public static final String API_REQUEST_MAPPING = "/openapi";
	public static final String API_ENTRY = "";
	public static final String API_ENTRY_PREFIX = API_REQUEST_MAPPING + API_ENTRY;
	public static final String API_ENTRY_REQUEST_MAPPING = API_ENTRY + "/**";

	@Autowired
	private OpenapiExecutePortalService openapiExecutePortalService;

	@PreAuthorize("@pms.hasPermission('openPlatform:api')")
	@Operation(summary = "开放服务接口入口")
	@PostMapping(API_ENTRY_REQUEST_MAPPING)
	public Object apiEntry(@RequestBody Object param,HttpServletRequest request){

		OpenapiCommand openapiCommand = new OpenapiCommand();
		openapiCommand.setParam(param);
		openapiCommand.setQueryString(request.getQueryString());
		// 理论上这里不可能为 null，否则可能是一个bug
		OpenapiContext context = OpenapiCollectTool.getContext();
		return openapiExecutePortalService.execute(openapiCommand,context);
	}
}