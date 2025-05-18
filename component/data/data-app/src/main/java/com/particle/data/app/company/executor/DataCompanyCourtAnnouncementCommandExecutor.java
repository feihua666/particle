package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyCourtAnnouncementGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyCourtAnnouncementService;
import com.particle.data.infrastructure.company.dos.DataCompanyCourtAnnouncementDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业法院公告 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:05
 */
@Component
@Validated
public class DataCompanyCourtAnnouncementCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyCourtAnnouncementGateway dataCompanyCourtAnnouncementGateway;
	private IDataCompanyCourtAnnouncementService iDataCompanyCourtAnnouncementService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyCourtAnnouncementGateway
	 */
	@Autowired
	public void setDataCompanyCourtAnnouncementGateway(DataCompanyCourtAnnouncementGateway dataCompanyCourtAnnouncementGateway) {
		this.dataCompanyCourtAnnouncementGateway = dataCompanyCourtAnnouncementGateway;
	}
	@Autowired
	public void setIDataCompanyCourtAnnouncementService(IDataCompanyCourtAnnouncementService iDataCompanyCourtAnnouncementService) {
		this.iDataCompanyCourtAnnouncementService = iDataCompanyCourtAnnouncementService;
	}
}
