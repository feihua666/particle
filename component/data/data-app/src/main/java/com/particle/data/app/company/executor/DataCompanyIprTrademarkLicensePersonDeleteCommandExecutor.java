package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyIprTrademarkLicensePersonAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkLicensePersonVO;
import com.particle.data.domain.company.DataCompanyIprTrademarkLicensePerson;
import com.particle.data.domain.company.DataCompanyIprTrademarkLicensePersonId;
import com.particle.data.domain.company.gateway.DataCompanyIprTrademarkLicensePersonGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkLicensePersonService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkLicensePersonDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权商标许可人 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:22
 */
@Component
@Validated
public class DataCompanyIprTrademarkLicensePersonDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprTrademarkLicensePersonGateway dataCompanyIprTrademarkLicensePersonGateway;
	private IDataCompanyIprTrademarkLicensePersonService iDataCompanyIprTrademarkLicensePersonService;

	/**
	 * 执行 企业知识产权商标许可人 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkLicensePersonVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyIprTrademarkLicensePersonId dataCompanyIprTrademarkLicensePersonId = DataCompanyIprTrademarkLicensePersonId.of(deleteCommand.getId());
		DataCompanyIprTrademarkLicensePerson byId = dataCompanyIprTrademarkLicensePersonGateway.getById(dataCompanyIprTrademarkLicensePersonId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyIprTrademarkLicensePersonGateway.delete(dataCompanyIprTrademarkLicensePersonId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyIprTrademarkLicensePersonAppStructMapping.instance.toDataCompanyIprTrademarkLicensePersonVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyIprTrademarkLicensePersonGateway
	 */
	@Autowired
	public void setDataCompanyIprTrademarkLicensePersonGateway(DataCompanyIprTrademarkLicensePersonGateway dataCompanyIprTrademarkLicensePersonGateway) {
		this.dataCompanyIprTrademarkLicensePersonGateway = dataCompanyIprTrademarkLicensePersonGateway;
	}
	@Autowired
	public void setIDataCompanyIprTrademarkLicensePersonService(IDataCompanyIprTrademarkLicensePersonService iDataCompanyIprTrademarkLicensePersonService) {
		this.iDataCompanyIprTrademarkLicensePersonService = iDataCompanyIprTrademarkLicensePersonService;
	}
}
