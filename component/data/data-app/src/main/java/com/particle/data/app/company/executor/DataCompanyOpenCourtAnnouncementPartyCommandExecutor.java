package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyOpenCourtAnnouncementPartyGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyOpenCourtAnnouncementPartyService;
import com.particle.data.infrastructure.company.dos.DataCompanyOpenCourtAnnouncementPartyDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业开庭公告当事人 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:03
 */
@Component
@Validated
public class DataCompanyOpenCourtAnnouncementPartyCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyOpenCourtAnnouncementPartyGateway dataCompanyOpenCourtAnnouncementPartyGateway;
	private IDataCompanyOpenCourtAnnouncementPartyService iDataCompanyOpenCourtAnnouncementPartyService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyOpenCourtAnnouncementPartyGateway
	 */
	@Autowired
	public void setDataCompanyOpenCourtAnnouncementPartyGateway(DataCompanyOpenCourtAnnouncementPartyGateway dataCompanyOpenCourtAnnouncementPartyGateway) {
		this.dataCompanyOpenCourtAnnouncementPartyGateway = dataCompanyOpenCourtAnnouncementPartyGateway;
	}
	@Autowired
	public void setIDataCompanyOpenCourtAnnouncementPartyService(IDataCompanyOpenCourtAnnouncementPartyService iDataCompanyOpenCourtAnnouncementPartyService) {
		this.iDataCompanyOpenCourtAnnouncementPartyService = iDataCompanyOpenCourtAnnouncementPartyService;
	}
}
