package com.particle.cms.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.light.share.code.ErrorCodeGlobalEnum;
import com.particle.cms.app.structmapping.CmsTemplateAppStructMapping;
import com.particle.cms.client.dto.data.CmsTemplateVO;
import com.particle.cms.domain.CmsTemplate;
import com.particle.cms.domain.CmsTemplateId;
import com.particle.cms.domain.gateway.CmsTemplateGateway;
import com.particle.cms.infrastructure.service.ICmsTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;

/**
 * <p>
 * 模板 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-01-21 21:03:36
 */
@Component
@Validated
public class CmsTemplateDeleteCommandExecutor  extends AbstractBaseExecutor {

	private CmsTemplateGateway cmsTemplateGateway;
	private ICmsTemplateService iCmsTemplateService;

	/**
	 * 执行 模板 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<CmsTemplateVO> execute(@Valid CommonIdCommand deleteCommand) {
		CmsTemplateId cmsTemplateId = CmsTemplateId.of(deleteCommand.getId());
		CmsTemplate byId = cmsTemplateGateway.getById(cmsTemplateId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = cmsTemplateGateway.delete(cmsTemplateId,deleteCommand);
		if (delete) {
			return SingleResponse.of(CmsTemplateAppStructMapping.instance.toCmsTemplateVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


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
