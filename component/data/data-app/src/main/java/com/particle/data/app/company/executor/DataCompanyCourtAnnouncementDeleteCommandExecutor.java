package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyCourtAnnouncementAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyCourtAnnouncementVO;
import com.particle.data.domain.company.DataCompanyCourtAnnouncement;
import com.particle.data.domain.company.DataCompanyCourtAnnouncementId;
import com.particle.data.domain.company.gateway.DataCompanyCourtAnnouncementGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyCourtAnnouncementService;
import com.particle.data.infrastructure.company.dos.DataCompanyCourtAnnouncementDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业法院公告 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:05
 */
@Component
@Validated
public class DataCompanyCourtAnnouncementDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyCourtAnnouncementGateway dataCompanyCourtAnnouncementGateway;
	private IDataCompanyCourtAnnouncementService iDataCompanyCourtAnnouncementService;

	/**
	 * 执行 企业法院公告 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyCourtAnnouncementVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyCourtAnnouncementId dataCompanyCourtAnnouncementId = DataCompanyCourtAnnouncementId.of(deleteCommand.getId());
		DataCompanyCourtAnnouncement byId = dataCompanyCourtAnnouncementGateway.getById(dataCompanyCourtAnnouncementId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyCourtAnnouncementGateway.delete(dataCompanyCourtAnnouncementId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyCourtAnnouncementAppStructMapping.instance.toDataCompanyCourtAnnouncementVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyCourtAnnouncementGateway
	 */
	@Autowired
	public void setDataCompanyCourtAnnouncementGateway(DataCompanyCourtAnnouncementGateway dataCompanyCourtAnnouncementGateway) {
		this.dataCompanyCourtAnnouncementGateway = dataCompanyCourtAnnouncementGateway;
	}
	@Autowired
	public void setIDataCompanyCourtAnnouncementService(IDataCompanyCourtAnnouncementService iDataCompanyCourtAnnouncementService) {
		this.iDataCompanyCourtAnnouncementService = iDataCompanyCourtAnnouncementService;
	}
}
