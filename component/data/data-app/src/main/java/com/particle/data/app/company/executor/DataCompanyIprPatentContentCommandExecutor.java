package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyIprPatentContentGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentContentService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentContentDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权专利内容 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:27
 */
@Component
@Validated
public class DataCompanyIprPatentContentCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentContentGateway dataCompanyIprPatentContentGateway;
	private IDataCompanyIprPatentContentService iDataCompanyIprPatentContentService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPatentContentGateway
	 */
	@Autowired
	public void setDataCompanyIprPatentContentGateway(DataCompanyIprPatentContentGateway dataCompanyIprPatentContentGateway) {
		this.dataCompanyIprPatentContentGateway = dataCompanyIprPatentContentGateway;
	}
	@Autowired
	public void setIDataCompanyIprPatentContentService(IDataCompanyIprPatentContentService iDataCompanyIprPatentContentService) {
		this.iDataCompanyIprPatentContentService = iDataCompanyIprPatentContentService;
	}
}
