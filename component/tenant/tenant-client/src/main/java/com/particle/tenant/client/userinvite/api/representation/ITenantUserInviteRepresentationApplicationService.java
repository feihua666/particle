package com.particle.tenant.client.userinvite.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tenant.client.userinvite.dto.command.representation.TenantUserInvitePageQueryCommand;
import com.particle.tenant.client.userinvite.dto.command.representation.TenantUserInviteQueryListCommand;
import com.particle.tenant.client.userinvite.dto.data.TenantUserInviteVO;

/**
 * <p>
 * 租户用户邀请 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface ITenantUserInviteRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<TenantUserInviteVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<TenantUserInviteVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param tenantUserInviteQueryListCommand
	 * @return
	 */
	MultiResponse<TenantUserInviteVO> queryList(TenantUserInviteQueryListCommand tenantUserInviteQueryListCommand);

	/**
	 * 分页查询
	 * @param tenantUserInvitePageQueryCommand
	 * @return
	 */
	PageResponse<TenantUserInviteVO> pageQuery(TenantUserInvitePageQueryCommand tenantUserInvitePageQueryCommand);

}
