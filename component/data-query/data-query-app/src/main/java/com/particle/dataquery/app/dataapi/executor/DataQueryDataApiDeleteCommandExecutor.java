package com.particle.dataquery.app.dataapi.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.dataquery.app.dataapi.structmapping.DataQueryDataApiAppStructMapping;
import com.particle.dataquery.client.dataapi.dto.data.DataQueryDataApiVO;
import com.particle.dataquery.domain.dataapi.DataQueryDataApi;
import com.particle.dataquery.domain.dataapi.DataQueryDataApiId;
import com.particle.dataquery.domain.dataapi.gateway.DataQueryDataApiGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 数据查询数据接口 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-03-21 13:11:05
 */
@Component
@Validated
public class DataQueryDataApiDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataQueryDataApiGateway dataQueryDataApiGateway;

	/**
	 * 执行 数据查询数据接口 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataQueryDataApiVO> execute(@Valid IdCommand deleteCommand) {
		DataQueryDataApiId dataQueryDataApiId = DataQueryDataApiId.of(deleteCommand.getId());
		DataQueryDataApi byId = dataQueryDataApiGateway.getById(dataQueryDataApiId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataQueryDataApiGateway.delete(dataQueryDataApiId);
		if (delete) {
			return SingleResponse.of(DataQueryDataApiAppStructMapping.instance.toDataQueryDataApiVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param dataQueryDataApiGateway
	 */
	@Autowired
	public void setDataQueryDataApiGateway(DataQueryDataApiGateway dataQueryDataApiGateway) {
		this.dataQueryDataApiGateway = dataQueryDataApiGateway;
	}
}
