package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyIprWorkCopyrightAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyIprWorkCopyrightVO;
import com.particle.data.domain.company.DataCompanyIprWorkCopyright;
import com.particle.data.domain.company.DataCompanyIprWorkCopyrightId;
import com.particle.data.domain.company.gateway.DataCompanyIprWorkCopyrightGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprWorkCopyrightService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprWorkCopyrightDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权作品著作 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:45
 */
@Component
@Validated
public class DataCompanyIprWorkCopyrightDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprWorkCopyrightGateway dataCompanyIprWorkCopyrightGateway;
	private IDataCompanyIprWorkCopyrightService iDataCompanyIprWorkCopyrightService;

	/**
	 * 执行 企业知识产权作品著作 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprWorkCopyrightVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyIprWorkCopyrightId dataCompanyIprWorkCopyrightId = DataCompanyIprWorkCopyrightId.of(deleteCommand.getId());
		DataCompanyIprWorkCopyright byId = dataCompanyIprWorkCopyrightGateway.getById(dataCompanyIprWorkCopyrightId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyIprWorkCopyrightGateway.delete(dataCompanyIprWorkCopyrightId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyIprWorkCopyrightAppStructMapping.instance.toDataCompanyIprWorkCopyrightVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


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
