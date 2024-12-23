package com.particle.global.openapi.endpoint;

import com.particle.global.dto.response.SingleResponse;
import com.particle.global.openapi.endpoint.command.OpenapiTestCommand;
import com.particle.global.openapi.endpoint.vo.OpenapiTestVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 开放接口测试相关接口
 * </p>
 *
 * @author yangwei
 * @since 2023-08-04 09:55:44
 */
@Tag(name = "全局开放接口测试相关接口")
@RestController
@RequestMapping("/openapi")
public class OpenapiTestController {


	@PreAuthorize("hasAuthority('openapi:testGet')")
	@Operation(summary = "开放接口测试Get方法")
	@GetMapping( "/testGet")
	public SingleResponse<OpenapiTestVO> testGet(@Valid OpenapiTestCommand testCommand){
		OpenapiTestVO openapiTestVO = OpenapiTestVO.createByOpenapiTestCommand(testCommand);
		return SingleResponse.of(openapiTestVO);
	}

	@PreAuthorize("hasAuthority('openapi:testPostForm')")
	@Operation(summary = "开放接口测试PostForm方法")
	@PostMapping( value = "/testPostForm",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public SingleResponse<OpenapiTestVO> testPostForm(@Valid OpenapiTestCommand testCommand){
		OpenapiTestVO openapiTestVO = OpenapiTestVO.createByOpenapiTestCommand(testCommand);
		return SingleResponse.of(openapiTestVO);
	}

	@PreAuthorize("hasAuthority('openapi:testPostBody')")
	@Operation(summary = "开放接口测试PostBody方法")
	@PostMapping( value = "/testPostBody")
	public SingleResponse<OpenapiTestVO> testPostBody(@Valid @RequestBody OpenapiTestCommand testCommand){
		OpenapiTestVO openapiTestVO = OpenapiTestVO.createByOpenapiTestCommand(testCommand);
		return SingleResponse.of(openapiTestVO);
	}

}
