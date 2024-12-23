package com.particle.navigation.app.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.navigation.app.structmapping.NavigationSiteTagAppStructMapping;
import com.particle.navigation.client.dto.command.representation.NavigationSiteTagPageQueryCommand;
import com.particle.navigation.client.dto.command.representation.NavigationSiteTagQueryListCommand;
import com.particle.navigation.client.dto.data.NavigationSiteTagVO;
import com.particle.navigation.infrastructure.dos.NavigationSiteTagDO;
import com.particle.navigation.infrastructure.service.INavigationSiteTagService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 导航网站标签 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-11-07 09:38:23
 */
@Component
@Validated
public class NavigationSiteTagQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private INavigationSiteTagService iNavigationSiteTagService;

	/**
	 * 执行 导航网站标签 列表查询指令
	 * @param navigationSiteTagQueryListCommand
	 * @return
	 */
	public MultiResponse<NavigationSiteTagVO> execute(@Valid NavigationSiteTagQueryListCommand navigationSiteTagQueryListCommand) {
		List<NavigationSiteTagDO> navigationSiteTagDO = iNavigationSiteTagService.list(navigationSiteTagQueryListCommand);
		List<NavigationSiteTagVO> navigationSiteTagVOs = NavigationSiteTagAppStructMapping.instance.navigationSiteTagDOsToNavigationSiteTagVOs(navigationSiteTagDO);
		return MultiResponse.of(navigationSiteTagVOs);
	}
	/**
	 * 执行 导航网站标签 分页查询指令
	 * @param navigationSiteTagPageQueryCommand
	 * @return
	 */
	public PageResponse<NavigationSiteTagVO> execute(@Valid NavigationSiteTagPageQueryCommand navigationSiteTagPageQueryCommand) {
		Page<NavigationSiteTagDO> page = iNavigationSiteTagService.listPage(navigationSiteTagPageQueryCommand);
		return NavigationSiteTagAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 导航网站标签 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<NavigationSiteTagVO> executeDetail(IdCommand detailCommand) {
		NavigationSiteTagDO byId = iNavigationSiteTagService.getById(detailCommand.getId());
		NavigationSiteTagVO navigationSiteTagVO = NavigationSiteTagAppStructMapping.instance.navigationSiteTagDOToNavigationSiteTagVO(byId);
		return SingleResponse.of(navigationSiteTagVO);
	}
	/**
	 * 执行 导航网站标签 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<NavigationSiteTagVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		NavigationSiteTagDO byId = iNavigationSiteTagService.getById(detailForUpdateCommand.getId());
		NavigationSiteTagVO navigationSiteTagVO = NavigationSiteTagAppStructMapping.instance.navigationSiteTagDOToNavigationSiteTagVO(byId);
		return SingleResponse.of(navigationSiteTagVO);
	}


	@Autowired
	public void setINavigationSiteTagService(INavigationSiteTagService iNavigationSiteTagService) {
		this.iNavigationSiteTagService = iNavigationSiteTagService;
	}
}
