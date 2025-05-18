package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyAnnualReportForeignGuaranteeAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportForeignGuaranteeQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportForeignGuaranteeVO;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportForeignGuaranteeDO;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportForeignGuaranteeService;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportForeignGuaranteePageQueryCommand;
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
 * 企业年报对外担保 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:36:11
 */
@Component
@Validated
public class DataCompanyAnnualReportForeignGuaranteeQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyAnnualReportForeignGuaranteeService iDataCompanyAnnualReportForeignGuaranteeService;

	/**
	 * 执行 企业年报对外担保 列表查询指令
	 * @param dataCompanyAnnualReportForeignGuaranteeQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyAnnualReportForeignGuaranteeVO> execute(@Valid DataCompanyAnnualReportForeignGuaranteeQueryListCommand dataCompanyAnnualReportForeignGuaranteeQueryListCommand) {
		List<DataCompanyAnnualReportForeignGuaranteeDO> dataCompanyAnnualReportForeignGuaranteeDO = iDataCompanyAnnualReportForeignGuaranteeService.list(dataCompanyAnnualReportForeignGuaranteeQueryListCommand);
		List<DataCompanyAnnualReportForeignGuaranteeVO> dataCompanyAnnualReportForeignGuaranteeVOs = DataCompanyAnnualReportForeignGuaranteeAppStructMapping.instance.dataCompanyAnnualReportForeignGuaranteeDOsToDataCompanyAnnualReportForeignGuaranteeVOs(dataCompanyAnnualReportForeignGuaranteeDO);
		return MultiResponse.of(dataCompanyAnnualReportForeignGuaranteeVOs);
	}
	/**
	 * 执行 企业年报对外担保 分页查询指令
	 * @param dataCompanyAnnualReportForeignGuaranteePageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportForeignGuaranteeVO> execute(@Valid DataCompanyAnnualReportForeignGuaranteePageQueryCommand dataCompanyAnnualReportForeignGuaranteePageQueryCommand) {
		Page<DataCompanyAnnualReportForeignGuaranteeDO> page = iDataCompanyAnnualReportForeignGuaranteeService.listPage(dataCompanyAnnualReportForeignGuaranteePageQueryCommand);
		return DataCompanyAnnualReportForeignGuaranteeAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业年报对外担保 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportForeignGuaranteeVO> executeDetail(IdCommand detailCommand) {
		DataCompanyAnnualReportForeignGuaranteeDO byId = iDataCompanyAnnualReportForeignGuaranteeService.getById(detailCommand.getId());
		DataCompanyAnnualReportForeignGuaranteeVO dataCompanyAnnualReportForeignGuaranteeVO = DataCompanyAnnualReportForeignGuaranteeAppStructMapping.instance.dataCompanyAnnualReportForeignGuaranteeDOToDataCompanyAnnualReportForeignGuaranteeVO(byId);
		return SingleResponse.of(dataCompanyAnnualReportForeignGuaranteeVO);
	}
	/**
	 * 执行 企业年报对外担保 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportForeignGuaranteeVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyAnnualReportForeignGuaranteeDO byId = iDataCompanyAnnualReportForeignGuaranteeService.getById(detailForUpdateCommand.getId());
		DataCompanyAnnualReportForeignGuaranteeVO dataCompanyAnnualReportForeignGuaranteeVO = DataCompanyAnnualReportForeignGuaranteeAppStructMapping.instance.dataCompanyAnnualReportForeignGuaranteeDOToDataCompanyAnnualReportForeignGuaranteeVO(byId);
		return SingleResponse.of(dataCompanyAnnualReportForeignGuaranteeVO);
	}


	@Autowired
	public void setIDataCompanyAnnualReportForeignGuaranteeService(IDataCompanyAnnualReportForeignGuaranteeService iDataCompanyAnnualReportForeignGuaranteeService) {
		this.iDataCompanyAnnualReportForeignGuaranteeService = iDataCompanyAnnualReportForeignGuaranteeService;
	}
}
