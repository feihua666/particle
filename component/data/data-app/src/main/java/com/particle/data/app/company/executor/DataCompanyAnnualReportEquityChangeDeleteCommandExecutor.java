package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyAnnualReportEquityChangeAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportEquityChangeVO;
import com.particle.data.domain.company.DataCompanyAnnualReportEquityChange;
import com.particle.data.domain.company.DataCompanyAnnualReportEquityChangeId;
import com.particle.data.domain.company.gateway.DataCompanyAnnualReportEquityChangeGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportEquityChangeService;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportEquityChangeDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业年报股权变更 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:57
 */
@Component
@Validated
public class DataCompanyAnnualReportEquityChangeDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAnnualReportEquityChangeGateway dataCompanyAnnualReportEquityChangeGateway;
	private IDataCompanyAnnualReportEquityChangeService iDataCompanyAnnualReportEquityChangeService;

	/**
	 * 执行 企业年报股权变更 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportEquityChangeVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyAnnualReportEquityChangeId dataCompanyAnnualReportEquityChangeId = DataCompanyAnnualReportEquityChangeId.of(deleteCommand.getId());
		DataCompanyAnnualReportEquityChange byId = dataCompanyAnnualReportEquityChangeGateway.getById(dataCompanyAnnualReportEquityChangeId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyAnnualReportEquityChangeGateway.delete(dataCompanyAnnualReportEquityChangeId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyAnnualReportEquityChangeAppStructMapping.instance.toDataCompanyAnnualReportEquityChangeVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyAnnualReportEquityChangeGateway
	 */
	@Autowired
	public void setDataCompanyAnnualReportEquityChangeGateway(DataCompanyAnnualReportEquityChangeGateway dataCompanyAnnualReportEquityChangeGateway) {
		this.dataCompanyAnnualReportEquityChangeGateway = dataCompanyAnnualReportEquityChangeGateway;
	}
	@Autowired
	public void setIDataCompanyAnnualReportEquityChangeService(IDataCompanyAnnualReportEquityChangeService iDataCompanyAnnualReportEquityChangeService) {
		this.iDataCompanyAnnualReportEquityChangeService = iDataCompanyAnnualReportEquityChangeService;
	}
}
