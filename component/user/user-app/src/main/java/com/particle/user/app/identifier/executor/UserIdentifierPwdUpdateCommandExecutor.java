package com.particle.user.app.identifier.executor;

import com.particle.global.dto.response.Response;
import com.particle.global.exception.ExceptionFactory;
import com.particle.global.exception.biz.BizException;
import com.particle.user.app.identifier.structmapping.UserIdentifierPwdAppStructMapping;
import com.particle.user.app.structmapping.UserAppStructMapping;
import com.particle.user.client.exception.ErrorCodeUserEnum;
import com.particle.user.client.identifier.dto.command.UserIdentifierPwdUpdateCommand;
import com.particle.user.client.identifier.dto.command.UserIdentifierResetPasswordCommand;
import com.particle.user.client.identifier.dto.command.UserResetPasswordCommand;
import com.particle.user.client.identifier.dto.data.UserIdentifierPwdVO;
import com.particle.user.domain.identifier.UserIdentifierPwd;
import com.particle.user.domain.identifier.UserIdentifierPwdId;
import com.particle.user.domain.identifier.gateway.UserIdentifierPwdGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.user.infrastructure.identifier.dos.UserIdentifierDO;
import com.particle.user.infrastructure.identifier.dos.UserIdentifierPwdDO;
import com.particle.user.infrastructure.identifier.service.IUserIdentifierPwdService;
import com.particle.user.infrastructure.identifier.service.IUserIdentifierService;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 用户密码 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Component
@Validated
public class UserIdentifierPwdUpdateCommandExecutor  extends AbstractBaseExecutor {

	private UserIdentifierPwdGateway userIdentifierPwdGateway;

	private IUserIdentifierService iUserIdentifierService;

	private IUserIdentifierPwdService iUserIdentifierPwdService;
	/**
	 * 执行 用户密码 更新指令
	 * @param userIdentifierPwdUpdateCommand
	 * @return
	 */
	public SingleResponse<UserIdentifierPwdVO> execute(@Valid UserIdentifierPwdUpdateCommand userIdentifierPwdUpdateCommand) {
		UserIdentifierPwd userIdentifierPwd = createByUserIdentifierPwdUpdateCommand(userIdentifierPwdUpdateCommand);
		userIdentifierPwd.setUpdateControl(userIdentifierPwdUpdateCommand);
		boolean save = userIdentifierPwdGateway.save(userIdentifierPwd);
		if (save) {
			return SingleResponse.of(UserIdentifierPwdAppStructMapping.instance.toUserIdentifierPwdVO(userIdentifierPwd));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 重置登录标识密码
	 * @param userIdentifierResetPasswordCommand
	 * @return
	 */
	public Response identifierResetPassword(@Valid UserIdentifierResetPasswordCommand userIdentifierResetPasswordCommand){
		UserIdentifierPwdUpdateCommand userIdentifierPwdUpdateCommand = new UserIdentifierPwdUpdateCommand();
		UserIdentifierDO userIdentifierDO = iUserIdentifierService.getById(userIdentifierResetPasswordCommand.getUserIdentifierId());
		userIdentifierPwdUpdateCommand.setUserId(userIdentifierDO.getUserId());
		userIdentifierPwdUpdateCommand.setIdentifierId(userIdentifierResetPasswordCommand.getUserIdentifierId());
		userIdentifierPwdUpdateCommand.setPwd(userIdentifierResetPasswordCommand.getEncodedPassword());
		userIdentifierPwdUpdateCommand.setPwdEncryptFlag(userIdentifierResetPasswordCommand.getPwdEncryptFlag());
		userIdentifierPwdUpdateCommand.setComplexity(userIdentifierResetPasswordCommand.getComplexity());
		userIdentifierPwdUpdateCommand.setIsExpired(false);
		userIdentifierPwdUpdateCommand.setExpiredReason(null);
		userIdentifierPwdUpdateCommand.setExpireAt(userIdentifierResetPasswordCommand.getExpireAt());
		userIdentifierPwdUpdateCommand.setPwdModifiedAt(LocalDateTime.now());

		userIdentifierPwdUpdateCommand.setIsNeedUpdate(userIdentifierResetPasswordCommand.getIsNeedUpdate());

		UserIdentifierPwdDO userIdentifierPwdDO = iUserIdentifierPwdService.getByIdentifierId(userIdentifierDO.getId());

		if (userIdentifierPwdDO == null) {
			throw ExceptionFactory.bizException(ErrorCodeUserEnum.USER_IDENTIFIER_PASSWORD_NOT_EXIST, "当前登录标识没有密码，请先添加");

		}

		userIdentifierPwdUpdateCommand.setId(userIdentifierPwdDO.getId());

		SingleResponse<UserIdentifierPwdVO> singleResponse = execute(userIdentifierPwdUpdateCommand);

		Response response = Response.buildSuccess();
		response.setErrCode(singleResponse.getErrCode());
		response.setErrMessage(singleResponse.getErrMessage());
		response.setStatus(singleResponse.getStatus());
		response.setSuccess(singleResponse.isSuccess());

		return response;
	}

	/**
	 * 用户重置密码
	 * @param userResetPasswordCommand
	 * @return
	 */
	public Response userResetPassword(@Valid UserResetPasswordCommand userResetPasswordCommand) {
		List<UserIdentifierDO> byUserId = iUserIdentifierService.getByUserId(userResetPasswordCommand.getUserId());
		if (byUserId.isEmpty()) {
			throw new BizException("当前用户没有账号，请先添加账号");
		}
		for (UserIdentifierDO userIdentifierDO : byUserId) {
			UserIdentifierResetPasswordCommand userIdentifierResetPasswordCommand = UserAppStructMapping.instance.userResetPasswordCommandToUserIdentifierResetPasswordCommand(userResetPasswordCommand);
			userIdentifierResetPasswordCommand.setUserIdentifierId(userIdentifierDO.getId());

			try {
				Response response = identifierResetPassword(userIdentifierResetPasswordCommand);
				// 如果没有成功，抛出异常，可以回滚数据
				if (!response.isSuccess()) {
					throw ExceptionFactory.bizException(response.iErrorCode());
				}
			} catch (BizException e) {
				// 如果不密码不存在异常，直接抛出，以回滚数据
				if (ErrorCodeUserEnum.USER_IDENTIFIER_PASSWORD_NOT_EXIST != e.getError()) {
					throw e;
				}
			}
		}

		return Response.buildSuccess();
	}

	/**
	 * 根据区域创建指令创建区域模型
	 * @param userIdentifierPwdUpdateCommand
	 * @return
	 */
	private UserIdentifierPwd createByUserIdentifierPwdUpdateCommand(UserIdentifierPwdUpdateCommand userIdentifierPwdUpdateCommand){
		UserIdentifierPwd userIdentifierPwd = UserIdentifierPwd.create();
		UserIdentifierPwdUpdateCommandToUserIdentifierPwdMapping.instance.fillUserIdentifierPwdByUserIdentifierPwdUpdateCommand(userIdentifierPwd, userIdentifierPwdUpdateCommand);
		return userIdentifierPwd;
	}

	@Mapper
	interface UserIdentifierPwdUpdateCommandToUserIdentifierPwdMapping{
		UserIdentifierPwdUpdateCommandToUserIdentifierPwdMapping instance = Mappers.getMapper(UserIdentifierPwdUpdateCommandToUserIdentifierPwdMapping.class );

		default UserIdentifierPwdId map(Long id){
			if (id == null) {
				return null;
			}
			return UserIdentifierPwdId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param userIdentifierPwd
		 * @param userIdentifierPwdUpdateCommand
		 */
		void fillUserIdentifierPwdByUserIdentifierPwdUpdateCommand(@MappingTarget UserIdentifierPwd userIdentifierPwd, UserIdentifierPwdUpdateCommand userIdentifierPwdUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param userIdentifierPwdGateway
	 */
	@Autowired
	public void setUserIdentifierPwdGateway(UserIdentifierPwdGateway userIdentifierPwdGateway) {
		this.userIdentifierPwdGateway = userIdentifierPwdGateway;
	}
	@Autowired
	public void setiUserIdentifierService(IUserIdentifierService iUserIdentifierService) {
		this.iUserIdentifierService = iUserIdentifierService;
	}

	@Autowired
	public void setiUserIdentifierPwdService(IUserIdentifierPwdService iUserIdentifierPwdService) {
		this.iUserIdentifierPwdService = iUserIdentifierPwdService;
	}
}
