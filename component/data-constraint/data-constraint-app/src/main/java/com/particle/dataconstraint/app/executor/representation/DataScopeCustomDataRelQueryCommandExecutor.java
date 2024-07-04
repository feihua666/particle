package com.particle.dataconstraint.app.executor.representation;

import com.particle.dataconstraint.app.structmapping.DataScopeCustomDataRelAppStructMapping;
import com.particle.dataconstraint.client.dto.command.representation.DataScopeCustomDataRelQueryListCommand;
import com.particle.dataconstraint.client.dto.data.DataScopeCustomDataRelVO;
import com.particle.dataconstraint.infrastructure.dos.DataScopeCustomDataRelDO;
import com.particle.dataconstraint.infrastructure.service.IDataScopeCustomDataRelService;
import com.particle.dataconstraint.client.dto.command.representation.DataScopeCustomDataRelPageQueryCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.global.dto.response.MultiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.PageResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 数据范围自定义数据关系 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-06-28 13:10:55
 */
@Component
@Validated
public class DataScopeCustomDataRelQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataScopeCustomDataRelService iDataScopeCustomDataRelService;

	/**
	 * 执行 数据范围自定义数据关系 列表查询指令
	 * @param dataScopeCustomDataRelQueryListCommand
	 * @return
	 */
	public MultiResponse<DataScopeCustomDataRelVO> execute(@Valid DataScopeCustomDataRelQueryListCommand dataScopeCustomDataRelQueryListCommand) {
		List<DataScopeCustomDataRelDO> dataScopeCustomDataRelDO = iDataScopeCustomDataRelService.list(dataScopeCustomDataRelQueryListCommand);
		List<DataScopeCustomDataRelVO> dataScopeCustomDataRelVOs = DataScopeCustomDataRelAppStructMapping.instance.dataScopeCustomDataRelDOsToDataScopeCustomDataRelVOs(dataScopeCustomDataRelDO);
		return MultiResponse.of(dataScopeCustomDataRelVOs);
	}
	/**
	 * 执行 数据范围自定义数据关系 分页查询指令
	 * @param dataScopeCustomDataRelPageQueryCommand
	 * @return
	 */
	public PageResponse<DataScopeCustomDataRelVO> execute(@Valid DataScopeCustomDataRelPageQueryCommand dataScopeCustomDataRelPageQueryCommand) {
		Page<DataScopeCustomDataRelDO> page = iDataScopeCustomDataRelService.listPage(dataScopeCustomDataRelPageQueryCommand);
		return DataScopeCustomDataRelAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 数据范围自定义数据关系 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataScopeCustomDataRelVO> executeDetail(IdCommand detailCommand) {
		DataScopeCustomDataRelDO byId = iDataScopeCustomDataRelService.getById(detailCommand.getId());
		DataScopeCustomDataRelVO dataScopeCustomDataRelVO = DataScopeCustomDataRelAppStructMapping.instance.dataScopeCustomDataRelDOToDataScopeCustomDataRelVO(byId);
		return SingleResponse.of(dataScopeCustomDataRelVO);
	}
	/**
	 * 执行 数据范围自定义数据关系 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataScopeCustomDataRelVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataScopeCustomDataRelDO byId = iDataScopeCustomDataRelService.getById(detailForUpdateCommand.getId());
		DataScopeCustomDataRelVO dataScopeCustomDataRelVO = DataScopeCustomDataRelAppStructMapping.instance.dataScopeCustomDataRelDOToDataScopeCustomDataRelVO(byId);
		return SingleResponse.of(dataScopeCustomDataRelVO);
	}

	/**
	 * 查询数据范围已分配的自定义数据菜单ids
	 * @param dataScopeIdCommand
	 * @return
	 */
	public MultiResponse<Long> queryCustomDataIdsByDataScopeId(@Valid IdCommand dataScopeIdCommand) {

		DataScopeCustomDataRelQueryListCommand dataScopeUserRelQueryListCommand = new DataScopeCustomDataRelQueryListCommand();
		dataScopeUserRelQueryListCommand.setDataScopeId(dataScopeIdCommand.getId());
		MultiResponse<DataScopeCustomDataRelVO> dataScopeUserRelVOMultiResponse = execute(dataScopeUserRelQueryListCommand);
		if(dataScopeUserRelVOMultiResponse.isNotEmpty()){
			List<Long> collect = dataScopeUserRelVOMultiResponse.getData().stream().map(DataScopeCustomDataRelVO::getDataId).collect(Collectors.toList());
			return MultiResponse.of(collect);
		}
		return MultiResponse.buildSuccess();
	}
	
	@Autowired
	public void setIDataScopeCustomDataRelService(IDataScopeCustomDataRelService iDataScopeCustomDataRelService) {
		this.iDataScopeCustomDataRelService = iDataScopeCustomDataRelService;
	}
}
