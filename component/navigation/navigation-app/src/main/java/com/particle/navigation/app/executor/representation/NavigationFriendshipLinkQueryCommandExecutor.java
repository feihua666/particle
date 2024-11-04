package com.particle.navigation.app.executor.representation;

import com.particle.navigation.app.structmapping.NavigationFriendshipLinkAppStructMapping;
import com.particle.navigation.client.dto.command.representation.NavigationFriendshipLinkQueryListCommand;
import com.particle.navigation.client.dto.data.NavigationFriendshipLinkVO;
import com.particle.navigation.infrastructure.dos.NavigationFriendshipLinkDO;
import com.particle.navigation.infrastructure.service.INavigationFriendshipLinkService;
import com.particle.navigation.client.dto.command.representation.NavigationFriendshipLinkPageQueryCommand;
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
 * 导航友情链接 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-11-03 11:09:01
 */
@Component
@Validated
public class NavigationFriendshipLinkQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private INavigationFriendshipLinkService iNavigationFriendshipLinkService;

	/**
	 * 执行 导航友情链接 列表查询指令
	 * @param navigationFriendshipLinkQueryListCommand
	 * @return
	 */
	public MultiResponse<NavigationFriendshipLinkVO> execute(@Valid NavigationFriendshipLinkQueryListCommand navigationFriendshipLinkQueryListCommand) {
		List<NavigationFriendshipLinkDO> navigationFriendshipLinkDO = iNavigationFriendshipLinkService.list(navigationFriendshipLinkQueryListCommand);
		List<NavigationFriendshipLinkVO> navigationFriendshipLinkVOs = NavigationFriendshipLinkAppStructMapping.instance.navigationFriendshipLinkDOsToNavigationFriendshipLinkVOs(navigationFriendshipLinkDO);
		return MultiResponse.of(navigationFriendshipLinkVOs);
	}
	/**
	 * 执行 导航友情链接 分页查询指令
	 * @param navigationFriendshipLinkPageQueryCommand
	 * @return
	 */
	public PageResponse<NavigationFriendshipLinkVO> execute(@Valid NavigationFriendshipLinkPageQueryCommand navigationFriendshipLinkPageQueryCommand) {
		Page<NavigationFriendshipLinkDO> page = iNavigationFriendshipLinkService.listPage(navigationFriendshipLinkPageQueryCommand);
		return NavigationFriendshipLinkAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 导航友情链接 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<NavigationFriendshipLinkVO> executeDetail(IdCommand detailCommand) {
		NavigationFriendshipLinkDO byId = iNavigationFriendshipLinkService.getById(detailCommand.getId());
		NavigationFriendshipLinkVO navigationFriendshipLinkVO = NavigationFriendshipLinkAppStructMapping.instance.navigationFriendshipLinkDOToNavigationFriendshipLinkVO(byId);
		return SingleResponse.of(navigationFriendshipLinkVO);
	}
	/**
	 * 执行 导航友情链接 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<NavigationFriendshipLinkVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		NavigationFriendshipLinkDO byId = iNavigationFriendshipLinkService.getById(detailForUpdateCommand.getId());
		NavigationFriendshipLinkVO navigationFriendshipLinkVO = NavigationFriendshipLinkAppStructMapping.instance.navigationFriendshipLinkDOToNavigationFriendshipLinkVO(byId);
		return SingleResponse.of(navigationFriendshipLinkVO);
	}


	@Autowired
	public void setINavigationFriendshipLinkService(INavigationFriendshipLinkService iNavigationFriendshipLinkService) {
		this.iNavigationFriendshipLinkService = iNavigationFriendshipLinkService;
	}
}
