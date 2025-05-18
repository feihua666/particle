package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanySeriousIllegalGateway;
import com.particle.data.infrastructure.company.service.IDataCompanySeriousIllegalService;
import com.particle.data.infrastructure.company.dos.DataCompanySeriousIllegalDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业严重违法 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:45
 */
@Component
@Validated
public class DataCompanySeriousIllegalCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanySeriousIllegalGateway dataCompanySeriousIllegalGateway;
	private IDataCompanySeriousIllegalService iDataCompanySeriousIllegalService;
	/**
	 * 注入使用set方法
	 * @param dataCompanySeriousIllegalGateway
	 */
	@Autowired
	public void setDataCompanySeriousIllegalGateway(DataCompanySeriousIllegalGateway dataCompanySeriousIllegalGateway) {
		this.dataCompanySeriousIllegalGateway = dataCompanySeriousIllegalGateway;
	}
	@Autowired
	public void setIDataCompanySeriousIllegalService(IDataCompanySeriousIllegalService iDataCompanySeriousIllegalService) {
		this.iDataCompanySeriousIllegalService = iDataCompanySeriousIllegalService;
	}
}
