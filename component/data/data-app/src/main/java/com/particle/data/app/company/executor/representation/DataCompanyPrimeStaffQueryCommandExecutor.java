package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyPrimeStaffAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyPrimeStaffQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyPrimeStaffVO;
import com.particle.data.infrastructure.company.dos.DataCompanyPrimeStaffDO;
import com.particle.data.infrastructure.company.service.IDataCompanyPrimeStaffService;
import com.particle.data.client.company.dto.command.representation.DataCompanyPrimeStaffPageQueryCommand;
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
 * 企业主要人员 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-06-20 16:18:44
 */
@Component
@Validated
public class DataCompanyPrimeStaffQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyPrimeStaffService iDataCompanyPrimeStaffService;

	/**
	 * 执行 企业主要人员 列表查询指令
	 * @param dataCompanyPrimeStaffQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyPrimeStaffVO> execute(@Valid DataCompanyPrimeStaffQueryListCommand dataCompanyPrimeStaffQueryListCommand) {
		List<DataCompanyPrimeStaffDO> dataCompanyPrimeStaffDO = iDataCompanyPrimeStaffService.list(dataCompanyPrimeStaffQueryListCommand);
		List<DataCompanyPrimeStaffVO> dataCompanyPrimeStaffVOs = DataCompanyPrimeStaffAppStructMapping.instance.dataCompanyPrimeStaffDOsToDataCompanyPrimeStaffVOs(dataCompanyPrimeStaffDO);
		return MultiResponse.of(dataCompanyPrimeStaffVOs);
	}
	/**
	 * 执行 企业主要人员 分页查询指令
	 * @param dataCompanyPrimeStaffPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyPrimeStaffVO> execute(@Valid DataCompanyPrimeStaffPageQueryCommand dataCompanyPrimeStaffPageQueryCommand) {
		Page<DataCompanyPrimeStaffDO> page = iDataCompanyPrimeStaffService.listPage(dataCompanyPrimeStaffPageQueryCommand);
		return DataCompanyPrimeStaffAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业主要人员 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyPrimeStaffVO> executeDetail(IdCommand detailCommand) {
		DataCompanyPrimeStaffDO byId = iDataCompanyPrimeStaffService.getById(detailCommand.getId());
		DataCompanyPrimeStaffVO dataCompanyPrimeStaffVO = DataCompanyPrimeStaffAppStructMapping.instance.dataCompanyPrimeStaffDOToDataCompanyPrimeStaffVO(byId);
		return SingleResponse.of(dataCompanyPrimeStaffVO);
	}
	/**
	 * 执行 企业主要人员 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyPrimeStaffVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyPrimeStaffDO byId = iDataCompanyPrimeStaffService.getById(detailForUpdateCommand.getId());
		DataCompanyPrimeStaffVO dataCompanyPrimeStaffVO = DataCompanyPrimeStaffAppStructMapping.instance.dataCompanyPrimeStaffDOToDataCompanyPrimeStaffVO(byId);
		return SingleResponse.of(dataCompanyPrimeStaffVO);
	}


	@Autowired
	public void setIDataCompanyPrimeStaffService(IDataCompanyPrimeStaffService iDataCompanyPrimeStaffService) {
		this.iDataCompanyPrimeStaffService = iDataCompanyPrimeStaffService;
	}
}
