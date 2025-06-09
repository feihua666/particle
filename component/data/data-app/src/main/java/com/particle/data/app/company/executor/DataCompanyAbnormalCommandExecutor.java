package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyAbnormalGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyAbnormalService;
import com.particle.data.infrastructure.company.dos.DataCompanyAbnormalDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业经营异常 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-05-29 10:47:31
 */
@Component
@Validated
public class DataCompanyAbnormalCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAbnormalGateway dataCompanyAbnormalGateway;
	private IDataCompanyAbnormalService iDataCompanyAbnormalService;
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
