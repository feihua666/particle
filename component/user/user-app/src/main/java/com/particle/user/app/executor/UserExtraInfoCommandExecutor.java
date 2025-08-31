package com.particle.user.app.executor;

import com.particle.user.domain.gateway.UserExtraInfoGateway;
import com.particle.user.infrastructure.service.IUserExtraInfoService;
import com.particle.user.infrastructure.dos.UserExtraInfoDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 用户扩展信息 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-08-30 23:39:50
 */
@Component
@Validated
public class UserExtraInfoCommandExecutor  extends AbstractBaseExecutor {

	private UserExtraInfoGateway userExtraInfoGateway;
	private IUserExtraInfoService iUserExtraInfoService;
	/**
	 * 注入使用set方法
	 * @param userExtraInfoGateway
	 */
	@Autowired
	public void setUserExtraInfoGateway(UserExtraInfoGateway userExtraInfoGateway) {
		this.userExtraInfoGateway = userExtraInfoGateway;
	}
	@Autowired
	public void setIUserExtraInfoService(IUserExtraInfoService iUserExtraInfoService) {
		this.iUserExtraInfoService = iUserExtraInfoService;
	}
}
