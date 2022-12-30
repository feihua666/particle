package com.particle.user.app.login.executor.representation;

import com.particle.user.app.login.structmapping.UserLoginRecordAppStructMapping;
import com.particle.user.client.login.dto.command.representation.UserLoginRecordQueryListCommand;
import com.particle.user.client.login.dto.data.UserLoginRecordVO;
import com.particle.user.infrastructure.login.dos.UserLoginRecordDO;
import com.particle.user.infrastructure.login.service.IUserLoginRecordService;
import com.particle.user.client.login.dto.command.representation.UserLoginRecordPageQueryCommand;
import com.particle.user.client.login.dto.command.representation.UserLoginRecordQueryDetailCommand;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.global.dto.response.MultiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import javax.validation.Valid;
import java.util.List;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 用户登录记录 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2022-11-26
 */
@Component
@Validated
public class UserLoginRecordQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IUserLoginRecordService iUserLoginRecordService;

	/**
	 * 执行 用户登录记录 列表查询指令
	 * @param userLoginRecordQueryListCommand
	 * @return
	 */
	public MultiResponse<UserLoginRecordVO> execute(@Valid UserLoginRecordQueryListCommand userLoginRecordQueryListCommand) {
		List<UserLoginRecordDO> userLoginRecordDO = iUserLoginRecordService.list(userLoginRecordQueryListCommand);
		List<UserLoginRecordVO> userLoginRecordVOs = UserLoginRecordAppStructMapping.instance.userLoginRecordDOsToUserLoginRecordVOs(userLoginRecordDO);
		return MultiResponse.of(userLoginRecordVOs);
	}
	/**
	 * 执行 用户登录记录 分页查询指令
	 * @param userLoginRecordPageQueryCommand
	 * @return
	 */
	public PageResponse<UserLoginRecordVO> execute(@Valid UserLoginRecordPageQueryCommand userLoginRecordPageQueryCommand) {
		Page<UserLoginRecordDO> page = iUserLoginRecordService.listPage(userLoginRecordPageQueryCommand);
		return UserLoginRecordAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 用户登录记录 展示用详情查询指令
	 * @param userLoginRecordQueryDetailCommand
	 * @return
	 */
	public SingleResponse<UserLoginRecordVO> execute(UserLoginRecordQueryDetailCommand userLoginRecordQueryDetailCommand) {
		UserLoginRecordDO byId = iUserLoginRecordService.getById(userLoginRecordQueryDetailCommand.getId());
		UserLoginRecordVO userLoginRecordVO = UserLoginRecordAppStructMapping.instance.userLoginRecordDOToUserLoginRecordVO(byId);
		return SingleResponse.of(userLoginRecordVO);
	}


	@Autowired
	public void setIUserLoginRecordService(IUserLoginRecordService iUserLoginRecordService) {
		this.iUserLoginRecordService = iUserLoginRecordService;
	}
}
