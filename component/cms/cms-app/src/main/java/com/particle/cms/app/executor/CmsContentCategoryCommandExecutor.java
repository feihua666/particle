package com.particle.cms.app.executor;

import com.particle.cms.domain.gateway.CmsContentCategoryGateway;
import com.particle.cms.infrastructure.service.ICmsContentCategoryService;
import com.particle.cms.infrastructure.dos.CmsContentCategoryDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 内容分类 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:40
 */
@Component
@Validated
public class CmsContentCategoryCommandExecutor  extends AbstractBaseExecutor {

	private CmsContentCategoryGateway cmsContentCategoryGateway;
	private ICmsContentCategoryService iCmsContentCategoryService;
	/**
	 * 注入使用set方法
	 * @param cmsContentCategoryGateway
	 */
	@Autowired
	public void setCmsContentCategoryGateway(CmsContentCategoryGateway cmsContentCategoryGateway) {
		this.cmsContentCategoryGateway = cmsContentCategoryGateway;
	}
	@Autowired
	public void setICmsContentCategoryService(ICmsContentCategoryService iCmsContentCategoryService) {
		this.iCmsContentCategoryService = iCmsContentCategoryService;
	}
}
