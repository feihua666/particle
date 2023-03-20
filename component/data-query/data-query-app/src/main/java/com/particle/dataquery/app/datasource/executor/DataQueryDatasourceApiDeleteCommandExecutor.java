package com.particle.dataquery.app.datasource.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.dataquery.app.datasource.structmapping.DataQueryDatasourceApiAppStructMapping;
import com.particle.dataquery.client.datasource.dto.data.DataQueryDatasourceApiVO;
import com.particle.dataquery.domain.datasource.DataQueryDatasourceApi;
import com.particle.dataquery.domain.datasource.DataQueryDatasourceApiId;
import com.particle.dataquery.domain.datasource.gateway.DataQueryDatasourceApiGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 数据查询数据源接口 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-03-14 22:21:40
 */
@Component
@Validated
public class DataQueryDatasourceApiDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataQueryDatasourceApiGateway dataQueryDatasourceApiGateway;

	/**
	 * 执行 数据查询数据源接口 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataQueryDatasourceApiVO> execute(@Valid IdCommand deleteCommand) {
		DataQueryDatasourceApiId dataQueryDatasourceApiId = DataQueryDatasourceApiId.of(deleteCommand.getId());
		DataQueryDatasourceApi byId = dataQueryDatasourceApiGateway.getById(dataQueryDatasourceApiId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataQueryDatasourceApiGateway.delete(dataQueryDatasourceApiId);
		if (delete) {
			return SingleResponse.of(DataQueryDatasourceApiAppStructMapping.instance.toDataQueryDatasourceApiVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param dataQueryDatasourceApiGateway
	 */
	@Autowired
	public void setDataQueryDatasourceApiGateway(DataQueryDatasourceApiGateway dataQueryDatasourceApiGateway) {
		this.dataQueryDatasourceApiGateway = dataQueryDatasourceApiGateway;
	}
}
