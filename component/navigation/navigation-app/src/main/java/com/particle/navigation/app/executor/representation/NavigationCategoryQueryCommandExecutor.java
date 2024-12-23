package com.particle.navigation.app.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.navigation.app.structmapping.NavigationCategoryAppStructMapping;
import com.particle.navigation.client.dto.command.representation.NavigationCategoryPageQueryCommand;
import com.particle.navigation.client.dto.command.representation.NavigationCategoryQueryListCommand;
import com.particle.navigation.client.dto.data.NavigationCategoryVO;
import com.particle.navigation.infrastructure.dos.NavigationCategoryDO;
import com.particle.navigation.infrastructure.service.INavigationCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 导航分类 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-10-22 15:34:42
 */
@Component
@Validated
public class NavigationCategoryQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private INavigationCategoryService iNavigationCategoryService;

	/**
	 * 执行 导航分类 列表查询指令
	 * @param navigationCategoryQueryListCommand
	 * @return
	 */
	public MultiResponse<NavigationCategoryVO> execute(@Valid NavigationCategoryQueryListCommand navigationCategoryQueryListCommand) {
		List<NavigationCategoryDO> navigationCategoryDO = iNavigationCategoryService.list(navigationCategoryQueryListCommand);
		List<NavigationCategoryVO> navigationCategoryVOs = NavigationCategoryAppStructMapping.instance.navigationCategoryDOsToNavigationCategoryVOs(navigationCategoryDO);
		return MultiResponse.of(navigationCategoryVOs);
	}
	/**
	 * 执行 导航分类 分页查询指令
	 * @param navigationCategoryPageQueryCommand
	 * @return
	 */
	public PageResponse<NavigationCategoryVO> execute(@Valid NavigationCategoryPageQueryCommand navigationCategoryPageQueryCommand) {
		Page<NavigationCategoryDO> page = iNavigationCategoryService.listPage(navigationCategoryPageQueryCommand);
		return NavigationCategoryAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 导航分类 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<NavigationCategoryVO> executeDetail(IdCommand detailCommand) {
		NavigationCategoryDO byId = iNavigationCategoryService.getById(detailCommand.getId());
		NavigationCategoryVO navigationCategoryVO = NavigationCategoryAppStructMapping.instance.navigationCategoryDOToNavigationCategoryVO(byId);
		return SingleResponse.of(navigationCategoryVO);
	}
	/**
	 * 执行 导航分类 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<NavigationCategoryVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		NavigationCategoryDO byId = iNavigationCategoryService.getById(detailForUpdateCommand.getId());
		NavigationCategoryVO navigationCategoryVO = NavigationCategoryAppStructMapping.instance.navigationCategoryDOToNavigationCategoryVO(byId);
		return SingleResponse.of(navigationCategoryVO);
	}


	@Autowired
	public void setINavigationCategoryService(INavigationCategoryService iNavigationCategoryService) {
		this.iNavigationCategoryService = iNavigationCategoryService;
	}
}
