package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyIprTrademarkTransferAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkTransferVO;
import com.particle.data.domain.company.DataCompanyIprTrademarkTransfer;
import com.particle.data.domain.company.DataCompanyIprTrademarkTransferId;
import com.particle.data.domain.company.gateway.DataCompanyIprTrademarkTransferGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkTransferService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkTransferDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权商标转让信息 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:04
 */
@Component
@Validated
public class DataCompanyIprTrademarkTransferDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprTrademarkTransferGateway dataCompanyIprTrademarkTransferGateway;
	private IDataCompanyIprTrademarkTransferService iDataCompanyIprTrademarkTransferService;

	/**
	 * 执行 企业知识产权商标转让信息 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkTransferVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyIprTrademarkTransferId dataCompanyIprTrademarkTransferId = DataCompanyIprTrademarkTransferId.of(deleteCommand.getId());
		DataCompanyIprTrademarkTransfer byId = dataCompanyIprTrademarkTransferGateway.getById(dataCompanyIprTrademarkTransferId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyIprTrademarkTransferGateway.delete(dataCompanyIprTrademarkTransferId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyIprTrademarkTransferAppStructMapping.instance.toDataCompanyIprTrademarkTransferVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyIprTrademarkTransferGateway
	 */
	@Autowired
	public void setDataCompanyIprTrademarkTransferGateway(DataCompanyIprTrademarkTransferGateway dataCompanyIprTrademarkTransferGateway) {
		this.dataCompanyIprTrademarkTransferGateway = dataCompanyIprTrademarkTransferGateway;
	}
	@Autowired
	public void setIDataCompanyIprTrademarkTransferService(IDataCompanyIprTrademarkTransferService iDataCompanyIprTrademarkTransferService) {
		this.iDataCompanyIprTrademarkTransferService = iDataCompanyIprTrademarkTransferService;
	}
}
