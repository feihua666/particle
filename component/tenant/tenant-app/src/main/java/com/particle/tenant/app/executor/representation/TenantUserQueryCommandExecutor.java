package com.particle.tenant.app.executor.representation;

import com.particle.tenant.app.structmapping.TenantUserAppStructMapping;
import com.particle.tenant.client.dto.command.representation.TenantUserQueryListCommand;
import com.particle.tenant.client.dto.data.TenantUserVO;
import com.particle.tenant.infrastructure.dos.TenantUserDO;
import com.particle.tenant.infrastructure.service.ITenantUserService;
import com.particle.tenant.client.dto.command.representation.TenantUserPageQueryCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.global.dto.response.MultiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.PageResponse;
import javax.validation.Valid;
import java.util.List;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 租户用户 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-04-12 15:36:44
 */
@Component
@Validated
public class TenantUserQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ITenantUserService iTenantUserService;

	/**
	 * 执行 租户用户 列表查询指令
	 * @param tenantUserQueryListCommand
	 * @return
	 */
	public MultiResponse<TenantUserVO> execute(@Valid TenantUserQueryListCommand tenantUserQueryListCommand) {
		List<TenantUserDO> tenantUserDO = iTenantUserService.list(tenantUserQueryListCommand);
		List<TenantUserVO> tenantUserVOs = TenantUserAppStructMapping.instance.tenantUserDOsToTenantUserVOs(tenantUserDO);
		return MultiResponse.of(tenantUserVOs);
	}
	/**
	 * 执行 租户用户 分页查询指令
	 * @param tenantUserPageQueryCommand
	 * @return
	 */
	public PageResponse<TenantUserVO> execute(@Valid TenantUserPageQueryCommand tenantUserPageQueryCommand) {
		Page<TenantUserDO> page = iTenantUserService.listPage(tenantUserPageQueryCommand);
		return TenantUserAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 租户用户 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<TenantUserVO> executeDetail(IdCommand detailCommand) {
		TenantUserDO byId = iTenantUserService.getById(detailCommand.getId());
		TenantUserVO tenantUserVO = TenantUserAppStructMapping.instance.tenantUserDOToTenantUserVO(byId);
		return SingleResponse.of(tenantUserVO);
	}
	/**
	 * 执行 租户用户 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<TenantUserVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		TenantUserDO byId = iTenantUserService.getById(detailForUpdateCommand.getId());
		TenantUserVO tenantUserVO = TenantUserAppStructMapping.instance.tenantUserDOToTenantUserVO(byId);
		return SingleResponse.of(tenantUserVO);
	}

	@Autowired
	public void setITenantUserService(ITenantUserService iTenantUserService) {
		this.iTenantUserService = iTenantUserService;
	}
}
