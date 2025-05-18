package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyRestrictHighConsumeAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyRestrictHighConsumeVO;
import com.particle.data.domain.company.DataCompanyRestrictHighConsume;
import com.particle.data.domain.company.DataCompanyRestrictHighConsumeId;
import com.particle.data.domain.company.gateway.DataCompanyRestrictHighConsumeGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyRestrictHighConsumeService;
import com.particle.data.infrastructure.company.dos.DataCompanyRestrictHighConsumeDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业限制高消费 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:19
 */
@Component
@Validated
public class DataCompanyRestrictHighConsumeDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyRestrictHighConsumeGateway dataCompanyRestrictHighConsumeGateway;
	private IDataCompanyRestrictHighConsumeService iDataCompanyRestrictHighConsumeService;

	/**
	 * 执行 企业限制高消费 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyRestrictHighConsumeVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyRestrictHighConsumeId dataCompanyRestrictHighConsumeId = DataCompanyRestrictHighConsumeId.of(deleteCommand.getId());
		DataCompanyRestrictHighConsume byId = dataCompanyRestrictHighConsumeGateway.getById(dataCompanyRestrictHighConsumeId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyRestrictHighConsumeGateway.delete(dataCompanyRestrictHighConsumeId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyRestrictHighConsumeAppStructMapping.instance.toDataCompanyRestrictHighConsumeVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyRestrictHighConsumeGateway
	 */
	@Autowired
	public void setDataCompanyRestrictHighConsumeGateway(DataCompanyRestrictHighConsumeGateway dataCompanyRestrictHighConsumeGateway) {
		this.dataCompanyRestrictHighConsumeGateway = dataCompanyRestrictHighConsumeGateway;
	}
	@Autowired
	public void setIDataCompanyRestrictHighConsumeService(IDataCompanyRestrictHighConsumeService iDataCompanyRestrictHighConsumeService) {
		this.iDataCompanyRestrictHighConsumeService = iDataCompanyRestrictHighConsumeService;
	}
}
