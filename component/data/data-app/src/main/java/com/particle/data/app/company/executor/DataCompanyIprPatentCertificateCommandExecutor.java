package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyIprPatentCertificateGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentCertificateService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentCertificateDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权专利证书信息 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:02
 */
@Component
@Validated
public class DataCompanyIprPatentCertificateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentCertificateGateway dataCompanyIprPatentCertificateGateway;
	private IDataCompanyIprPatentCertificateService iDataCompanyIprPatentCertificateService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPatentCertificateGateway
	 */
	@Autowired
	public void setDataCompanyIprPatentCertificateGateway(DataCompanyIprPatentCertificateGateway dataCompanyIprPatentCertificateGateway) {
		this.dataCompanyIprPatentCertificateGateway = dataCompanyIprPatentCertificateGateway;
	}
	@Autowired
	public void setIDataCompanyIprPatentCertificateService(IDataCompanyIprPatentCertificateService iDataCompanyIprPatentCertificateService) {
		this.iDataCompanyIprPatentCertificateService = iDataCompanyIprPatentCertificateService;
	}
}
