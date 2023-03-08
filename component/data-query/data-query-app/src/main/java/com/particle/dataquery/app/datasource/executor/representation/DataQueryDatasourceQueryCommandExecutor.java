package com.particle.dataquery.app.datasource.executor.representation;

import com.particle.dataquery.app.datasource.structmapping.DataQueryDatasourceAppStructMapping;
import com.particle.dataquery.client.datasource.dto.command.representation.DataQueryDatasourceQueryListCommand;
import com.particle.dataquery.client.datasource.dto.data.DataQueryDatasourceVO;
import com.particle.dataquery.infrastructure.datasource.dos.DataQueryDatasourceDO;
import com.particle.dataquery.infrastructure.datasource.service.IDataQueryDatasourceService;
import com.particle.dataquery.client.datasource.dto.command.representation.DataQueryDatasourcePageQueryCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.global.dto.response.MultiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.PageResponse;
import javax.validation.Valid;
import java.util.List;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 数据查询数据源 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-03-08 14:29:00
 */
@Component
@Validated
public class DataQueryDatasourceQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataQueryDatasourceService iDataQueryDatasourceService;

	/**
	 * 执行 数据查询数据源 列表查询指令
	 * @param dataQueryDatasourceQueryListCommand
	 * @return
	 */
	public MultiResponse<DataQueryDatasourceVO> execute(@Valid DataQueryDatasourceQueryListCommand dataQueryDatasourceQueryListCommand) {
		List<DataQueryDatasourceDO> dataQueryDatasourceDO = iDataQueryDatasourceService.list(dataQueryDatasourceQueryListCommand);
		List<DataQueryDatasourceVO> dataQueryDatasourceVOs = DataQueryDatasourceAppStructMapping.instance.dataQueryDatasourceDOsToDataQueryDatasourceVOs(dataQueryDatasourceDO);
		return MultiResponse.of(dataQueryDatasourceVOs);
	}
	/**
	 * 执行 数据查询数据源 分页查询指令
	 * @param dataQueryDatasourcePageQueryCommand
	 * @return
	 */
	public PageResponse<DataQueryDatasourceVO> execute(@Valid DataQueryDatasourcePageQueryCommand dataQueryDatasourcePageQueryCommand) {
		Page<DataQueryDatasourceDO> page = iDataQueryDatasourceService.listPage(dataQueryDatasourcePageQueryCommand);
		return DataQueryDatasourceAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 数据查询数据源 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataQueryDatasourceVO> executeDetail(IdCommand detailCommand) {
		DataQueryDatasourceDO byId = iDataQueryDatasourceService.getById(detailCommand.getId());
		DataQueryDatasourceVO dataQueryDatasourceVO = DataQueryDatasourceAppStructMapping.instance.dataQueryDatasourceDOToDataQueryDatasourceVO(byId);
		return SingleResponse.of(dataQueryDatasourceVO);
	}
	/**
	 * 执行 数据查询数据源 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataQueryDatasourceVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataQueryDatasourceDO byId = iDataQueryDatasourceService.getById(detailForUpdateCommand.getId());
		DataQueryDatasourceVO dataQueryDatasourceVO = DataQueryDatasourceAppStructMapping.instance.dataQueryDatasourceDOToDataQueryDatasourceVO(byId);
		return SingleResponse.of(dataQueryDatasourceVO);
	}

	@Autowired
	public void setIDataQueryDatasourceService(IDataQueryDatasourceService iDataQueryDatasourceService) {
		this.iDataQueryDatasourceService = iDataQueryDatasourceService;
	}
}
