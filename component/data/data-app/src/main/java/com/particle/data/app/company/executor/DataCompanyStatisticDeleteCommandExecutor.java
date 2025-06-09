package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyStatisticAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyStatisticVO;
import com.particle.data.domain.company.DataCompanyStatistic;
import com.particle.data.domain.company.DataCompanyStatisticId;
import com.particle.data.domain.company.gateway.DataCompanyStatisticGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyStatisticService;
import com.particle.data.infrastructure.company.dos.DataCompanyStatisticDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业统计 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-04 15:53:01
 */
@Component
@Validated
public class DataCompanyStatisticDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyStatisticGateway dataCompanyStatisticGateway;
	private IDataCompanyStatisticService iDataCompanyStatisticService;

	/**
	 * 执行 企业统计 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyStatisticVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyStatisticId dataCompanyStatisticId = DataCompanyStatisticId.of(deleteCommand.getId());
		DataCompanyStatistic byId = dataCompanyStatisticGateway.getById(dataCompanyStatisticId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyStatisticGateway.delete(dataCompanyStatisticId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyStatisticAppStructMapping.instance.toDataCompanyStatisticVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyStatisticGateway
	 */
	@Autowired
	public void setDataCompanyStatisticGateway(DataCompanyStatisticGateway dataCompanyStatisticGateway) {
		this.dataCompanyStatisticGateway = dataCompanyStatisticGateway;
	}
	@Autowired
	public void setIDataCompanyStatisticService(IDataCompanyStatisticService iDataCompanyStatisticService) {
		this.iDataCompanyStatisticService = iDataCompanyStatisticService;
	}
}
