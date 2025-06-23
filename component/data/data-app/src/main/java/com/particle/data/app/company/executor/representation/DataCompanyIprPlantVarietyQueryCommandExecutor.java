package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyIprPlantVarietyAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPlantVarietyQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPlantVarietyVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPlantVarietyDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPlantVarietyService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPlantVarietyPageQueryCommand;
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
 * 企业知识产权植物新品种 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-06-16 11:17:40
 */
@Component
@Validated
public class DataCompanyIprPlantVarietyQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyIprPlantVarietyService iDataCompanyIprPlantVarietyService;

	/**
	 * 执行 企业知识产权植物新品种 列表查询指令
	 * @param dataCompanyIprPlantVarietyQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyIprPlantVarietyVO> execute(@Valid DataCompanyIprPlantVarietyQueryListCommand dataCompanyIprPlantVarietyQueryListCommand) {
		List<DataCompanyIprPlantVarietyDO> dataCompanyIprPlantVarietyDO = iDataCompanyIprPlantVarietyService.list(dataCompanyIprPlantVarietyQueryListCommand);
		List<DataCompanyIprPlantVarietyVO> dataCompanyIprPlantVarietyVOs = DataCompanyIprPlantVarietyAppStructMapping.instance.dataCompanyIprPlantVarietyDOsToDataCompanyIprPlantVarietyVOs(dataCompanyIprPlantVarietyDO);
		return MultiResponse.of(dataCompanyIprPlantVarietyVOs);
	}
	/**
	 * 执行 企业知识产权植物新品种 分页查询指令
	 * @param dataCompanyIprPlantVarietyPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPlantVarietyVO> execute(@Valid DataCompanyIprPlantVarietyPageQueryCommand dataCompanyIprPlantVarietyPageQueryCommand) {
		Page<DataCompanyIprPlantVarietyDO> page = iDataCompanyIprPlantVarietyService.listPage(dataCompanyIprPlantVarietyPageQueryCommand);
		return DataCompanyIprPlantVarietyAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业知识产权植物新品种 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPlantVarietyVO> executeDetail(IdCommand detailCommand) {
		DataCompanyIprPlantVarietyDO byId = iDataCompanyIprPlantVarietyService.getById(detailCommand.getId());
		DataCompanyIprPlantVarietyVO dataCompanyIprPlantVarietyVO = DataCompanyIprPlantVarietyAppStructMapping.instance.dataCompanyIprPlantVarietyDOToDataCompanyIprPlantVarietyVO(byId);
		return SingleResponse.of(dataCompanyIprPlantVarietyVO);
	}
	/**
	 * 执行 企业知识产权植物新品种 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPlantVarietyVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyIprPlantVarietyDO byId = iDataCompanyIprPlantVarietyService.getById(detailForUpdateCommand.getId());
		DataCompanyIprPlantVarietyVO dataCompanyIprPlantVarietyVO = DataCompanyIprPlantVarietyAppStructMapping.instance.dataCompanyIprPlantVarietyDOToDataCompanyIprPlantVarietyVO(byId);
		return SingleResponse.of(dataCompanyIprPlantVarietyVO);
	}


	@Autowired
	public void setIDataCompanyIprPlantVarietyService(IDataCompanyIprPlantVarietyService iDataCompanyIprPlantVarietyService) {
		this.iDataCompanyIprPlantVarietyService = iDataCompanyIprPlantVarietyService;
	}
}
