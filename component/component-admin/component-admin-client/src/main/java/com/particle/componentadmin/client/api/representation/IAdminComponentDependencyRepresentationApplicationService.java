package com.particle.componentadmin.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.componentadmin.client.dto.command.representation.AdminComponentDependencyPageQueryCommand;
import com.particle.componentadmin.client.dto.command.representation.AdminComponentDependencyQueryListCommand;
import com.particle.componentadmin.client.dto.data.AdminComponentDependencyVO;

/**
 * <p>
 * 组件依赖关系 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IAdminComponentDependencyRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<AdminComponentDependencyVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<AdminComponentDependencyVO> queryDetail(CommonIdCommand detailCommand);

	/**
	 * 列表查询
	 * @param adminComponentDependencyQueryListCommand
	 * @return
	 */
	MultiResponse<AdminComponentDependencyVO> queryList(AdminComponentDependencyQueryListCommand adminComponentDependencyQueryListCommand);

	/**
	 * 分页查询
	 * @param adminComponentDependencyPageQueryCommand
	 * @return
	 */
	PageResponse<AdminComponentDependencyVO> pageQuery(AdminComponentDependencyPageQueryCommand adminComponentDependencyPageQueryCommand);

	/**
	 * 查询源组件已分配的依赖组件id
	 * @param componentCommonIdCommand
	 * @return
	 */
	public MultiResponse<Long> queryDependComponentIdsByComponentId(CommonIdCommand componentCommonIdCommand);

	/**
	 * 查询依赖组件已分配的源组件id
	 * @param dependComponentCommonIdCommand
	 * @return
	 */
	public MultiResponse<Long> queryComponentIdsByDependComponentId(CommonIdCommand dependComponentCommonIdCommand);
}
