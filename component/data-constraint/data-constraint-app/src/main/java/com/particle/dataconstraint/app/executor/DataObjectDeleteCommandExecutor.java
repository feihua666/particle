package com.particle.dataconstraint.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dataconstraint.app.structmapping.DataObjectAppStructMapping;
import com.particle.dataconstraint.client.dto.data.DataObjectVO;
import com.particle.dataconstraint.domain.DataObject;
import com.particle.dataconstraint.domain.DataObjectId;
import com.particle.dataconstraint.domain.gateway.DataObjectGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 数据对象 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:18
 */
@Component
@Validated
public class DataObjectDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataObjectGateway dataObjectGateway;

	/**
	 * 执行 数据对象 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataObjectVO> execute(@Valid IdCommand deleteCommand) {
		DataObjectId dataObjectId = DataObjectId.of(deleteCommand.getId());
		DataObject byId = dataObjectGateway.getById(dataObjectId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataObjectGateway.delete(dataObjectId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataObjectAppStructMapping.instance.toDataObjectVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param dataObjectGateway
	 */
	@Autowired
	public void setDataObjectGateway(DataObjectGateway dataObjectGateway) {
		this.dataObjectGateway = dataObjectGateway;
	}
}
