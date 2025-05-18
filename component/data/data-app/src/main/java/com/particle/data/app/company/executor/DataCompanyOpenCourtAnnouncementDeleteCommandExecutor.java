package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyOpenCourtAnnouncementAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyOpenCourtAnnouncementVO;
import com.particle.data.domain.company.DataCompanyOpenCourtAnnouncement;
import com.particle.data.domain.company.DataCompanyOpenCourtAnnouncementId;
import com.particle.data.domain.company.gateway.DataCompanyOpenCourtAnnouncementGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyOpenCourtAnnouncementService;
import com.particle.data.infrastructure.company.dos.DataCompanyOpenCourtAnnouncementDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业开庭公告 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:31
 */
@Component
@Validated
public class DataCompanyOpenCourtAnnouncementDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyOpenCourtAnnouncementGateway dataCompanyOpenCourtAnnouncementGateway;
	private IDataCompanyOpenCourtAnnouncementService iDataCompanyOpenCourtAnnouncementService;

	/**
	 * 执行 企业开庭公告 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyOpenCourtAnnouncementVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyOpenCourtAnnouncementId dataCompanyOpenCourtAnnouncementId = DataCompanyOpenCourtAnnouncementId.of(deleteCommand.getId());
		DataCompanyOpenCourtAnnouncement byId = dataCompanyOpenCourtAnnouncementGateway.getById(dataCompanyOpenCourtAnnouncementId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyOpenCourtAnnouncementGateway.delete(dataCompanyOpenCourtAnnouncementId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyOpenCourtAnnouncementAppStructMapping.instance.toDataCompanyOpenCourtAnnouncementVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyOpenCourtAnnouncementGateway
	 */
	@Autowired
	public void setDataCompanyOpenCourtAnnouncementGateway(DataCompanyOpenCourtAnnouncementGateway dataCompanyOpenCourtAnnouncementGateway) {
		this.dataCompanyOpenCourtAnnouncementGateway = dataCompanyOpenCourtAnnouncementGateway;
	}
	@Autowired
	public void setIDataCompanyOpenCourtAnnouncementService(IDataCompanyOpenCourtAnnouncementService iDataCompanyOpenCourtAnnouncementService) {
		this.iDataCompanyOpenCourtAnnouncementService = iDataCompanyOpenCourtAnnouncementService;
	}
}
