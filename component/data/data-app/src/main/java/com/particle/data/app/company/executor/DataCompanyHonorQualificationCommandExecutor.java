package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyHonorQualificationGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyHonorQualificationService;
import com.particle.data.infrastructure.company.dos.DataCompanyHonorQualificationDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业荣誉资质 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:39:14
 */
@Component
@Validated
public class DataCompanyHonorQualificationCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyHonorQualificationGateway dataCompanyHonorQualificationGateway;
	private IDataCompanyHonorQualificationService iDataCompanyHonorQualificationService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyHonorQualificationGateway
	 */
	@Autowired
	public void setDataCompanyHonorQualificationGateway(DataCompanyHonorQualificationGateway dataCompanyHonorQualificationGateway) {
		this.dataCompanyHonorQualificationGateway = dataCompanyHonorQualificationGateway;
	}
	@Autowired
	public void setIDataCompanyHonorQualificationService(IDataCompanyHonorQualificationService iDataCompanyHonorQualificationService) {
		this.iDataCompanyHonorQualificationService = iDataCompanyHonorQualificationService;
	}
}
