package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyPersonGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyPersonService;
import com.particle.data.infrastructure.company.dos.DataCompanyPersonDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业个人 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:50
 */
@Component
@Validated
public class DataCompanyPersonCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyPersonGateway dataCompanyPersonGateway;
	private IDataCompanyPersonService iDataCompanyPersonService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyPersonGateway
	 */
	@Autowired
	public void setDataCompanyPersonGateway(DataCompanyPersonGateway dataCompanyPersonGateway) {
		this.dataCompanyPersonGateway = dataCompanyPersonGateway;
	}
	@Autowired
	public void setIDataCompanyPersonService(IDataCompanyPersonService iDataCompanyPersonService) {
		this.iDataCompanyPersonService = iDataCompanyPersonService;
	}
}
