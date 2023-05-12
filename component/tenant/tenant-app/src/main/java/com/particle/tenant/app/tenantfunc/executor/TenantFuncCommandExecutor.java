package com.particle.tenant.app.tenantfunc.executor;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.Response;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.dto.RelDTO;
import com.particle.tenant.client.tenantfunc.dto.command.TenantAssignFuncCommand;
import com.particle.tenant.domain.gateway.TenantFuncGateway;
import com.particle.tenant.infrastructure.tenantfunc.dos.TenantFuncDO;
import com.particle.tenant.infrastructure.tenantfunc.service.ITenantFuncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 租户功能菜单 指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-04-19 11:14:14
 */
@Component
@Validated
public class TenantFuncCommandExecutor extends AbstractBaseExecutor {

	private ITenantFuncService tenantFuncService;
	private TenantFuncGateway tenantFuncGateway;

	/**
	 * 租户分配功能菜单
	 * @param cf
	 * @return
	 */
	public Response tenantAssignFunc(TenantAssignFuncCommand cf) {
		List<Long> funcIdsByFuncApplicationId = Collections.emptyList();
		if (cf.getFuncApplicationId() != null) {
			funcIdsByFuncApplicationId = tenantFuncGateway.getFuncIdsByFuncApplicationId(cf.getFuncApplicationId());
			Assert.notEmpty(funcIdsByFuncApplicationId,"应用下无功能菜单可用");
		}
		List<Long> finalFuncIdsByFuncApplicationId = funcIdsByFuncApplicationId;
		boolean result = tenantFuncService.removeAndAssignRel(cf.getTenantId(),
				cf.getCheckedFuncIds(),cf.getUncheckedFuncIds(),
				cf.getIsLazyLoad(), TenantFuncDO::getTenantId,TenantFuncDO::getFuncId,
				(relDto)-> convertToTenantFuncDO(relDto,cf.getFuncApplicationId()),(lambdaQueryWrapper)->{
					lambdaQueryWrapper.in(CollectionUtil.isNotEmpty(finalFuncIdsByFuncApplicationId),TenantFuncDO::getFuncId, finalFuncIdsByFuncApplicationId);
		});
		return Response.buildSuccess();
	}

	private TenantFuncDO convertToTenantFuncDO(RelDTO relDTO,Long funcApplicationId){
		TenantFuncDO tenantFuncDO = new TenantFuncDO();
		tenantFuncDO.setTenantId(relDTO.getMainId());
		tenantFuncDO.setFuncId(relDTO.getOtherId());
		tenantFuncDO.setFuncApplicationId(funcApplicationId);
		return tenantFuncDO;
	}
	@Autowired
	public void setTenantFuncService(ITenantFuncService tenantFuncService) {
		this.tenantFuncService = tenantFuncService;
	}

	@Autowired
	public void setTenantFuncGateway(TenantFuncGateway tenantFuncGateway) {
		this.tenantFuncGateway = tenantFuncGateway;
	}
}
