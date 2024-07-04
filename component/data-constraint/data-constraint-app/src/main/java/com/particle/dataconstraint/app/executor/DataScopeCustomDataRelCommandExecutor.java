package com.particle.dataconstraint.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.dataconstraint.client.dto.command.DataScopeAssignCustomDataCommand;
import com.particle.dataconstraint.domain.gateway.DataScopeCustomDataRelGateway;
import com.particle.dataconstraint.infrastructure.dos.DataScopeCustomDataRelDO;
import com.particle.dataconstraint.infrastructure.service.IDataScopeCustomDataRelService;
import com.particle.global.dto.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 数据范围自定义数据关系 指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-06-28 16:14:40
 */
@Component
@Validated
public class DataScopeCustomDataRelCommandExecutor extends AbstractBaseExecutor {

	private DataScopeCustomDataRelGateway dataScopeCustomDataRelGateway;

	private IDataScopeCustomDataRelService iDataScopeCustomDataRelService;




	/**
	 * 数据范围分配自定义数据
	 * @param dataScopeAssignCustomDataCommand
	 * @return
	 */
	public Response dataScopeAssignCustomData(@Valid DataScopeAssignCustomDataCommand dataScopeAssignCustomDataCommand) {
		boolean result = iDataScopeCustomDataRelService.removeAndAssignRel(dataScopeAssignCustomDataCommand.getDataScopeId(),
				dataScopeAssignCustomDataCommand.getCheckedDataIds(),dataScopeAssignCustomDataCommand.getUncheckedDataIds(),
				dataScopeAssignCustomDataCommand.getIsLazyLoad(), DataScopeCustomDataRelDO::getDataScopeId,DataScopeCustomDataRelDO::getDataId,
				(relDto)->new DataScopeCustomDataRelDO().setDataScopeId(relDto.getMainId()).setDataId(relDto.getOtherId()));
		return Response.buildSuccess();
	}
	
	/**
	 * 注入使用set方法
	 * @param dataScopeCustomDataRelGateway
	 */
	@Autowired
	public void setDataScopeCustomDataRelGateway(DataScopeCustomDataRelGateway dataScopeCustomDataRelGateway) {
		this.dataScopeCustomDataRelGateway = dataScopeCustomDataRelGateway;
	}

	@Autowired
	public void setiDataScopeCustomDataRelService(IDataScopeCustomDataRelService iDataScopeCustomDataRelService) {
		this.iDataScopeCustomDataRelService = iDataScopeCustomDataRelService;
	}
}
