package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyIprGeograGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprGeograService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprGeograDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权地理标识 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:33
 */
@Component
@Validated
public class DataCompanyIprGeograCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprGeograGateway dataCompanyIprGeograGateway;
	private IDataCompanyIprGeograService iDataCompanyIprGeograService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyIprGeograGateway
	 */
	@Autowired
	public void setDataCompanyIprGeograGateway(DataCompanyIprGeograGateway dataCompanyIprGeograGateway) {
		this.dataCompanyIprGeograGateway = dataCompanyIprGeograGateway;
	}
	@Autowired
	public void setIDataCompanyIprGeograService(IDataCompanyIprGeograService iDataCompanyIprGeograService) {
		this.iDataCompanyIprGeograService = iDataCompanyIprGeograService;
	}
}
