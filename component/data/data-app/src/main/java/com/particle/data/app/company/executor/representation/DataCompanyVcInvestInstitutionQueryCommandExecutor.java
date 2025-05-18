package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyVcInvestInstitutionAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcInvestInstitutionQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcInvestInstitutionVO;
import com.particle.data.infrastructure.company.dos.DataCompanyVcInvestInstitutionDO;
import com.particle.data.infrastructure.company.service.IDataCompanyVcInvestInstitutionService;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcInvestInstitutionPageQueryCommand;
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
 * 企业投资机构 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:46:13
 */
@Component
@Validated
public class DataCompanyVcInvestInstitutionQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyVcInvestInstitutionService iDataCompanyVcInvestInstitutionService;

	/**
	 * 执行 企业投资机构 列表查询指令
	 * @param dataCompanyVcInvestInstitutionQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyVcInvestInstitutionVO> execute(@Valid DataCompanyVcInvestInstitutionQueryListCommand dataCompanyVcInvestInstitutionQueryListCommand) {
		List<DataCompanyVcInvestInstitutionDO> dataCompanyVcInvestInstitutionDO = iDataCompanyVcInvestInstitutionService.list(dataCompanyVcInvestInstitutionQueryListCommand);
		List<DataCompanyVcInvestInstitutionVO> dataCompanyVcInvestInstitutionVOs = DataCompanyVcInvestInstitutionAppStructMapping.instance.dataCompanyVcInvestInstitutionDOsToDataCompanyVcInvestInstitutionVOs(dataCompanyVcInvestInstitutionDO);
		return MultiResponse.of(dataCompanyVcInvestInstitutionVOs);
	}
	/**
	 * 执行 企业投资机构 分页查询指令
	 * @param dataCompanyVcInvestInstitutionPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyVcInvestInstitutionVO> execute(@Valid DataCompanyVcInvestInstitutionPageQueryCommand dataCompanyVcInvestInstitutionPageQueryCommand) {
		Page<DataCompanyVcInvestInstitutionDO> page = iDataCompanyVcInvestInstitutionService.listPage(dataCompanyVcInvestInstitutionPageQueryCommand);
		return DataCompanyVcInvestInstitutionAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业投资机构 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyVcInvestInstitutionVO> executeDetail(IdCommand detailCommand) {
		DataCompanyVcInvestInstitutionDO byId = iDataCompanyVcInvestInstitutionService.getById(detailCommand.getId());
		DataCompanyVcInvestInstitutionVO dataCompanyVcInvestInstitutionVO = DataCompanyVcInvestInstitutionAppStructMapping.instance.dataCompanyVcInvestInstitutionDOToDataCompanyVcInvestInstitutionVO(byId);
		return SingleResponse.of(dataCompanyVcInvestInstitutionVO);
	}
	/**
	 * 执行 企业投资机构 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyVcInvestInstitutionVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyVcInvestInstitutionDO byId = iDataCompanyVcInvestInstitutionService.getById(detailForUpdateCommand.getId());
		DataCompanyVcInvestInstitutionVO dataCompanyVcInvestInstitutionVO = DataCompanyVcInvestInstitutionAppStructMapping.instance.dataCompanyVcInvestInstitutionDOToDataCompanyVcInvestInstitutionVO(byId);
		return SingleResponse.of(dataCompanyVcInvestInstitutionVO);
	}


	@Autowired
	public void setIDataCompanyVcInvestInstitutionService(IDataCompanyVcInvestInstitutionService iDataCompanyVcInvestInstitutionService) {
		this.iDataCompanyVcInvestInstitutionService = iDataCompanyVcInvestInstitutionService;
	}
}
