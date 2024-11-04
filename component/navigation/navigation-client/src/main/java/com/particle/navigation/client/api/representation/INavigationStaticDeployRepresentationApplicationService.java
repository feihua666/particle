package com.particle.navigation.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.navigation.client.dto.command.representation.NavigationStaticDeployPageQueryCommand;
import com.particle.navigation.client.dto.command.representation.NavigationStaticDeployQueryListCommand;
import com.particle.navigation.client.dto.data.NavigationStaticDeployVO;

/**
 * <p>
 * 导航网站静态部署 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface INavigationStaticDeployRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<NavigationStaticDeployVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<NavigationStaticDeployVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param navigationStaticDeployQueryListCommand
	 * @return
	 */
	MultiResponse<NavigationStaticDeployVO> queryList(NavigationStaticDeployQueryListCommand navigationStaticDeployQueryListCommand);

	/**
	 * 分页查询
	 * @param navigationStaticDeployPageQueryCommand
	 * @return
	 */
	PageResponse<NavigationStaticDeployVO> pageQuery(NavigationStaticDeployPageQueryCommand navigationStaticDeployPageQueryCommand);

}
