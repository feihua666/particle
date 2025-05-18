package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyIprPatentLicenseAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentLicenseVO;
import com.particle.data.domain.company.DataCompanyIprPatentLicense;
import com.particle.data.domain.company.DataCompanyIprPatentLicenseId;
import com.particle.data.domain.company.gateway.DataCompanyIprPatentLicenseGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentLicenseService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentLicenseDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权专利许可信息 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:59
 */
@Component
@Validated
public class DataCompanyIprPatentLicenseDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentLicenseGateway dataCompanyIprPatentLicenseGateway;
	private IDataCompanyIprPatentLicenseService iDataCompanyIprPatentLicenseService;

	/**
	 * 执行 企业知识产权专利许可信息 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentLicenseVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyIprPatentLicenseId dataCompanyIprPatentLicenseId = DataCompanyIprPatentLicenseId.of(deleteCommand.getId());
		DataCompanyIprPatentLicense byId = dataCompanyIprPatentLicenseGateway.getById(dataCompanyIprPatentLicenseId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyIprPatentLicenseGateway.delete(dataCompanyIprPatentLicenseId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyIprPatentLicenseAppStructMapping.instance.toDataCompanyIprPatentLicenseVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPatentLicenseGateway
	 */
	@Autowired
	public void setDataCompanyIprPatentLicenseGateway(DataCompanyIprPatentLicenseGateway dataCompanyIprPatentLicenseGateway) {
		this.dataCompanyIprPatentLicenseGateway = dataCompanyIprPatentLicenseGateway;
	}
	@Autowired
	public void setIDataCompanyIprPatentLicenseService(IDataCompanyIprPatentLicenseService iDataCompanyIprPatentLicenseService) {
		this.iDataCompanyIprPatentLicenseService = iDataCompanyIprPatentLicenseService;
	}
}
