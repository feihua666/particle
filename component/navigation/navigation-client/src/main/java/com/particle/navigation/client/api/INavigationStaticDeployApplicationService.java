package com.particle.navigation.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.navigation.client.dto.command.NavigationStaticDeployCreateCommand;
import com.particle.navigation.client.dto.command.NavigationStaticDeployUpdateCommand;
import com.particle.navigation.client.dto.data.NavigationStaticDeployVO;

import java.time.LocalDateTime;

/**
 * <p>
 * 导航网站静态部署 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-11-01 10:02:52
 */
public interface INavigationStaticDeployApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param navigationStaticDeployCreateCommand
	 * @return
	 */
	SingleResponse<NavigationStaticDeployVO> create(NavigationStaticDeployCreateCommand navigationStaticDeployCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<NavigationStaticDeployVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param navigationStaticDeployUpdateCommand
	 * @return
	 */
	SingleResponse<NavigationStaticDeployVO> update(NavigationStaticDeployUpdateCommand navigationStaticDeployUpdateCommand);

	/**
	 * 更新部署时间
	 * @param deleteCommand
	 * @param deployAt
	 * @return
	 */
	Response updateLastDeployAt(IdCommand deleteCommand,LocalDateTime deployAt);
}
