package com.particle.tenant.app.tenantfunc.executor;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.global.mybatis.plus.dto.RelDTO;
import com.particle.tenant.app.tenantfunc.structmapping.TenantFuncAppStructMapping;
import com.particle.tenant.client.tenantfunc.dto.command.TenantAssignFuncCommand;
import com.particle.tenant.client.tenantfunc.dto.command.TenantFuncUpdateCommand;
import com.particle.tenant.client.tenantfunc.dto.data.TenantFuncVO;
import com.particle.tenant.domain.gateway.TenantFuncGateway;
import com.particle.tenant.domain.tenantfunc.TenantFunc;
import com.particle.tenant.domain.tenantfunc.TenantFuncId;
import com.particle.tenant.infrastructure.tenantfunc.dos.TenantFuncDO;
import com.particle.tenant.infrastructure.tenantfunc.service.ITenantFuncService;
import com.particle.tenant.infrastructure.tenantfuncapplication.dos.TenantFuncApplicationDO;
import com.particle.tenant.infrastructure.tenantfuncapplication.service.ITenantFuncApplicationService;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
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

	private ITenantFuncService iTenantFuncService;
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
		boolean result = iTenantFuncService.removeAndAssignRel(cf.getTenantId(),
				cf.getCheckedFuncIds(),cf.getUncheckedFuncIds(),
				cf.getIsLazyLoad(), TenantFuncDO::getTenantId,TenantFuncDO::getFuncId,
				(relDto)-> convertToTenantFuncDO(relDto),(lambdaQueryWrapper)->{
					lambdaQueryWrapper.in(CollectionUtil.isNotEmpty(finalFuncIdsByFuncApplicationId),TenantFuncDO::getFuncId, finalFuncIdsByFuncApplicationId);
		});
		return Response.buildSuccess();
	}

	private TenantFuncDO convertToTenantFuncDO(RelDTO relDTO){
		TenantFuncDO tenantFuncDO = new TenantFuncDO();
		tenantFuncDO.setTenantId(relDTO.getMainId());
		tenantFuncDO.setFuncId(relDTO.getOtherId());
		return tenantFuncDO;
	}

	@Autowired
	public void setiTenantFuncService(ITenantFuncService iTenantFuncService) {
		this.iTenantFuncService = iTenantFuncService;
	}

	public void setTenantFuncGateway(TenantFuncGateway tenantFuncGateway) {
		this.tenantFuncGateway = tenantFuncGateway;
	}
}
