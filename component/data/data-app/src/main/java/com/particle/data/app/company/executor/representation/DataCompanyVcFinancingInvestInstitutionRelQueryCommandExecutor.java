package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyVcFinancingInvestInstitutionRelAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcFinancingInvestInstitutionRelQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcFinancingInvestInstitutionRelVO;
import com.particle.data.infrastructure.company.dos.DataCompanyVcFinancingInvestInstitutionRelDO;
import com.particle.data.infrastructure.company.service.IDataCompanyVcFinancingInvestInstitutionRelService;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcFinancingInvestInstitutionRelPageQueryCommand;
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
 * 企业融资历史投资机构关系 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:46:28
 */
@Component
@Validated
public class DataCompanyVcFinancingInvestInstitutionRelQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyVcFinancingInvestInstitutionRelService iDataCompanyVcFinancingInvestInstitutionRelService;

	/**
	 * 执行 企业融资历史投资机构关系 列表查询指令
	 * @param dataCompanyVcFinancingInvestInstitutionRelQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyVcFinancingInvestInstitutionRelVO> execute(@Valid DataCompanyVcFinancingInvestInstitutionRelQueryListCommand dataCompanyVcFinancingInvestInstitutionRelQueryListCommand) {
		List<DataCompanyVcFinancingInvestInstitutionRelDO> dataCompanyVcFinancingInvestInstitutionRelDO = iDataCompanyVcFinancingInvestInstitutionRelService.list(dataCompanyVcFinancingInvestInstitutionRelQueryListCommand);
		List<DataCompanyVcFinancingInvestInstitutionRelVO> dataCompanyVcFinancingInvestInstitutionRelVOs = DataCompanyVcFinancingInvestInstitutionRelAppStructMapping.instance.dataCompanyVcFinancingInvestInstitutionRelDOsToDataCompanyVcFinancingInvestInstitutionRelVOs(dataCompanyVcFinancingInvestInstitutionRelDO);
		return MultiResponse.of(dataCompanyVcFinancingInvestInstitutionRelVOs);
	}
	/**
	 * 执行 企业融资历史投资机构关系 分页查询指令
	 * @param dataCompanyVcFinancingInvestInstitutionRelPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyVcFinancingInvestInstitutionRelVO> execute(@Valid DataCompanyVcFinancingInvestInstitutionRelPageQueryCommand dataCompanyVcFinancingInvestInstitutionRelPageQueryCommand) {
		Page<DataCompanyVcFinancingInvestInstitutionRelDO> page = iDataCompanyVcFinancingInvestInstitutionRelService.listPage(dataCompanyVcFinancingInvestInstitutionRelPageQueryCommand);
		return DataCompanyVcFinancingInvestInstitutionRelAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业融资历史投资机构关系 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyVcFinancingInvestInstitutionRelVO> executeDetail(IdCommand detailCommand) {
		DataCompanyVcFinancingInvestInstitutionRelDO byId = iDataCompanyVcFinancingInvestInstitutionRelService.getById(detailCommand.getId());
		DataCompanyVcFinancingInvestInstitutionRelVO dataCompanyVcFinancingInvestInstitutionRelVO = DataCompanyVcFinancingInvestInstitutionRelAppStructMapping.instance.dataCompanyVcFinancingInvestInstitutionRelDOToDataCompanyVcFinancingInvestInstitutionRelVO(byId);
		return SingleResponse.of(dataCompanyVcFinancingInvestInstitutionRelVO);
	}
	/**
	 * 执行 企业融资历史投资机构关系 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyVcFinancingInvestInstitutionRelVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyVcFinancingInvestInstitutionRelDO byId = iDataCompanyVcFinancingInvestInstitutionRelService.getById(detailForUpdateCommand.getId());
		DataCompanyVcFinancingInvestInstitutionRelVO dataCompanyVcFinancingInvestInstitutionRelVO = DataCompanyVcFinancingInvestInstitutionRelAppStructMapping.instance.dataCompanyVcFinancingInvestInstitutionRelDOToDataCompanyVcFinancingInvestInstitutionRelVO(byId);
		return SingleResponse.of(dataCompanyVcFinancingInvestInstitutionRelVO);
	}


	/**
	 * 查询企业融资表ID已分配的企业投资机构表ids
	 * @param companyVcFinancingIdCommand
	 * @return
	 */
	public MultiResponse<Long> queryCompanyVcInvestInstitutionIdsByCompanyVcFinancingId(@Valid IdCommand companyVcFinancingIdCommand) {

		DataCompanyVcFinancingInvestInstitutionRelQueryListCommand dataCompanyVcFinancingInvestInstitutionRelQueryListCommand = new DataCompanyVcFinancingInvestInstitutionRelQueryListCommand();
		dataCompanyVcFinancingInvestInstitutionRelQueryListCommand.setCompanyVcFinancingId(companyVcFinancingIdCommand.getId());
		MultiResponse<DataCompanyVcFinancingInvestInstitutionRelVO> dataCompanyVcFinancingInvestInstitutionRelVOMultiResponse = execute(dataCompanyVcFinancingInvestInstitutionRelQueryListCommand);
		if(dataCompanyVcFinancingInvestInstitutionRelVOMultiResponse.isNotEmpty()){
			List<Long> collect = dataCompanyVcFinancingInvestInstitutionRelVOMultiResponse.getData().stream().map(DataCompanyVcFinancingInvestInstitutionRelVO::getCompanyVcInvestInstitutionId).collect(Collectors.toList());
			return MultiResponse.of(collect);
		}
		return MultiResponse.buildSuccess();
	}
	/**
	 * 查询企业投资机构表已分配的企业融资表IDids
	 * @param companyVcInvestInstitutionIdCommand
	 * @return
	 */
	public MultiResponse<Long> queryCompanyVcFinancingIdsByCompanyVcInvestInstitutionId(@Valid IdCommand companyVcInvestInstitutionIdCommand) {

		DataCompanyVcFinancingInvestInstitutionRelQueryListCommand dataCompanyVcFinancingInvestInstitutionRelQueryListCommand = new DataCompanyVcFinancingInvestInstitutionRelQueryListCommand();
		dataCompanyVcFinancingInvestInstitutionRelQueryListCommand.setCompanyVcInvestInstitutionId(companyVcInvestInstitutionIdCommand.getId());
		MultiResponse<DataCompanyVcFinancingInvestInstitutionRelVO> dataCompanyVcFinancingInvestInstitutionRelVOMultiResponse = execute(dataCompanyVcFinancingInvestInstitutionRelQueryListCommand);
		if(dataCompanyVcFinancingInvestInstitutionRelVOMultiResponse.isNotEmpty()){
			List<Long> collect = dataCompanyVcFinancingInvestInstitutionRelVOMultiResponse.getData().stream().map(DataCompanyVcFinancingInvestInstitutionRelVO::getCompanyVcFinancingId).collect(Collectors.toList());
			return MultiResponse.of(collect);
		}
		return MultiResponse.buildSuccess();
	}

	@Autowired
	public void setIDataCompanyVcFinancingInvestInstitutionRelService(IDataCompanyVcFinancingInvestInstitutionRelService iDataCompanyVcFinancingInvestInstitutionRelService) {
		this.iDataCompanyVcFinancingInvestInstitutionRelService = iDataCompanyVcFinancingInvestInstitutionRelService;
	}
}
