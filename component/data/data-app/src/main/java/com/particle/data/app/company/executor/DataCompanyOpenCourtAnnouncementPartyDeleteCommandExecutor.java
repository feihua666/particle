package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyOpenCourtAnnouncementPartyAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyOpenCourtAnnouncementPartyVO;
import com.particle.data.domain.company.DataCompanyOpenCourtAnnouncementParty;
import com.particle.data.domain.company.DataCompanyOpenCourtAnnouncementPartyId;
import com.particle.data.domain.company.gateway.DataCompanyOpenCourtAnnouncementPartyGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyOpenCourtAnnouncementPartyService;
import com.particle.data.infrastructure.company.dos.DataCompanyOpenCourtAnnouncementPartyDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业开庭公告当事人 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:03
 */
@Component
@Validated
public class DataCompanyOpenCourtAnnouncementPartyDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyOpenCourtAnnouncementPartyGateway dataCompanyOpenCourtAnnouncementPartyGateway;
	private IDataCompanyOpenCourtAnnouncementPartyService iDataCompanyOpenCourtAnnouncementPartyService;

	/**
	 * 执行 企业开庭公告当事人 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyOpenCourtAnnouncementPartyVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyOpenCourtAnnouncementPartyId dataCompanyOpenCourtAnnouncementPartyId = DataCompanyOpenCourtAnnouncementPartyId.of(deleteCommand.getId());
		DataCompanyOpenCourtAnnouncementParty byId = dataCompanyOpenCourtAnnouncementPartyGateway.getById(dataCompanyOpenCourtAnnouncementPartyId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyOpenCourtAnnouncementPartyGateway.delete(dataCompanyOpenCourtAnnouncementPartyId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyOpenCourtAnnouncementPartyAppStructMapping.instance.toDataCompanyOpenCourtAnnouncementPartyVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyOpenCourtAnnouncementPartyGateway
	 */
	@Autowired
	public void setDataCompanyOpenCourtAnnouncementPartyGateway(DataCompanyOpenCourtAnnouncementPartyGateway dataCompanyOpenCourtAnnouncementPartyGateway) {
		this.dataCompanyOpenCourtAnnouncementPartyGateway = dataCompanyOpenCourtAnnouncementPartyGateway;
	}
	@Autowired
	public void setIDataCompanyOpenCourtAnnouncementPartyService(IDataCompanyOpenCourtAnnouncementPartyService iDataCompanyOpenCourtAnnouncementPartyService) {
		this.iDataCompanyOpenCourtAnnouncementPartyService = iDataCompanyOpenCourtAnnouncementPartyService;
	}
}
