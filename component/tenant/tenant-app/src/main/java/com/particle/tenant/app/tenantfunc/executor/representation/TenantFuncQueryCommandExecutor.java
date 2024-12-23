package com.particle.tenant.app.tenantfunc.executor.representation;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tenant.app.tenantfunc.structmapping.TenantFuncAppStructMapping;
import com.particle.tenant.client.tenantfunc.dto.command.representation.TenantFuncPageQueryCommand;
import com.particle.tenant.client.tenantfunc.dto.command.representation.TenantFuncQueryFuncIdsByTenantIdCommand;
import com.particle.tenant.client.tenantfunc.dto.command.representation.TenantFuncQueryListCommand;
import com.particle.tenant.client.tenantfunc.dto.data.TenantFuncVO;
import com.particle.tenant.domain.gateway.TenantFuncFuncGateway;
import com.particle.tenant.infrastructure.tenantfunc.dos.TenantFuncDO;
import com.particle.tenant.infrastructure.tenantfunc.service.ITenantFuncService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 租户功能菜单 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-04-17 18:11:17
 */
@Component
@Validated
public class TenantFuncQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ITenantFuncService iTenantFuncService;
	private TenantFuncFuncGateway tenantFuncFuncGateway;

	/**
	 * 执行 租户功能菜单 列表查询指令
	 * @param tenantFuncQueryListCommand
	 * @return
	 */
	public MultiResponse<TenantFuncVO> execute(@Valid TenantFuncQueryListCommand tenantFuncQueryListCommand) {
		List<TenantFuncDO> tenantFuncDO = iTenantFuncService.list(tenantFuncQueryListCommand);
		List<TenantFuncVO> tenantFuncVOs = TenantFuncAppStructMapping.instance.tenantFuncDOsToTenantFuncVOs(tenantFuncDO);
		return MultiResponse.of(tenantFuncVOs);
	}
	/**
	 * 执行 租户功能菜单 分页查询指令
	 * @param tenantFuncPageQueryCommand
	 * @return
	 */
	public PageResponse<TenantFuncVO> execute(@Valid TenantFuncPageQueryCommand tenantFuncPageQueryCommand) {
		Page<TenantFuncDO> page = iTenantFuncService.listPage(tenantFuncPageQueryCommand);
		return TenantFuncAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 租户功能菜单 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<TenantFuncVO> executeDetail(@Valid IdCommand detailCommand) {
		TenantFuncDO byId = iTenantFuncService.getById(detailCommand.getId());
		TenantFuncVO tenantFuncVO = TenantFuncAppStructMapping.instance.tenantFuncDOToTenantFuncVO(byId);
		return SingleResponse.of(tenantFuncVO);
	}
	/**
	 * 执行 租户功能菜单 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<TenantFuncVO> executeDetailForUpdate(@Valid IdCommand detailForUpdateCommand) {
		TenantFuncDO byId = iTenantFuncService.getById(detailForUpdateCommand.getId());
		TenantFuncVO tenantFuncVO = TenantFuncAppStructMapping.instance.tenantFuncDOToTenantFuncVO(byId);
		return SingleResponse.of(tenantFuncVO);
	}

	/**
	 * 根据租户id查询
	 * @param tenantIdCommand
	 * @return
	 */
	public MultiResponse<Long> queryFuncIdsByTenantId(@Valid TenantFuncQueryFuncIdsByTenantIdCommand tenantIdCommand) {
		List<Long> funcIdsByFuncApplicationId = Collections.emptyList();
		if (tenantIdCommand.getFuncApplicationId() != null) {
			funcIdsByFuncApplicationId = tenantFuncFuncGateway.getFuncIdsByFuncApplicationId(tenantIdCommand.getFuncApplicationId());
			if (CollectionUtil.isEmpty(funcIdsByFuncApplicationId)) {
				// 如果应用下没有数据，直接返回空
				return MultiResponse.buildSuccess();
			}
		}
		List<TenantFuncDO> list = iTenantFuncService.listByColumn(tenantIdCommand.getId(),TenantFuncDO::getTenantId);
		List<Long> finalFuncIdsByFuncApplicationId = funcIdsByFuncApplicationId;
		List<Long> collect = list.stream().map(TenantFuncDO::getFuncId).filter(funcId  -> finalFuncIdsByFuncApplicationId.contains(funcId)).collect(Collectors.toList());
		return MultiResponse.of(collect);
	}

	@Autowired
	public void setITenantFuncService(ITenantFuncService iTenantFuncService) {
		this.iTenantFuncService = iTenantFuncService;
	}


	@Autowired
	public void setTenantFuncGateway(TenantFuncFuncGateway tenantFuncFuncGateway) {
		this.tenantFuncFuncGateway = tenantFuncFuncGateway;
	}
}
