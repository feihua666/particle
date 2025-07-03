package com.particle.cms.app.executor;

import com.particle.cms.domain.gateway.CmsContentMultimediaGateway;
import com.particle.cms.infrastructure.service.ICmsContentMultimediaService;
import com.particle.cms.infrastructure.dos.CmsContentMultimediaDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 内容多媒体 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:28
 */
@Component
@Validated
public class CmsContentMultimediaCommandExecutor  extends AbstractBaseExecutor {

	private CmsContentMultimediaGateway cmsContentMultimediaGateway;
	private ICmsContentMultimediaService iCmsContentMultimediaService;
	/**
	 * 注入使用set方法
	 * @param cmsContentMultimediaGateway
	 */
	@Autowired
	public void setCmsContentMultimediaGateway(CmsContentMultimediaGateway cmsContentMultimediaGateway) {
		this.cmsContentMultimediaGateway = cmsContentMultimediaGateway;
	}
	@Autowired
	public void setICmsContentMultimediaService(ICmsContentMultimediaService iCmsContentMultimediaService) {
		this.iCmsContentMultimediaService = iCmsContentMultimediaService;
	}
}
