package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyIprTrademarkTransferPersonAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkTransferPersonVO;
import com.particle.data.domain.company.DataCompanyIprTrademarkTransferPerson;
import com.particle.data.domain.company.DataCompanyIprTrademarkTransferPersonId;
import com.particle.data.domain.company.gateway.DataCompanyIprTrademarkTransferPersonGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkTransferPersonService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkTransferPersonDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权商标转让人 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:13
 */
@Component
@Validated
public class DataCompanyIprTrademarkTransferPersonDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprTrademarkTransferPersonGateway dataCompanyIprTrademarkTransferPersonGateway;
	private IDataCompanyIprTrademarkTransferPersonService iDataCompanyIprTrademarkTransferPersonService;

	/**
	 * 执行 企业知识产权商标转让人 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkTransferPersonVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyIprTrademarkTransferPersonId dataCompanyIprTrademarkTransferPersonId = DataCompanyIprTrademarkTransferPersonId.of(deleteCommand.getId());
		DataCompanyIprTrademarkTransferPerson byId = dataCompanyIprTrademarkTransferPersonGateway.getById(dataCompanyIprTrademarkTransferPersonId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyIprTrademarkTransferPersonGateway.delete(dataCompanyIprTrademarkTransferPersonId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyIprTrademarkTransferPersonAppStructMapping.instance.toDataCompanyIprTrademarkTransferPersonVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyIprTrademarkTransferPersonGateway
	 */
	@Autowired
	public void setDataCompanyIprTrademarkTransferPersonGateway(DataCompanyIprTrademarkTransferPersonGateway dataCompanyIprTrademarkTransferPersonGateway) {
		this.dataCompanyIprTrademarkTransferPersonGateway = dataCompanyIprTrademarkTransferPersonGateway;
	}
	@Autowired
	public void setIDataCompanyIprTrademarkTransferPersonService(IDataCompanyIprTrademarkTransferPersonService iDataCompanyIprTrademarkTransferPersonService) {
		this.iDataCompanyIprTrademarkTransferPersonService = iDataCompanyIprTrademarkTransferPersonService;
	}
}
