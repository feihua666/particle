package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyAnnualReportWebsiteAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportWebsiteVO;
import com.particle.data.domain.company.DataCompanyAnnualReportWebsite;
import com.particle.data.domain.company.DataCompanyAnnualReportWebsiteId;
import com.particle.data.domain.company.gateway.DataCompanyAnnualReportWebsiteGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportWebsiteService;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportWebsiteDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业年报网站网店 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:16
 */
@Component
@Validated
public class DataCompanyAnnualReportWebsiteDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAnnualReportWebsiteGateway dataCompanyAnnualReportWebsiteGateway;
	private IDataCompanyAnnualReportWebsiteService iDataCompanyAnnualReportWebsiteService;

	/**
	 * 执行 企业年报网站网店 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportWebsiteVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyAnnualReportWebsiteId dataCompanyAnnualReportWebsiteId = DataCompanyAnnualReportWebsiteId.of(deleteCommand.getId());
		DataCompanyAnnualReportWebsite byId = dataCompanyAnnualReportWebsiteGateway.getById(dataCompanyAnnualReportWebsiteId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyAnnualReportWebsiteGateway.delete(dataCompanyAnnualReportWebsiteId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyAnnualReportWebsiteAppStructMapping.instance.toDataCompanyAnnualReportWebsiteVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyAnnualReportWebsiteGateway
	 */
	@Autowired
	public void setDataCompanyAnnualReportWebsiteGateway(DataCompanyAnnualReportWebsiteGateway dataCompanyAnnualReportWebsiteGateway) {
		this.dataCompanyAnnualReportWebsiteGateway = dataCompanyAnnualReportWebsiteGateway;
	}
	@Autowired
	public void setIDataCompanyAnnualReportWebsiteService(IDataCompanyAnnualReportWebsiteService iDataCompanyAnnualReportWebsiteService) {
		this.iDataCompanyAnnualReportWebsiteService = iDataCompanyAnnualReportWebsiteService;
	}
}
