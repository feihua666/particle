package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyStatisticAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyStatisticQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyStatisticVO;
import com.particle.data.infrastructure.company.dos.DataCompanyStatisticDO;
import com.particle.data.infrastructure.company.service.IDataCompanyStatisticService;
import com.particle.data.client.company.dto.command.representation.DataCompanyStatisticPageQueryCommand;
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
 * 企业统计 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-06-04 15:53:01
 */
@Component
@Validated
public class DataCompanyStatisticQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyStatisticService iDataCompanyStatisticService;

	/**
	 * 执行 企业统计 列表查询指令
	 * @param dataCompanyStatisticQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyStatisticVO> execute(@Valid DataCompanyStatisticQueryListCommand dataCompanyStatisticQueryListCommand) {
		List<DataCompanyStatisticDO> dataCompanyStatisticDO = iDataCompanyStatisticService.list(dataCompanyStatisticQueryListCommand);
		List<DataCompanyStatisticVO> dataCompanyStatisticVOs = DataCompanyStatisticAppStructMapping.instance.dataCompanyStatisticDOsToDataCompanyStatisticVOs(dataCompanyStatisticDO);
		return MultiResponse.of(dataCompanyStatisticVOs);
	}
	/**
	 * 执行 企业统计 分页查询指令
	 * @param dataCompanyStatisticPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyStatisticVO> execute(@Valid DataCompanyStatisticPageQueryCommand dataCompanyStatisticPageQueryCommand) {
		Page<DataCompanyStatisticDO> page = iDataCompanyStatisticService.listPage(dataCompanyStatisticPageQueryCommand);
		return DataCompanyStatisticAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业统计 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyStatisticVO> executeDetail(IdCommand detailCommand) {
		DataCompanyStatisticDO byId = iDataCompanyStatisticService.getById(detailCommand.getId());
		DataCompanyStatisticVO dataCompanyStatisticVO = DataCompanyStatisticAppStructMapping.instance.dataCompanyStatisticDOToDataCompanyStatisticVO(byId);
		return SingleResponse.of(dataCompanyStatisticVO);
	}
	/**
	 * 执行 企业统计 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyStatisticVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyStatisticDO byId = iDataCompanyStatisticService.getById(detailForUpdateCommand.getId());
		DataCompanyStatisticVO dataCompanyStatisticVO = DataCompanyStatisticAppStructMapping.instance.dataCompanyStatisticDOToDataCompanyStatisticVO(byId);
		return SingleResponse.of(dataCompanyStatisticVO);
	}


	@Autowired
	public void setIDataCompanyStatisticService(IDataCompanyStatisticService iDataCompanyStatisticService) {
		this.iDataCompanyStatisticService = iDataCompanyStatisticService;
	}
}
