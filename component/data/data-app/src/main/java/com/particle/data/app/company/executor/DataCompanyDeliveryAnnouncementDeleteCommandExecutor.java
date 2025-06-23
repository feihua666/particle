package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyDeliveryAnnouncementAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyDeliveryAnnouncementVO;
import com.particle.data.domain.company.DataCompanyDeliveryAnnouncement;
import com.particle.data.domain.company.DataCompanyDeliveryAnnouncementId;
import com.particle.data.domain.company.gateway.DataCompanyDeliveryAnnouncementGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyDeliveryAnnouncementService;
import com.particle.data.infrastructure.company.dos.DataCompanyDeliveryAnnouncementDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业送达公告 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:06
 */
@Component
@Validated
public class DataCompanyDeliveryAnnouncementDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyDeliveryAnnouncementGateway dataCompanyDeliveryAnnouncementGateway;
	private IDataCompanyDeliveryAnnouncementService iDataCompanyDeliveryAnnouncementService;

	/**
	 * 执行 企业送达公告 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyDeliveryAnnouncementVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyDeliveryAnnouncementId dataCompanyDeliveryAnnouncementId = DataCompanyDeliveryAnnouncementId.of(deleteCommand.getId());
		DataCompanyDeliveryAnnouncement byId = dataCompanyDeliveryAnnouncementGateway.getById(dataCompanyDeliveryAnnouncementId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyDeliveryAnnouncementGateway.delete(dataCompanyDeliveryAnnouncementId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyDeliveryAnnouncementAppStructMapping.instance.toDataCompanyDeliveryAnnouncementVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyDeliveryAnnouncementGateway
	 */
	@Autowired
	public void setDataCompanyDeliveryAnnouncementGateway(DataCompanyDeliveryAnnouncementGateway dataCompanyDeliveryAnnouncementGateway) {
		this.dataCompanyDeliveryAnnouncementGateway = dataCompanyDeliveryAnnouncementGateway;
	}
	@Autowired
	public void setIDataCompanyDeliveryAnnouncementService(IDataCompanyDeliveryAnnouncementService iDataCompanyDeliveryAnnouncementService) {
		this.iDataCompanyDeliveryAnnouncementService = iDataCompanyDeliveryAnnouncementService;
	}
}
