package com.particle.cms.app.executor;

import com.particle.cms.domain.gateway.CmsContentViewRecordGateway;
import com.particle.cms.infrastructure.service.ICmsContentViewRecordService;
import com.particle.cms.infrastructure.dos.CmsContentViewRecordDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 内容访问记录 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:38
 */
@Component
@Validated
public class CmsContentViewRecordCommandExecutor  extends AbstractBaseExecutor {

	private CmsContentViewRecordGateway cmsContentViewRecordGateway;
	private ICmsContentViewRecordService iCmsContentViewRecordService;
	/**
	 * 注入使用set方法
	 * @param cmsContentViewRecordGateway
	 */
	@Autowired
	public void setCmsContentViewRecordGateway(CmsContentViewRecordGateway cmsContentViewRecordGateway) {
		this.cmsContentViewRecordGateway = cmsContentViewRecordGateway;
	}
	@Autowired
	public void setICmsContentViewRecordService(ICmsContentViewRecordService iCmsContentViewRecordService) {
		this.iCmsContentViewRecordService = iCmsContentViewRecordService;
	}
}
