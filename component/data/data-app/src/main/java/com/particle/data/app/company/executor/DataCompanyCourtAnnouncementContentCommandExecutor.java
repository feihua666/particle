package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyCourtAnnouncementContentGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyCourtAnnouncementContentService;
import com.particle.data.infrastructure.company.dos.DataCompanyCourtAnnouncementContentDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业法院公告内容 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:28
 */
@Component
@Validated
public class DataCompanyCourtAnnouncementContentCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyCourtAnnouncementContentGateway dataCompanyCourtAnnouncementContentGateway;
	private IDataCompanyCourtAnnouncementContentService iDataCompanyCourtAnnouncementContentService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyCourtAnnouncementContentGateway
	 */
	@Autowired
	public void setDataCompanyCourtAnnouncementContentGateway(DataCompanyCourtAnnouncementContentGateway dataCompanyCourtAnnouncementContentGateway) {
		this.dataCompanyCourtAnnouncementContentGateway = dataCompanyCourtAnnouncementContentGateway;
	}
	@Autowired
	public void setIDataCompanyCourtAnnouncementContentService(IDataCompanyCourtAnnouncementContentService iDataCompanyCourtAnnouncementContentService) {
		this.iDataCompanyCourtAnnouncementContentService = iDataCompanyCourtAnnouncementContentService;
	}
}
