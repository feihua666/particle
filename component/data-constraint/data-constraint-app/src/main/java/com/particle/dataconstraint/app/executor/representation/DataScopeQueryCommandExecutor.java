package com.particle.dataconstraint.app.executor.representation;

import com.particle.dataconstraint.app.structmapping.DataScopeAppStructMapping;
import com.particle.dataconstraint.client.dto.command.representation.DataScopeQueryListCommand;
import com.particle.dataconstraint.client.dto.data.DataScopeVO;
import com.particle.dataconstraint.infrastructure.dos.DataScopeDO;
import com.particle.dataconstraint.infrastructure.service.IDataScopeService;
import com.particle.dataconstraint.client.dto.command.representation.DataScopePageQueryCommand;
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
 * 数据范围 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-06-28 13:10:38
 */
@Component
@Validated
public class DataScopeQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataScopeService iDataScopeService;

	/**
	 * 执行 数据范围 列表查询指令
	 * @param dataScopeQueryListCommand
	 * @return
	 */
	public MultiResponse<DataScopeVO> execute(@Valid DataScopeQueryListCommand dataScopeQueryListCommand) {
		List<DataScopeDO> dataScopeDO = iDataScopeService.list(dataScopeQueryListCommand);
		List<DataScopeVO> dataScopeVOs = DataScopeAppStructMapping.instance.dataScopeDOsToDataScopeVOs(dataScopeDO);
		return MultiResponse.of(dataScopeVOs);
	}
	/**
	 * 执行 数据范围 分页查询指令
	 * @param dataScopePageQueryCommand
	 * @return
	 */
	public PageResponse<DataScopeVO> execute(@Valid DataScopePageQueryCommand dataScopePageQueryCommand) {
		Page<DataScopeDO> page = iDataScopeService.listPage(dataScopePageQueryCommand);
		return DataScopeAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 数据范围 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataScopeVO> executeDetail(IdCommand detailCommand) {
		DataScopeDO byId = iDataScopeService.getById(detailCommand.getId());
		DataScopeVO dataScopeVO = DataScopeAppStructMapping.instance.dataScopeDOToDataScopeVO(byId);
		return SingleResponse.of(dataScopeVO);
	}
	/**
	 * 执行 数据范围 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataScopeVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataScopeDO byId = iDataScopeService.getById(detailForUpdateCommand.getId());
		DataScopeVO dataScopeVO = DataScopeAppStructMapping.instance.dataScopeDOToDataScopeVO(byId);
		return SingleResponse.of(dataScopeVO);
	}

	@Autowired
	public void setIDataScopeService(IDataScopeService iDataScopeService) {
		this.iDataScopeService = iDataScopeService;
	}
}
