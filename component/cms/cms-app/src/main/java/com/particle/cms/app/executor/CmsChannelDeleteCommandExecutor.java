package com.particle.cms.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.cms.app.structmapping.CmsChannelAppStructMapping;
import com.particle.cms.client.dto.data.CmsChannelVO;
import com.particle.cms.domain.CmsChannel;
import com.particle.cms.domain.CmsChannelId;
import com.particle.cms.domain.gateway.CmsChannelGateway;
import com.particle.cms.infrastructure.service.ICmsChannelService;
import com.particle.cms.infrastructure.dos.CmsChannelDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 栏目 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:55
 */
@Component
@Validated
public class CmsChannelDeleteCommandExecutor  extends AbstractBaseExecutor {

	private CmsChannelGateway cmsChannelGateway;
	private ICmsChannelService iCmsChannelService;

	/**
	 * 执行 栏目 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<CmsChannelVO> execute(@Valid IdCommand deleteCommand) {
		CmsChannelId cmsChannelId = CmsChannelId.of(deleteCommand.getId());
		CmsChannel byId = cmsChannelGateway.getById(cmsChannelId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = cmsChannelGateway.delete(cmsChannelId,deleteCommand);
		if (delete) {
			return SingleResponse.of(CmsChannelAppStructMapping.instance.toCmsChannelVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param cmsChannelGateway
	 */
	@Autowired
	public void setCmsChannelGateway(CmsChannelGateway cmsChannelGateway) {
		this.cmsChannelGateway = cmsChannelGateway;
	}
	@Autowired
	public void setICmsChannelService(ICmsChannelService iCmsChannelService) {
		this.iCmsChannelService = iCmsChannelService;
	}
}
