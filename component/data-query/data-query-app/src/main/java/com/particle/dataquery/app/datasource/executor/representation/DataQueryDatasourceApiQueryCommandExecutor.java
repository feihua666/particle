package com.particle.dataquery.app.datasource.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dataquery.app.datasource.structmapping.DataQueryDatasourceApiAppStructMapping;
import com.particle.dataquery.client.datasource.dto.command.representation.DataQueryDatasourceApiPageQueryCommand;
import com.particle.dataquery.client.datasource.dto.command.representation.DataQueryDatasourceApiQueryListCommand;
import com.particle.dataquery.client.datasource.dto.data.DataQueryDatasourceApiVO;
import com.particle.dataquery.infrastructure.datasource.dos.DataQueryDatasourceApiDO;
import com.particle.dataquery.infrastructure.datasource.service.IDataQueryDatasourceApiService;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

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
public class DataQueryDatasourceApiQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataQueryDatasourceApiService iDataQueryDatasourceApiService;

	/**
	 * 执行 数据查询数据源接口 列表查询指令
	 * @param dataQueryDatasourceApiQueryListCommand
	 * @return
	 */
	public MultiResponse<DataQueryDatasourceApiVO> execute(@Valid DataQueryDatasourceApiQueryListCommand dataQueryDatasourceApiQueryListCommand) {
		List<DataQueryDatasourceApiDO> dataQueryDatasourceApiDO = iDataQueryDatasourceApiService.list(dataQueryDatasourceApiQueryListCommand);
		List<DataQueryDatasourceApiVO> dataQueryDatasourceApiVOs = DataQueryDatasourceApiAppStructMapping.instance.dataQueryDatasourceApiDOsToDataQueryDatasourceApiVOs(dataQueryDatasourceApiDO);
		return MultiResponse.of(dataQueryDatasourceApiVOs);
	}
	/**
	 * 执行 数据查询数据源接口 分页查询指令
	 * @param dataQueryDatasourceApiPageQueryCommand
	 * @return
	 */
	public PageResponse<DataQueryDatasourceApiVO> execute(@Valid DataQueryDatasourceApiPageQueryCommand dataQueryDatasourceApiPageQueryCommand) {
		Page<DataQueryDatasourceApiDO> page = iDataQueryDatasourceApiService.listPage(dataQueryDatasourceApiPageQueryCommand);
		return DataQueryDatasourceApiAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 数据查询数据源接口 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataQueryDatasourceApiVO> executeDetail(IdCommand detailCommand) {
		DataQueryDatasourceApiDO byId = iDataQueryDatasourceApiService.getById(detailCommand.getId());
		DataQueryDatasourceApiVO dataQueryDatasourceApiVO = DataQueryDatasourceApiAppStructMapping.instance.dataQueryDatasourceApiDOToDataQueryDatasourceApiVO(byId);
		return SingleResponse.of(dataQueryDatasourceApiVO);
	}
	/**
	 * 执行 数据查询数据源接口 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataQueryDatasourceApiVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataQueryDatasourceApiDO byId = iDataQueryDatasourceApiService.getById(detailForUpdateCommand.getId());
		DataQueryDatasourceApiVO dataQueryDatasourceApiVO = DataQueryDatasourceApiAppStructMapping.instance.dataQueryDatasourceApiDOToDataQueryDatasourceApiVO(byId);
		return SingleResponse.of(dataQueryDatasourceApiVO);
	}

	@Autowired
	public void setIDataQueryDatasourceApiService(IDataQueryDatasourceApiService iDataQueryDatasourceApiService) {
		this.iDataQueryDatasourceApiService = iDataQueryDatasourceApiService;
	}
}
