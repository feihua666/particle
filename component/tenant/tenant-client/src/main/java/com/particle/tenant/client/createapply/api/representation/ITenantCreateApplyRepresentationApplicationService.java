package com.particle.tenant.client.createapply.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tenant.client.createapply.dto.command.representation.TenantCreateApplyPageQueryCommand;
import com.particle.tenant.client.createapply.dto.command.representation.TenantCreateApplyQueryListCommand;
import com.particle.tenant.client.createapply.dto.data.TenantCreateApplyVO;

/**
 * <p>
 * 租户创建申请 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface ITenantCreateApplyRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<TenantCreateApplyVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<TenantCreateApplyVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param tenantCreateApplyQueryListCommand
	 * @return
	 */
	MultiResponse<TenantCreateApplyVO> queryList(TenantCreateApplyQueryListCommand tenantCreateApplyQueryListCommand);

	/**
	 * 分页查询
	 * @param tenantCreateApplyPageQueryCommand
	 * @return
	 */
	PageResponse<TenantCreateApplyVO> pageQuery(TenantCreateApplyPageQueryCommand tenantCreateApplyPageQueryCommand);

}
