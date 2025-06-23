package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyIprSoftwareCopyrightAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyIprSoftwareCopyrightVO;
import com.particle.data.domain.company.DataCompanyIprSoftwareCopyright;
import com.particle.data.domain.company.DataCompanyIprSoftwareCopyrightId;
import com.particle.data.domain.company.gateway.DataCompanyIprSoftwareCopyrightGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprSoftwareCopyrightService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprSoftwareCopyrightDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权软件著作 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:01
 */
@Component
@Validated
public class DataCompanyIprSoftwareCopyrightDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprSoftwareCopyrightGateway dataCompanyIprSoftwareCopyrightGateway;
	private IDataCompanyIprSoftwareCopyrightService iDataCompanyIprSoftwareCopyrightService;

	/**
	 * 执行 企业知识产权软件著作 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprSoftwareCopyrightVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyIprSoftwareCopyrightId dataCompanyIprSoftwareCopyrightId = DataCompanyIprSoftwareCopyrightId.of(deleteCommand.getId());
		DataCompanyIprSoftwareCopyright byId = dataCompanyIprSoftwareCopyrightGateway.getById(dataCompanyIprSoftwareCopyrightId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyIprSoftwareCopyrightGateway.delete(dataCompanyIprSoftwareCopyrightId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyIprSoftwareCopyrightAppStructMapping.instance.toDataCompanyIprSoftwareCopyrightVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyIprSoftwareCopyrightGateway
	 */
	@Autowired
	public void setDataCompanyIprSoftwareCopyrightGateway(DataCompanyIprSoftwareCopyrightGateway dataCompanyIprSoftwareCopyrightGateway) {
		this.dataCompanyIprSoftwareCopyrightGateway = dataCompanyIprSoftwareCopyrightGateway;
	}
	@Autowired
	public void setIDataCompanyIprSoftwareCopyrightService(IDataCompanyIprSoftwareCopyrightService iDataCompanyIprSoftwareCopyrightService) {
		this.iDataCompanyIprSoftwareCopyrightService = iDataCompanyIprSoftwareCopyrightService;
	}
}
