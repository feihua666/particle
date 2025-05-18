package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyIprPatentStatisticGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentStatisticService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentStatisticDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权专利统计 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:36
 */
@Component
@Validated
public class DataCompanyIprPatentStatisticCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentStatisticGateway dataCompanyIprPatentStatisticGateway;
	private IDataCompanyIprPatentStatisticService iDataCompanyIprPatentStatisticService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPatentStatisticGateway
	 */
	@Autowired
	public void setDataCompanyIprPatentStatisticGateway(DataCompanyIprPatentStatisticGateway dataCompanyIprPatentStatisticGateway) {
		this.dataCompanyIprPatentStatisticGateway = dataCompanyIprPatentStatisticGateway;
	}
	@Autowired
	public void setIDataCompanyIprPatentStatisticService(IDataCompanyIprPatentStatisticService iDataCompanyIprPatentStatisticService) {
		this.iDataCompanyIprPatentStatisticService = iDataCompanyIprPatentStatisticService;
	}
}
