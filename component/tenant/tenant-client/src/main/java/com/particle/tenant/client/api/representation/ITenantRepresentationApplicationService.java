package com.particle.tenant.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tenant.client.dto.command.representation.TenantPageQueryCommand;
import com.particle.tenant.client.dto.command.representation.TenantQueryListCommand;
import com.particle.tenant.client.dto.data.TenantVO;

/**
 * <p>
 * 租户 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface ITenantRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<TenantVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<TenantVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param tenantQueryListCommand
	 * @return
	 */
	MultiResponse<TenantVO> queryList(TenantQueryListCommand tenantQueryListCommand);

	/**
	 * 分页查询
	 * @param tenantPageQueryCommand
	 * @return
	 */
	PageResponse<TenantVO> pageQuery(TenantPageQueryCommand tenantPageQueryCommand);

}
