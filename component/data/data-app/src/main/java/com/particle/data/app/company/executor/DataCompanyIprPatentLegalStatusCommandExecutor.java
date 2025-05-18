package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyIprPatentLegalStatusGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentLegalStatusService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentLegalStatusDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权专利法律状态 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:03
 */
@Component
@Validated
public class DataCompanyIprPatentLegalStatusCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentLegalStatusGateway dataCompanyIprPatentLegalStatusGateway;
	private IDataCompanyIprPatentLegalStatusService iDataCompanyIprPatentLegalStatusService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPatentLegalStatusGateway
	 */
	@Autowired
	public void setDataCompanyIprPatentLegalStatusGateway(DataCompanyIprPatentLegalStatusGateway dataCompanyIprPatentLegalStatusGateway) {
		this.dataCompanyIprPatentLegalStatusGateway = dataCompanyIprPatentLegalStatusGateway;
	}
	@Autowired
	public void setIDataCompanyIprPatentLegalStatusService(IDataCompanyIprPatentLegalStatusService iDataCompanyIprPatentLegalStatusService) {
		this.iDataCompanyIprPatentLegalStatusService = iDataCompanyIprPatentLegalStatusService;
	}
}
