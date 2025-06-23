package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyIprPlantVarietyAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyIprPlantVarietyVO;
import com.particle.data.domain.company.DataCompanyIprPlantVariety;
import com.particle.data.domain.company.DataCompanyIprPlantVarietyId;
import com.particle.data.domain.company.gateway.DataCompanyIprPlantVarietyGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPlantVarietyService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPlantVarietyDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权植物新品种 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:40
 */
@Component
@Validated
public class DataCompanyIprPlantVarietyDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPlantVarietyGateway dataCompanyIprPlantVarietyGateway;
	private IDataCompanyIprPlantVarietyService iDataCompanyIprPlantVarietyService;

	/**
	 * 执行 企业知识产权植物新品种 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPlantVarietyVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyIprPlantVarietyId dataCompanyIprPlantVarietyId = DataCompanyIprPlantVarietyId.of(deleteCommand.getId());
		DataCompanyIprPlantVariety byId = dataCompanyIprPlantVarietyGateway.getById(dataCompanyIprPlantVarietyId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyIprPlantVarietyGateway.delete(dataCompanyIprPlantVarietyId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyIprPlantVarietyAppStructMapping.instance.toDataCompanyIprPlantVarietyVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPlantVarietyGateway
	 */
	@Autowired
	public void setDataCompanyIprPlantVarietyGateway(DataCompanyIprPlantVarietyGateway dataCompanyIprPlantVarietyGateway) {
		this.dataCompanyIprPlantVarietyGateway = dataCompanyIprPlantVarietyGateway;
	}
	@Autowired
	public void setIDataCompanyIprPlantVarietyService(IDataCompanyIprPlantVarietyService iDataCompanyIprPlantVarietyService) {
		this.iDataCompanyIprPlantVarietyService = iDataCompanyIprPlantVarietyService;
	}
}
