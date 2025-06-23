package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyIprPlantVarietyChangeAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyIprPlantVarietyChangeVO;
import com.particle.data.domain.company.DataCompanyIprPlantVarietyChange;
import com.particle.data.domain.company.DataCompanyIprPlantVarietyChangeId;
import com.particle.data.domain.company.gateway.DataCompanyIprPlantVarietyChangeGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPlantVarietyChangeService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPlantVarietyChangeDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权植物新品种变更信息 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:52
 */
@Component
@Validated
public class DataCompanyIprPlantVarietyChangeDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPlantVarietyChangeGateway dataCompanyIprPlantVarietyChangeGateway;
	private IDataCompanyIprPlantVarietyChangeService iDataCompanyIprPlantVarietyChangeService;

	/**
	 * 执行 企业知识产权植物新品种变更信息 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPlantVarietyChangeVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyIprPlantVarietyChangeId dataCompanyIprPlantVarietyChangeId = DataCompanyIprPlantVarietyChangeId.of(deleteCommand.getId());
		DataCompanyIprPlantVarietyChange byId = dataCompanyIprPlantVarietyChangeGateway.getById(dataCompanyIprPlantVarietyChangeId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyIprPlantVarietyChangeGateway.delete(dataCompanyIprPlantVarietyChangeId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyIprPlantVarietyChangeAppStructMapping.instance.toDataCompanyIprPlantVarietyChangeVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPlantVarietyChangeGateway
	 */
	@Autowired
	public void setDataCompanyIprPlantVarietyChangeGateway(DataCompanyIprPlantVarietyChangeGateway dataCompanyIprPlantVarietyChangeGateway) {
		this.dataCompanyIprPlantVarietyChangeGateway = dataCompanyIprPlantVarietyChangeGateway;
	}
	@Autowired
	public void setIDataCompanyIprPlantVarietyChangeService(IDataCompanyIprPlantVarietyChangeService iDataCompanyIprPlantVarietyChangeService) {
		this.iDataCompanyIprPlantVarietyChangeService = iDataCompanyIprPlantVarietyChangeService;
	}
}
