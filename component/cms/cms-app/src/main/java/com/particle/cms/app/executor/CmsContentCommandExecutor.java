package com.particle.cms.app.executor;

import com.particle.cms.domain.gateway.CmsContentGateway;
import com.particle.cms.infrastructure.service.ICmsContentService;
import com.particle.cms.infrastructure.dos.CmsContentDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 内容 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:16
 */
@Component
@Validated
public class CmsContentCommandExecutor  extends AbstractBaseExecutor {

	private CmsContentGateway cmsContentGateway;
	private ICmsContentService iCmsContentService;
	/**
	 * 注入使用set方法
	 * @param cmsContentGateway
	 */
	@Autowired
	public void setCmsContentGateway(CmsContentGateway cmsContentGateway) {
		this.cmsContentGateway = cmsContentGateway;
	}
	@Autowired
	public void setICmsContentService(ICmsContentService iCmsContentService) {
		this.iCmsContentService = iCmsContentService;
	}
}
