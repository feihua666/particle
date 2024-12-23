package com.particle.openplatform.adapter.app.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.openapi.enums.OpenapiDigestAlgorithm;
import com.particle.global.openapi.enums.OpenapiSignatureAlgorithm;
import com.particle.openplatform.client.app.api.IOpenplatformAppApplicationService;
import com.particle.openplatform.client.app.dto.command.representation.OpenplatformAlgorithmListCommand;
import com.particle.openplatform.client.app.dto.data.OpenplatformAlgorithmVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 开放平台应用前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:40:15
 */
@Tag(name = "开放平台应用pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/openplatform_app")
public class OpenplatformAppFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IOpenplatformAppApplicationService iOpenplatformAppApplicationService;

	@Operation(summary = "获取开放平台算法")
	@GetMapping("/algorithm_list")
	public MultiResponse<OpenplatformAlgorithmVO> algorithmList(@Valid OpenplatformAlgorithmListCommand openplatformAlgorithmListCommand){
		List<OpenplatformAlgorithmVO> oauth2AlgorithmVOList = new ArrayList<>();
		List<String> digestAlgorithm = Arrays.stream(OpenapiDigestAlgorithm.values()).map(item -> item.name()).collect(Collectors.toList());
		List<String> signatureAlgorithm = Arrays.stream(OpenapiSignatureAlgorithm.values()).map(item -> item.name()).collect(Collectors.toList());

		if (OpenplatformAlgorithmListCommand.AlgorithmType.digest.name().equals(openplatformAlgorithmListCommand.getType())) {
			oauth2AlgorithmVOList.addAll(OpenplatformAlgorithmVO.createByAlgorithmStrs(digestAlgorithm));
		}else if (OpenplatformAlgorithmListCommand.AlgorithmType.signature.name().equals(openplatformAlgorithmListCommand.getType())) {
			oauth2AlgorithmVOList.addAll(OpenplatformAlgorithmVO.createByAlgorithmStrs(signatureAlgorithm));
		}
		return MultiResponse.of(oauth2AlgorithmVOList);
	}
}
