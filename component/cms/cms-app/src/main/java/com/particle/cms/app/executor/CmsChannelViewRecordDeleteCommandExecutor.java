package com.particle.cms.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.cms.app.structmapping.CmsChannelViewRecordAppStructMapping;
import com.particle.cms.client.dto.data.CmsChannelViewRecordVO;
import com.particle.cms.domain.CmsChannelViewRecord;
import com.particle.cms.domain.CmsChannelViewRecordId;
import com.particle.cms.domain.gateway.CmsChannelViewRecordGateway;
import com.particle.cms.infrastructure.service.ICmsChannelViewRecordService;
import com.particle.cms.infrastructure.dos.CmsChannelViewRecordDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 栏目访问记录 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:22
 */
@Component
@Validated
public class CmsChannelViewRecordDeleteCommandExecutor  extends AbstractBaseExecutor {

	private CmsChannelViewRecordGateway cmsChannelViewRecordGateway;
	private ICmsChannelViewRecordService iCmsChannelViewRecordService;

	/**
	 * 执行 栏目访问记录 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<CmsChannelViewRecordVO> execute(@Valid IdCommand deleteCommand) {
		CmsChannelViewRecordId cmsChannelViewRecordId = CmsChannelViewRecordId.of(deleteCommand.getId());
		CmsChannelViewRecord byId = cmsChannelViewRecordGateway.getById(cmsChannelViewRecordId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = cmsChannelViewRecordGateway.delete(cmsChannelViewRecordId,deleteCommand);
		if (delete) {
			return SingleResponse.of(CmsChannelViewRecordAppStructMapping.instance.toCmsChannelViewRecordVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param cmsChannelViewRecordGateway
	 */
	@Autowired
	public void setCmsChannelViewRecordGateway(CmsChannelViewRecordGateway cmsChannelViewRecordGateway) {
		this.cmsChannelViewRecordGateway = cmsChannelViewRecordGateway;
	}
	@Autowired
	public void setICmsChannelViewRecordService(ICmsChannelViewRecordService iCmsChannelViewRecordService) {
		this.iCmsChannelViewRecordService = iCmsChannelViewRecordService;
	}
}
