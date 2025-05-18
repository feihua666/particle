package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyAnnualReportForeignGuaranteeAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportForeignGuaranteeVO;
import com.particle.data.domain.company.DataCompanyAnnualReportForeignGuarantee;
import com.particle.data.domain.company.DataCompanyAnnualReportForeignGuaranteeId;
import com.particle.data.domain.company.gateway.DataCompanyAnnualReportForeignGuaranteeGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportForeignGuaranteeService;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportForeignGuaranteeDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业年报对外担保 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:11
 */
@Component
@Validated
public class DataCompanyAnnualReportForeignGuaranteeDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAnnualReportForeignGuaranteeGateway dataCompanyAnnualReportForeignGuaranteeGateway;
	private IDataCompanyAnnualReportForeignGuaranteeService iDataCompanyAnnualReportForeignGuaranteeService;

	/**
	 * 执行 企业年报对外担保 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportForeignGuaranteeVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyAnnualReportForeignGuaranteeId dataCompanyAnnualReportForeignGuaranteeId = DataCompanyAnnualReportForeignGuaranteeId.of(deleteCommand.getId());
		DataCompanyAnnualReportForeignGuarantee byId = dataCompanyAnnualReportForeignGuaranteeGateway.getById(dataCompanyAnnualReportForeignGuaranteeId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyAnnualReportForeignGuaranteeGateway.delete(dataCompanyAnnualReportForeignGuaranteeId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyAnnualReportForeignGuaranteeAppStructMapping.instance.toDataCompanyAnnualReportForeignGuaranteeVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyAnnualReportForeignGuaranteeGateway
	 */
	@Autowired
	public void setDataCompanyAnnualReportForeignGuaranteeGateway(DataCompanyAnnualReportForeignGuaranteeGateway dataCompanyAnnualReportForeignGuaranteeGateway) {
		this.dataCompanyAnnualReportForeignGuaranteeGateway = dataCompanyAnnualReportForeignGuaranteeGateway;
	}
	@Autowired
	public void setIDataCompanyAnnualReportForeignGuaranteeService(IDataCompanyAnnualReportForeignGuaranteeService iDataCompanyAnnualReportForeignGuaranteeService) {
		this.iDataCompanyAnnualReportForeignGuaranteeService = iDataCompanyAnnualReportForeignGuaranteeService;
	}
}
