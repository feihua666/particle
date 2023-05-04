package com.particle.user.adapter.identifier.login;

import com.particle.global.dto.response.MultiResponse;
import com.particle.global.security.security.login.LoginUser;
import com.particle.user.app.identifier.structmapping.UserIdentifierAppStructMapping;
import com.particle.user.client.identifier.dto.data.UserIdentifierVO;
import com.particle.user.infrastructure.identifier.dos.UserIdentifierDO;
import com.particle.user.infrastructure.identifier.service.IUserIdentifierService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

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
@Api(tags = "用户账号登录标识，登录用户相关")
public class IdentifierLoginController {

	@Autowired
	private IUserIdentifierService iUserIdentifierService;

	@ApiOperation("获取当前登录用户的登录标识")
	@PreAuthorize("hasAuthority('user')")
	@GetMapping("/identifier")
	@ResponseStatus(HttpStatus.OK)
	public MultiResponse<UserIdentifierVO> identifier(@ApiIgnore LoginUser loginUser) {
		List<UserIdentifierDO> byUserId = iUserIdentifierService.getByUserId(loginUser.getId());
		List<UserIdentifierVO> userIdentifierVOS = UserIdentifierAppStructMapping.instance.userIdentifierDOsToUserIdentifierVOs(byUserId);
		return MultiResponse.of(userIdentifierVOS);
	}
}
