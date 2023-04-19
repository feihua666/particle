package com.particle.tenant.client.tenantfunc.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tenant.client.tenantfunc.dto.command.representation.TenantFuncPageQueryCommand;
import com.particle.tenant.client.tenantfunc.dto.command.representation.TenantFuncQueryFuncIdsByTenantIdCommand;
import com.particle.tenant.client.tenantfunc.dto.command.representation.TenantFuncQueryListCommand;
import com.particle.tenant.client.tenantfunc.dto.data.TenantFuncVO;

/**
 * <p>
 * 租户功能菜单 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface ITenantFuncRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<TenantFuncVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<TenantFuncVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param tenantFuncQueryListCommand
	 * @return
	 */
	MultiResponse<TenantFuncVO> queryList(TenantFuncQueryListCommand tenantFuncQueryListCommand);

	/**
	 * 分页查询
	 * @param tenantFuncPageQueryCommand
	 * @return
	 */
	PageResponse<TenantFuncVO> pageQuery(TenantFuncPageQueryCommand tenantFuncPageQueryCommand);

	/**
	 * 根据租户id查询已分配的功能菜单id
	 * @param funcIdCommand
	 * @return
	 */
	public MultiResponse<Long> queryFuncIdsByTenantId(TenantFuncQueryFuncIdsByTenantIdCommand funcIdCommand);
}
