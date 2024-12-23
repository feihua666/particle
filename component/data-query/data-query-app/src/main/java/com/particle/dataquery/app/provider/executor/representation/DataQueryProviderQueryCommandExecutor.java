package com.particle.dataquery.app.provider.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dataquery.app.provider.structmapping.DataQueryProviderAppStructMapping;
import com.particle.dataquery.client.provider.dto.command.representation.DataQueryProviderPageQueryCommand;
import com.particle.dataquery.client.provider.dto.command.representation.DataQueryProviderQueryListCommand;
import com.particle.dataquery.client.provider.dto.data.DataQueryProviderVO;
import com.particle.dataquery.infrastructure.provider.dos.DataQueryProviderDO;
import com.particle.dataquery.infrastructure.provider.service.IDataQueryProviderService;
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
 * 数据查询供应商 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-03-03 19:19:55
 */
@Component
@Validated
public class DataQueryProviderQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataQueryProviderService iDataQueryProviderService;

	/**
	 * 执行 数据查询供应商 列表查询指令
	 * @param dataQueryProviderQueryListCommand
	 * @return
	 */
	public MultiResponse<DataQueryProviderVO> execute(@Valid DataQueryProviderQueryListCommand dataQueryProviderQueryListCommand) {
		List<DataQueryProviderDO> dataQueryProviderDO = iDataQueryProviderService.list(dataQueryProviderQueryListCommand);
		List<DataQueryProviderVO> dataQueryProviderVOs = DataQueryProviderAppStructMapping.instance.dataQueryProviderDOsToDataQueryProviderVOs(dataQueryProviderDO);
		return MultiResponse.of(dataQueryProviderVOs);
	}
	/**
	 * 执行 数据查询供应商 分页查询指令
	 * @param dataQueryProviderPageQueryCommand
	 * @return
	 */
	public PageResponse<DataQueryProviderVO> execute(@Valid DataQueryProviderPageQueryCommand dataQueryProviderPageQueryCommand) {
		Page<DataQueryProviderDO> page = iDataQueryProviderService.listPage(dataQueryProviderPageQueryCommand);
		return DataQueryProviderAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 数据查询供应商 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataQueryProviderVO> executeDetail(IdCommand detailCommand) {
		DataQueryProviderDO byId = iDataQueryProviderService.getById(detailCommand.getId());
		DataQueryProviderVO dataQueryProviderVO = DataQueryProviderAppStructMapping.instance.dataQueryProviderDOToDataQueryProviderVO(byId);
		return SingleResponse.of(dataQueryProviderVO);
	}
	/**
	 * 执行 数据查询供应商 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataQueryProviderVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataQueryProviderDO byId = iDataQueryProviderService.getById(detailForUpdateCommand.getId());
		DataQueryProviderVO dataQueryProviderVO = DataQueryProviderAppStructMapping.instance.dataQueryProviderDOToDataQueryProviderVO(byId);
		return SingleResponse.of(dataQueryProviderVO);
	}

	@Autowired
	public void setIDataQueryProviderService(IDataQueryProviderService iDataQueryProviderService) {
		this.iDataQueryProviderService = iDataQueryProviderService;
	}
}
