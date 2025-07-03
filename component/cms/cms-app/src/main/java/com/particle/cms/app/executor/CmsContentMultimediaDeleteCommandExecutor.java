package com.particle.cms.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.cms.app.structmapping.CmsContentMultimediaAppStructMapping;
import com.particle.cms.client.dto.data.CmsContentMultimediaVO;
import com.particle.cms.domain.CmsContentMultimedia;
import com.particle.cms.domain.CmsContentMultimediaId;
import com.particle.cms.domain.gateway.CmsContentMultimediaGateway;
import com.particle.cms.infrastructure.service.ICmsContentMultimediaService;
import com.particle.cms.infrastructure.dos.CmsContentMultimediaDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 内容多媒体 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:28
 */
@Component
@Validated
public class CmsContentMultimediaDeleteCommandExecutor  extends AbstractBaseExecutor {

	private CmsContentMultimediaGateway cmsContentMultimediaGateway;
	private ICmsContentMultimediaService iCmsContentMultimediaService;

	/**
	 * 执行 内容多媒体 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<CmsContentMultimediaVO> execute(@Valid IdCommand deleteCommand) {
		CmsContentMultimediaId cmsContentMultimediaId = CmsContentMultimediaId.of(deleteCommand.getId());
		CmsContentMultimedia byId = cmsContentMultimediaGateway.getById(cmsContentMultimediaId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = cmsContentMultimediaGateway.delete(cmsContentMultimediaId,deleteCommand);
		if (delete) {
			return SingleResponse.of(CmsContentMultimediaAppStructMapping.instance.toCmsContentMultimediaVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


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
