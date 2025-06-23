package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyIprTrademarkLicenseAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkLicenseVO;
import com.particle.data.domain.company.DataCompanyIprTrademarkLicense;
import com.particle.data.domain.company.DataCompanyIprTrademarkLicenseId;
import com.particle.data.domain.company.gateway.DataCompanyIprTrademarkLicenseGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkLicenseService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkLicenseDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权商标许可信息 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:10
 */
@Component
@Validated
public class DataCompanyIprTrademarkLicenseDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprTrademarkLicenseGateway dataCompanyIprTrademarkLicenseGateway;
	private IDataCompanyIprTrademarkLicenseService iDataCompanyIprTrademarkLicenseService;

	/**
	 * 执行 企业知识产权商标许可信息 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkLicenseVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyIprTrademarkLicenseId dataCompanyIprTrademarkLicenseId = DataCompanyIprTrademarkLicenseId.of(deleteCommand.getId());
		DataCompanyIprTrademarkLicense byId = dataCompanyIprTrademarkLicenseGateway.getById(dataCompanyIprTrademarkLicenseId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyIprTrademarkLicenseGateway.delete(dataCompanyIprTrademarkLicenseId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyIprTrademarkLicenseAppStructMapping.instance.toDataCompanyIprTrademarkLicenseVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyIprTrademarkLicenseGateway
	 */
	@Autowired
	public void setDataCompanyIprTrademarkLicenseGateway(DataCompanyIprTrademarkLicenseGateway dataCompanyIprTrademarkLicenseGateway) {
		this.dataCompanyIprTrademarkLicenseGateway = dataCompanyIprTrademarkLicenseGateway;
	}
	@Autowired
	public void setIDataCompanyIprTrademarkLicenseService(IDataCompanyIprTrademarkLicenseService iDataCompanyIprTrademarkLicenseService) {
		this.iDataCompanyIprTrademarkLicenseService = iDataCompanyIprTrademarkLicenseService;
	}
}
