package com.particle.cms.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.cms.app.structmapping.CmsTemplateContentAppStructMapping;
import com.particle.cms.client.dto.data.CmsTemplateContentVO;
import com.particle.cms.domain.CmsTemplateContent;
import com.particle.cms.domain.CmsTemplateContentId;
import com.particle.cms.domain.gateway.CmsTemplateContentGateway;
import com.particle.cms.infrastructure.service.ICmsTemplateContentService;
import com.particle.cms.infrastructure.dos.CmsTemplateContentDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 模板内容 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:13:35
 */
@Component
@Validated
public class CmsTemplateContentDeleteCommandExecutor  extends AbstractBaseExecutor {

	private CmsTemplateContentGateway cmsTemplateContentGateway;
	private ICmsTemplateContentService iCmsTemplateContentService;

	/**
	 * 执行 模板内容 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<CmsTemplateContentVO> execute(@Valid IdCommand deleteCommand) {
		CmsTemplateContentId cmsTemplateContentId = CmsTemplateContentId.of(deleteCommand.getId());
		CmsTemplateContent byId = cmsTemplateContentGateway.getById(cmsTemplateContentId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = cmsTemplateContentGateway.delete(cmsTemplateContentId,deleteCommand);
		if (delete) {
			return SingleResponse.of(CmsTemplateContentAppStructMapping.instance.toCmsTemplateContentVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


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
