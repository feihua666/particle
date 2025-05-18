package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanySeriousIllegalAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanySeriousIllegalVO;
import com.particle.data.domain.company.DataCompanySeriousIllegal;
import com.particle.data.domain.company.DataCompanySeriousIllegalId;
import com.particle.data.domain.company.gateway.DataCompanySeriousIllegalGateway;
import com.particle.data.infrastructure.company.service.IDataCompanySeriousIllegalService;
import com.particle.data.infrastructure.company.dos.DataCompanySeriousIllegalDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业严重违法 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:45
 */
@Component
@Validated
public class DataCompanySeriousIllegalDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanySeriousIllegalGateway dataCompanySeriousIllegalGateway;
	private IDataCompanySeriousIllegalService iDataCompanySeriousIllegalService;

	/**
	 * 执行 企业严重违法 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanySeriousIllegalVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanySeriousIllegalId dataCompanySeriousIllegalId = DataCompanySeriousIllegalId.of(deleteCommand.getId());
		DataCompanySeriousIllegal byId = dataCompanySeriousIllegalGateway.getById(dataCompanySeriousIllegalId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanySeriousIllegalGateway.delete(dataCompanySeriousIllegalId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanySeriousIllegalAppStructMapping.instance.toDataCompanySeriousIllegalVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanySeriousIllegalGateway
	 */
	@Autowired
	public void setDataCompanySeriousIllegalGateway(DataCompanySeriousIllegalGateway dataCompanySeriousIllegalGateway) {
		this.dataCompanySeriousIllegalGateway = dataCompanySeriousIllegalGateway;
	}
	@Autowired
	public void setIDataCompanySeriousIllegalService(IDataCompanySeriousIllegalService iDataCompanySeriousIllegalService) {
		this.iDataCompanySeriousIllegalService = iDataCompanySeriousIllegalService;
	}
}
