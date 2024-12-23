package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.app.company.structmapping.DataCompanyMd5AppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyMd5VO;
import com.particle.data.domain.company.DataCompanyMd5;
import com.particle.data.domain.company.DataCompanyMd5Id;
import com.particle.data.domain.company.gateway.DataCompanyMd5Gateway;
import com.particle.data.infrastructure.company.service.IDataCompanyMd5Service;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业md5 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:59
 */
@Component
@Validated
public class DataCompanyMd5DeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyMd5Gateway dataCompanyMd5Gateway;
	private IDataCompanyMd5Service iDataCompanyMd5Service;

	/**
	 * 执行 企业md5 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyMd5VO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyMd5Id dataCompanyMd5Id = DataCompanyMd5Id.of(deleteCommand.getId());
		DataCompanyMd5 byId = dataCompanyMd5Gateway.getById(dataCompanyMd5Id);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyMd5Gateway.delete(dataCompanyMd5Id,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyMd5AppStructMapping.instance.toDataCompanyMd5VO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


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
