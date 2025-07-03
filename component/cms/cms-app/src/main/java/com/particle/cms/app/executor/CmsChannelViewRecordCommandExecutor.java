package com.particle.cms.app.executor;

import com.particle.cms.domain.gateway.CmsChannelViewRecordGateway;
import com.particle.cms.infrastructure.service.ICmsChannelViewRecordService;
import com.particle.cms.infrastructure.dos.CmsChannelViewRecordDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 栏目访问记录 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:22
 */
@Component
@Validated
public class CmsChannelViewRecordCommandExecutor  extends AbstractBaseExecutor {

	private CmsChannelViewRecordGateway cmsChannelViewRecordGateway;
	private ICmsChannelViewRecordService iCmsChannelViewRecordService;
	/**
	 * 注入使用set方法
	 * @param cmsChannelViewRecordGateway
	 */
	@Autowired
	public void setCmsChannelViewRecordGateway(CmsChannelViewRecordGateway cmsChannelViewRecordGateway) {
		this.cmsChannelViewRecordGateway = cmsChannelViewRecordGateway;
	}
	@Autowired
	public void setICmsChannelViewRecordService(ICmsChannelViewRecordService iCmsChannelViewRecordService) {
		this.iCmsChannelViewRecordService = iCmsChannelViewRecordService;
	}
}
