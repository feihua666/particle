package com.particle.tenant.client.tenantfuncapplication.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tenant.client.tenantfuncapplication.dto.command.representation.TenantFuncApplicationPageQueryCommand;
import com.particle.tenant.client.tenantfuncapplication.dto.command.representation.TenantFuncApplicationQueryListCommand;
import com.particle.tenant.client.tenantfuncapplication.dto.data.TenantFuncApplicationVO;

/**
 * <p>
 * 租户功能应用 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface ITenantFuncApplicationRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<TenantFuncApplicationVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<TenantFuncApplicationVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param tenantFuncApplicationQueryListCommand
	 * @return
	 */
	MultiResponse<TenantFuncApplicationVO> queryList(TenantFuncApplicationQueryListCommand tenantFuncApplicationQueryListCommand);

	/**
	 * 分页查询
	 * @param tenantFuncApplicationPageQueryCommand
	 * @return
	 */
	PageResponse<TenantFuncApplicationVO> pageQuery(TenantFuncApplicationPageQueryCommand tenantFuncApplicationPageQueryCommand);


	/**
	 * 根据租户ID查询已分配的功能应用id
	 * @param tenantIdCommand
	 * @return
	 */
	public MultiResponse<Long> queryFuncApplicationIdsByTenantId(IdCommand tenantIdCommand);
}
