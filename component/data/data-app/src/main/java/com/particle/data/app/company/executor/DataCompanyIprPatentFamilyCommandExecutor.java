package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyIprPatentFamilyGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentFamilyService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentFamilyDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权专利同族信息 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:43
 */
@Component
@Validated
public class DataCompanyIprPatentFamilyCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentFamilyGateway dataCompanyIprPatentFamilyGateway;
	private IDataCompanyIprPatentFamilyService iDataCompanyIprPatentFamilyService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPatentFamilyGateway
	 */
	@Autowired
	public void setDataCompanyIprPatentFamilyGateway(DataCompanyIprPatentFamilyGateway dataCompanyIprPatentFamilyGateway) {
		this.dataCompanyIprPatentFamilyGateway = dataCompanyIprPatentFamilyGateway;
	}
	@Autowired
	public void setIDataCompanyIprPatentFamilyService(IDataCompanyIprPatentFamilyService iDataCompanyIprPatentFamilyService) {
		this.iDataCompanyIprPatentFamilyService = iDataCompanyIprPatentFamilyService;
	}
}
