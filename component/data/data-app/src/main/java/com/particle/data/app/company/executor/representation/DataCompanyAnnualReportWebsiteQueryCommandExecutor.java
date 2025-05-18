package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyAnnualReportWebsiteAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportWebsiteQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportWebsiteVO;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportWebsiteDO;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportWebsiteService;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportWebsitePageQueryCommand;
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
 * 企业年报网站网店 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:37:16
 */
@Component
@Validated
public class DataCompanyAnnualReportWebsiteQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyAnnualReportWebsiteService iDataCompanyAnnualReportWebsiteService;

	/**
	 * 执行 企业年报网站网店 列表查询指令
	 * @param dataCompanyAnnualReportWebsiteQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyAnnualReportWebsiteVO> execute(@Valid DataCompanyAnnualReportWebsiteQueryListCommand dataCompanyAnnualReportWebsiteQueryListCommand) {
		List<DataCompanyAnnualReportWebsiteDO> dataCompanyAnnualReportWebsiteDO = iDataCompanyAnnualReportWebsiteService.list(dataCompanyAnnualReportWebsiteQueryListCommand);
		List<DataCompanyAnnualReportWebsiteVO> dataCompanyAnnualReportWebsiteVOs = DataCompanyAnnualReportWebsiteAppStructMapping.instance.dataCompanyAnnualReportWebsiteDOsToDataCompanyAnnualReportWebsiteVOs(dataCompanyAnnualReportWebsiteDO);
		return MultiResponse.of(dataCompanyAnnualReportWebsiteVOs);
	}
	/**
	 * 执行 企业年报网站网店 分页查询指令
	 * @param dataCompanyAnnualReportWebsitePageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportWebsiteVO> execute(@Valid DataCompanyAnnualReportWebsitePageQueryCommand dataCompanyAnnualReportWebsitePageQueryCommand) {
		Page<DataCompanyAnnualReportWebsiteDO> page = iDataCompanyAnnualReportWebsiteService.listPage(dataCompanyAnnualReportWebsitePageQueryCommand);
		return DataCompanyAnnualReportWebsiteAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业年报网站网店 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportWebsiteVO> executeDetail(IdCommand detailCommand) {
		DataCompanyAnnualReportWebsiteDO byId = iDataCompanyAnnualReportWebsiteService.getById(detailCommand.getId());
		DataCompanyAnnualReportWebsiteVO dataCompanyAnnualReportWebsiteVO = DataCompanyAnnualReportWebsiteAppStructMapping.instance.dataCompanyAnnualReportWebsiteDOToDataCompanyAnnualReportWebsiteVO(byId);
		return SingleResponse.of(dataCompanyAnnualReportWebsiteVO);
	}
	/**
	 * 执行 企业年报网站网店 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportWebsiteVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyAnnualReportWebsiteDO byId = iDataCompanyAnnualReportWebsiteService.getById(detailForUpdateCommand.getId());
		DataCompanyAnnualReportWebsiteVO dataCompanyAnnualReportWebsiteVO = DataCompanyAnnualReportWebsiteAppStructMapping.instance.dataCompanyAnnualReportWebsiteDOToDataCompanyAnnualReportWebsiteVO(byId);
		return SingleResponse.of(dataCompanyAnnualReportWebsiteVO);
	}


	@Autowired
	public void setIDataCompanyAnnualReportWebsiteService(IDataCompanyAnnualReportWebsiteService iDataCompanyAnnualReportWebsiteService) {
		this.iDataCompanyAnnualReportWebsiteService = iDataCompanyAnnualReportWebsiteService;
	}
}
