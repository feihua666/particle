package com.particle.data.app.temp.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.app.temp.structmapping.DataCompanyMd5IdsAppStructMapping;
import com.particle.data.client.temp.dto.data.DataCompanyMd5IdsVO;
import com.particle.data.domain.temp.DataCompanyMd5Ids;
import com.particle.data.domain.temp.DataCompanyMd5IdsId;
import com.particle.data.domain.temp.gateway.DataCompanyMd5IdsGateway;
import com.particle.data.infrastructure.temp.service.IDataCompanyMd5IdsService;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业md5ids 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:24:11
 */
@Component
@Validated
public class DataCompanyMd5IdsDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyMd5IdsGateway dataCompanyMd5IdsGateway;
	private IDataCompanyMd5IdsService iDataCompanyMd5IdsService;

	/**
	 * 执行 企业md5ids 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyMd5IdsVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyMd5IdsId dataCompanyMd5IdsId = DataCompanyMd5IdsId.of(deleteCommand.getId());
		DataCompanyMd5Ids byId = dataCompanyMd5IdsGateway.getById(dataCompanyMd5IdsId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyMd5IdsGateway.delete(dataCompanyMd5IdsId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyMd5IdsAppStructMapping.instance.toDataCompanyMd5IdsVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyMd5IdsGateway
	 */
	@Autowired
	public void setDataCompanyMd5IdsGateway(DataCompanyMd5IdsGateway dataCompanyMd5IdsGateway) {
		this.dataCompanyMd5IdsGateway = dataCompanyMd5IdsGateway;
	}
	@Autowired
	public void setIDataCompanyMd5IdsService(IDataCompanyMd5IdsService iDataCompanyMd5IdsService) {
		this.iDataCompanyMd5IdsService = iDataCompanyMd5IdsService;
	}
}
