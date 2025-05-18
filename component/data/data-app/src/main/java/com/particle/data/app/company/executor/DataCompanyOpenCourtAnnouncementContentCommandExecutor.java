package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyOpenCourtAnnouncementContentGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyOpenCourtAnnouncementContentService;
import com.particle.data.infrastructure.company.dos.DataCompanyOpenCourtAnnouncementContentDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业开庭公告内容 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:18
 */
@Component
@Validated
public class DataCompanyOpenCourtAnnouncementContentCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyOpenCourtAnnouncementContentGateway dataCompanyOpenCourtAnnouncementContentGateway;
	private IDataCompanyOpenCourtAnnouncementContentService iDataCompanyOpenCourtAnnouncementContentService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyOpenCourtAnnouncementContentGateway
	 */
	@Autowired
	public void setDataCompanyOpenCourtAnnouncementContentGateway(DataCompanyOpenCourtAnnouncementContentGateway dataCompanyOpenCourtAnnouncementContentGateway) {
		this.dataCompanyOpenCourtAnnouncementContentGateway = dataCompanyOpenCourtAnnouncementContentGateway;
	}
	@Autowired
	public void setIDataCompanyOpenCourtAnnouncementContentService(IDataCompanyOpenCourtAnnouncementContentService iDataCompanyOpenCourtAnnouncementContentService) {
		this.iDataCompanyOpenCourtAnnouncementContentService = iDataCompanyOpenCourtAnnouncementContentService;
	}
}
