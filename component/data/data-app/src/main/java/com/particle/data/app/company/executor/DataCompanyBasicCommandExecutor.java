package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyBasicGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyBasicService;
import com.particle.data.infrastructure.company.dos.DataCompanyBasicDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业基本信息 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Component
@Validated
public class DataCompanyBasicCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyBasicGateway dataCompanyBasicGateway;
	private IDataCompanyBasicService iDataCompanyBasicService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyBasicGateway
	 */
	@Autowired
	public void setDataCompanyBasicGateway(DataCompanyBasicGateway dataCompanyBasicGateway) {
		this.dataCompanyBasicGateway = dataCompanyBasicGateway;
	}
	@Autowired
	public void setIDataCompanyBasicService(IDataCompanyBasicService iDataCompanyBasicService) {
		this.iDataCompanyBasicService = iDataCompanyBasicService;
	}
}
