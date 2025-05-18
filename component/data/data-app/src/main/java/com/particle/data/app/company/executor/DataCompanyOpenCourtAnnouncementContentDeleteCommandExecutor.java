package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyOpenCourtAnnouncementContentAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyOpenCourtAnnouncementContentVO;
import com.particle.data.domain.company.DataCompanyOpenCourtAnnouncementContent;
import com.particle.data.domain.company.DataCompanyOpenCourtAnnouncementContentId;
import com.particle.data.domain.company.gateway.DataCompanyOpenCourtAnnouncementContentGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyOpenCourtAnnouncementContentService;
import com.particle.data.infrastructure.company.dos.DataCompanyOpenCourtAnnouncementContentDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业开庭公告内容 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:18
 */
@Component
@Validated
public class DataCompanyOpenCourtAnnouncementContentDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyOpenCourtAnnouncementContentGateway dataCompanyOpenCourtAnnouncementContentGateway;
	private IDataCompanyOpenCourtAnnouncementContentService iDataCompanyOpenCourtAnnouncementContentService;

	/**
	 * 执行 企业开庭公告内容 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyOpenCourtAnnouncementContentVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyOpenCourtAnnouncementContentId dataCompanyOpenCourtAnnouncementContentId = DataCompanyOpenCourtAnnouncementContentId.of(deleteCommand.getId());
		DataCompanyOpenCourtAnnouncementContent byId = dataCompanyOpenCourtAnnouncementContentGateway.getById(dataCompanyOpenCourtAnnouncementContentId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyOpenCourtAnnouncementContentGateway.delete(dataCompanyOpenCourtAnnouncementContentId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyOpenCourtAnnouncementContentAppStructMapping.instance.toDataCompanyOpenCourtAnnouncementContentVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyOpenCourtAnnouncementContentGateway
	 */
	@Autowired
	public void setDataCompanyOpenCourtAnnouncementContentGateway(DataCompanyOpenCourtAnnouncementContentGateway dataCompanyOpenCourtAnnouncementContentGateway) {
		this.dataCompanyOpenCourtAnnouncementContentGateway = dataCompanyOpenCourtAnnouncementContentGateway;
	}
	@Autowired
	public void setIDataCompanyOpenCourtAnnouncementContentService(IDataCompanyOpenCourtAnnouncementContentService iDataCompanyOpenCourtAnnouncementContentService) {
		this.iDataCompanyOpenCourtAnnouncementContentService = iDataCompanyOpenCourtAnnouncementContentService;
	}
}
