package com.particle.dataconstraint.app.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dataconstraint.app.structmapping.DataObjectAppStructMapping;
import com.particle.dataconstraint.client.dto.command.representation.DataObjectPageQueryCommand;
import com.particle.dataconstraint.client.dto.command.representation.DataObjectQueryListCommand;
import com.particle.dataconstraint.client.dto.data.DataObjectVO;
import com.particle.dataconstraint.infrastructure.dos.DataObjectDO;
import com.particle.dataconstraint.infrastructure.service.IDataObjectService;
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
 * 数据对象 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-06-28 13:10:18
 */
@Component
@Validated
public class DataObjectQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataObjectService iDataObjectService;

	/**
	 * 执行 数据对象 列表查询指令
	 * @param dataObjectQueryListCommand
	 * @return
	 */
	public MultiResponse<DataObjectVO> execute(@Valid DataObjectQueryListCommand dataObjectQueryListCommand) {
		List<DataObjectDO> dataObjectDO = iDataObjectService.list(dataObjectQueryListCommand);
		List<DataObjectVO> dataObjectVOs = DataObjectAppStructMapping.instance.dataObjectDOsToDataObjectVOs(dataObjectDO);
		return MultiResponse.of(dataObjectVOs);
	}
	/**
	 * 执行 数据对象 分页查询指令
	 * @param dataObjectPageQueryCommand
	 * @return
	 */
	public PageResponse<DataObjectVO> execute(@Valid DataObjectPageQueryCommand dataObjectPageQueryCommand) {
		Page<DataObjectDO> page = iDataObjectService.listPage(dataObjectPageQueryCommand);
		return DataObjectAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 数据对象 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataObjectVO> executeDetail(IdCommand detailCommand) {
		DataObjectDO byId = iDataObjectService.getById(detailCommand.getId());
		DataObjectVO dataObjectVO = DataObjectAppStructMapping.instance.dataObjectDOToDataObjectVO(byId);
		return SingleResponse.of(dataObjectVO);
	}
	/**
	 * 执行 数据对象 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataObjectVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataObjectDO byId = iDataObjectService.getById(detailForUpdateCommand.getId());
		DataObjectVO dataObjectVO = DataObjectAppStructMapping.instance.dataObjectDOToDataObjectVO(byId);
		return SingleResponse.of(dataObjectVO);
	}

	@Autowired
	public void setIDataObjectService(IDataObjectService iDataObjectService) {
		this.iDataObjectService = iDataObjectService;
	}
}
