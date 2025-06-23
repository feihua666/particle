package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyIprTrademarkAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkVO;
import com.particle.data.domain.company.DataCompanyIprTrademark;
import com.particle.data.domain.company.DataCompanyIprTrademarkId;
import com.particle.data.domain.company.gateway.DataCompanyIprTrademarkGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权商标 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:14:45
 */
@Component
@Validated
public class DataCompanyIprTrademarkDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprTrademarkGateway dataCompanyIprTrademarkGateway;
	private IDataCompanyIprTrademarkService iDataCompanyIprTrademarkService;

	/**
	 * 执行 企业知识产权商标 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyIprTrademarkId dataCompanyIprTrademarkId = DataCompanyIprTrademarkId.of(deleteCommand.getId());
		DataCompanyIprTrademark byId = dataCompanyIprTrademarkGateway.getById(dataCompanyIprTrademarkId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyIprTrademarkGateway.delete(dataCompanyIprTrademarkId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyIprTrademarkAppStructMapping.instance.toDataCompanyIprTrademarkVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyIprTrademarkGateway
	 */
	@Autowired
	public void setDataCompanyIprTrademarkGateway(DataCompanyIprTrademarkGateway dataCompanyIprTrademarkGateway) {
		this.dataCompanyIprTrademarkGateway = dataCompanyIprTrademarkGateway;
	}
	@Autowired
	public void setIDataCompanyIprTrademarkService(IDataCompanyIprTrademarkService iDataCompanyIprTrademarkService) {
		this.iDataCompanyIprTrademarkService = iDataCompanyIprTrademarkService;
	}
}
