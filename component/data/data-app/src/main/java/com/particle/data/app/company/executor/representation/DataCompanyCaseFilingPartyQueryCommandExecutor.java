package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyCaseFilingPartyAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyCaseFilingPartyQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyCaseFilingPartyVO;
import com.particle.data.infrastructure.company.dos.DataCompanyCaseFilingPartyDO;
import com.particle.data.infrastructure.company.service.IDataCompanyCaseFilingPartyService;
import com.particle.data.client.company.dto.command.representation.DataCompanyCaseFilingPartyPageQueryCommand;
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
 * 企业立案信息当事人 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:37:50
 */
@Component
@Validated
public class DataCompanyCaseFilingPartyQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyCaseFilingPartyService iDataCompanyCaseFilingPartyService;

	/**
	 * 执行 企业立案信息当事人 列表查询指令
	 * @param dataCompanyCaseFilingPartyQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyCaseFilingPartyVO> execute(@Valid DataCompanyCaseFilingPartyQueryListCommand dataCompanyCaseFilingPartyQueryListCommand) {
		List<DataCompanyCaseFilingPartyDO> dataCompanyCaseFilingPartyDO = iDataCompanyCaseFilingPartyService.list(dataCompanyCaseFilingPartyQueryListCommand);
		List<DataCompanyCaseFilingPartyVO> dataCompanyCaseFilingPartyVOs = DataCompanyCaseFilingPartyAppStructMapping.instance.dataCompanyCaseFilingPartyDOsToDataCompanyCaseFilingPartyVOs(dataCompanyCaseFilingPartyDO);
		return MultiResponse.of(dataCompanyCaseFilingPartyVOs);
	}
	/**
	 * 执行 企业立案信息当事人 分页查询指令
	 * @param dataCompanyCaseFilingPartyPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyCaseFilingPartyVO> execute(@Valid DataCompanyCaseFilingPartyPageQueryCommand dataCompanyCaseFilingPartyPageQueryCommand) {
		Page<DataCompanyCaseFilingPartyDO> page = iDataCompanyCaseFilingPartyService.listPage(dataCompanyCaseFilingPartyPageQueryCommand);
		return DataCompanyCaseFilingPartyAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业立案信息当事人 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyCaseFilingPartyVO> executeDetail(IdCommand detailCommand) {
		DataCompanyCaseFilingPartyDO byId = iDataCompanyCaseFilingPartyService.getById(detailCommand.getId());
		DataCompanyCaseFilingPartyVO dataCompanyCaseFilingPartyVO = DataCompanyCaseFilingPartyAppStructMapping.instance.dataCompanyCaseFilingPartyDOToDataCompanyCaseFilingPartyVO(byId);
		return SingleResponse.of(dataCompanyCaseFilingPartyVO);
	}
	/**
	 * 执行 企业立案信息当事人 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyCaseFilingPartyVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyCaseFilingPartyDO byId = iDataCompanyCaseFilingPartyService.getById(detailForUpdateCommand.getId());
		DataCompanyCaseFilingPartyVO dataCompanyCaseFilingPartyVO = DataCompanyCaseFilingPartyAppStructMapping.instance.dataCompanyCaseFilingPartyDOToDataCompanyCaseFilingPartyVO(byId);
		return SingleResponse.of(dataCompanyCaseFilingPartyVO);
	}


	@Autowired
	public void setIDataCompanyCaseFilingPartyService(IDataCompanyCaseFilingPartyService iDataCompanyCaseFilingPartyService) {
		this.iDataCompanyCaseFilingPartyService = iDataCompanyCaseFilingPartyService;
	}
}
