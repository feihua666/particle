package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyDeliveryAnnouncementContentAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyDeliveryAnnouncementContentVO;
import com.particle.data.domain.company.DataCompanyDeliveryAnnouncementContent;
import com.particle.data.domain.company.DataCompanyDeliveryAnnouncementContentId;
import com.particle.data.domain.company.gateway.DataCompanyDeliveryAnnouncementContentGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyDeliveryAnnouncementContentService;
import com.particle.data.infrastructure.company.dos.DataCompanyDeliveryAnnouncementContentDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业送达公告内容 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:18
 */
@Component
@Validated
public class DataCompanyDeliveryAnnouncementContentDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyDeliveryAnnouncementContentGateway dataCompanyDeliveryAnnouncementContentGateway;
	private IDataCompanyDeliveryAnnouncementContentService iDataCompanyDeliveryAnnouncementContentService;

	/**
	 * 执行 企业送达公告内容 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyDeliveryAnnouncementContentVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyDeliveryAnnouncementContentId dataCompanyDeliveryAnnouncementContentId = DataCompanyDeliveryAnnouncementContentId.of(deleteCommand.getId());
		DataCompanyDeliveryAnnouncementContent byId = dataCompanyDeliveryAnnouncementContentGateway.getById(dataCompanyDeliveryAnnouncementContentId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyDeliveryAnnouncementContentGateway.delete(dataCompanyDeliveryAnnouncementContentId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyDeliveryAnnouncementContentAppStructMapping.instance.toDataCompanyDeliveryAnnouncementContentVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyDeliveryAnnouncementContentGateway
	 */
	@Autowired
	public void setDataCompanyDeliveryAnnouncementContentGateway(DataCompanyDeliveryAnnouncementContentGateway dataCompanyDeliveryAnnouncementContentGateway) {
		this.dataCompanyDeliveryAnnouncementContentGateway = dataCompanyDeliveryAnnouncementContentGateway;
	}
	@Autowired
	public void setIDataCompanyDeliveryAnnouncementContentService(IDataCompanyDeliveryAnnouncementContentService iDataCompanyDeliveryAnnouncementContentService) {
		this.iDataCompanyDeliveryAnnouncementContentService = iDataCompanyDeliveryAnnouncementContentService;
	}
}
