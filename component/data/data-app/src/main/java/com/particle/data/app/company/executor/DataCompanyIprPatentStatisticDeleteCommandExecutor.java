package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyIprPatentStatisticAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentStatisticVO;
import com.particle.data.domain.company.DataCompanyIprPatentStatistic;
import com.particle.data.domain.company.DataCompanyIprPatentStatisticId;
import com.particle.data.domain.company.gateway.DataCompanyIprPatentStatisticGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentStatisticService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentStatisticDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权专利统计 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:36
 */
@Component
@Validated
public class DataCompanyIprPatentStatisticDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentStatisticGateway dataCompanyIprPatentStatisticGateway;
	private IDataCompanyIprPatentStatisticService iDataCompanyIprPatentStatisticService;

	/**
	 * 执行 企业知识产权专利统计 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentStatisticVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyIprPatentStatisticId dataCompanyIprPatentStatisticId = DataCompanyIprPatentStatisticId.of(deleteCommand.getId());
		DataCompanyIprPatentStatistic byId = dataCompanyIprPatentStatisticGateway.getById(dataCompanyIprPatentStatisticId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyIprPatentStatisticGateway.delete(dataCompanyIprPatentStatisticId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyIprPatentStatisticAppStructMapping.instance.toDataCompanyIprPatentStatisticVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


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
