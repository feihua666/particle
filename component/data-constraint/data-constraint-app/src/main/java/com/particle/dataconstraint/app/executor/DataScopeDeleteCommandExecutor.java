package com.particle.dataconstraint.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.dataconstraint.app.structmapping.DataScopeAppStructMapping;
import com.particle.dataconstraint.client.dto.data.DataScopeVO;
import com.particle.dataconstraint.domain.DataScope;
import com.particle.dataconstraint.domain.DataScopeId;
import com.particle.dataconstraint.domain.gateway.DataScopeGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 数据范围 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:38
 */
@Component
@Validated
public class DataScopeDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataScopeGateway dataScopeGateway;

	/**
	 * 执行 数据范围 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataScopeVO> execute(@Valid IdCommand deleteCommand) {
		DataScopeId dataScopeId = DataScopeId.of(deleteCommand.getId());
		DataScope byId = dataScopeGateway.getById(dataScopeId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataScopeGateway.delete(dataScopeId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataScopeAppStructMapping.instance.toDataScopeVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param dataScopeGateway
	 */
	@Autowired
	public void setDataScopeGateway(DataScopeGateway dataScopeGateway) {
		this.dataScopeGateway = dataScopeGateway;
	}
}
