package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyIprPatentQuoteGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentQuoteService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentQuoteDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权专利引证信息 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:24
 */
@Component
@Validated
public class DataCompanyIprPatentQuoteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentQuoteGateway dataCompanyIprPatentQuoteGateway;
	private IDataCompanyIprPatentQuoteService iDataCompanyIprPatentQuoteService;
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
