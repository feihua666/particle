package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyIprPatentCitedGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentCitedService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentCitedDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权专利被引证信息 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:15
 */
@Component
@Validated
public class DataCompanyIprPatentCitedCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentCitedGateway dataCompanyIprPatentCitedGateway;
	private IDataCompanyIprPatentCitedService iDataCompanyIprPatentCitedService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPatentCitedGateway
	 */
	@Autowired
	public void setDataCompanyIprPatentCitedGateway(DataCompanyIprPatentCitedGateway dataCompanyIprPatentCitedGateway) {
		this.dataCompanyIprPatentCitedGateway = dataCompanyIprPatentCitedGateway;
	}
	@Autowired
	public void setIDataCompanyIprPatentCitedService(IDataCompanyIprPatentCitedService iDataCompanyIprPatentCitedService) {
		this.iDataCompanyIprPatentCitedService = iDataCompanyIprPatentCitedService;
	}
}
