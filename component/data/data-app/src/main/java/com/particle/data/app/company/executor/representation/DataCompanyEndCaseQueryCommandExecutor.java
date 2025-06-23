package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyEndCaseAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyEndCaseQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyEndCaseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyEndCaseDO;
import com.particle.data.infrastructure.company.service.IDataCompanyEndCaseService;
import com.particle.data.client.company.dto.command.representation.DataCompanyEndCasePageQueryCommand;
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
 * 企业终本案件 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-06-20 16:18:57
 */
@Component
@Validated
public class DataCompanyEndCaseQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyEndCaseService iDataCompanyEndCaseService;

	/**
	 * 执行 企业终本案件 列表查询指令
	 * @param dataCompanyEndCaseQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyEndCaseVO> execute(@Valid DataCompanyEndCaseQueryListCommand dataCompanyEndCaseQueryListCommand) {
		List<DataCompanyEndCaseDO> dataCompanyEndCaseDO = iDataCompanyEndCaseService.list(dataCompanyEndCaseQueryListCommand);
		List<DataCompanyEndCaseVO> dataCompanyEndCaseVOs = DataCompanyEndCaseAppStructMapping.instance.dataCompanyEndCaseDOsToDataCompanyEndCaseVOs(dataCompanyEndCaseDO);
		return MultiResponse.of(dataCompanyEndCaseVOs);
	}
	/**
	 * 执行 企业终本案件 分页查询指令
	 * @param dataCompanyEndCasePageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyEndCaseVO> execute(@Valid DataCompanyEndCasePageQueryCommand dataCompanyEndCasePageQueryCommand) {
		Page<DataCompanyEndCaseDO> page = iDataCompanyEndCaseService.listPage(dataCompanyEndCasePageQueryCommand);
		return DataCompanyEndCaseAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业终本案件 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyEndCaseVO> executeDetail(IdCommand detailCommand) {
		DataCompanyEndCaseDO byId = iDataCompanyEndCaseService.getById(detailCommand.getId());
		DataCompanyEndCaseVO dataCompanyEndCaseVO = DataCompanyEndCaseAppStructMapping.instance.dataCompanyEndCaseDOToDataCompanyEndCaseVO(byId);
		return SingleResponse.of(dataCompanyEndCaseVO);
	}
	/**
	 * 执行 企业终本案件 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyEndCaseVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyEndCaseDO byId = iDataCompanyEndCaseService.getById(detailForUpdateCommand.getId());
		DataCompanyEndCaseVO dataCompanyEndCaseVO = DataCompanyEndCaseAppStructMapping.instance.dataCompanyEndCaseDOToDataCompanyEndCaseVO(byId);
		return SingleResponse.of(dataCompanyEndCaseVO);
	}


	@Autowired
	public void setIDataCompanyEndCaseService(IDataCompanyEndCaseService iDataCompanyEndCaseService) {
		this.iDataCompanyEndCaseService = iDataCompanyEndCaseService;
	}
}
