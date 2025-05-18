package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyCaseFilingAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyCaseFilingQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyCaseFilingVO;
import com.particle.data.infrastructure.company.dos.DataCompanyCaseFilingDO;
import com.particle.data.infrastructure.company.service.IDataCompanyCaseFilingService;
import com.particle.data.client.company.dto.command.representation.DataCompanyCaseFilingPageQueryCommand;
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
 * 企业立案信息 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:37:36
 */
@Component
@Validated
public class DataCompanyCaseFilingQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyCaseFilingService iDataCompanyCaseFilingService;

	/**
	 * 执行 企业立案信息 列表查询指令
	 * @param dataCompanyCaseFilingQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyCaseFilingVO> execute(@Valid DataCompanyCaseFilingQueryListCommand dataCompanyCaseFilingQueryListCommand) {
		List<DataCompanyCaseFilingDO> dataCompanyCaseFilingDO = iDataCompanyCaseFilingService.list(dataCompanyCaseFilingQueryListCommand);
		List<DataCompanyCaseFilingVO> dataCompanyCaseFilingVOs = DataCompanyCaseFilingAppStructMapping.instance.dataCompanyCaseFilingDOsToDataCompanyCaseFilingVOs(dataCompanyCaseFilingDO);
		return MultiResponse.of(dataCompanyCaseFilingVOs);
	}
	/**
	 * 执行 企业立案信息 分页查询指令
	 * @param dataCompanyCaseFilingPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyCaseFilingVO> execute(@Valid DataCompanyCaseFilingPageQueryCommand dataCompanyCaseFilingPageQueryCommand) {
		Page<DataCompanyCaseFilingDO> page = iDataCompanyCaseFilingService.listPage(dataCompanyCaseFilingPageQueryCommand);
		return DataCompanyCaseFilingAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业立案信息 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyCaseFilingVO> executeDetail(IdCommand detailCommand) {
		DataCompanyCaseFilingDO byId = iDataCompanyCaseFilingService.getById(detailCommand.getId());
		DataCompanyCaseFilingVO dataCompanyCaseFilingVO = DataCompanyCaseFilingAppStructMapping.instance.dataCompanyCaseFilingDOToDataCompanyCaseFilingVO(byId);
		return SingleResponse.of(dataCompanyCaseFilingVO);
	}
	/**
	 * 执行 企业立案信息 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyCaseFilingVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyCaseFilingDO byId = iDataCompanyCaseFilingService.getById(detailForUpdateCommand.getId());
		DataCompanyCaseFilingVO dataCompanyCaseFilingVO = DataCompanyCaseFilingAppStructMapping.instance.dataCompanyCaseFilingDOToDataCompanyCaseFilingVO(byId);
		return SingleResponse.of(dataCompanyCaseFilingVO);
	}


	@Autowired
	public void setIDataCompanyCaseFilingService(IDataCompanyCaseFilingService iDataCompanyCaseFilingService) {
		this.iDataCompanyCaseFilingService = iDataCompanyCaseFilingService;
	}
}
