package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyCourtAnnouncementPartyGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyCourtAnnouncementPartyService;
import com.particle.data.infrastructure.company.dos.DataCompanyCourtAnnouncementPartyDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业法院公告当事人 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:44
 */
@Component
@Validated
public class DataCompanyCourtAnnouncementPartyCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyCourtAnnouncementPartyGateway dataCompanyCourtAnnouncementPartyGateway;
	private IDataCompanyCourtAnnouncementPartyService iDataCompanyCourtAnnouncementPartyService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyCourtAnnouncementPartyGateway
	 */
	@Autowired
	public void setDataCompanyCourtAnnouncementPartyGateway(DataCompanyCourtAnnouncementPartyGateway dataCompanyCourtAnnouncementPartyGateway) {
		this.dataCompanyCourtAnnouncementPartyGateway = dataCompanyCourtAnnouncementPartyGateway;
	}
	@Autowired
	public void setIDataCompanyCourtAnnouncementPartyService(IDataCompanyCourtAnnouncementPartyService iDataCompanyCourtAnnouncementPartyService) {
		this.iDataCompanyCourtAnnouncementPartyService = iDataCompanyCourtAnnouncementPartyService;
	}
}
