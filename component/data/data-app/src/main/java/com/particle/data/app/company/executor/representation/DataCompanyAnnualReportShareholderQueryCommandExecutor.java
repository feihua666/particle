package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyAnnualReportShareholderAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportShareholderQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportShareholderVO;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportShareholderDO;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportShareholderService;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportShareholderPageQueryCommand;
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
 * 企业年报股东 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:36:37
 */
@Component
@Validated
public class DataCompanyAnnualReportShareholderQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyAnnualReportShareholderService iDataCompanyAnnualReportShareholderService;

	/**
	 * 执行 企业年报股东 列表查询指令
	 * @param dataCompanyAnnualReportShareholderQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyAnnualReportShareholderVO> execute(@Valid DataCompanyAnnualReportShareholderQueryListCommand dataCompanyAnnualReportShareholderQueryListCommand) {
		List<DataCompanyAnnualReportShareholderDO> dataCompanyAnnualReportShareholderDO = iDataCompanyAnnualReportShareholderService.list(dataCompanyAnnualReportShareholderQueryListCommand);
		List<DataCompanyAnnualReportShareholderVO> dataCompanyAnnualReportShareholderVOs = DataCompanyAnnualReportShareholderAppStructMapping.instance.dataCompanyAnnualReportShareholderDOsToDataCompanyAnnualReportShareholderVOs(dataCompanyAnnualReportShareholderDO);
		return MultiResponse.of(dataCompanyAnnualReportShareholderVOs);
	}
	/**
	 * 执行 企业年报股东 分页查询指令
	 * @param dataCompanyAnnualReportShareholderPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportShareholderVO> execute(@Valid DataCompanyAnnualReportShareholderPageQueryCommand dataCompanyAnnualReportShareholderPageQueryCommand) {
		Page<DataCompanyAnnualReportShareholderDO> page = iDataCompanyAnnualReportShareholderService.listPage(dataCompanyAnnualReportShareholderPageQueryCommand);
		return DataCompanyAnnualReportShareholderAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业年报股东 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportShareholderVO> executeDetail(IdCommand detailCommand) {
		DataCompanyAnnualReportShareholderDO byId = iDataCompanyAnnualReportShareholderService.getById(detailCommand.getId());
		DataCompanyAnnualReportShareholderVO dataCompanyAnnualReportShareholderVO = DataCompanyAnnualReportShareholderAppStructMapping.instance.dataCompanyAnnualReportShareholderDOToDataCompanyAnnualReportShareholderVO(byId);
		return SingleResponse.of(dataCompanyAnnualReportShareholderVO);
	}
	/**
	 * 执行 企业年报股东 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportShareholderVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyAnnualReportShareholderDO byId = iDataCompanyAnnualReportShareholderService.getById(detailForUpdateCommand.getId());
		DataCompanyAnnualReportShareholderVO dataCompanyAnnualReportShareholderVO = DataCompanyAnnualReportShareholderAppStructMapping.instance.dataCompanyAnnualReportShareholderDOToDataCompanyAnnualReportShareholderVO(byId);
		return SingleResponse.of(dataCompanyAnnualReportShareholderVO);
	}


	@Autowired
	public void setIDataCompanyAnnualReportShareholderService(IDataCompanyAnnualReportShareholderService iDataCompanyAnnualReportShareholderService) {
		this.iDataCompanyAnnualReportShareholderService = iDataCompanyAnnualReportShareholderService;
	}
}
