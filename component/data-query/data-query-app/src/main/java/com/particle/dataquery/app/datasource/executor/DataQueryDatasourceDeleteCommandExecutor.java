package com.particle.dataquery.app.datasource.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.dataquery.app.datasource.structmapping.DataQueryDatasourceAppStructMapping;
import com.particle.dataquery.client.datasource.dto.data.DataQueryDatasourceVO;
import com.particle.dataquery.domain.datasource.DataQueryDatasource;
import com.particle.dataquery.domain.datasource.DataQueryDatasourceId;
import com.particle.dataquery.domain.datasource.gateway.DataQueryDatasourceGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 数据查询数据源 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-03-08 14:29:00
 */
@Component
@Validated
public class DataQueryDatasourceDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataQueryDatasourceGateway dataQueryDatasourceGateway;

	/**
	 * 执行 数据查询数据源 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataQueryDatasourceVO> execute(@Valid IdCommand deleteCommand) {
		DataQueryDatasourceId dataQueryDatasourceId = DataQueryDatasourceId.of(deleteCommand.getId());
		DataQueryDatasource byId = dataQueryDatasourceGateway.getById(dataQueryDatasourceId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataQueryDatasourceGateway.delete(dataQueryDatasourceId);
		if (delete) {
			return SingleResponse.of(DataQueryDatasourceAppStructMapping.instance.toDataQueryDatasourceVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param dataQueryDatasourceGateway
	 */
	@Autowired
	public void setDataQueryDatasourceGateway(DataQueryDatasourceGateway dataQueryDatasourceGateway) {
		this.dataQueryDatasourceGateway = dataQueryDatasourceGateway;
	}
}
