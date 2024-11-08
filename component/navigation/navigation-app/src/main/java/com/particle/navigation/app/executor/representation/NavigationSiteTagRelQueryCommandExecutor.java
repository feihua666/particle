package com.particle.navigation.app.executor.representation;

import com.particle.navigation.app.structmapping.NavigationSiteTagRelAppStructMapping;
import com.particle.navigation.client.dto.command.representation.NavigationSiteTagRelQueryListCommand;
import com.particle.navigation.client.dto.data.NavigationSiteTagRelVO;
import com.particle.navigation.infrastructure.dos.NavigationSiteTagRelDO;
import com.particle.navigation.infrastructure.service.INavigationSiteTagRelService;
import com.particle.navigation.client.dto.command.representation.NavigationSiteTagRelPageQueryCommand;
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
 * 导航网站标签关系 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-11-07 09:39:14
 */
@Component
@Validated
public class NavigationSiteTagRelQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private INavigationSiteTagRelService iNavigationSiteTagRelService;

	/**
	 * 执行 导航网站标签关系 列表查询指令
	 * @param navigationSiteTagRelQueryListCommand
	 * @return
	 */
	public MultiResponse<NavigationSiteTagRelVO> execute(@Valid NavigationSiteTagRelQueryListCommand navigationSiteTagRelQueryListCommand) {
		List<NavigationSiteTagRelDO> navigationSiteTagRelDO = iNavigationSiteTagRelService.list(navigationSiteTagRelQueryListCommand);
		List<NavigationSiteTagRelVO> navigationSiteTagRelVOs = NavigationSiteTagRelAppStructMapping.instance.navigationSiteTagRelDOsToNavigationSiteTagRelVOs(navigationSiteTagRelDO);
		return MultiResponse.of(navigationSiteTagRelVOs);
	}
	/**
	 * 执行 导航网站标签关系 分页查询指令
	 * @param navigationSiteTagRelPageQueryCommand
	 * @return
	 */
	public PageResponse<NavigationSiteTagRelVO> execute(@Valid NavigationSiteTagRelPageQueryCommand navigationSiteTagRelPageQueryCommand) {
		Page<NavigationSiteTagRelDO> page = iNavigationSiteTagRelService.listPage(navigationSiteTagRelPageQueryCommand);
		return NavigationSiteTagRelAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 导航网站标签关系 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<NavigationSiteTagRelVO> executeDetail(IdCommand detailCommand) {
		NavigationSiteTagRelDO byId = iNavigationSiteTagRelService.getById(detailCommand.getId());
		NavigationSiteTagRelVO navigationSiteTagRelVO = NavigationSiteTagRelAppStructMapping.instance.navigationSiteTagRelDOToNavigationSiteTagRelVO(byId);
		return SingleResponse.of(navigationSiteTagRelVO);
	}
	/**
	 * 执行 导航网站标签关系 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<NavigationSiteTagRelVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		NavigationSiteTagRelDO byId = iNavigationSiteTagRelService.getById(detailForUpdateCommand.getId());
		NavigationSiteTagRelVO navigationSiteTagRelVO = NavigationSiteTagRelAppStructMapping.instance.navigationSiteTagRelDOToNavigationSiteTagRelVO(byId);
		return SingleResponse.of(navigationSiteTagRelVO);
	}


	/**
	 * 查询网站已分配的网站标签ids
	 * @param navigationSiteIdCommand
	 * @return
	 */
	public MultiResponse<Long> queryNavigationSiteTagIdsByNavigationSiteId(@Valid IdCommand navigationSiteIdCommand) {

		NavigationSiteTagRelQueryListCommand navigationSiteTagRelQueryListCommand = new NavigationSiteTagRelQueryListCommand();
		navigationSiteTagRelQueryListCommand.setNavigationSiteId(navigationSiteIdCommand.getId());
		MultiResponse<NavigationSiteTagRelVO> navigationSiteTagRelVOMultiResponse = execute(navigationSiteTagRelQueryListCommand);
		if(navigationSiteTagRelVOMultiResponse.isNotEmpty()){
			List<Long> collect = navigationSiteTagRelVOMultiResponse.getData().stream().map(NavigationSiteTagRelVO::getNavigationSiteTagId).collect(Collectors.toList());
			return MultiResponse.of(collect);
		}
		return MultiResponse.buildSuccess();
	}
	/**
	 * 查询网站标签已分配的网站ids
	 * @param navigationSiteTagIdCommand
	 * @return
	 */
	public MultiResponse<Long> queryNavigationSiteIdsByNavigationSiteTagId(@Valid IdCommand navigationSiteTagIdCommand) {

		NavigationSiteTagRelQueryListCommand navigationSiteTagRelQueryListCommand = new NavigationSiteTagRelQueryListCommand();
		navigationSiteTagRelQueryListCommand.setNavigationSiteTagId(navigationSiteTagIdCommand.getId());
		MultiResponse<NavigationSiteTagRelVO> navigationSiteTagRelVOMultiResponse = execute(navigationSiteTagRelQueryListCommand);
		if(navigationSiteTagRelVOMultiResponse.isNotEmpty()){
			List<Long> collect = navigationSiteTagRelVOMultiResponse.getData().stream().map(NavigationSiteTagRelVO::getNavigationSiteId).collect(Collectors.toList());
			return MultiResponse.of(collect);
		}
		return MultiResponse.buildSuccess();
	}

	@Autowired
	public void setINavigationSiteTagRelService(INavigationSiteTagRelService iNavigationSiteTagRelService) {
		this.iNavigationSiteTagRelService = iNavigationSiteTagRelService;
	}
}
