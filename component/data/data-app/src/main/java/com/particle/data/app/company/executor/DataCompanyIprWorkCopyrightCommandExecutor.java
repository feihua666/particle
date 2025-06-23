package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyIprWorkCopyrightGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprWorkCopyrightService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprWorkCopyrightDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权作品著作 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:45
 */
@Component
@Validated
public class DataCompanyIprWorkCopyrightCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprWorkCopyrightGateway dataCompanyIprWorkCopyrightGateway;
	private IDataCompanyIprWorkCopyrightService iDataCompanyIprWorkCopyrightService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyIprWorkCopyrightGateway
	 */
	@Autowired
	public void setDataCompanyIprWorkCopyrightGateway(DataCompanyIprWorkCopyrightGateway dataCompanyIprWorkCopyrightGateway) {
		this.dataCompanyIprWorkCopyrightGateway = dataCompanyIprWorkCopyrightGateway;
	}
	@Autowired
	public void setIDataCompanyIprWorkCopyrightService(IDataCompanyIprWorkCopyrightService iDataCompanyIprWorkCopyrightService) {
		this.iDataCompanyIprWorkCopyrightService = iDataCompanyIprWorkCopyrightService;
	}
}
