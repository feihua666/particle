package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyDeliveryAnnouncementPartyGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyDeliveryAnnouncementPartyService;
import com.particle.data.infrastructure.company.dos.DataCompanyDeliveryAnnouncementPartyDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业送达公告当事人 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:33
 */
@Component
@Validated
public class DataCompanyDeliveryAnnouncementPartyCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyDeliveryAnnouncementPartyGateway dataCompanyDeliveryAnnouncementPartyGateway;
	private IDataCompanyDeliveryAnnouncementPartyService iDataCompanyDeliveryAnnouncementPartyService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyDeliveryAnnouncementPartyGateway
	 */
	@Autowired
	public void setDataCompanyDeliveryAnnouncementPartyGateway(DataCompanyDeliveryAnnouncementPartyGateway dataCompanyDeliveryAnnouncementPartyGateway) {
		this.dataCompanyDeliveryAnnouncementPartyGateway = dataCompanyDeliveryAnnouncementPartyGateway;
	}
	@Autowired
	public void setIDataCompanyDeliveryAnnouncementPartyService(IDataCompanyDeliveryAnnouncementPartyService iDataCompanyDeliveryAnnouncementPartyService) {
		this.iDataCompanyDeliveryAnnouncementPartyService = iDataCompanyDeliveryAnnouncementPartyService;
	}
}
