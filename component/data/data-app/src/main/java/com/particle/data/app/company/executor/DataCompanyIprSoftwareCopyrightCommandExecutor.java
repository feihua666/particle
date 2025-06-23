package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyIprSoftwareCopyrightGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprSoftwareCopyrightService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprSoftwareCopyrightDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权软件著作 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:01
 */
@Component
@Validated
public class DataCompanyIprSoftwareCopyrightCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprSoftwareCopyrightGateway dataCompanyIprSoftwareCopyrightGateway;
	private IDataCompanyIprSoftwareCopyrightService iDataCompanyIprSoftwareCopyrightService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyIprSoftwareCopyrightGateway
	 */
	@Autowired
	public void setDataCompanyIprSoftwareCopyrightGateway(DataCompanyIprSoftwareCopyrightGateway dataCompanyIprSoftwareCopyrightGateway) {
		this.dataCompanyIprSoftwareCopyrightGateway = dataCompanyIprSoftwareCopyrightGateway;
	}
	@Autowired
	public void setIDataCompanyIprSoftwareCopyrightService(IDataCompanyIprSoftwareCopyrightService iDataCompanyIprSoftwareCopyrightService) {
		this.iDataCompanyIprSoftwareCopyrightService = iDataCompanyIprSoftwareCopyrightService;
	}
}
