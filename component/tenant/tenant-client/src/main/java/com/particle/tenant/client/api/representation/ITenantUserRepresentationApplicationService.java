package com.particle.tenant.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tenant.client.dto.command.representation.TenantUserPageQueryCommand;
import com.particle.tenant.client.dto.command.representation.TenantUserQueryListCommand;
import com.particle.tenant.client.dto.data.TenantUserVO;

/**
 * <p>
 * 租户用户 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface ITenantUserRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<TenantUserVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<TenantUserVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param tenantUserQueryListCommand
	 * @return
	 */
	MultiResponse<TenantUserVO> queryList(TenantUserQueryListCommand tenantUserQueryListCommand);

	/**
	 * 分页查询
	 * @param tenantUserPageQueryCommand
	 * @return
	 */
	PageResponse<TenantUserVO> pageQuery(TenantUserPageQueryCommand tenantUserPageQueryCommand);

}
