package com.particle.dataquery.app.provider.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.dataquery.app.provider.structmapping.DataQueryProviderAppStructMapping;
import com.particle.dataquery.client.provider.dto.data.DataQueryProviderVO;
import com.particle.dataquery.domain.provider.DataQueryProvider;
import com.particle.dataquery.domain.provider.DataQueryProviderId;
import com.particle.dataquery.domain.provider.gateway.DataQueryProviderGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 数据查询供应商 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-03-03 19:19:55
 */
@Component
@Validated
public class DataQueryProviderDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataQueryProviderGateway dataQueryProviderGateway;

	/**
	 * 执行 数据查询供应商 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataQueryProviderVO> execute(@Valid IdCommand deleteCommand) {
		DataQueryProviderId dataQueryProviderId = DataQueryProviderId.of(deleteCommand.getId());
		DataQueryProvider byId = dataQueryProviderGateway.getById(dataQueryProviderId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataQueryProviderGateway.delete(dataQueryProviderId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataQueryProviderAppStructMapping.instance.toDataQueryProviderVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param dataQueryProviderGateway
	 */
	@Autowired
	public void setDataQueryProviderGateway(DataQueryProviderGateway dataQueryProviderGateway) {
		this.dataQueryProviderGateway = dataQueryProviderGateway;
	}
}
