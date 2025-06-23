package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyDeliveryAnnouncementPartyAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyDeliveryAnnouncementPartyVO;
import com.particle.data.domain.company.DataCompanyDeliveryAnnouncementParty;
import com.particle.data.domain.company.DataCompanyDeliveryAnnouncementPartyId;
import com.particle.data.domain.company.gateway.DataCompanyDeliveryAnnouncementPartyGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyDeliveryAnnouncementPartyService;
import com.particle.data.infrastructure.company.dos.DataCompanyDeliveryAnnouncementPartyDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业送达公告当事人 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:33
 */
@Component
@Validated
public class DataCompanyDeliveryAnnouncementPartyDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyDeliveryAnnouncementPartyGateway dataCompanyDeliveryAnnouncementPartyGateway;
	private IDataCompanyDeliveryAnnouncementPartyService iDataCompanyDeliveryAnnouncementPartyService;

	/**
	 * 执行 企业送达公告当事人 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyDeliveryAnnouncementPartyVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyDeliveryAnnouncementPartyId dataCompanyDeliveryAnnouncementPartyId = DataCompanyDeliveryAnnouncementPartyId.of(deleteCommand.getId());
		DataCompanyDeliveryAnnouncementParty byId = dataCompanyDeliveryAnnouncementPartyGateway.getById(dataCompanyDeliveryAnnouncementPartyId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyDeliveryAnnouncementPartyGateway.delete(dataCompanyDeliveryAnnouncementPartyId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyDeliveryAnnouncementPartyAppStructMapping.instance.toDataCompanyDeliveryAnnouncementPartyVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyDeliveryAnnouncementPartyGateway
	 */
	@Autowired
	public void setDataCompanyDeliveryAnnouncementPartyGateway(DataCompanyDeliveryAnnouncementPartyGateway dataCompanyDeliveryAnnouncementPartyGateway) {
		this.dataCompanyDeliveryAnnouncementPartyGateway = dataCompanyDeliveryAnnouncementPartyGateway;
	}
	@Autowired
	public void setIDataCompanyDeliveryAnnouncementPartyService(IDataCompanyDeliveryAnnouncementPartyService iDataCompanyDeliveryAnnouncementPartyService) {
		this.iDataCompanyDeliveryAnnouncementPartyService = iDataCompanyDeliveryAnnouncementPartyService;
	}
}
