package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyPunishmentGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyPunishmentService;
import com.particle.data.infrastructure.company.dos.DataCompanyPunishmentDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业行政处罚 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:37
 */
@Component
@Validated
public class DataCompanyPunishmentCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyPunishmentGateway dataCompanyPunishmentGateway;
	private IDataCompanyPunishmentService iDataCompanyPunishmentService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyPunishmentGateway
	 */
	@Autowired
	public void setDataCompanyPunishmentGateway(DataCompanyPunishmentGateway dataCompanyPunishmentGateway) {
		this.dataCompanyPunishmentGateway = dataCompanyPunishmentGateway;
	}
	@Autowired
	public void setIDataCompanyPunishmentService(IDataCompanyPunishmentService iDataCompanyPunishmentService) {
		this.iDataCompanyPunishmentService = iDataCompanyPunishmentService;
	}
}
