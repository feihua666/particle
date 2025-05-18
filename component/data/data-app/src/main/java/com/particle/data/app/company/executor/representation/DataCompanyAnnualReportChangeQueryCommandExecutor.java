package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyAnnualReportChangeAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportChangeQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportChangeVO;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportChangeDO;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportChangeService;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportChangePageQueryCommand;
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
 * 企业年报变更 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:35:43
 */
@Component
@Validated
public class DataCompanyAnnualReportChangeQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyAnnualReportChangeService iDataCompanyAnnualReportChangeService;

	/**
	 * 执行 企业年报变更 列表查询指令
	 * @param dataCompanyAnnualReportChangeQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyAnnualReportChangeVO> execute(@Valid DataCompanyAnnualReportChangeQueryListCommand dataCompanyAnnualReportChangeQueryListCommand) {
		List<DataCompanyAnnualReportChangeDO> dataCompanyAnnualReportChangeDO = iDataCompanyAnnualReportChangeService.list(dataCompanyAnnualReportChangeQueryListCommand);
		List<DataCompanyAnnualReportChangeVO> dataCompanyAnnualReportChangeVOs = DataCompanyAnnualReportChangeAppStructMapping.instance.dataCompanyAnnualReportChangeDOsToDataCompanyAnnualReportChangeVOs(dataCompanyAnnualReportChangeDO);
		return MultiResponse.of(dataCompanyAnnualReportChangeVOs);
	}
	/**
	 * 执行 企业年报变更 分页查询指令
	 * @param dataCompanyAnnualReportChangePageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportChangeVO> execute(@Valid DataCompanyAnnualReportChangePageQueryCommand dataCompanyAnnualReportChangePageQueryCommand) {
		Page<DataCompanyAnnualReportChangeDO> page = iDataCompanyAnnualReportChangeService.listPage(dataCompanyAnnualReportChangePageQueryCommand);
		return DataCompanyAnnualReportChangeAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业年报变更 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportChangeVO> executeDetail(IdCommand detailCommand) {
		DataCompanyAnnualReportChangeDO byId = iDataCompanyAnnualReportChangeService.getById(detailCommand.getId());
		DataCompanyAnnualReportChangeVO dataCompanyAnnualReportChangeVO = DataCompanyAnnualReportChangeAppStructMapping.instance.dataCompanyAnnualReportChangeDOToDataCompanyAnnualReportChangeVO(byId);
		return SingleResponse.of(dataCompanyAnnualReportChangeVO);
	}
	/**
	 * 执行 企业年报变更 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportChangeVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyAnnualReportChangeDO byId = iDataCompanyAnnualReportChangeService.getById(detailForUpdateCommand.getId());
		DataCompanyAnnualReportChangeVO dataCompanyAnnualReportChangeVO = DataCompanyAnnualReportChangeAppStructMapping.instance.dataCompanyAnnualReportChangeDOToDataCompanyAnnualReportChangeVO(byId);
		return SingleResponse.of(dataCompanyAnnualReportChangeVO);
	}


	@Autowired
	public void setIDataCompanyAnnualReportChangeService(IDataCompanyAnnualReportChangeService iDataCompanyAnnualReportChangeService) {
		this.iDataCompanyAnnualReportChangeService = iDataCompanyAnnualReportChangeService;
	}
}
