package com.particle.user.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.user.app.structmapping.UserExtraInfoAppStructMapping;
import com.particle.user.client.dto.data.UserExtraInfoVO;
import com.particle.user.domain.UserExtraInfo;
import com.particle.user.domain.UserExtraInfoId;
import com.particle.user.domain.gateway.UserExtraInfoGateway;
import com.particle.user.infrastructure.service.IUserExtraInfoService;
import com.particle.user.infrastructure.dos.UserExtraInfoDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 用户扩展信息 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-08-30 23:39:50
 */
@Component
@Validated
public class UserExtraInfoDeleteCommandExecutor  extends AbstractBaseExecutor {

	private UserExtraInfoGateway userExtraInfoGateway;
	private IUserExtraInfoService iUserExtraInfoService;

	/**
	 * 执行 用户扩展信息 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<UserExtraInfoVO> execute(@Valid IdCommand deleteCommand) {
		UserExtraInfoId userExtraInfoId = UserExtraInfoId.of(deleteCommand.getId());
		UserExtraInfo byId = userExtraInfoGateway.getById(userExtraInfoId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = userExtraInfoGateway.delete(userExtraInfoId,deleteCommand);
		if (delete) {
			return SingleResponse.of(UserExtraInfoAppStructMapping.instance.toUserExtraInfoVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


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
