package com.particle.cms.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.cms.app.structmapping.CmsContentAppStructMapping;
import com.particle.cms.client.dto.data.CmsContentVO;
import com.particle.cms.domain.CmsContent;
import com.particle.cms.domain.CmsContentId;
import com.particle.cms.domain.gateway.CmsContentGateway;
import com.particle.cms.infrastructure.service.ICmsContentService;
import com.particle.cms.infrastructure.dos.CmsContentDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 内容 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:16
 */
@Component
@Validated
public class CmsContentDeleteCommandExecutor  extends AbstractBaseExecutor {

	private CmsContentGateway cmsContentGateway;
	private ICmsContentService iCmsContentService;

	/**
	 * 执行 内容 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<CmsContentVO> execute(@Valid IdCommand deleteCommand) {
		CmsContentId cmsContentId = CmsContentId.of(deleteCommand.getId());
		CmsContent byId = cmsContentGateway.getById(cmsContentId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = cmsContentGateway.delete(cmsContentId,deleteCommand);
		if (delete) {
			return SingleResponse.of(CmsContentAppStructMapping.instance.toCmsContentVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


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
