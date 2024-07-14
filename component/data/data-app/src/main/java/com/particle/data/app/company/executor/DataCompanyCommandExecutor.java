package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.domain.company.gateway.DataCompanyGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业 指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:44
 */
@Component
@Validated
public class DataCompanyCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyGateway dataCompanyGateway;
	private IDataCompanyService iDataCompanyService;


	/**
	 * 注入使用set方法
	 * @param dataCompanyGateway
	 */
	@Autowired
	public void setDataCompanyGateway(DataCompanyGateway dataCompanyGateway) {
		this.dataCompanyGateway = dataCompanyGateway;
	}
	@Autowired
	public void setIDataCompanyService(IDataCompanyService iDataCompanyService) {
		this.iDataCompanyService = iDataCompanyService;
	}
}
