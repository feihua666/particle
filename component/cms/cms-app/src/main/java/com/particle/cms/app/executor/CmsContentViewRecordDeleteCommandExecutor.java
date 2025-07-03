package com.particle.cms.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.cms.app.structmapping.CmsContentViewRecordAppStructMapping;
import com.particle.cms.client.dto.data.CmsContentViewRecordVO;
import com.particle.cms.domain.CmsContentViewRecord;
import com.particle.cms.domain.CmsContentViewRecordId;
import com.particle.cms.domain.gateway.CmsContentViewRecordGateway;
import com.particle.cms.infrastructure.service.ICmsContentViewRecordService;
import com.particle.cms.infrastructure.dos.CmsContentViewRecordDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 内容访问记录 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:38
 */
@Component
@Validated
public class CmsContentViewRecordDeleteCommandExecutor  extends AbstractBaseExecutor {

	private CmsContentViewRecordGateway cmsContentViewRecordGateway;
	private ICmsContentViewRecordService iCmsContentViewRecordService;

	/**
	 * 执行 内容访问记录 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<CmsContentViewRecordVO> execute(@Valid IdCommand deleteCommand) {
		CmsContentViewRecordId cmsContentViewRecordId = CmsContentViewRecordId.of(deleteCommand.getId());
		CmsContentViewRecord byId = cmsContentViewRecordGateway.getById(cmsContentViewRecordId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = cmsContentViewRecordGateway.delete(cmsContentViewRecordId,deleteCommand);
		if (delete) {
			return SingleResponse.of(CmsContentViewRecordAppStructMapping.instance.toCmsContentViewRecordVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


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
