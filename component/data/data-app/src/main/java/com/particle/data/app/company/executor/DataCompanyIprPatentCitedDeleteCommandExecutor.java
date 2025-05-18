package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyIprPatentCitedAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentCitedVO;
import com.particle.data.domain.company.DataCompanyIprPatentCited;
import com.particle.data.domain.company.DataCompanyIprPatentCitedId;
import com.particle.data.domain.company.gateway.DataCompanyIprPatentCitedGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentCitedService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentCitedDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权专利被引证信息 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:15
 */
@Component
@Validated
public class DataCompanyIprPatentCitedDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentCitedGateway dataCompanyIprPatentCitedGateway;
	private IDataCompanyIprPatentCitedService iDataCompanyIprPatentCitedService;

	/**
	 * 执行 企业知识产权专利被引证信息 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentCitedVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyIprPatentCitedId dataCompanyIprPatentCitedId = DataCompanyIprPatentCitedId.of(deleteCommand.getId());
		DataCompanyIprPatentCited byId = dataCompanyIprPatentCitedGateway.getById(dataCompanyIprPatentCitedId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyIprPatentCitedGateway.delete(dataCompanyIprPatentCitedId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyIprPatentCitedAppStructMapping.instance.toDataCompanyIprPatentCitedVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPatentCitedGateway
	 */
	@Autowired
	public void setDataCompanyIprPatentCitedGateway(DataCompanyIprPatentCitedGateway dataCompanyIprPatentCitedGateway) {
		this.dataCompanyIprPatentCitedGateway = dataCompanyIprPatentCitedGateway;
	}
	@Autowired
	public void setIDataCompanyIprPatentCitedService(IDataCompanyIprPatentCitedService iDataCompanyIprPatentCitedService) {
		this.iDataCompanyIprPatentCitedService = iDataCompanyIprPatentCitedService;
	}
}
