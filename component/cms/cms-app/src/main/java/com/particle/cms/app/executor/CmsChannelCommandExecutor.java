package com.particle.cms.app.executor;

import com.particle.cms.domain.gateway.CmsChannelGateway;
import com.particle.cms.infrastructure.service.ICmsChannelService;
import com.particle.cms.infrastructure.dos.CmsChannelDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 栏目 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:55
 */
@Component
@Validated
public class CmsChannelCommandExecutor  extends AbstractBaseExecutor {

	private CmsChannelGateway cmsChannelGateway;
	private ICmsChannelService iCmsChannelService;
	/**
	 * 注入使用set方法
	 * @param cmsChannelGateway
	 */
	@Autowired
	public void setCmsChannelGateway(CmsChannelGateway cmsChannelGateway) {
		this.cmsChannelGateway = cmsChannelGateway;
	}
	@Autowired
	public void setICmsChannelService(ICmsChannelService iCmsChannelService) {
		this.iCmsChannelService = iCmsChannelService;
	}
}
