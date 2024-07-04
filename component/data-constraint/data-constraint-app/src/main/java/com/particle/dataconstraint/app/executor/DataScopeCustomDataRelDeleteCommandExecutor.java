package com.particle.dataconstraint.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dataconstraint.app.structmapping.DataScopeCustomDataRelAppStructMapping;
import com.particle.dataconstraint.client.dto.data.DataScopeCustomDataRelVO;
import com.particle.dataconstraint.domain.DataScopeCustomDataRel;
import com.particle.dataconstraint.domain.DataScopeCustomDataRelId;
import com.particle.dataconstraint.domain.gateway.DataScopeCustomDataRelGateway;
import com.particle.dataconstraint.infrastructure.dos.DataScopeCustomDataRelDO;
import com.particle.dataconstraint.infrastructure.service.IDataScopeCustomDataRelService;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
/**
 * <p>
 * 数据范围自定义数据关系 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:55
 */
@Component
@Validated
public class DataScopeCustomDataRelDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataScopeCustomDataRelGateway dataScopeCustomDataRelGateway;
	private IDataScopeCustomDataRelService iDataScopeCustomDataRelService;

	/**
	 * 执行 数据范围自定义数据关系 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataScopeCustomDataRelVO> execute(@Valid IdCommand deleteCommand) {
		DataScopeCustomDataRelId dataScopeCustomDataRelId = DataScopeCustomDataRelId.of(deleteCommand.getId());
		DataScopeCustomDataRel byId = dataScopeCustomDataRelGateway.getById(dataScopeCustomDataRelId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataScopeCustomDataRelGateway.delete(dataScopeCustomDataRelId);
		if (delete) {
			return SingleResponse.of(DataScopeCustomDataRelAppStructMapping.instance.toDataScopeCustomDataRelVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}
	/**
	 * 根据 dataScopeId 删除
	 * @param dataScopeIdCommand
	 * @return
	 */
	public Response deleteByDataScopeId(@Valid IdCommand dataScopeIdCommand) {
		boolean result = iDataScopeCustomDataRelService.deleteByColumn(dataScopeIdCommand.getId(), DataScopeCustomDataRelDO::getDataScopeId);
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
