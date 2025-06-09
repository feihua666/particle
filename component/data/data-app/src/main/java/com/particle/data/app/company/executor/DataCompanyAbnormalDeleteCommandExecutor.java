package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyAbnormalAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyAbnormalVO;
import com.particle.data.domain.company.DataCompanyAbnormal;
import com.particle.data.domain.company.DataCompanyAbnormalId;
import com.particle.data.domain.company.gateway.DataCompanyAbnormalGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyAbnormalService;
import com.particle.data.infrastructure.company.dos.DataCompanyAbnormalDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业经营异常 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-05-29 10:47:31
 */
@Component
@Validated
public class DataCompanyAbnormalDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAbnormalGateway dataCompanyAbnormalGateway;
	private IDataCompanyAbnormalService iDataCompanyAbnormalService;

	/**
	 * 执行 企业经营异常 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAbnormalVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyAbnormalId dataCompanyAbnormalId = DataCompanyAbnormalId.of(deleteCommand.getId());
		DataCompanyAbnormal byId = dataCompanyAbnormalGateway.getById(dataCompanyAbnormalId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyAbnormalGateway.delete(dataCompanyAbnormalId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyAbnormalAppStructMapping.instance.toDataCompanyAbnormalVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyAbnormalGateway
	 */
	@Autowired
	public void setDataCompanyAbnormalGateway(DataCompanyAbnormalGateway dataCompanyAbnormalGateway) {
		this.dataCompanyAbnormalGateway = dataCompanyAbnormalGateway;
	}
	@Autowired
	public void setIDataCompanyAbnormalService(IDataCompanyAbnormalService iDataCompanyAbnormalService) {
		this.iDataCompanyAbnormalService = iDataCompanyAbnormalService;
	}
}
