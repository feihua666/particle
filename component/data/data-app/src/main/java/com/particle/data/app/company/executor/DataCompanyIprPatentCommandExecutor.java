package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyIprPatentGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权专利 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:39:48
 */
@Component
@Validated
public class DataCompanyIprPatentCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentGateway dataCompanyIprPatentGateway;
	private IDataCompanyIprPatentService iDataCompanyIprPatentService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPatentGateway
	 */
	@Autowired
	public void setDataCompanyIprPatentGateway(DataCompanyIprPatentGateway dataCompanyIprPatentGateway) {
		this.dataCompanyIprPatentGateway = dataCompanyIprPatentGateway;
	}
	@Autowired
	public void setIDataCompanyIprPatentService(IDataCompanyIprPatentService iDataCompanyIprPatentService) {
		this.iDataCompanyIprPatentService = iDataCompanyIprPatentService;
	}
}
