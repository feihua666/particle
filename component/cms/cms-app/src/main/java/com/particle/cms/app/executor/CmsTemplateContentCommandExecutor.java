package com.particle.cms.app.executor;

import com.particle.cms.domain.gateway.CmsTemplateContentGateway;
import com.particle.cms.infrastructure.service.ICmsTemplateContentService;
import com.particle.cms.infrastructure.dos.CmsTemplateContentDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 模板内容 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:13:35
 */
@Component
@Validated
public class CmsTemplateContentCommandExecutor  extends AbstractBaseExecutor {

	private CmsTemplateContentGateway cmsTemplateContentGateway;
	private ICmsTemplateContentService iCmsTemplateContentService;
	/**
	 * 注入使用set方法
	 * @param cmsTemplateContentGateway
	 */
	@Autowired
	public void setCmsTemplateContentGateway(CmsTemplateContentGateway cmsTemplateContentGateway) {
		this.cmsTemplateContentGateway = cmsTemplateContentGateway;
	}
	@Autowired
	public void setICmsTemplateContentService(ICmsTemplateContentService iCmsTemplateContentService) {
		this.iCmsTemplateContentService = iCmsTemplateContentService;
	}
}
