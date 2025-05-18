package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyPunishmentAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyPunishmentVO;
import com.particle.data.domain.company.DataCompanyPunishment;
import com.particle.data.domain.company.DataCompanyPunishmentId;
import com.particle.data.domain.company.gateway.DataCompanyPunishmentGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyPunishmentService;
import com.particle.data.infrastructure.company.dos.DataCompanyPunishmentDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业行政处罚 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:37
 */
@Component
@Validated
public class DataCompanyPunishmentDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyPunishmentGateway dataCompanyPunishmentGateway;
	private IDataCompanyPunishmentService iDataCompanyPunishmentService;

	/**
	 * 执行 企业行政处罚 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyPunishmentVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyPunishmentId dataCompanyPunishmentId = DataCompanyPunishmentId.of(deleteCommand.getId());
		DataCompanyPunishment byId = dataCompanyPunishmentGateway.getById(dataCompanyPunishmentId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyPunishmentGateway.delete(dataCompanyPunishmentId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyPunishmentAppStructMapping.instance.toDataCompanyPunishmentVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


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
