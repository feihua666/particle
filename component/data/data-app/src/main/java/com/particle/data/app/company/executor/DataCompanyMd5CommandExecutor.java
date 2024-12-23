package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.domain.company.gateway.DataCompanyMd5Gateway;
import com.particle.data.infrastructure.company.service.IDataCompanyMd5Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业md5 指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:59
 */
@Component
@Validated
public class DataCompanyMd5CommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyMd5Gateway dataCompanyMd5Gateway;
	private IDataCompanyMd5Service iDataCompanyMd5Service;
	/**
	 * 注入使用set方法
	 * @param dataCompanyMd5Gateway
	 */
	@Autowired
	public void setDataCompanyMd5Gateway(DataCompanyMd5Gateway dataCompanyMd5Gateway) {
		this.dataCompanyMd5Gateway = dataCompanyMd5Gateway;
	}
	@Autowired
	public void setIDataCompanyMd5Service(IDataCompanyMd5Service iDataCompanyMd5Service) {
		this.iDataCompanyMd5Service = iDataCompanyMd5Service;
	}
}
