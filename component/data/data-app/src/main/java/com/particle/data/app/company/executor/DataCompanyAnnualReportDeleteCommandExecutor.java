package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyAnnualReportAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportVO;
import com.particle.data.domain.company.DataCompanyAnnualReport;
import com.particle.data.domain.company.DataCompanyAnnualReportId;
import com.particle.data.domain.company.gateway.DataCompanyAnnualReportGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportService;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业年报 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:58
 */
@Component
@Validated
public class DataCompanyAnnualReportDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAnnualReportGateway dataCompanyAnnualReportGateway;
	private IDataCompanyAnnualReportService iDataCompanyAnnualReportService;

	/**
	 * 执行 企业年报 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyAnnualReportId dataCompanyAnnualReportId = DataCompanyAnnualReportId.of(deleteCommand.getId());
		DataCompanyAnnualReport byId = dataCompanyAnnualReportGateway.getById(dataCompanyAnnualReportId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyAnnualReportGateway.delete(dataCompanyAnnualReportId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyAnnualReportAppStructMapping.instance.toDataCompanyAnnualReportVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyAnnualReportGateway
	 */
	@Autowired
	public void setDataCompanyAnnualReportGateway(DataCompanyAnnualReportGateway dataCompanyAnnualReportGateway) {
		this.dataCompanyAnnualReportGateway = dataCompanyAnnualReportGateway;
	}
	@Autowired
	public void setIDataCompanyAnnualReportService(IDataCompanyAnnualReportService iDataCompanyAnnualReportService) {
		this.iDataCompanyAnnualReportService = iDataCompanyAnnualReportService;
	}
}
