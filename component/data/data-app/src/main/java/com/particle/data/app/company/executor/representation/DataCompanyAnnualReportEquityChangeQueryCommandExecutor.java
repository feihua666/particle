package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyAnnualReportEquityChangeAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportEquityChangeQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportEquityChangeVO;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportEquityChangeDO;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportEquityChangeService;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportEquityChangePageQueryCommand;
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
 * 企业年报股权变更 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:35:57
 */
@Component
@Validated
public class DataCompanyAnnualReportEquityChangeQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyAnnualReportEquityChangeService iDataCompanyAnnualReportEquityChangeService;

	/**
	 * 执行 企业年报股权变更 列表查询指令
	 * @param dataCompanyAnnualReportEquityChangeQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyAnnualReportEquityChangeVO> execute(@Valid DataCompanyAnnualReportEquityChangeQueryListCommand dataCompanyAnnualReportEquityChangeQueryListCommand) {
		List<DataCompanyAnnualReportEquityChangeDO> dataCompanyAnnualReportEquityChangeDO = iDataCompanyAnnualReportEquityChangeService.list(dataCompanyAnnualReportEquityChangeQueryListCommand);
		List<DataCompanyAnnualReportEquityChangeVO> dataCompanyAnnualReportEquityChangeVOs = DataCompanyAnnualReportEquityChangeAppStructMapping.instance.dataCompanyAnnualReportEquityChangeDOsToDataCompanyAnnualReportEquityChangeVOs(dataCompanyAnnualReportEquityChangeDO);
		return MultiResponse.of(dataCompanyAnnualReportEquityChangeVOs);
	}
	/**
	 * 执行 企业年报股权变更 分页查询指令
	 * @param dataCompanyAnnualReportEquityChangePageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportEquityChangeVO> execute(@Valid DataCompanyAnnualReportEquityChangePageQueryCommand dataCompanyAnnualReportEquityChangePageQueryCommand) {
		Page<DataCompanyAnnualReportEquityChangeDO> page = iDataCompanyAnnualReportEquityChangeService.listPage(dataCompanyAnnualReportEquityChangePageQueryCommand);
		return DataCompanyAnnualReportEquityChangeAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业年报股权变更 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportEquityChangeVO> executeDetail(IdCommand detailCommand) {
		DataCompanyAnnualReportEquityChangeDO byId = iDataCompanyAnnualReportEquityChangeService.getById(detailCommand.getId());
		DataCompanyAnnualReportEquityChangeVO dataCompanyAnnualReportEquityChangeVO = DataCompanyAnnualReportEquityChangeAppStructMapping.instance.dataCompanyAnnualReportEquityChangeDOToDataCompanyAnnualReportEquityChangeVO(byId);
		return SingleResponse.of(dataCompanyAnnualReportEquityChangeVO);
	}
	/**
	 * 执行 企业年报股权变更 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportEquityChangeVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyAnnualReportEquityChangeDO byId = iDataCompanyAnnualReportEquityChangeService.getById(detailForUpdateCommand.getId());
		DataCompanyAnnualReportEquityChangeVO dataCompanyAnnualReportEquityChangeVO = DataCompanyAnnualReportEquityChangeAppStructMapping.instance.dataCompanyAnnualReportEquityChangeDOToDataCompanyAnnualReportEquityChangeVO(byId);
		return SingleResponse.of(dataCompanyAnnualReportEquityChangeVO);
	}


	@Autowired
	public void setIDataCompanyAnnualReportEquityChangeService(IDataCompanyAnnualReportEquityChangeService iDataCompanyAnnualReportEquityChangeService) {
		this.iDataCompanyAnnualReportEquityChangeService = iDataCompanyAnnualReportEquityChangeService;
	}
}
