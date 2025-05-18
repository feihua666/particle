package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyShareholderGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyShareholderService;
import com.particle.data.infrastructure.company.dos.DataCompanyShareholderDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业股东 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:01
 */
@Component
@Validated
public class DataCompanyShareholderCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyShareholderGateway dataCompanyShareholderGateway;
	private IDataCompanyShareholderService iDataCompanyShareholderService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyShareholderGateway
	 */
	@Autowired
	public void setDataCompanyShareholderGateway(DataCompanyShareholderGateway dataCompanyShareholderGateway) {
		this.dataCompanyShareholderGateway = dataCompanyShareholderGateway;
	}
	@Autowired
	public void setIDataCompanyShareholderService(IDataCompanyShareholderService iDataCompanyShareholderService) {
		this.iDataCompanyShareholderService = iDataCompanyShareholderService;
	}
}
