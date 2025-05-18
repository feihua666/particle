package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyVcFinancingAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcFinancingQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcFinancingVO;
import com.particle.data.infrastructure.company.dos.DataCompanyVcFinancingDO;
import com.particle.data.infrastructure.company.service.IDataCompanyVcFinancingService;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcFinancingPageQueryCommand;
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
 * 企业融资 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:46:43
 */
@Component
@Validated
public class DataCompanyVcFinancingQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyVcFinancingService iDataCompanyVcFinancingService;

	/**
	 * 执行 企业融资 列表查询指令
	 * @param dataCompanyVcFinancingQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyVcFinancingVO> execute(@Valid DataCompanyVcFinancingQueryListCommand dataCompanyVcFinancingQueryListCommand) {
		List<DataCompanyVcFinancingDO> dataCompanyVcFinancingDO = iDataCompanyVcFinancingService.list(dataCompanyVcFinancingQueryListCommand);
		List<DataCompanyVcFinancingVO> dataCompanyVcFinancingVOs = DataCompanyVcFinancingAppStructMapping.instance.dataCompanyVcFinancingDOsToDataCompanyVcFinancingVOs(dataCompanyVcFinancingDO);
		return MultiResponse.of(dataCompanyVcFinancingVOs);
	}
	/**
	 * 执行 企业融资 分页查询指令
	 * @param dataCompanyVcFinancingPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyVcFinancingVO> execute(@Valid DataCompanyVcFinancingPageQueryCommand dataCompanyVcFinancingPageQueryCommand) {
		Page<DataCompanyVcFinancingDO> page = iDataCompanyVcFinancingService.listPage(dataCompanyVcFinancingPageQueryCommand);
		return DataCompanyVcFinancingAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业融资 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyVcFinancingVO> executeDetail(IdCommand detailCommand) {
		DataCompanyVcFinancingDO byId = iDataCompanyVcFinancingService.getById(detailCommand.getId());
		DataCompanyVcFinancingVO dataCompanyVcFinancingVO = DataCompanyVcFinancingAppStructMapping.instance.dataCompanyVcFinancingDOToDataCompanyVcFinancingVO(byId);
		return SingleResponse.of(dataCompanyVcFinancingVO);
	}
	/**
	 * 执行 企业融资 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyVcFinancingVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyVcFinancingDO byId = iDataCompanyVcFinancingService.getById(detailForUpdateCommand.getId());
		DataCompanyVcFinancingVO dataCompanyVcFinancingVO = DataCompanyVcFinancingAppStructMapping.instance.dataCompanyVcFinancingDOToDataCompanyVcFinancingVO(byId);
		return SingleResponse.of(dataCompanyVcFinancingVO);
	}


	@Autowired
	public void setIDataCompanyVcFinancingService(IDataCompanyVcFinancingService iDataCompanyVcFinancingService) {
		this.iDataCompanyVcFinancingService = iDataCompanyVcFinancingService;
	}
}
