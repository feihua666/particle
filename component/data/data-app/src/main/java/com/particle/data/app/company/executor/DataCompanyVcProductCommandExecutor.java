package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyVcProductGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyVcProductService;
import com.particle.data.infrastructure.company.dos.DataCompanyVcProductDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业融资产品 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:47:14
 */
@Component
@Validated
public class DataCompanyVcProductCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyVcProductGateway dataCompanyVcProductGateway;
	private IDataCompanyVcProductService iDataCompanyVcProductService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyVcProductGateway
	 */
	@Autowired
	public void setDataCompanyVcProductGateway(DataCompanyVcProductGateway dataCompanyVcProductGateway) {
		this.dataCompanyVcProductGateway = dataCompanyVcProductGateway;
	}
	@Autowired
	public void setIDataCompanyVcProductService(IDataCompanyVcProductService iDataCompanyVcProductService) {
		this.iDataCompanyVcProductService = iDataCompanyVcProductService;
	}
}
