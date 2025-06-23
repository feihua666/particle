package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyIprGeograApproveAnnouncementAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyIprGeograApproveAnnouncementVO;
import com.particle.data.domain.company.DataCompanyIprGeograApproveAnnouncement;
import com.particle.data.domain.company.DataCompanyIprGeograApproveAnnouncementId;
import com.particle.data.domain.company.gateway.DataCompanyIprGeograApproveAnnouncementGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprGeograApproveAnnouncementService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprGeograApproveAnnouncementDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权地理标识核准公告 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:21
 */
@Component
@Validated
public class DataCompanyIprGeograApproveAnnouncementDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprGeograApproveAnnouncementGateway dataCompanyIprGeograApproveAnnouncementGateway;
	private IDataCompanyIprGeograApproveAnnouncementService iDataCompanyIprGeograApproveAnnouncementService;

	/**
	 * 执行 企业知识产权地理标识核准公告 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprGeograApproveAnnouncementVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyIprGeograApproveAnnouncementId dataCompanyIprGeograApproveAnnouncementId = DataCompanyIprGeograApproveAnnouncementId.of(deleteCommand.getId());
		DataCompanyIprGeograApproveAnnouncement byId = dataCompanyIprGeograApproveAnnouncementGateway.getById(dataCompanyIprGeograApproveAnnouncementId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyIprGeograApproveAnnouncementGateway.delete(dataCompanyIprGeograApproveAnnouncementId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyIprGeograApproveAnnouncementAppStructMapping.instance.toDataCompanyIprGeograApproveAnnouncementVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyIprGeograApproveAnnouncementGateway
	 */
	@Autowired
	public void setDataCompanyIprGeograApproveAnnouncementGateway(DataCompanyIprGeograApproveAnnouncementGateway dataCompanyIprGeograApproveAnnouncementGateway) {
		this.dataCompanyIprGeograApproveAnnouncementGateway = dataCompanyIprGeograApproveAnnouncementGateway;
	}
	@Autowired
	public void setIDataCompanyIprGeograApproveAnnouncementService(IDataCompanyIprGeograApproveAnnouncementService iDataCompanyIprGeograApproveAnnouncementService) {
		this.iDataCompanyIprGeograApproveAnnouncementService = iDataCompanyIprGeograApproveAnnouncementService;
	}
}
