package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyAnnualReportSocialSecurityAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportSocialSecurityVO;
import com.particle.data.domain.company.DataCompanyAnnualReportSocialSecurity;
import com.particle.data.domain.company.DataCompanyAnnualReportSocialSecurityId;
import com.particle.data.domain.company.gateway.DataCompanyAnnualReportSocialSecurityGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportSocialSecurityService;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportSocialSecurityDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业年报社保 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:03
 */
@Component
@Validated
public class DataCompanyAnnualReportSocialSecurityDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAnnualReportSocialSecurityGateway dataCompanyAnnualReportSocialSecurityGateway;
	private IDataCompanyAnnualReportSocialSecurityService iDataCompanyAnnualReportSocialSecurityService;

	/**
	 * 执行 企业年报社保 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportSocialSecurityVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyAnnualReportSocialSecurityId dataCompanyAnnualReportSocialSecurityId = DataCompanyAnnualReportSocialSecurityId.of(deleteCommand.getId());
		DataCompanyAnnualReportSocialSecurity byId = dataCompanyAnnualReportSocialSecurityGateway.getById(dataCompanyAnnualReportSocialSecurityId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyAnnualReportSocialSecurityGateway.delete(dataCompanyAnnualReportSocialSecurityId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyAnnualReportSocialSecurityAppStructMapping.instance.toDataCompanyAnnualReportSocialSecurityVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyAnnualReportSocialSecurityGateway
	 */
	@Autowired
	public void setDataCompanyAnnualReportSocialSecurityGateway(DataCompanyAnnualReportSocialSecurityGateway dataCompanyAnnualReportSocialSecurityGateway) {
		this.dataCompanyAnnualReportSocialSecurityGateway = dataCompanyAnnualReportSocialSecurityGateway;
	}
	@Autowired
	public void setIDataCompanyAnnualReportSocialSecurityService(IDataCompanyAnnualReportSocialSecurityService iDataCompanyAnnualReportSocialSecurityService) {
		this.iDataCompanyAnnualReportSocialSecurityService = iDataCompanyAnnualReportSocialSecurityService;
	}
}
