package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyIprPlantVarietyChangeAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPlantVarietyChangeQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPlantVarietyChangeVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPlantVarietyChangeDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPlantVarietyChangeService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPlantVarietyChangePageQueryCommand;
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
 * 企业知识产权植物新品种变更信息 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-06-16 11:17:52
 */
@Component
@Validated
public class DataCompanyIprPlantVarietyChangeQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyIprPlantVarietyChangeService iDataCompanyIprPlantVarietyChangeService;

	/**
	 * 执行 企业知识产权植物新品种变更信息 列表查询指令
	 * @param dataCompanyIprPlantVarietyChangeQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyIprPlantVarietyChangeVO> execute(@Valid DataCompanyIprPlantVarietyChangeQueryListCommand dataCompanyIprPlantVarietyChangeQueryListCommand) {
		List<DataCompanyIprPlantVarietyChangeDO> dataCompanyIprPlantVarietyChangeDO = iDataCompanyIprPlantVarietyChangeService.list(dataCompanyIprPlantVarietyChangeQueryListCommand);
		List<DataCompanyIprPlantVarietyChangeVO> dataCompanyIprPlantVarietyChangeVOs = DataCompanyIprPlantVarietyChangeAppStructMapping.instance.dataCompanyIprPlantVarietyChangeDOsToDataCompanyIprPlantVarietyChangeVOs(dataCompanyIprPlantVarietyChangeDO);
		return MultiResponse.of(dataCompanyIprPlantVarietyChangeVOs);
	}
	/**
	 * 执行 企业知识产权植物新品种变更信息 分页查询指令
	 * @param dataCompanyIprPlantVarietyChangePageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPlantVarietyChangeVO> execute(@Valid DataCompanyIprPlantVarietyChangePageQueryCommand dataCompanyIprPlantVarietyChangePageQueryCommand) {
		Page<DataCompanyIprPlantVarietyChangeDO> page = iDataCompanyIprPlantVarietyChangeService.listPage(dataCompanyIprPlantVarietyChangePageQueryCommand);
		return DataCompanyIprPlantVarietyChangeAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业知识产权植物新品种变更信息 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPlantVarietyChangeVO> executeDetail(IdCommand detailCommand) {
		DataCompanyIprPlantVarietyChangeDO byId = iDataCompanyIprPlantVarietyChangeService.getById(detailCommand.getId());
		DataCompanyIprPlantVarietyChangeVO dataCompanyIprPlantVarietyChangeVO = DataCompanyIprPlantVarietyChangeAppStructMapping.instance.dataCompanyIprPlantVarietyChangeDOToDataCompanyIprPlantVarietyChangeVO(byId);
		return SingleResponse.of(dataCompanyIprPlantVarietyChangeVO);
	}
	/**
	 * 执行 企业知识产权植物新品种变更信息 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPlantVarietyChangeVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyIprPlantVarietyChangeDO byId = iDataCompanyIprPlantVarietyChangeService.getById(detailForUpdateCommand.getId());
		DataCompanyIprPlantVarietyChangeVO dataCompanyIprPlantVarietyChangeVO = DataCompanyIprPlantVarietyChangeAppStructMapping.instance.dataCompanyIprPlantVarietyChangeDOToDataCompanyIprPlantVarietyChangeVO(byId);
		return SingleResponse.of(dataCompanyIprPlantVarietyChangeVO);
	}


	@Autowired
	public void setIDataCompanyIprPlantVarietyChangeService(IDataCompanyIprPlantVarietyChangeService iDataCompanyIprPlantVarietyChangeService) {
		this.iDataCompanyIprPlantVarietyChangeService = iDataCompanyIprPlantVarietyChangeService;
	}
}
