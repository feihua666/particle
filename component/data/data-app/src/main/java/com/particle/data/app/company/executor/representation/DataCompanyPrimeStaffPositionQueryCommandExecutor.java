package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyPrimeStaffPositionAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyPrimeStaffPositionQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyPrimeStaffPositionVO;
import com.particle.data.infrastructure.company.dos.DataCompanyPrimeStaffPositionDO;
import com.particle.data.infrastructure.company.service.IDataCompanyPrimeStaffPositionService;
import com.particle.data.client.company.dto.command.representation.DataCompanyPrimeStaffPositionPageQueryCommand;
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
 * 企业主要人员职位 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-06-22 15:07:33
 */
@Component
@Validated
public class DataCompanyPrimeStaffPositionQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyPrimeStaffPositionService iDataCompanyPrimeStaffPositionService;

	/**
	 * 执行 企业主要人员职位 列表查询指令
	 * @param dataCompanyPrimeStaffPositionQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyPrimeStaffPositionVO> execute(@Valid DataCompanyPrimeStaffPositionQueryListCommand dataCompanyPrimeStaffPositionQueryListCommand) {
		List<DataCompanyPrimeStaffPositionDO> dataCompanyPrimeStaffPositionDO = iDataCompanyPrimeStaffPositionService.list(dataCompanyPrimeStaffPositionQueryListCommand);
		List<DataCompanyPrimeStaffPositionVO> dataCompanyPrimeStaffPositionVOs = DataCompanyPrimeStaffPositionAppStructMapping.instance.dataCompanyPrimeStaffPositionDOsToDataCompanyPrimeStaffPositionVOs(dataCompanyPrimeStaffPositionDO);
		return MultiResponse.of(dataCompanyPrimeStaffPositionVOs);
	}
	/**
	 * 执行 企业主要人员职位 分页查询指令
	 * @param dataCompanyPrimeStaffPositionPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyPrimeStaffPositionVO> execute(@Valid DataCompanyPrimeStaffPositionPageQueryCommand dataCompanyPrimeStaffPositionPageQueryCommand) {
		Page<DataCompanyPrimeStaffPositionDO> page = iDataCompanyPrimeStaffPositionService.listPage(dataCompanyPrimeStaffPositionPageQueryCommand);
		return DataCompanyPrimeStaffPositionAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业主要人员职位 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyPrimeStaffPositionVO> executeDetail(IdCommand detailCommand) {
		DataCompanyPrimeStaffPositionDO byId = iDataCompanyPrimeStaffPositionService.getById(detailCommand.getId());
		DataCompanyPrimeStaffPositionVO dataCompanyPrimeStaffPositionVO = DataCompanyPrimeStaffPositionAppStructMapping.instance.dataCompanyPrimeStaffPositionDOToDataCompanyPrimeStaffPositionVO(byId);
		return SingleResponse.of(dataCompanyPrimeStaffPositionVO);
	}
	/**
	 * 执行 企业主要人员职位 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyPrimeStaffPositionVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyPrimeStaffPositionDO byId = iDataCompanyPrimeStaffPositionService.getById(detailForUpdateCommand.getId());
		DataCompanyPrimeStaffPositionVO dataCompanyPrimeStaffPositionVO = DataCompanyPrimeStaffPositionAppStructMapping.instance.dataCompanyPrimeStaffPositionDOToDataCompanyPrimeStaffPositionVO(byId);
		return SingleResponse.of(dataCompanyPrimeStaffPositionVO);
	}


	@Autowired
	public void setIDataCompanyPrimeStaffPositionService(IDataCompanyPrimeStaffPositionService iDataCompanyPrimeStaffPositionService) {
		this.iDataCompanyPrimeStaffPositionService = iDataCompanyPrimeStaffPositionService;
	}
}
