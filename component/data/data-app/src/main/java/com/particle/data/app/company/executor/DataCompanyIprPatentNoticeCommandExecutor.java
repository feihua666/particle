package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyIprPatentNoticeGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentNoticeService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentNoticeDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权专利通知书信息 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:13
 */
@Component
@Validated
public class DataCompanyIprPatentNoticeCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentNoticeGateway dataCompanyIprPatentNoticeGateway;
	private IDataCompanyIprPatentNoticeService iDataCompanyIprPatentNoticeService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPatentNoticeGateway
	 */
	@Autowired
	public void setDataCompanyIprPatentNoticeGateway(DataCompanyIprPatentNoticeGateway dataCompanyIprPatentNoticeGateway) {
		this.dataCompanyIprPatentNoticeGateway = dataCompanyIprPatentNoticeGateway;
	}
	@Autowired
	public void setIDataCompanyIprPatentNoticeService(IDataCompanyIprPatentNoticeService iDataCompanyIprPatentNoticeService) {
		this.iDataCompanyIprPatentNoticeService = iDataCompanyIprPatentNoticeService;
	}
}
