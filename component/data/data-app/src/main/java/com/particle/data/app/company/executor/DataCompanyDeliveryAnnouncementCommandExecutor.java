package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyDeliveryAnnouncementGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyDeliveryAnnouncementService;
import com.particle.data.infrastructure.company.dos.DataCompanyDeliveryAnnouncementDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业送达公告 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:06
 */
@Component
@Validated
public class DataCompanyDeliveryAnnouncementCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyDeliveryAnnouncementGateway dataCompanyDeliveryAnnouncementGateway;
	private IDataCompanyDeliveryAnnouncementService iDataCompanyDeliveryAnnouncementService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyDeliveryAnnouncementGateway
	 */
	@Autowired
	public void setDataCompanyDeliveryAnnouncementGateway(DataCompanyDeliveryAnnouncementGateway dataCompanyDeliveryAnnouncementGateway) {
		this.dataCompanyDeliveryAnnouncementGateway = dataCompanyDeliveryAnnouncementGateway;
	}
	@Autowired
	public void setIDataCompanyDeliveryAnnouncementService(IDataCompanyDeliveryAnnouncementService iDataCompanyDeliveryAnnouncementService) {
		this.iDataCompanyDeliveryAnnouncementService = iDataCompanyDeliveryAnnouncementService;
	}
}
