package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyIprTrademarkTransferGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkTransferService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkTransferDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权商标转让信息 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:04
 */
@Component
@Validated
public class DataCompanyIprTrademarkTransferCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprTrademarkTransferGateway dataCompanyIprTrademarkTransferGateway;
	private IDataCompanyIprTrademarkTransferService iDataCompanyIprTrademarkTransferService;
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
