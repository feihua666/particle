package com.particle.user.adapter.identifier.login;

import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.security.security.login.LoginUser;
import com.particle.user.app.identifier.structmapping.UserIdentifierAppStructMapping;
import com.particle.user.client.identifier.api.IUserIdentifierApplicationService;
import com.particle.user.client.identifier.dto.command.UserIdentifierCreateCommand;
import com.particle.user.client.identifier.dto.command.UserIdentifierCreateEmailBindCommand;
import com.particle.user.client.identifier.dto.command.UserIdentifierCreateMobilelBindCommand;
import com.particle.user.client.identifier.dto.data.UserIdentifierVO;
import com.particle.user.domain.enums.UserAccountType;
import com.particle.user.domain.gateway.UserDictGateway;
import com.particle.user.infrastructure.identifier.dos.UserIdentifierDO;
import com.particle.user.infrastructure.identifier.service.IUserIdentifierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户账号登录标识，登录用户相关
 * </p>
 *
 * @author yangwei
 * @since 2023-05-04 16:59:38
 */
@RestController
@RequestMapping("/user-identifier/login")
@Tag(name = "用户账号登录标识，登录用户相关")
public class IdentifierLoginController {

	@Autowired
	private IUserIdentifierService iUserIdentifierService;

	@Autowired
	private IUserIdentifierApplicationService iUserIdentifierApplicationService;

	@Autowired
	private UserDictGateway userDictGateway;

	@Operation(summary = "获取当前登录用户的登录标识")
	@PreAuthorize("hasAuthority('user')")
	@GetMapping("/identifier")
	@ResponseStatus(HttpStatus.OK)
	public MultiResponse<UserIdentifierVO> identifier(Long identityTypeDictId, @Parameter(hidden = true) LoginUser loginUser) {
		List<UserIdentifierDO> byUserId = iUserIdentifierService.getByUserId(loginUser.getId());
		if (identityTypeDictId != null) {
			byUserId = byUserId.stream().filter(item -> identityTypeDictId.equals(item.getIdentityTypeDictId())).collect(Collectors.toList());
		}
		List<UserIdentifierVO> userIdentifierVOS = UserIdentifierAppStructMapping.instance.userIdentifierDOsToUserIdentifierVOs(byUserId);
		return MultiResponse.of(userIdentifierVOS);
	}

	@Operation(summary = "获取当前登录用户绑定的邮箱")
	@PreAuthorize("hasAuthority('user')")
	@GetMapping("/identifierEmail")
	@ResponseStatus(HttpStatus.OK)
	public MultiResponse<UserIdentifierVO> identifierEmail(@Parameter(hidden = true) LoginUser loginUser) {
		Long identityTypeDictId = userDictGateway.getDictIdByGroupCodeAndItemValue(
				UserAccountType.Group.user_account_type.groupCode(),
				UserAccountType.front_email.itemValue());

		return identifier(identityTypeDictId,loginUser);
	}

	@Operation(summary = "获取当前登录用户绑定的手机")
	@PreAuthorize("hasAuthority('user')")
	@GetMapping("/identifierMobile")
	@ResponseStatus(HttpStatus.OK)
	public MultiResponse<UserIdentifierVO> identifierMobile(@Parameter(hidden = true) LoginUser loginUser) {
		Long identityTypeDictId = userDictGateway.getDictIdByGroupCodeAndItemValue(
				UserAccountType.Group.user_account_type.groupCode(),
				UserAccountType.front_mobile.itemValue());

		return identifier(identityTypeDictId,loginUser);
	}

	@PreAuthorize("hasAuthority('user')")
	@Operation(summary = "绑定邮箱")
	@PostMapping("/createEmailBind")
	public SingleResponse<UserIdentifierVO> createEmailBind(@RequestBody UserIdentifierCreateEmailBindCommand userIdentifierCreateCommand, @Parameter(hidden = true) LoginUser loginUser){
		Long identityTypeDictId = userDictGateway.getDictIdByGroupCodeAndItemValue(
				UserAccountType.Group.user_account_type.groupCode(),
				UserAccountType.front_email.itemValue());
		return doCreateBind(loginUser.getId(),userIdentifierCreateCommand.getEmail(),identityTypeDictId);
	}

	@PreAuthorize("hasAuthority('user')")
	@Operation(summary = "绑定手机号")
	@PostMapping("/createMobileBind")
	public SingleResponse<UserIdentifierVO> createMobileBind(@RequestBody UserIdentifierCreateMobilelBindCommand userIdentifierCreateMobilelBindCommand, @Parameter(hidden = true) LoginUser loginUser){
		Long identityTypeDictId = userDictGateway.getDictIdByGroupCodeAndItemValue(
				UserAccountType.Group.user_account_type.groupCode(),
				UserAccountType.front_mobile.itemValue());
		return doCreateBind(loginUser.getId(),userIdentifierCreateMobilelBindCommand.getMobile(),identityTypeDictId);
	}

	/**
	 * 添加绑定
	 * @param userId
	 * @param identifier
	 * @param identityTypeDictId
	 * @return
	 */
	private SingleResponse<UserIdentifierVO> doCreateBind(Long userId,String identifier, Long identityTypeDictId) {
		UserIdentifierCreateCommand userIdentifierCreateCommand = new UserIdentifierCreateCommand();
		userIdentifierCreateCommand.setUserId(userId);
		userIdentifierCreateCommand.setIdentifier(identifier);
		userIdentifierCreateCommand.setIdentityTypeDictId(identityTypeDictId);
		userIdentifierCreateCommand.setIsLock(false);
		userIdentifierCreateCommand.setIsExpired(false);
		return iUserIdentifierApplicationService.createBind(userIdentifierCreateCommand);

	}
}
