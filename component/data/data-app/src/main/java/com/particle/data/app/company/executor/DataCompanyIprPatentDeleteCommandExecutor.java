package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyIprPatentAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentVO;
import com.particle.data.domain.company.DataCompanyIprPatent;
import com.particle.data.domain.company.DataCompanyIprPatentId;
import com.particle.data.domain.company.gateway.DataCompanyIprPatentGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权专利 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:39:48
 */
@Component
@Validated
public class DataCompanyIprPatentDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentGateway dataCompanyIprPatentGateway;
	private IDataCompanyIprPatentService iDataCompanyIprPatentService;

	/**
	 * 执行 企业知识产权专利 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyIprPatentId dataCompanyIprPatentId = DataCompanyIprPatentId.of(deleteCommand.getId());
		DataCompanyIprPatent byId = dataCompanyIprPatentGateway.getById(dataCompanyIprPatentId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyIprPatentGateway.delete(dataCompanyIprPatentId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyIprPatentAppStructMapping.instance.toDataCompanyIprPatentVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPatentGateway
	 */
	@Autowired
	public void setDataCompanyIprPatentGateway(DataCompanyIprPatentGateway dataCompanyIprPatentGateway) {
		this.dataCompanyIprPatentGateway = dataCompanyIprPatentGateway;
	}
	@Autowired
	public void setIDataCompanyIprPatentService(IDataCompanyIprPatentService iDataCompanyIprPatentService) {
		this.iDataCompanyIprPatentService = iDataCompanyIprPatentService;
	}
}
