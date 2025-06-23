package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyIprGeograAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyIprGeograVO;
import com.particle.data.domain.company.DataCompanyIprGeogra;
import com.particle.data.domain.company.DataCompanyIprGeograId;
import com.particle.data.domain.company.gateway.DataCompanyIprGeograGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprGeograService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprGeograDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权地理标识 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:33
 */
@Component
@Validated
public class DataCompanyIprGeograDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprGeograGateway dataCompanyIprGeograGateway;
	private IDataCompanyIprGeograService iDataCompanyIprGeograService;

	/**
	 * 执行 企业知识产权地理标识 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprGeograVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyIprGeograId dataCompanyIprGeograId = DataCompanyIprGeograId.of(deleteCommand.getId());
		DataCompanyIprGeogra byId = dataCompanyIprGeograGateway.getById(dataCompanyIprGeograId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyIprGeograGateway.delete(dataCompanyIprGeograId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyIprGeograAppStructMapping.instance.toDataCompanyIprGeograVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyIprGeograGateway
	 */
	@Autowired
	public void setDataCompanyIprGeograGateway(DataCompanyIprGeograGateway dataCompanyIprGeograGateway) {
		this.dataCompanyIprGeograGateway = dataCompanyIprGeograGateway;
	}
	@Autowired
	public void setIDataCompanyIprGeograService(IDataCompanyIprGeograService iDataCompanyIprGeograService) {
		this.iDataCompanyIprGeograService = iDataCompanyIprGeograService;
	}
}
