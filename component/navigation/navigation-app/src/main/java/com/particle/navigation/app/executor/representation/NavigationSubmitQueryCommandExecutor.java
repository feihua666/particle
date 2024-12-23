package com.particle.navigation.app.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.navigation.app.structmapping.NavigationSubmitAppStructMapping;
import com.particle.navigation.client.dto.command.representation.NavigationSubmitPageQueryCommand;
import com.particle.navigation.client.dto.command.representation.NavigationSubmitQueryListCommand;
import com.particle.navigation.client.dto.data.NavigationSubmitVO;
import com.particle.navigation.infrastructure.dos.NavigationSubmitDO;
import com.particle.navigation.infrastructure.service.INavigationSubmitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 导航提交 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-11-03 11:09:19
 */
@Component
@Validated
public class NavigationSubmitQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private INavigationSubmitService iNavigationSubmitService;

	/**
	 * 执行 导航提交 列表查询指令
	 * @param navigationSubmitQueryListCommand
	 * @return
	 */
	public MultiResponse<NavigationSubmitVO> execute(@Valid NavigationSubmitQueryListCommand navigationSubmitQueryListCommand) {
		List<NavigationSubmitDO> navigationSubmitDO = iNavigationSubmitService.list(navigationSubmitQueryListCommand);
		List<NavigationSubmitVO> navigationSubmitVOs = NavigationSubmitAppStructMapping.instance.navigationSubmitDOsToNavigationSubmitVOs(navigationSubmitDO);
		return MultiResponse.of(navigationSubmitVOs);
	}
	/**
	 * 执行 导航提交 分页查询指令
	 * @param navigationSubmitPageQueryCommand
	 * @return
	 */
	public PageResponse<NavigationSubmitVO> execute(@Valid NavigationSubmitPageQueryCommand navigationSubmitPageQueryCommand) {
		Page<NavigationSubmitDO> page = iNavigationSubmitService.listPage(navigationSubmitPageQueryCommand);
		return NavigationSubmitAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 导航提交 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<NavigationSubmitVO> executeDetail(IdCommand detailCommand) {
		NavigationSubmitDO byId = iNavigationSubmitService.getById(detailCommand.getId());
		NavigationSubmitVO navigationSubmitVO = NavigationSubmitAppStructMapping.instance.navigationSubmitDOToNavigationSubmitVO(byId);
		return SingleResponse.of(navigationSubmitVO);
	}
	/**
	 * 执行 导航提交 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<NavigationSubmitVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		NavigationSubmitDO byId = iNavigationSubmitService.getById(detailForUpdateCommand.getId());
		NavigationSubmitVO navigationSubmitVO = NavigationSubmitAppStructMapping.instance.navigationSubmitDOToNavigationSubmitVO(byId);
		return SingleResponse.of(navigationSubmitVO);
	}


	@Autowired
	public void setINavigationSubmitService(INavigationSubmitService iNavigationSubmitService) {
		this.iNavigationSubmitService = iNavigationSubmitService;
	}
}
