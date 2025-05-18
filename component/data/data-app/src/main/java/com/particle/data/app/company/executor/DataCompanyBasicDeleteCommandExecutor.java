package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyBasicAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyBasicVO;
import com.particle.data.domain.company.DataCompanyBasic;
import com.particle.data.domain.company.DataCompanyBasicId;
import com.particle.data.domain.company.gateway.DataCompanyBasicGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyBasicService;
import com.particle.data.infrastructure.company.dos.DataCompanyBasicDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业基本信息 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Component
@Validated
public class DataCompanyBasicDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyBasicGateway dataCompanyBasicGateway;
	private IDataCompanyBasicService iDataCompanyBasicService;

	/**
	 * 执行 企业基本信息 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyBasicVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyBasicId dataCompanyBasicId = DataCompanyBasicId.of(deleteCommand.getId());
		DataCompanyBasic byId = dataCompanyBasicGateway.getById(dataCompanyBasicId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyBasicGateway.delete(dataCompanyBasicId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyBasicAppStructMapping.instance.toDataCompanyBasicVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyBasicGateway
	 */
	@Autowired
	public void setDataCompanyBasicGateway(DataCompanyBasicGateway dataCompanyBasicGateway) {
		this.dataCompanyBasicGateway = dataCompanyBasicGateway;
	}
	@Autowired
	public void setIDataCompanyBasicService(IDataCompanyBasicService iDataCompanyBasicService) {
		this.iDataCompanyBasicService = iDataCompanyBasicService;
	}
}
