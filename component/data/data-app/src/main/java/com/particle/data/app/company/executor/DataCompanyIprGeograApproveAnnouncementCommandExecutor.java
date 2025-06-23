package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyIprGeograApproveAnnouncementGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprGeograApproveAnnouncementService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprGeograApproveAnnouncementDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权地理标识核准公告 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:21
 */
@Component
@Validated
public class DataCompanyIprGeograApproveAnnouncementCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprGeograApproveAnnouncementGateway dataCompanyIprGeograApproveAnnouncementGateway;
	private IDataCompanyIprGeograApproveAnnouncementService iDataCompanyIprGeograApproveAnnouncementService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyIprGeograApproveAnnouncementGateway
	 */
	@Autowired
	public void setDataCompanyIprGeograApproveAnnouncementGateway(DataCompanyIprGeograApproveAnnouncementGateway dataCompanyIprGeograApproveAnnouncementGateway) {
		this.dataCompanyIprGeograApproveAnnouncementGateway = dataCompanyIprGeograApproveAnnouncementGateway;
	}
	@Autowired
	public void setIDataCompanyIprGeograApproveAnnouncementService(IDataCompanyIprGeograApproveAnnouncementService iDataCompanyIprGeograApproveAnnouncementService) {
		this.iDataCompanyIprGeograApproveAnnouncementService = iDataCompanyIprGeograApproveAnnouncementService;
	}
}
