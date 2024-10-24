package com.particle.navigation.app.executor.representation;

import com.particle.navigation.app.structmapping.NavigationSiteAppStructMapping;
import com.particle.navigation.client.dto.command.representation.NavigationSiteQueryListCommand;
import com.particle.navigation.client.dto.data.NavigationSiteVO;
import com.particle.navigation.infrastructure.dos.NavigationSiteDO;
import com.particle.navigation.infrastructure.service.INavigationSiteService;
import com.particle.navigation.client.dto.command.representation.NavigationSitePageQueryCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.global.dto.response.MultiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.PageResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.SingleResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 导航网站 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-10-22 15:34:56
 */
@Component
@Validated
public class NavigationSiteQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private INavigationSiteService iNavigationSiteService;

	/**
	 * 执行 导航网站 列表查询指令
	 * @param navigationSiteQueryListCommand
	 * @return
	 */
	public MultiResponse<NavigationSiteVO> execute(@Valid NavigationSiteQueryListCommand navigationSiteQueryListCommand) {
		List<NavigationSiteDO> navigationSiteDO = iNavigationSiteService.list(navigationSiteQueryListCommand);
		List<NavigationSiteVO> navigationSiteVOs = NavigationSiteAppStructMapping.instance.navigationSiteDOsToNavigationSiteVOs(navigationSiteDO);
		return MultiResponse.of(navigationSiteVOs);
	}
	/**
	 * 执行 导航网站 分页查询指令
	 * @param navigationSitePageQueryCommand
	 * @return
	 */
	public PageResponse<NavigationSiteVO> execute(@Valid NavigationSitePageQueryCommand navigationSitePageQueryCommand) {
		Page<NavigationSiteDO> page = iNavigationSiteService.listPage(navigationSitePageQueryCommand);
		return NavigationSiteAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 导航网站 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<NavigationSiteVO> executeDetail(IdCommand detailCommand) {
		NavigationSiteDO byId = iNavigationSiteService.getById(detailCommand.getId());
		NavigationSiteVO navigationSiteVO = NavigationSiteAppStructMapping.instance.navigationSiteDOToNavigationSiteVO(byId);
		return SingleResponse.of(navigationSiteVO);
	}
	/**
	 * 执行 导航网站 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<NavigationSiteVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		NavigationSiteDO byId = iNavigationSiteService.getById(detailForUpdateCommand.getId());
		NavigationSiteVO navigationSiteVO = NavigationSiteAppStructMapping.instance.navigationSiteDOToNavigationSiteVO(byId);
		return SingleResponse.of(navigationSiteVO);
	}


	@Autowired
	public void setINavigationSiteService(INavigationSiteService iNavigationSiteService) {
		this.iNavigationSiteService = iNavigationSiteService;
	}
}
