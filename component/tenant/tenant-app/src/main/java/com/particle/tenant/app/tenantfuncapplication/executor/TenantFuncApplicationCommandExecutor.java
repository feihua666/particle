package com.particle.tenant.app.tenantfuncapplication.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.Response;
import com.particle.global.mybatis.plus.dto.RelDTO;
import com.particle.tenant.client.tenantfunc.dto.command.TenantAssignFuncCommand;
import com.particle.tenant.client.tenantfuncapplication.dto.command.TenantAssignFuncApplicationCommand;
import com.particle.tenant.infrastructure.tenantfunc.dos.TenantFuncDO;
import com.particle.tenant.infrastructure.tenantfunc.service.ITenantFuncService;
import com.particle.tenant.infrastructure.tenantfuncapplication.dos.TenantFuncApplicationDO;
import com.particle.tenant.infrastructure.tenantfuncapplication.service.ITenantFuncApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 租户功能应用 指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-04-19 11:14:14
 */
@Component
@Validated
public class TenantFuncApplicationCommandExecutor extends AbstractBaseExecutor {

	private ITenantFuncApplicationService iTenantFuncApplicationService;
	/**
	 * 租户分配功能应用
	 * @param cf
	 * @return
	 */
	public Response tenantAssignFuncApplication(TenantAssignFuncApplicationCommand cf) {
		boolean result = iTenantFuncApplicationService.removeAndAssignRel(cf.getTenantId(),
				cf.getCheckedFuncApplicationIds(),cf.getUncheckedFuncApplicationIds(),
				cf.getIsLazyLoad(), TenantFuncApplicationDO::getTenantId,TenantFuncApplicationDO::getFuncApplicationId,
				(relDto)-> convertToTenantFuncApplicationDO(relDto));
		return Response.buildSuccess();
	}

	private TenantFuncApplicationDO convertToTenantFuncApplicationDO(RelDTO relDTO){
		TenantFuncApplicationDO tenantFuncApplicationDO = new TenantFuncApplicationDO();
		tenantFuncApplicationDO.setTenantId(relDTO.getMainId());
		tenantFuncApplicationDO.setFuncApplicationId(relDTO.getOtherId());
		return tenantFuncApplicationDO;
	}
	
	@Autowired

	public void setiTenantFuncApplicationService(ITenantFuncApplicationService iTenantFuncApplicationService) {
		this.iTenantFuncApplicationService = iTenantFuncApplicationService;
	}
}
