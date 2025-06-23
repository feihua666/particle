package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanySpotCheckAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanySpotCheckVO;
import com.particle.data.domain.company.DataCompanySpotCheck;
import com.particle.data.domain.company.DataCompanySpotCheckId;
import com.particle.data.domain.company.gateway.DataCompanySpotCheckGateway;
import com.particle.data.infrastructure.company.service.IDataCompanySpotCheckService;
import com.particle.data.infrastructure.company.dos.DataCompanySpotCheckDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业抽查检查 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:39
 */
@Component
@Validated
public class DataCompanySpotCheckDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanySpotCheckGateway dataCompanySpotCheckGateway;
	private IDataCompanySpotCheckService iDataCompanySpotCheckService;

	/**
	 * 执行 企业抽查检查 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanySpotCheckVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanySpotCheckId dataCompanySpotCheckId = DataCompanySpotCheckId.of(deleteCommand.getId());
		DataCompanySpotCheck byId = dataCompanySpotCheckGateway.getById(dataCompanySpotCheckId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanySpotCheckGateway.delete(dataCompanySpotCheckId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanySpotCheckAppStructMapping.instance.toDataCompanySpotCheckVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanySpotCheckGateway
	 */
	@Autowired
	public void setDataCompanySpotCheckGateway(DataCompanySpotCheckGateway dataCompanySpotCheckGateway) {
		this.dataCompanySpotCheckGateway = dataCompanySpotCheckGateway;
	}
	@Autowired
	public void setIDataCompanySpotCheckService(IDataCompanySpotCheckService iDataCompanySpotCheckService) {
		this.iDataCompanySpotCheckService = iDataCompanySpotCheckService;
	}
}
