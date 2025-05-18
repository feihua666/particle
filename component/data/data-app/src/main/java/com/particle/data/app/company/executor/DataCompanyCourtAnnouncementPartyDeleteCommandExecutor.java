package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyCourtAnnouncementPartyAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyCourtAnnouncementPartyVO;
import com.particle.data.domain.company.DataCompanyCourtAnnouncementParty;
import com.particle.data.domain.company.DataCompanyCourtAnnouncementPartyId;
import com.particle.data.domain.company.gateway.DataCompanyCourtAnnouncementPartyGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyCourtAnnouncementPartyService;
import com.particle.data.infrastructure.company.dos.DataCompanyCourtAnnouncementPartyDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业法院公告当事人 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:44
 */
@Component
@Validated
public class DataCompanyCourtAnnouncementPartyDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyCourtAnnouncementPartyGateway dataCompanyCourtAnnouncementPartyGateway;
	private IDataCompanyCourtAnnouncementPartyService iDataCompanyCourtAnnouncementPartyService;

	/**
	 * 执行 企业法院公告当事人 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyCourtAnnouncementPartyVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyCourtAnnouncementPartyId dataCompanyCourtAnnouncementPartyId = DataCompanyCourtAnnouncementPartyId.of(deleteCommand.getId());
		DataCompanyCourtAnnouncementParty byId = dataCompanyCourtAnnouncementPartyGateway.getById(dataCompanyCourtAnnouncementPartyId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyCourtAnnouncementPartyGateway.delete(dataCompanyCourtAnnouncementPartyId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyCourtAnnouncementPartyAppStructMapping.instance.toDataCompanyCourtAnnouncementPartyVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyCourtAnnouncementPartyGateway
	 */
	@Autowired
	public void setDataCompanyCourtAnnouncementPartyGateway(DataCompanyCourtAnnouncementPartyGateway dataCompanyCourtAnnouncementPartyGateway) {
		this.dataCompanyCourtAnnouncementPartyGateway = dataCompanyCourtAnnouncementPartyGateway;
	}
	@Autowired
	public void setIDataCompanyCourtAnnouncementPartyService(IDataCompanyCourtAnnouncementPartyService iDataCompanyCourtAnnouncementPartyService) {
		this.iDataCompanyCourtAnnouncementPartyService = iDataCompanyCourtAnnouncementPartyService;
	}
}
