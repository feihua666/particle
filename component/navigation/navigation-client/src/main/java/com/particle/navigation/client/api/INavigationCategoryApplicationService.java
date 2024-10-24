package com.particle.navigation.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.navigation.client.dto.command.NavigationCategoryCreateCommand;
import com.particle.navigation.client.dto.command.NavigationCategoryUpdateCommand;
import com.particle.navigation.client.dto.data.NavigationCategoryVO;
/**
 * <p>
 * 导航分类 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:34:42
 */
public interface INavigationCategoryApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param navigationCategoryCreateCommand
	 * @return
	 */
	SingleResponse<NavigationCategoryVO> create(NavigationCategoryCreateCommand navigationCategoryCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<NavigationCategoryVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param navigationCategoryUpdateCommand
	 * @return
	 */
	SingleResponse<NavigationCategoryVO> update(NavigationCategoryUpdateCommand navigationCategoryUpdateCommand);
}
