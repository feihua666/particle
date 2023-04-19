package com.particle.tenant.client.userinvite.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tenant.client.userinvite.dto.command.representation.TenantUserInviteUserRecordPageQueryCommand;
import com.particle.tenant.client.userinvite.dto.command.representation.TenantUserInviteUserRecordQueryListCommand;
import com.particle.tenant.client.userinvite.dto.data.TenantUserInviteUserRecordVO;

/**
 * <p>
 * 租户用户邀请记录 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface ITenantUserInviteUserRecordRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<TenantUserInviteUserRecordVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<TenantUserInviteUserRecordVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param tenantUserInviteUserRecordQueryListCommand
	 * @return
	 */
	MultiResponse<TenantUserInviteUserRecordVO> queryList(TenantUserInviteUserRecordQueryListCommand tenantUserInviteUserRecordQueryListCommand);

	/**
	 * 分页查询
	 * @param tenantUserInviteUserRecordPageQueryCommand
	 * @return
	 */
	PageResponse<TenantUserInviteUserRecordVO> pageQuery(TenantUserInviteUserRecordPageQueryCommand tenantUserInviteUserRecordPageQueryCommand);

}
