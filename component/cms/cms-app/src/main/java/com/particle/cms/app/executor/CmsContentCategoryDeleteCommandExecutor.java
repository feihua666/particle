package com.particle.cms.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.cms.app.structmapping.CmsContentCategoryAppStructMapping;
import com.particle.cms.client.dto.data.CmsContentCategoryVO;
import com.particle.cms.domain.CmsContentCategory;
import com.particle.cms.domain.CmsContentCategoryId;
import com.particle.cms.domain.gateway.CmsContentCategoryGateway;
import com.particle.cms.infrastructure.service.ICmsContentCategoryService;
import com.particle.cms.infrastructure.dos.CmsContentCategoryDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 内容分类 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:40
 */
@Component
@Validated
public class CmsContentCategoryDeleteCommandExecutor  extends AbstractBaseExecutor {

	private CmsContentCategoryGateway cmsContentCategoryGateway;
	private ICmsContentCategoryService iCmsContentCategoryService;

	/**
	 * 执行 内容分类 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<CmsContentCategoryVO> execute(@Valid IdCommand deleteCommand) {
		CmsContentCategoryId cmsContentCategoryId = CmsContentCategoryId.of(deleteCommand.getId());
		CmsContentCategory byId = cmsContentCategoryGateway.getById(cmsContentCategoryId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = cmsContentCategoryGateway.delete(cmsContentCategoryId,deleteCommand);
		if (delete) {
			return SingleResponse.of(CmsContentCategoryAppStructMapping.instance.toCmsContentCategoryVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


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
