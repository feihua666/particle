package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyIprPatentPaymentGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentPaymentService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentPaymentDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权专利缴费信息 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:27
 */
@Component
@Validated
public class DataCompanyIprPatentPaymentCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentPaymentGateway dataCompanyIprPatentPaymentGateway;
	private IDataCompanyIprPatentPaymentService iDataCompanyIprPatentPaymentService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPatentPaymentGateway
	 */
	@Autowired
	public void setDataCompanyIprPatentPaymentGateway(DataCompanyIprPatentPaymentGateway dataCompanyIprPatentPaymentGateway) {
		this.dataCompanyIprPatentPaymentGateway = dataCompanyIprPatentPaymentGateway;
	}
	@Autowired
	public void setIDataCompanyIprPatentPaymentService(IDataCompanyIprPatentPaymentService iDataCompanyIprPatentPaymentService) {
		this.iDataCompanyIprPatentPaymentService = iDataCompanyIprPatentPaymentService;
	}
}
