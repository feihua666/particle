package com.particle.dataquery.app.dataapi.executor.representation;

import com.particle.dataquery.app.dataapi.structmapping.DataQueryDataApiAppStructMapping;
import com.particle.dataquery.client.dataapi.dto.command.representation.DataQueryDataApiQueryListCommand;
import com.particle.dataquery.client.dataapi.dto.data.DataQueryDataApiVO;
import com.particle.dataquery.infrastructure.dataapi.dos.DataQueryDataApiDO;
import com.particle.dataquery.infrastructure.dataapi.service.IDataQueryDataApiService;
import com.particle.dataquery.client.dataapi.dto.command.representation.DataQueryDataApiPageQueryCommand;
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
 * 数据查询数据接口 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-03-21 13:11:05
 */
@Component
@Validated
public class DataQueryDataApiQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataQueryDataApiService iDataQueryDataApiService;

	/**
	 * 执行 数据查询数据接口 列表查询指令
	 * @param dataQueryDataApiQueryListCommand
	 * @return
	 */
	public MultiResponse<DataQueryDataApiVO> execute(@Valid DataQueryDataApiQueryListCommand dataQueryDataApiQueryListCommand) {
		List<DataQueryDataApiDO> dataQueryDataApiDO = iDataQueryDataApiService.list(dataQueryDataApiQueryListCommand);
		List<DataQueryDataApiVO> dataQueryDataApiVOs = DataQueryDataApiAppStructMapping.instance.dataQueryDataApiDOsToDataQueryDataApiVOs(dataQueryDataApiDO);
		return MultiResponse.of(dataQueryDataApiVOs);
	}
	/**
	 * 执行 数据查询数据接口 分页查询指令
	 * @param dataQueryDataApiPageQueryCommand
	 * @return
	 */
	public PageResponse<DataQueryDataApiVO> execute(@Valid DataQueryDataApiPageQueryCommand dataQueryDataApiPageQueryCommand) {
		Page<DataQueryDataApiDO> page = iDataQueryDataApiService.listPage(dataQueryDataApiPageQueryCommand);
		return DataQueryDataApiAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 数据查询数据接口 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataQueryDataApiVO> executeDetail(IdCommand detailCommand) {
		DataQueryDataApiDO byId = iDataQueryDataApiService.getById(detailCommand.getId());
		DataQueryDataApiVO dataQueryDataApiVO = DataQueryDataApiAppStructMapping.instance.dataQueryDataApiDOToDataQueryDataApiVO(byId);
		return SingleResponse.of(dataQueryDataApiVO);
	}
	/**
	 * 执行 数据查询数据接口 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataQueryDataApiVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataQueryDataApiDO byId = iDataQueryDataApiService.getById(detailForUpdateCommand.getId());
		DataQueryDataApiVO dataQueryDataApiVO = DataQueryDataApiAppStructMapping.instance.dataQueryDataApiDOToDataQueryDataApiVO(byId);
		return SingleResponse.of(dataQueryDataApiVO);
	}

	@Autowired
	public void setIDataQueryDataApiService(IDataQueryDataApiService iDataQueryDataApiService) {
		this.iDataQueryDataApiService = iDataQueryDataApiService;
	}
}
