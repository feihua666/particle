package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyAdministrativeLicenseAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyAdministrativeLicenseVO;
import com.particle.data.domain.company.DataCompanyAdministrativeLicense;
import com.particle.data.domain.company.DataCompanyAdministrativeLicenseId;
import com.particle.data.domain.company.gateway.DataCompanyAdministrativeLicenseGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyAdministrativeLicenseService;
import com.particle.data.infrastructure.company.dos.DataCompanyAdministrativeLicenseDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业行政许可 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:17:53
 */
@Component
@Validated
public class DataCompanyAdministrativeLicenseDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAdministrativeLicenseGateway dataCompanyAdministrativeLicenseGateway;
	private IDataCompanyAdministrativeLicenseService iDataCompanyAdministrativeLicenseService;

	/**
	 * 执行 企业行政许可 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAdministrativeLicenseVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyAdministrativeLicenseId dataCompanyAdministrativeLicenseId = DataCompanyAdministrativeLicenseId.of(deleteCommand.getId());
		DataCompanyAdministrativeLicense byId = dataCompanyAdministrativeLicenseGateway.getById(dataCompanyAdministrativeLicenseId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyAdministrativeLicenseGateway.delete(dataCompanyAdministrativeLicenseId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyAdministrativeLicenseAppStructMapping.instance.toDataCompanyAdministrativeLicenseVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyAdministrativeLicenseGateway
	 */
	@Autowired
	public void setDataCompanyAdministrativeLicenseGateway(DataCompanyAdministrativeLicenseGateway dataCompanyAdministrativeLicenseGateway) {
		this.dataCompanyAdministrativeLicenseGateway = dataCompanyAdministrativeLicenseGateway;
	}
	@Autowired
	public void setIDataCompanyAdministrativeLicenseService(IDataCompanyAdministrativeLicenseService iDataCompanyAdministrativeLicenseService) {
		this.iDataCompanyAdministrativeLicenseService = iDataCompanyAdministrativeLicenseService;
	}
}
