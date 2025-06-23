package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyIprIntegratedCircuitAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprIntegratedCircuitQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprIntegratedCircuitVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprIntegratedCircuitDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprIntegratedCircuitService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprIntegratedCircuitPageQueryCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.global.dto.response.MultiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.PageResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.SingleResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 企业知识产权集成电路 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-06-16 11:17:28
 */
@Component
@Validated
public class DataCompanyIprIntegratedCircuitQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyIprIntegratedCircuitService iDataCompanyIprIntegratedCircuitService;

	/**
	 * 执行 企业知识产权集成电路 列表查询指令
	 * @param dataCompanyIprIntegratedCircuitQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyIprIntegratedCircuitVO> execute(@Valid DataCompanyIprIntegratedCircuitQueryListCommand dataCompanyIprIntegratedCircuitQueryListCommand) {
		List<DataCompanyIprIntegratedCircuitDO> dataCompanyIprIntegratedCircuitDO = iDataCompanyIprIntegratedCircuitService.list(dataCompanyIprIntegratedCircuitQueryListCommand);
		List<DataCompanyIprIntegratedCircuitVO> dataCompanyIprIntegratedCircuitVOs = DataCompanyIprIntegratedCircuitAppStructMapping.instance.dataCompanyIprIntegratedCircuitDOsToDataCompanyIprIntegratedCircuitVOs(dataCompanyIprIntegratedCircuitDO);
		return MultiResponse.of(dataCompanyIprIntegratedCircuitVOs);
	}
	/**
	 * 执行 企业知识产权集成电路 分页查询指令
	 * @param dataCompanyIprIntegratedCircuitPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprIntegratedCircuitVO> execute(@Valid DataCompanyIprIntegratedCircuitPageQueryCommand dataCompanyIprIntegratedCircuitPageQueryCommand) {
		Page<DataCompanyIprIntegratedCircuitDO> page = iDataCompanyIprIntegratedCircuitService.listPage(dataCompanyIprIntegratedCircuitPageQueryCommand);
		return DataCompanyIprIntegratedCircuitAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业知识产权集成电路 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprIntegratedCircuitVO> executeDetail(IdCommand detailCommand) {
		DataCompanyIprIntegratedCircuitDO byId = iDataCompanyIprIntegratedCircuitService.getById(detailCommand.getId());
		DataCompanyIprIntegratedCircuitVO dataCompanyIprIntegratedCircuitVO = DataCompanyIprIntegratedCircuitAppStructMapping.instance.dataCompanyIprIntegratedCircuitDOToDataCompanyIprIntegratedCircuitVO(byId);
		return SingleResponse.of(dataCompanyIprIntegratedCircuitVO);
	}
	/**
	 * 执行 企业知识产权集成电路 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprIntegratedCircuitVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyIprIntegratedCircuitDO byId = iDataCompanyIprIntegratedCircuitService.getById(detailForUpdateCommand.getId());
		DataCompanyIprIntegratedCircuitVO dataCompanyIprIntegratedCircuitVO = DataCompanyIprIntegratedCircuitAppStructMapping.instance.dataCompanyIprIntegratedCircuitDOToDataCompanyIprIntegratedCircuitVO(byId);
		return SingleResponse.of(dataCompanyIprIntegratedCircuitVO);
	}


	@Autowired
	public void setIDataCompanyIprIntegratedCircuitService(IDataCompanyIprIntegratedCircuitService iDataCompanyIprIntegratedCircuitService) {
		this.iDataCompanyIprIntegratedCircuitService = iDataCompanyIprIntegratedCircuitService;
	}
}
