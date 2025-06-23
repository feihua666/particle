package com.particle.data.app.company.executor.representation.exwarehouse;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyPrimeStaffPositionAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyPrimeStaffPositionExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyPrimeStaffPositionExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyPrimeStaffPositionDO;
import com.particle.data.infrastructure.company.service.IDataCompanyPrimeStaffPositionService;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 企业主要人员职位出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyPrimeStaffPositionExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyPrimeStaffPositionService iDataCompanyPrimeStaffPositionService;

	/**
	 * 企业主要人员职位出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyPrimeStaffPositionExWarehouseVO> exWarehouse(@Valid DataCompanyPrimeStaffPositionExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyPrimeStaffPositionDO> dataCompanyPrimeStaffPositionDOPage = iDataCompanyPrimeStaffPositionService.listPageByCompanyPrimeStaffId(dataCompanyExWarehouseQueryCommand.getCompanyPrimeStaffId(), dataCompanyExWarehouseQueryCommand);
		if (dataCompanyPrimeStaffPositionDOPage == null || dataCompanyPrimeStaffPositionDOPage.getRecords() == null || dataCompanyPrimeStaffPositionDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyPrimeStaffPositionAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyPrimeStaffPositionDOPage);
	}
	/**
	 * 企业主要人员职位出库
	 * @param positionName
	 * @return
	 */
	public SingleResponse<DataCompanyPrimeStaffPositionExWarehouseVO> exWarehouseByCompanyPrimeStaffIdAndPositionName(Long companyPrimeStaffId,String positionName) {
		DataCompanyPrimeStaffPositionDO dataCompanyPrimeStaffPositionDO = iDataCompanyPrimeStaffPositionService.getByCompanyPrimeStaffIdAndPositionName(companyPrimeStaffId,positionName);
		if (dataCompanyPrimeStaffPositionDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyPrimeStaffPositionExWarehouseVO dataCompanyPrimeStaffPositionExWarehouseVO = DataCompanyPrimeStaffPositionAppStructMapping.instance.dataCompanyPrimeStaffPositionDOToDataCompanyPrimeStaffPositionExWarehouseVO(dataCompanyPrimeStaffPositionDO);
		return SingleResponse.of(dataCompanyPrimeStaffPositionExWarehouseVO);
	}
	/**
	 * 企业主要人员职位出库
	 * @param companyPrimeStaffId
	 * @returns
	 */
	public MultiResponse<DataCompanyPrimeStaffPositionExWarehouseVO> exWarehouseByCompanyPrimeStaffId(Long companyPrimeStaffId) {
		List<DataCompanyPrimeStaffPositionDO> dataCompanyPrimeStaffPositionDOs = iDataCompanyPrimeStaffPositionService.listByCompanyPrimeStaffId(companyPrimeStaffId);
		if (dataCompanyPrimeStaffPositionDOs == null || dataCompanyPrimeStaffPositionDOs.isEmpty()) {
			return MultiResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		List<DataCompanyPrimeStaffPositionExWarehouseVO> dataCompanyPrimeStaffPositionExWarehouseVOs = DataCompanyPrimeStaffPositionAppStructMapping.instance.dataCompanyPrimeStaffPositionDOsToDataCompanyPrimeStaffPositionExWarehouseVOs(dataCompanyPrimeStaffPositionDOs);
		return MultiResponse.of(dataCompanyPrimeStaffPositionExWarehouseVOs);
	}
	/**
	 * 企业主要人员职位出库
	 * @param companyPrimeStaffIds
	 * @returns
	 */
	public MultiResponse<DataCompanyPrimeStaffPositionExWarehouseVO> exWarehouseByCompanyPrimeStaffIds(List<Long> companyPrimeStaffIds) {
		List<DataCompanyPrimeStaffPositionDO> dataCompanyPrimeStaffPositionDOs = iDataCompanyPrimeStaffPositionService.getByCompanyPrimeStaffIds(companyPrimeStaffIds);
		if (dataCompanyPrimeStaffPositionDOs == null || dataCompanyPrimeStaffPositionDOs.isEmpty()) {
			return MultiResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		List<DataCompanyPrimeStaffPositionExWarehouseVO> dataCompanyPrimeStaffPositionExWarehouseVOs = DataCompanyPrimeStaffPositionAppStructMapping.instance.dataCompanyPrimeStaffPositionDOsToDataCompanyPrimeStaffPositionExWarehouseVOs(dataCompanyPrimeStaffPositionDOs);
		return MultiResponse.of(dataCompanyPrimeStaffPositionExWarehouseVOs);
	}

	/**
	 * 企业主要人员职位出库
	 * @param companyPrimeStaffIds
	 * @return
	 */
	public SingleResponse<Map<Long, List<DataCompanyPrimeStaffPositionExWarehouseVO>>> exWarehouseGroupByCompanyPrimeStaffIdByCompanyPrimeStaffIds(List<Long> companyPrimeStaffIds) {
		List<DataCompanyPrimeStaffPositionDO> dataCompanyPrimeStaffPositionDOs = iDataCompanyPrimeStaffPositionService.getByCompanyPrimeStaffIds(companyPrimeStaffIds);
		if (dataCompanyPrimeStaffPositionDOs == null || dataCompanyPrimeStaffPositionDOs.isEmpty()) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		List<DataCompanyPrimeStaffPositionExWarehouseVO> dataCompanyPrimeStaffPositionExWarehouseVOs = DataCompanyPrimeStaffPositionAppStructMapping.instance.dataCompanyPrimeStaffPositionDOsToDataCompanyPrimeStaffPositionExWarehouseVOs(dataCompanyPrimeStaffPositionDOs);
		Map<Long, List<DataCompanyPrimeStaffPositionExWarehouseVO>> group = dataCompanyPrimeStaffPositionExWarehouseVOs.stream().collect(Collectors.groupingBy(DataCompanyPrimeStaffPositionExWarehouseVO::getCompanyPrimeStaffId));
		return SingleResponse.of(group);
	}
	/**
	 * 企业主要人员职位出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyPrimeStaffPositionExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyPrimeStaffPositionDO dataCompanyPrimeStaffPositionDO = iDataCompanyPrimeStaffPositionService.getById(id);
		if (dataCompanyPrimeStaffPositionDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyPrimeStaffPositionExWarehouseVO dataCompanyPrimeStaffPositionExWarehouseVO = DataCompanyPrimeStaffPositionAppStructMapping.instance.dataCompanyPrimeStaffPositionDOToDataCompanyPrimeStaffPositionExWarehouseVO(dataCompanyPrimeStaffPositionDO);
		return SingleResponse.of(dataCompanyPrimeStaffPositionExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyPrimeStaffPositionService(IDataCompanyPrimeStaffPositionService iDataCompanyPrimeStaffPositionService) {
		this.iDataCompanyPrimeStaffPositionService = iDataCompanyPrimeStaffPositionService;
	}
}
