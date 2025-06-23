package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyIprPlantVarietyGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPlantVarietyService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPlantVarietyDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权植物新品种 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:40
 */
@Component
@Validated
public class DataCompanyIprPlantVarietyCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPlantVarietyGateway dataCompanyIprPlantVarietyGateway;
	private IDataCompanyIprPlantVarietyService iDataCompanyIprPlantVarietyService;
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
