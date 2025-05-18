package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyAnnualReportSocialSecurityAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportSocialSecurityQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportSocialSecurityVO;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportSocialSecurityDO;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportSocialSecurityService;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportSocialSecurityPageQueryCommand;
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
 * 企业年报社保 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:37:03
 */
@Component
@Validated
public class DataCompanyAnnualReportSocialSecurityQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyAnnualReportSocialSecurityService iDataCompanyAnnualReportSocialSecurityService;

	/**
	 * 执行 企业年报社保 列表查询指令
	 * @param dataCompanyAnnualReportSocialSecurityQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyAnnualReportSocialSecurityVO> execute(@Valid DataCompanyAnnualReportSocialSecurityQueryListCommand dataCompanyAnnualReportSocialSecurityQueryListCommand) {
		List<DataCompanyAnnualReportSocialSecurityDO> dataCompanyAnnualReportSocialSecurityDO = iDataCompanyAnnualReportSocialSecurityService.list(dataCompanyAnnualReportSocialSecurityQueryListCommand);
		List<DataCompanyAnnualReportSocialSecurityVO> dataCompanyAnnualReportSocialSecurityVOs = DataCompanyAnnualReportSocialSecurityAppStructMapping.instance.dataCompanyAnnualReportSocialSecurityDOsToDataCompanyAnnualReportSocialSecurityVOs(dataCompanyAnnualReportSocialSecurityDO);
		return MultiResponse.of(dataCompanyAnnualReportSocialSecurityVOs);
	}
	/**
	 * 执行 企业年报社保 分页查询指令
	 * @param dataCompanyAnnualReportSocialSecurityPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportSocialSecurityVO> execute(@Valid DataCompanyAnnualReportSocialSecurityPageQueryCommand dataCompanyAnnualReportSocialSecurityPageQueryCommand) {
		Page<DataCompanyAnnualReportSocialSecurityDO> page = iDataCompanyAnnualReportSocialSecurityService.listPage(dataCompanyAnnualReportSocialSecurityPageQueryCommand);
		return DataCompanyAnnualReportSocialSecurityAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业年报社保 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportSocialSecurityVO> executeDetail(IdCommand detailCommand) {
		DataCompanyAnnualReportSocialSecurityDO byId = iDataCompanyAnnualReportSocialSecurityService.getById(detailCommand.getId());
		DataCompanyAnnualReportSocialSecurityVO dataCompanyAnnualReportSocialSecurityVO = DataCompanyAnnualReportSocialSecurityAppStructMapping.instance.dataCompanyAnnualReportSocialSecurityDOToDataCompanyAnnualReportSocialSecurityVO(byId);
		return SingleResponse.of(dataCompanyAnnualReportSocialSecurityVO);
	}
	/**
	 * 执行 企业年报社保 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportSocialSecurityVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyAnnualReportSocialSecurityDO byId = iDataCompanyAnnualReportSocialSecurityService.getById(detailForUpdateCommand.getId());
		DataCompanyAnnualReportSocialSecurityVO dataCompanyAnnualReportSocialSecurityVO = DataCompanyAnnualReportSocialSecurityAppStructMapping.instance.dataCompanyAnnualReportSocialSecurityDOToDataCompanyAnnualReportSocialSecurityVO(byId);
		return SingleResponse.of(dataCompanyAnnualReportSocialSecurityVO);
	}


	@Autowired
	public void setIDataCompanyAnnualReportSocialSecurityService(IDataCompanyAnnualReportSocialSecurityService iDataCompanyAnnualReportSocialSecurityService) {
		this.iDataCompanyAnnualReportSocialSecurityService = iDataCompanyAnnualReportSocialSecurityService;
	}
}
