package com.particle.navigation.app.executor.representation;

import com.particle.navigation.app.structmapping.NavigationStaticDeployAppStructMapping;
import com.particle.navigation.client.dto.command.representation.NavigationStaticDeployQueryListCommand;
import com.particle.navigation.client.dto.data.NavigationStaticDeployVO;
import com.particle.navigation.infrastructure.dos.NavigationStaticDeployDO;
import com.particle.navigation.infrastructure.service.INavigationStaticDeployService;
import com.particle.navigation.client.dto.command.representation.NavigationStaticDeployPageQueryCommand;
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
 * 导航网站静态部署 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-11-01 10:02:52
 */
@Component
@Validated
public class NavigationStaticDeployQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private INavigationStaticDeployService iNavigationStaticDeployService;

	/**
	 * 执行 导航网站静态部署 列表查询指令
	 * @param navigationStaticDeployQueryListCommand
	 * @return
	 */
	public MultiResponse<NavigationStaticDeployVO> execute(@Valid NavigationStaticDeployQueryListCommand navigationStaticDeployQueryListCommand) {
		List<NavigationStaticDeployDO> navigationStaticDeployDO = iNavigationStaticDeployService.list(navigationStaticDeployQueryListCommand);
		List<NavigationStaticDeployVO> navigationStaticDeployVOs = NavigationStaticDeployAppStructMapping.instance.navigationStaticDeployDOsToNavigationStaticDeployVOs(navigationStaticDeployDO);
		return MultiResponse.of(navigationStaticDeployVOs);
	}
	/**
	 * 执行 导航网站静态部署 分页查询指令
	 * @param navigationStaticDeployPageQueryCommand
	 * @return
	 */
	public PageResponse<NavigationStaticDeployVO> execute(@Valid NavigationStaticDeployPageQueryCommand navigationStaticDeployPageQueryCommand) {
		Page<NavigationStaticDeployDO> page = iNavigationStaticDeployService.listPage(navigationStaticDeployPageQueryCommand);
		return NavigationStaticDeployAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 导航网站静态部署 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<NavigationStaticDeployVO> executeDetail(IdCommand detailCommand) {
		NavigationStaticDeployDO byId = iNavigationStaticDeployService.getById(detailCommand.getId());
		NavigationStaticDeployVO navigationStaticDeployVO = NavigationStaticDeployAppStructMapping.instance.navigationStaticDeployDOToNavigationStaticDeployVO(byId);
		return SingleResponse.of(navigationStaticDeployVO);
	}
	/**
	 * 执行 导航网站静态部署 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<NavigationStaticDeployVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		NavigationStaticDeployDO byId = iNavigationStaticDeployService.getById(detailForUpdateCommand.getId());
		NavigationStaticDeployVO navigationStaticDeployVO = NavigationStaticDeployAppStructMapping.instance.navigationStaticDeployDOToNavigationStaticDeployVO(byId);
		return SingleResponse.of(navigationStaticDeployVO);
	}


	@Autowired
	public void setINavigationStaticDeployService(INavigationStaticDeployService iNavigationStaticDeployService) {
		this.iNavigationStaticDeployService = iNavigationStaticDeployService;
	}
}
