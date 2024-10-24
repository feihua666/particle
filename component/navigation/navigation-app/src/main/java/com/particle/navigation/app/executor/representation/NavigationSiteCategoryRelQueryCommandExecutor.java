package com.particle.navigation.app.executor.representation;

import com.particle.navigation.app.structmapping.NavigationSiteCategoryRelAppStructMapping;
import com.particle.navigation.client.dto.command.representation.NavigationSiteCategoryRelQueryListCommand;
import com.particle.navigation.client.dto.data.NavigationSiteCategoryRelVO;
import com.particle.navigation.infrastructure.dos.NavigationSiteCategoryRelDO;
import com.particle.navigation.infrastructure.service.INavigationSiteCategoryRelService;
import com.particle.navigation.client.dto.command.representation.NavigationSiteCategoryRelPageQueryCommand;
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
 * 导航网站分类关系 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-10-22 15:35:11
 */
@Component
@Validated
public class NavigationSiteCategoryRelQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private INavigationSiteCategoryRelService iNavigationSiteCategoryRelService;

	/**
	 * 执行 导航网站分类关系 列表查询指令
	 * @param navigationSiteCategoryRelQueryListCommand
	 * @return
	 */
	public MultiResponse<NavigationSiteCategoryRelVO> execute(@Valid NavigationSiteCategoryRelQueryListCommand navigationSiteCategoryRelQueryListCommand) {
		List<NavigationSiteCategoryRelDO> navigationSiteCategoryRelDO = iNavigationSiteCategoryRelService.list(navigationSiteCategoryRelQueryListCommand);
		List<NavigationSiteCategoryRelVO> navigationSiteCategoryRelVOs = NavigationSiteCategoryRelAppStructMapping.instance.navigationSiteCategoryRelDOsToNavigationSiteCategoryRelVOs(navigationSiteCategoryRelDO);
		return MultiResponse.of(navigationSiteCategoryRelVOs);
	}
	/**
	 * 执行 导航网站分类关系 分页查询指令
	 * @param navigationSiteCategoryRelPageQueryCommand
	 * @return
	 */
	public PageResponse<NavigationSiteCategoryRelVO> execute(@Valid NavigationSiteCategoryRelPageQueryCommand navigationSiteCategoryRelPageQueryCommand) {
		Page<NavigationSiteCategoryRelDO> page = iNavigationSiteCategoryRelService.listPage(navigationSiteCategoryRelPageQueryCommand);
		return NavigationSiteCategoryRelAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 导航网站分类关系 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<NavigationSiteCategoryRelVO> executeDetail(IdCommand detailCommand) {
		NavigationSiteCategoryRelDO byId = iNavigationSiteCategoryRelService.getById(detailCommand.getId());
		NavigationSiteCategoryRelVO navigationSiteCategoryRelVO = NavigationSiteCategoryRelAppStructMapping.instance.navigationSiteCategoryRelDOToNavigationSiteCategoryRelVO(byId);
		return SingleResponse.of(navigationSiteCategoryRelVO);
	}
	/**
	 * 执行 导航网站分类关系 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<NavigationSiteCategoryRelVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		NavigationSiteCategoryRelDO byId = iNavigationSiteCategoryRelService.getById(detailForUpdateCommand.getId());
		NavigationSiteCategoryRelVO navigationSiteCategoryRelVO = NavigationSiteCategoryRelAppStructMapping.instance.navigationSiteCategoryRelDOToNavigationSiteCategoryRelVO(byId);
		return SingleResponse.of(navigationSiteCategoryRelVO);
	}


	/**
	 * 查询导航网站已分配的导航分类ids
	 * @param navigationSiteIdCommand
	 * @return
	 */
	public MultiResponse<Long> queryNavigationCategoryIdsByNavigationSiteId(@Valid IdCommand navigationSiteIdCommand) {

		NavigationSiteCategoryRelQueryListCommand navigationSiteCategoryRelQueryListCommand = new NavigationSiteCategoryRelQueryListCommand();
		navigationSiteCategoryRelQueryListCommand.setNavigationSiteId(navigationSiteIdCommand.getId());
		MultiResponse<NavigationSiteCategoryRelVO> navigationSiteCategoryRelVOMultiResponse = execute(navigationSiteCategoryRelQueryListCommand);
		if(navigationSiteCategoryRelVOMultiResponse.isNotEmpty()){
			List<Long> collect = navigationSiteCategoryRelVOMultiResponse.getData().stream().map(NavigationSiteCategoryRelVO::getNavigationCategoryId).collect(Collectors.toList());
			return MultiResponse.of(collect);
		}
		return MultiResponse.buildSuccess();
	}
	/**
	 * 查询导航分类已分配的导航网站ids
	 * @param navigationCategoryIdCommand
	 * @return
	 */
	public MultiResponse<Long> queryNavigationSiteIdsByNavigationCategoryId(@Valid IdCommand navigationCategoryIdCommand) {

		NavigationSiteCategoryRelQueryListCommand navigationSiteCategoryRelQueryListCommand = new NavigationSiteCategoryRelQueryListCommand();
		navigationSiteCategoryRelQueryListCommand.setNavigationCategoryId(navigationCategoryIdCommand.getId());
		MultiResponse<NavigationSiteCategoryRelVO> navigationSiteCategoryRelVOMultiResponse = execute(navigationSiteCategoryRelQueryListCommand);
		if(navigationSiteCategoryRelVOMultiResponse.isNotEmpty()){
			List<Long> collect = navigationSiteCategoryRelVOMultiResponse.getData().stream().map(NavigationSiteCategoryRelVO::getNavigationSiteId).collect(Collectors.toList());
			return MultiResponse.of(collect);
		}
		return MultiResponse.buildSuccess();
	}

	@Autowired
	public void setINavigationSiteCategoryRelService(INavigationSiteCategoryRelService iNavigationSiteCategoryRelService) {
		this.iNavigationSiteCategoryRelService = iNavigationSiteCategoryRelService;
	}
}
