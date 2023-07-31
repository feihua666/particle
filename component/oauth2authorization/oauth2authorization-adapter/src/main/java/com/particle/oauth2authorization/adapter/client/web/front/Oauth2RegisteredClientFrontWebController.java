package com.particle.oauth2authorization.adapter.client.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.global.dto.response.MultiResponse;
import com.particle.oauth2authorization.client.client.api.IOauth2RegisteredClientApplicationService;
import com.particle.oauth2authorization.client.client.dto.command.representation.Oauth2AlgorithmListCommand;
import com.particle.oauth2authorization.client.client.dto.command.representation.Oauth2RegisteredClientQueryListCommand;
import com.particle.oauth2authorization.client.client.dto.data.Oauth2AlgorithmVO;
import com.particle.oauth2authorization.client.client.dto.data.Oauth2RegisteredClientVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * oauth2客户端前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-07-25 17:03:38
 */
@Tag(name = "oauth2客户端pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/oauth2_registered_client")
public class Oauth2RegisteredClientFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IOauth2RegisteredClientApplicationService iOauth2RegisteredClientApplicationService;

	@Operation(summary = "获取oauth2算法")
	@GetMapping("/algorithm_list")
	public MultiResponse<Oauth2AlgorithmVO> algorithmList(Oauth2AlgorithmListCommand oauth2AlgorithmListCommand){
		List<Oauth2AlgorithmVO> oauth2AlgorithmVOList = new ArrayList<>();
		List<String> signatureAlgorithm = Arrays.stream(SignatureAlgorithm.values()).map(SignatureAlgorithm::getName).collect(Collectors.toList());
		List<String> macAlgorithm = Arrays.stream(MacAlgorithm.values()).map(MacAlgorithm::getName).collect(Collectors.toList());

		if (Oauth2AlgorithmListCommand.AlgorithmType.mac.name().equals(oauth2AlgorithmListCommand.getType())) {
			oauth2AlgorithmVOList.addAll(Oauth2AlgorithmVO.createByAlgorithmStrs(macAlgorithm));
		}else if (Oauth2AlgorithmListCommand.AlgorithmType.signature.name().equals(oauth2AlgorithmListCommand.getType())) {
			oauth2AlgorithmVOList.addAll(Oauth2AlgorithmVO.createByAlgorithmStrs(signatureAlgorithm));
		}else {
			oauth2AlgorithmVOList.addAll(Oauth2AlgorithmVO.createByAlgorithmStrs(macAlgorithm));
			oauth2AlgorithmVOList.addAll(Oauth2AlgorithmVO.createByAlgorithmStrs(signatureAlgorithm));
		}
		return MultiResponse.of(oauth2AlgorithmVOList);
	}


}