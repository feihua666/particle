package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyDeliveryAnnouncementContentGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyDeliveryAnnouncementContentService;
import com.particle.data.infrastructure.company.dos.DataCompanyDeliveryAnnouncementContentDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业送达公告内容 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:18
 */
@Component
@Validated
public class DataCompanyDeliveryAnnouncementContentCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyDeliveryAnnouncementContentGateway dataCompanyDeliveryAnnouncementContentGateway;
	private IDataCompanyDeliveryAnnouncementContentService iDataCompanyDeliveryAnnouncementContentService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyDeliveryAnnouncementContentGateway
	 */
	@Autowired
	public void setDataCompanyDeliveryAnnouncementContentGateway(DataCompanyDeliveryAnnouncementContentGateway dataCompanyDeliveryAnnouncementContentGateway) {
		this.dataCompanyDeliveryAnnouncementContentGateway = dataCompanyDeliveryAnnouncementContentGateway;
	}
	@Autowired
	public void setIDataCompanyDeliveryAnnouncementContentService(IDataCompanyDeliveryAnnouncementContentService iDataCompanyDeliveryAnnouncementContentService) {
		this.iDataCompanyDeliveryAnnouncementContentService = iDataCompanyDeliveryAnnouncementContentService;
	}
}
