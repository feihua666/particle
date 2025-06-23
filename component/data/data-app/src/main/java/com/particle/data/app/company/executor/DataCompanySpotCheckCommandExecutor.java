package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanySpotCheckGateway;
import com.particle.data.infrastructure.company.service.IDataCompanySpotCheckService;
import com.particle.data.infrastructure.company.dos.DataCompanySpotCheckDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业抽查检查 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:39
 */
@Component
@Validated
public class DataCompanySpotCheckCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanySpotCheckGateway dataCompanySpotCheckGateway;
	private IDataCompanySpotCheckService iDataCompanySpotCheckService;
	/**
	 * 注入使用set方法
	 * @param dataCompanySpotCheckGateway
	 */
	@Autowired
	public void setDataCompanySpotCheckGateway(DataCompanySpotCheckGateway dataCompanySpotCheckGateway) {
		this.dataCompanySpotCheckGateway = dataCompanySpotCheckGateway;
	}
	@Autowired
	public void setIDataCompanySpotCheckService(IDataCompanySpotCheckService iDataCompanySpotCheckService) {
		this.iDataCompanySpotCheckService = iDataCompanySpotCheckService;
	}
}
