package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyAnnualReportForeignInvestAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportForeignInvestVO;
import com.particle.data.domain.company.DataCompanyAnnualReportForeignInvest;
import com.particle.data.domain.company.DataCompanyAnnualReportForeignInvestId;
import com.particle.data.domain.company.gateway.DataCompanyAnnualReportForeignInvestGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportForeignInvestService;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportForeignInvestDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业年报对外投资 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:23
 */
@Component
@Validated
public class DataCompanyAnnualReportForeignInvestDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAnnualReportForeignInvestGateway dataCompanyAnnualReportForeignInvestGateway;
	private IDataCompanyAnnualReportForeignInvestService iDataCompanyAnnualReportForeignInvestService;

	/**
	 * 执行 企业年报对外投资 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportForeignInvestVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyAnnualReportForeignInvestId dataCompanyAnnualReportForeignInvestId = DataCompanyAnnualReportForeignInvestId.of(deleteCommand.getId());
		DataCompanyAnnualReportForeignInvest byId = dataCompanyAnnualReportForeignInvestGateway.getById(dataCompanyAnnualReportForeignInvestId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyAnnualReportForeignInvestGateway.delete(dataCompanyAnnualReportForeignInvestId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyAnnualReportForeignInvestAppStructMapping.instance.toDataCompanyAnnualReportForeignInvestVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyAnnualReportForeignInvestGateway
	 */
	@Autowired
	public void setDataCompanyAnnualReportForeignInvestGateway(DataCompanyAnnualReportForeignInvestGateway dataCompanyAnnualReportForeignInvestGateway) {
		this.dataCompanyAnnualReportForeignInvestGateway = dataCompanyAnnualReportForeignInvestGateway;
	}
	@Autowired
	public void setIDataCompanyAnnualReportForeignInvestService(IDataCompanyAnnualReportForeignInvestService iDataCompanyAnnualReportForeignInvestService) {
		this.iDataCompanyAnnualReportForeignInvestService = iDataCompanyAnnualReportForeignInvestService;
	}
}
