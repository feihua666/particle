package com.particle.cms.app.executor;

import com.particle.cms.domain.gateway.CmsTemplateGateway;
import com.particle.cms.infrastructure.service.ICmsTemplateService;
import com.particle.cms.infrastructure.dos.CmsTemplateDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 模板 指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-01-21 21:03:36
 */
@Component
@Validated
public class CmsTemplateCommandExecutor  extends AbstractBaseExecutor {

	private CmsTemplateGateway cmsTemplateGateway;
	private ICmsTemplateService iCmsTemplateService;
	/**
	 * 注入使用set方法
	 * @param cmsTemplateGateway
	 */
	@Autowired
	public void setCmsTemplateGateway(CmsTemplateGateway cmsTemplateGateway) {
		this.cmsTemplateGateway = cmsTemplateGateway;
	}
	@Autowired
	public void setICmsTemplateService(ICmsTemplateService iCmsTemplateService) {
		this.iCmsTemplateService = iCmsTemplateService;
	}
}
