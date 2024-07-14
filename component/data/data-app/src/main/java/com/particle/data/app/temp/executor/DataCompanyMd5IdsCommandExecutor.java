package com.particle.data.app.temp.executor;

import com.particle.data.domain.temp.gateway.DataCompanyMd5IdsGateway;
import com.particle.data.infrastructure.temp.service.IDataCompanyMd5IdsService;
import com.particle.data.infrastructure.temp.dos.DataCompanyMd5IdsDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 企业md5ids 指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:24:11
 */
@Component
@Validated
public class DataCompanyMd5IdsCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyMd5IdsGateway dataCompanyMd5IdsGateway;
	private IDataCompanyMd5IdsService iDataCompanyMd5IdsService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyMd5IdsGateway
	 */
	@Autowired
	public void setDataCompanyMd5IdsGateway(DataCompanyMd5IdsGateway dataCompanyMd5IdsGateway) {
		this.dataCompanyMd5IdsGateway = dataCompanyMd5IdsGateway;
	}
	@Autowired
	public void setIDataCompanyMd5IdsService(IDataCompanyMd5IdsService iDataCompanyMd5IdsService) {
		this.iDataCompanyMd5IdsService = iDataCompanyMd5IdsService;
	}
}
