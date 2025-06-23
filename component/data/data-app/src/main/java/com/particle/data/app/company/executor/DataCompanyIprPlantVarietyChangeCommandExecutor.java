package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyIprPlantVarietyChangeGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPlantVarietyChangeService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPlantVarietyChangeDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权植物新品种变更信息 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:52
 */
@Component
@Validated
public class DataCompanyIprPlantVarietyChangeCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPlantVarietyChangeGateway dataCompanyIprPlantVarietyChangeGateway;
	private IDataCompanyIprPlantVarietyChangeService iDataCompanyIprPlantVarietyChangeService;
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
