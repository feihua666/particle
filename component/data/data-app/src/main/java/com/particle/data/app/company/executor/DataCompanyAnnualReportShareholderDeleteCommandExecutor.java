package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyAnnualReportShareholderAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportShareholderVO;
import com.particle.data.domain.company.DataCompanyAnnualReportShareholder;
import com.particle.data.domain.company.DataCompanyAnnualReportShareholderId;
import com.particle.data.domain.company.gateway.DataCompanyAnnualReportShareholderGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportShareholderService;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportShareholderDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业年报股东 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:37
 */
@Component
@Validated
public class DataCompanyAnnualReportShareholderDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAnnualReportShareholderGateway dataCompanyAnnualReportShareholderGateway;
	private IDataCompanyAnnualReportShareholderService iDataCompanyAnnualReportShareholderService;

	/**
	 * 执行 企业年报股东 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportShareholderVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyAnnualReportShareholderId dataCompanyAnnualReportShareholderId = DataCompanyAnnualReportShareholderId.of(deleteCommand.getId());
		DataCompanyAnnualReportShareholder byId = dataCompanyAnnualReportShareholderGateway.getById(dataCompanyAnnualReportShareholderId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyAnnualReportShareholderGateway.delete(dataCompanyAnnualReportShareholderId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyAnnualReportShareholderAppStructMapping.instance.toDataCompanyAnnualReportShareholderVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyAnnualReportShareholderGateway
	 */
	@Autowired
	public void setDataCompanyAnnualReportShareholderGateway(DataCompanyAnnualReportShareholderGateway dataCompanyAnnualReportShareholderGateway) {
		this.dataCompanyAnnualReportShareholderGateway = dataCompanyAnnualReportShareholderGateway;
	}
	@Autowired
	public void setIDataCompanyAnnualReportShareholderService(IDataCompanyAnnualReportShareholderService iDataCompanyAnnualReportShareholderService) {
		this.iDataCompanyAnnualReportShareholderService = iDataCompanyAnnualReportShareholderService;
	}
}
