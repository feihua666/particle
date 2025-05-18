package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyOpenCourtAnnouncementGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyOpenCourtAnnouncementService;
import com.particle.data.infrastructure.company.dos.DataCompanyOpenCourtAnnouncementDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业开庭公告 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:31
 */
@Component
@Validated
public class DataCompanyOpenCourtAnnouncementCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyOpenCourtAnnouncementGateway dataCompanyOpenCourtAnnouncementGateway;
	private IDataCompanyOpenCourtAnnouncementService iDataCompanyOpenCourtAnnouncementService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyOpenCourtAnnouncementGateway
	 */
	@Autowired
	public void setDataCompanyOpenCourtAnnouncementGateway(DataCompanyOpenCourtAnnouncementGateway dataCompanyOpenCourtAnnouncementGateway) {
		this.dataCompanyOpenCourtAnnouncementGateway = dataCompanyOpenCourtAnnouncementGateway;
	}
	@Autowired
	public void setIDataCompanyOpenCourtAnnouncementService(IDataCompanyOpenCourtAnnouncementService iDataCompanyOpenCourtAnnouncementService) {
		this.iDataCompanyOpenCourtAnnouncementService = iDataCompanyOpenCourtAnnouncementService;
	}
}
