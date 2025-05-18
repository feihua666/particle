package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyAnnualReportAssetsAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportAssetsQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportAssetsVO;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportAssetsDO;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportAssetsService;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportAssetsPageQueryCommand;
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
 * 企业资产状况信息 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:35:31
 */
@Component
@Validated
public class DataCompanyAnnualReportAssetsQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyAnnualReportAssetsService iDataCompanyAnnualReportAssetsService;

	/**
	 * 执行 企业资产状况信息 列表查询指令
	 * @param dataCompanyAnnualReportAssetsQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyAnnualReportAssetsVO> execute(@Valid DataCompanyAnnualReportAssetsQueryListCommand dataCompanyAnnualReportAssetsQueryListCommand) {
		List<DataCompanyAnnualReportAssetsDO> dataCompanyAnnualReportAssetsDO = iDataCompanyAnnualReportAssetsService.list(dataCompanyAnnualReportAssetsQueryListCommand);
		List<DataCompanyAnnualReportAssetsVO> dataCompanyAnnualReportAssetsVOs = DataCompanyAnnualReportAssetsAppStructMapping.instance.dataCompanyAnnualReportAssetsDOsToDataCompanyAnnualReportAssetsVOs(dataCompanyAnnualReportAssetsDO);
		return MultiResponse.of(dataCompanyAnnualReportAssetsVOs);
	}
	/**
	 * 执行 企业资产状况信息 分页查询指令
	 * @param dataCompanyAnnualReportAssetsPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportAssetsVO> execute(@Valid DataCompanyAnnualReportAssetsPageQueryCommand dataCompanyAnnualReportAssetsPageQueryCommand) {
		Page<DataCompanyAnnualReportAssetsDO> page = iDataCompanyAnnualReportAssetsService.listPage(dataCompanyAnnualReportAssetsPageQueryCommand);
		return DataCompanyAnnualReportAssetsAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业资产状况信息 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportAssetsVO> executeDetail(IdCommand detailCommand) {
		DataCompanyAnnualReportAssetsDO byId = iDataCompanyAnnualReportAssetsService.getById(detailCommand.getId());
		DataCompanyAnnualReportAssetsVO dataCompanyAnnualReportAssetsVO = DataCompanyAnnualReportAssetsAppStructMapping.instance.dataCompanyAnnualReportAssetsDOToDataCompanyAnnualReportAssetsVO(byId);
		return SingleResponse.of(dataCompanyAnnualReportAssetsVO);
	}
	/**
	 * 执行 企业资产状况信息 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportAssetsVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyAnnualReportAssetsDO byId = iDataCompanyAnnualReportAssetsService.getById(detailForUpdateCommand.getId());
		DataCompanyAnnualReportAssetsVO dataCompanyAnnualReportAssetsVO = DataCompanyAnnualReportAssetsAppStructMapping.instance.dataCompanyAnnualReportAssetsDOToDataCompanyAnnualReportAssetsVO(byId);
		return SingleResponse.of(dataCompanyAnnualReportAssetsVO);
	}


	@Autowired
	public void setIDataCompanyAnnualReportAssetsService(IDataCompanyAnnualReportAssetsService iDataCompanyAnnualReportAssetsService) {
		this.iDataCompanyAnnualReportAssetsService = iDataCompanyAnnualReportAssetsService;
	}
}
