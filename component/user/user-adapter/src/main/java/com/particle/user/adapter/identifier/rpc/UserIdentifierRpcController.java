package com.particle.user.adapter.identifier.rpc;

import com.particle.user.app.identifier.structmapping.UserIdentifierAppStructMapping;
import com.particle.user.client.identifier.api.IUserIdentifierApplicationService;
import com.particle.user.adapter.feign.client.identifier.rpc.UserIdentifierRpcFeignClient;
import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.global.dto.response.SingleResponse;
import com.particle.user.client.identifier.dto.data.UserIdentifierVO;
import com.particle.user.infrastructure.identifier.dos.UserIdentifierDO;
import com.particle.user.infrastructure.identifier.service.IUserIdentifierService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户登录标识远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Tag(name = "用户登录标识远程调用相关接口")
@RestController
@RequestMapping("/rpc/user-identifier")
public class UserIdentifierRpcController extends AbstractBaseRpcAdapter implements UserIdentifierRpcFeignClient {

	@Autowired
	private IUserIdentifierApplicationService iUserIdentifierApplicationService;


	@Autowired
	private IUserIdentifierService iIdentifierService;

	@Operation(summary = "根据登录标识获取")
	@GetMapping("/getByIdentifier")
	@Override
	public SingleResponse<UserIdentifierVO> getByIdentifier(String identifier) {
		UserIdentifierDO userIdentifierDO = iIdentifierService.getByIdentifier(identifier);
		if (userIdentifierDO == null) {
			return SingleResponse.buildSuccess();
		}

		UserIdentifierVO userIdentifierVO = UserIdentifierAppStructMapping.instance.userIdentifierDOToUserIdentifierVO(userIdentifierDO);
		return SingleResponse.of(userIdentifierVO);
	}

	@Override
	public SingleResponse<UserIdentifierVO> getByUserIdAndType(Long userId, Long identifierTypeDictId) {
		UserIdentifierDO userIdentifierDO = iIdentifierService.getByUserIdAndIdentifierTypeDictId(userId, identifierTypeDictId);
		if (userIdentifierDO == null) {
			return SingleResponse.buildSuccess();
		}

		UserIdentifierVO userIdentifierVO = UserIdentifierAppStructMapping.instance.userIdentifierDOToUserIdentifierVO(userIdentifierDO);
		return SingleResponse.of(userIdentifierVO);
	}
}