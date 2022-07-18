package com.particle.test.app.executor;

import com.particle.test.app.structmapping.UserSimpleAppStructMapping;
import com.particle.test.client.dto.command.UserSimpleQueryListCommand;
import com.particle.test.client.dto.data.UserSimpleVO;
import com.particle.test.infrastructure.dos.UserSimpleDO;
import com.particle.test.infrastructure.service.IUserSimpleService;
import com.particle.test.client.dto.command.UserSimplePageQueryCommand;
import com.particle.test.client.dto.command.UserSimpleQueryDetailCommand;
import com.particle.test.client.dto.command.UserSimpleQueryDetailForUpdateCommand;
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
 * 简单用户 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2022-07-15
 */
@Component
@Validated
public class UserSimpleQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IUserSimpleService iUserSimpleService;

	/**
	 * 执行 简单用户 列表查询指令
	 * @param userSimpleQueryListCommand
	 * @return
	 */
	public MultiResponse<UserSimpleVO> execute(@Valid UserSimpleQueryListCommand userSimpleQueryListCommand) {
		List<UserSimpleDO> userSimpleDO = iUserSimpleService.list(userSimpleQueryListCommand);
		List<UserSimpleVO> userSimpleVOs = UserSimpleAppStructMapping.instance.userSimpleDOsToUserSimpleVOs(userSimpleDO);
		return MultiResponse.of(userSimpleVOs);
	}
	/**
	 * 执行 简单用户 分页查询指令
	 * @param userSimplePageQueryCommand
	 * @return
	 */
	public PageResponse<UserSimpleVO> execute(@Valid UserSimplePageQueryCommand userSimplePageQueryCommand) {
		Page<UserSimpleDO> page = iUserSimpleService.listPage(userSimplePageQueryCommand);
		return UserSimpleAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 简单用户 展示用详情查询指令
	 * @param userSimpleQueryDetailCommand
	 * @return
	 */
	public SingleResponse<UserSimpleVO> execute(UserSimpleQueryDetailCommand userSimpleQueryDetailCommand) {
		UserSimpleDO byId = iUserSimpleService.getById(userSimpleQueryDetailCommand.getId());
		UserSimpleVO userSimpleVO = UserSimpleAppStructMapping.instance.userSimpleDOToUserSimpleVO(byId);
		return SingleResponse.of(userSimpleVO);
	}
	/**
	 * 执行 简单用户 更新用详情查询指令
	 * @param userSimpleQueryDetailForUpdateCommand
	 * @return
	 */
	public SingleResponse<UserSimpleVO> execute(UserSimpleQueryDetailForUpdateCommand userSimpleQueryDetailForUpdateCommand) {
		UserSimpleDO byId = iUserSimpleService.getById(userSimpleQueryDetailForUpdateCommand.getId());
		UserSimpleVO userSimpleVO = UserSimpleAppStructMapping.instance.userSimpleDOToUserSimpleVO(byId);
		return SingleResponse.of(userSimpleVO);
	}

	@Autowired
	public void setIUserSimpleService(IUserSimpleService iUserSimpleService) {
		this.iUserSimpleService = iUserSimpleService;
	}
}
