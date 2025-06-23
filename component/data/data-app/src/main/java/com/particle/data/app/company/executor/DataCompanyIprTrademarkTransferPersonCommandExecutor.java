package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyIprTrademarkTransferPersonGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkTransferPersonService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkTransferPersonDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权商标转让人 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:13
 */
@Component
@Validated
public class DataCompanyIprTrademarkTransferPersonCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprTrademarkTransferPersonGateway dataCompanyIprTrademarkTransferPersonGateway;
	private IDataCompanyIprTrademarkTransferPersonService iDataCompanyIprTrademarkTransferPersonService;
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
