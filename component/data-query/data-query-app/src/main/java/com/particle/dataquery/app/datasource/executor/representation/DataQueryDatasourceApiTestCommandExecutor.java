package com.particle.dataquery.app.datasource.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dataquery.app.datasource.structmapping.DataQueryDatasourceApiAppStructMapping;
import com.particle.dataquery.client.datasource.dto.command.DataQueryDatasourceApiTestCommand;
import com.particle.dataquery.client.datasource.dto.command.representation.DataQueryDatasourceApiPageQueryCommand;
import com.particle.dataquery.client.datasource.dto.command.representation.DataQueryDatasourceApiQueryListCommand;
import com.particle.dataquery.client.datasource.dto.data.DataQueryDatasourceApiVO;
import com.particle.dataquery.domain.datasource.DataQueryDatasource;
import com.particle.dataquery.domain.datasource.DataQueryDatasourceApi;
import com.particle.dataquery.domain.datasource.DataQueryDatasourceApiId;
import com.particle.dataquery.domain.datasource.DataQueryDatasourceId;
import com.particle.dataquery.domain.datasource.gateway.DataQueryDatasourceApiGateway;
import com.particle.dataquery.domain.datasource.gateway.DataQueryDatasourceGateway;
import com.particle.dataquery.domain.datasource.gateway.DatasourceApiQueryGateway;
import com.particle.dataquery.infrastructure.datasource.dos.DataQueryDatasourceApiDO;
import com.particle.dataquery.infrastructure.datasource.service.IDataQueryDatasourceApiService;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 数据查询数据源接口 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-03-14 22:21:40
 */
@Component
@Validated
public class DataQueryDatasourceApiTestCommandExecutor extends AbstractBaseQueryExecutor {

	private DataQueryDatasourceGateway dataQueryDatasourceGateway;
	private DataQueryDatasourceApiGateway dataQueryDatasourceApiGateway;
	private DatasourceApiQueryGateway datasourceApiQueryGateway;

	/**
	 * 接口测试
	 * @param dataQueryDatasourceApiTestCommand
	 * @return
	 */
	public Object test(DataQueryDatasourceApiTestCommand dataQueryDatasourceApiTestCommand) {
		DataQueryDatasourceApi dataQueryDatasourceApi = dataQueryDatasourceApiGateway.getById(DataQueryDatasourceApiId.of(dataQueryDatasourceApiTestCommand.getDatasourceApiId()));
		Assert.notNull(dataQueryDatasourceApi,"接口不存在");
		DataQueryDatasource dataQueryDatasource = dataQueryDatasourceGateway.getById(DataQueryDatasourceId.of(dataQueryDatasourceApi.getDataQueryDatasourceId()));
		Object query = datasourceApiQueryGateway.query(dataQueryDatasource, dataQueryDatasourceApi, dataQueryDatasourceApiTestCommand.getParam());
		return query;
	}

	@Autowired
	public void setDataQueryDatasourceGateway(DataQueryDatasourceGateway dataQueryDatasourceGateway) {
		this.dataQueryDatasourceGateway = dataQueryDatasourceGateway;
	}

	@Autowired
	public void setDataQueryDatasourceApiGateway(DataQueryDatasourceApiGateway dataQueryDatasourceApiGateway) {
		this.dataQueryDatasourceApiGateway = dataQueryDatasourceApiGateway;
	}

	@Autowired
	public void setDatasourceApiQueryGateway(DatasourceApiQueryGateway datasourceApiQueryGateway) {
		this.datasourceApiQueryGateway = datasourceApiQueryGateway;
	}
}
