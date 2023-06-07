package com.particle.tenant.app.tenantfunc.executor;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.Response;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.dto.RelDTO;
import com.particle.tenant.client.tenantfunc.dto.command.TenantAssignFuncCommand;
import com.particle.tenant.domain.gateway.TenantFuncFuncGateway;
import com.particle.tenant.domain.gateway.TenantRoleGateway;
import com.particle.tenant.infrastructure.tenantfunc.dos.TenantFuncDO;
import com.particle.tenant.infrastructure.tenantfunc.service.ITenantFuncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
	private TenantFuncFuncGateway tenantFuncFuncGateway;

	private TenantRoleGateway tenantRoleGateway;
	/**
	 * 租户分配功能菜单
	 * @param cf
	 * @return
	 */
	public Response tenantAssignFunc(TenantAssignFuncCommand cf) {
		List<Long> funcIdsByFuncApplicationId = Collections.emptyList();
		if (cf.getFuncApplicationId() != null) {
			funcIdsByFuncApplicationId = tenantFuncFuncGateway.getFuncIdsByFuncApplicationId(cf.getFuncApplicationId());
			Assert.notEmpty(funcIdsByFuncApplicationId,"应用下无功能菜单可用");
		}
		List<Long> finalFuncIdsByFuncApplicationId = funcIdsByFuncApplicationId;
		boolean result = tenantFuncService.removeAndAssignRel(cf.getTenantId(),
				cf.getCheckedFuncIds(),cf.getUncheckedFuncIds(),
				cf.getIsLazyLoad(), TenantFuncDO::getTenantId,TenantFuncDO::getFuncId,
				(relDto)-> convertToTenantFuncDO(relDto,cf.getFuncApplicationId()),(lambdaQueryWrapper)->{
					lambdaQueryWrapper.in(CollectionUtil.isNotEmpty(finalFuncIdsByFuncApplicationId),TenantFuncDO::getFuncId, finalFuncIdsByFuncApplicationId);
		});

		// 分配完成后，可能功能会变少，这就需要将已经不存在的功能从已经分配的角色剔除
		List<TenantFuncDO> list = tenantFuncService.list(Wrappers.<TenantFuncDO>lambdaQuery()
				.eq(TenantFuncDO::getTenantId, cf.getTenantId())
				.eq(cf.getFuncApplicationId() != null,TenantFuncDO::getFuncApplicationId,cf.getFuncApplicationId())
		);
		List<Long> collect = list.stream().map(TenantFuncDO::getFuncId).collect(Collectors.toList());
		tenantRoleGateway.deleteOutOfScopeRoleFuncRelByScopedFuncIds(collect,cf.getTenantId());
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
	public void setTenantFuncGateway(TenantFuncFuncGateway tenantFuncFuncGateway) {
		this.tenantFuncFuncGateway = tenantFuncFuncGateway;
	}
	@Autowired
	public void setTenantRoleGateway(TenantRoleGateway tenantRoleGateway) {
		this.tenantRoleGateway = tenantRoleGateway;
	}
}
