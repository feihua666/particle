package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyIprTrademarkGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权商标 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:14:45
 */
@Component
@Validated
public class DataCompanyIprTrademarkCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprTrademarkGateway dataCompanyIprTrademarkGateway;
	private IDataCompanyIprTrademarkService iDataCompanyIprTrademarkService;
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
