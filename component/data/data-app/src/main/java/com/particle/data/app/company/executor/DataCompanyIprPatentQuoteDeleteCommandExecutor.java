package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyIprPatentQuoteAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentQuoteVO;
import com.particle.data.domain.company.DataCompanyIprPatentQuote;
import com.particle.data.domain.company.DataCompanyIprPatentQuoteId;
import com.particle.data.domain.company.gateway.DataCompanyIprPatentQuoteGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentQuoteService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentQuoteDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权专利引证信息 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:24
 */
@Component
@Validated
public class DataCompanyIprPatentQuoteDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentQuoteGateway dataCompanyIprPatentQuoteGateway;
	private IDataCompanyIprPatentQuoteService iDataCompanyIprPatentQuoteService;

	/**
	 * 执行 企业知识产权专利引证信息 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentQuoteVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyIprPatentQuoteId dataCompanyIprPatentQuoteId = DataCompanyIprPatentQuoteId.of(deleteCommand.getId());
		DataCompanyIprPatentQuote byId = dataCompanyIprPatentQuoteGateway.getById(dataCompanyIprPatentQuoteId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyIprPatentQuoteGateway.delete(dataCompanyIprPatentQuoteId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyIprPatentQuoteAppStructMapping.instance.toDataCompanyIprPatentQuoteVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPatentQuoteGateway
	 */
	@Autowired
	public void setDataCompanyIprPatentQuoteGateway(DataCompanyIprPatentQuoteGateway dataCompanyIprPatentQuoteGateway) {
		this.dataCompanyIprPatentQuoteGateway = dataCompanyIprPatentQuoteGateway;
	}
	@Autowired
	public void setIDataCompanyIprPatentQuoteService(IDataCompanyIprPatentQuoteService iDataCompanyIprPatentQuoteService) {
		this.iDataCompanyIprPatentQuoteService = iDataCompanyIprPatentQuoteService;
	}
}
