package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyIprPatentTransferAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentTransferVO;
import com.particle.data.domain.company.DataCompanyIprPatentTransfer;
import com.particle.data.domain.company.DataCompanyIprPatentTransferId;
import com.particle.data.domain.company.gateway.DataCompanyIprPatentTransferGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentTransferService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentTransferDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权专利转让信息 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:51
 */
@Component
@Validated
public class DataCompanyIprPatentTransferDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentTransferGateway dataCompanyIprPatentTransferGateway;
	private IDataCompanyIprPatentTransferService iDataCompanyIprPatentTransferService;

	/**
	 * 执行 企业知识产权专利转让信息 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentTransferVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyIprPatentTransferId dataCompanyIprPatentTransferId = DataCompanyIprPatentTransferId.of(deleteCommand.getId());
		DataCompanyIprPatentTransfer byId = dataCompanyIprPatentTransferGateway.getById(dataCompanyIprPatentTransferId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyIprPatentTransferGateway.delete(dataCompanyIprPatentTransferId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyIprPatentTransferAppStructMapping.instance.toDataCompanyIprPatentTransferVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPatentTransferGateway
	 */
	@Autowired
	public void setDataCompanyIprPatentTransferGateway(DataCompanyIprPatentTransferGateway dataCompanyIprPatentTransferGateway) {
		this.dataCompanyIprPatentTransferGateway = dataCompanyIprPatentTransferGateway;
	}
	@Autowired
	public void setIDataCompanyIprPatentTransferService(IDataCompanyIprPatentTransferService iDataCompanyIprPatentTransferService) {
		this.iDataCompanyIprPatentTransferService = iDataCompanyIprPatentTransferService;
	}
}
