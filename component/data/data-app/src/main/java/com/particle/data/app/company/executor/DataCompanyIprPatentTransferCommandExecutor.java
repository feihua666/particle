package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyIprPatentTransferGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentTransferService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentTransferDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权专利转让信息 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:51
 */
@Component
@Validated
public class DataCompanyIprPatentTransferCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentTransferGateway dataCompanyIprPatentTransferGateway;
	private IDataCompanyIprPatentTransferService iDataCompanyIprPatentTransferService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPatentTransferGateway
	 */
	@Autowired
	public void setDataCompanyIprPatentTransferGateway(DataCompanyIprPatentTransferGateway dataCompanyIprPatentTransferGateway) {
		this.dataCompanyIprPatentTransferGateway = dataCompanyIprPatentTransferGateway;
	}
	@Autowired
	public void setIDataCompanyIprPatentTransferService(IDataCompanyIprPatentTransferService iDataCompanyIprPatentTransferService) {
		this.iDataCompanyIprPatentTransferService = iDataCompanyIprPatentTransferService;
	}
}
