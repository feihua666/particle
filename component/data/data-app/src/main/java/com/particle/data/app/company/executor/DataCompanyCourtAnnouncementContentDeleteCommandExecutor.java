package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyCourtAnnouncementContentAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyCourtAnnouncementContentVO;
import com.particle.data.domain.company.DataCompanyCourtAnnouncementContent;
import com.particle.data.domain.company.DataCompanyCourtAnnouncementContentId;
import com.particle.data.domain.company.gateway.DataCompanyCourtAnnouncementContentGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyCourtAnnouncementContentService;
import com.particle.data.infrastructure.company.dos.DataCompanyCourtAnnouncementContentDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业法院公告内容 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:28
 */
@Component
@Validated
public class DataCompanyCourtAnnouncementContentDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyCourtAnnouncementContentGateway dataCompanyCourtAnnouncementContentGateway;
	private IDataCompanyCourtAnnouncementContentService iDataCompanyCourtAnnouncementContentService;

	/**
	 * 执行 企业法院公告内容 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyCourtAnnouncementContentVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyCourtAnnouncementContentId dataCompanyCourtAnnouncementContentId = DataCompanyCourtAnnouncementContentId.of(deleteCommand.getId());
		DataCompanyCourtAnnouncementContent byId = dataCompanyCourtAnnouncementContentGateway.getById(dataCompanyCourtAnnouncementContentId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyCourtAnnouncementContentGateway.delete(dataCompanyCourtAnnouncementContentId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyCourtAnnouncementContentAppStructMapping.instance.toDataCompanyCourtAnnouncementContentVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyCourtAnnouncementContentGateway
	 */
	@Autowired
	public void setDataCompanyCourtAnnouncementContentGateway(DataCompanyCourtAnnouncementContentGateway dataCompanyCourtAnnouncementContentGateway) {
		this.dataCompanyCourtAnnouncementContentGateway = dataCompanyCourtAnnouncementContentGateway;
	}
	@Autowired
	public void setIDataCompanyCourtAnnouncementContentService(IDataCompanyCourtAnnouncementContentService iDataCompanyCourtAnnouncementContentService) {
		this.iDataCompanyCourtAnnouncementContentService = iDataCompanyCourtAnnouncementContentService;
	}
}
