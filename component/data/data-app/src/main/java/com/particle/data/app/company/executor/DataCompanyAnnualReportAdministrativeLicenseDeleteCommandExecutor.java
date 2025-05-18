package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyAnnualReportAdministrativeLicenseAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportAdministrativeLicenseVO;
import com.particle.data.domain.company.DataCompanyAnnualReportAdministrativeLicense;
import com.particle.data.domain.company.DataCompanyAnnualReportAdministrativeLicenseId;
import com.particle.data.domain.company.gateway.DataCompanyAnnualReportAdministrativeLicenseGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportAdministrativeLicenseService;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportAdministrativeLicenseDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业年报行政许可 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:15
 */
@Component
@Validated
public class DataCompanyAnnualReportAdministrativeLicenseDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAnnualReportAdministrativeLicenseGateway dataCompanyAnnualReportAdministrativeLicenseGateway;
	private IDataCompanyAnnualReportAdministrativeLicenseService iDataCompanyAnnualReportAdministrativeLicenseService;

	/**
	 * 执行 企业年报行政许可 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportAdministrativeLicenseVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyAnnualReportAdministrativeLicenseId dataCompanyAnnualReportAdministrativeLicenseId = DataCompanyAnnualReportAdministrativeLicenseId.of(deleteCommand.getId());
		DataCompanyAnnualReportAdministrativeLicense byId = dataCompanyAnnualReportAdministrativeLicenseGateway.getById(dataCompanyAnnualReportAdministrativeLicenseId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyAnnualReportAdministrativeLicenseGateway.delete(dataCompanyAnnualReportAdministrativeLicenseId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyAnnualReportAdministrativeLicenseAppStructMapping.instance.toDataCompanyAnnualReportAdministrativeLicenseVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyAnnualReportAdministrativeLicenseGateway
	 */
	@Autowired
	public void setDataCompanyAnnualReportAdministrativeLicenseGateway(DataCompanyAnnualReportAdministrativeLicenseGateway dataCompanyAnnualReportAdministrativeLicenseGateway) {
		this.dataCompanyAnnualReportAdministrativeLicenseGateway = dataCompanyAnnualReportAdministrativeLicenseGateway;
	}
	@Autowired
	public void setIDataCompanyAnnualReportAdministrativeLicenseService(IDataCompanyAnnualReportAdministrativeLicenseService iDataCompanyAnnualReportAdministrativeLicenseService) {
		this.iDataCompanyAnnualReportAdministrativeLicenseService = iDataCompanyAnnualReportAdministrativeLicenseService;
	}
}
