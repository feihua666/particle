package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyAnnualReportChangeAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportChangeVO;
import com.particle.data.domain.company.DataCompanyAnnualReportChange;
import com.particle.data.domain.company.DataCompanyAnnualReportChangeId;
import com.particle.data.domain.company.gateway.DataCompanyAnnualReportChangeGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportChangeService;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportChangeDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业年报变更 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:43
 */
@Component
@Validated
public class DataCompanyAnnualReportChangeDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAnnualReportChangeGateway dataCompanyAnnualReportChangeGateway;
	private IDataCompanyAnnualReportChangeService iDataCompanyAnnualReportChangeService;

	/**
	 * 执行 企业年报变更 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportChangeVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyAnnualReportChangeId dataCompanyAnnualReportChangeId = DataCompanyAnnualReportChangeId.of(deleteCommand.getId());
		DataCompanyAnnualReportChange byId = dataCompanyAnnualReportChangeGateway.getById(dataCompanyAnnualReportChangeId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyAnnualReportChangeGateway.delete(dataCompanyAnnualReportChangeId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyAnnualReportChangeAppStructMapping.instance.toDataCompanyAnnualReportChangeVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyAnnualReportChangeGateway
	 */
	@Autowired
	public void setDataCompanyAnnualReportChangeGateway(DataCompanyAnnualReportChangeGateway dataCompanyAnnualReportChangeGateway) {
		this.dataCompanyAnnualReportChangeGateway = dataCompanyAnnualReportChangeGateway;
	}
	@Autowired
	public void setIDataCompanyAnnualReportChangeService(IDataCompanyAnnualReportChangeService iDataCompanyAnnualReportChangeService) {
		this.iDataCompanyAnnualReportChangeService = iDataCompanyAnnualReportChangeService;
	}
}
