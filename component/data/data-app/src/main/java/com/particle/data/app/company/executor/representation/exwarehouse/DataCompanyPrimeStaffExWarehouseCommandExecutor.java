package com.particle.data.app.company.executor.representation.exwarehouse;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyPrimeStaffAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyPrimeStaffExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyPrimeStaffExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyPrimeStaffDO;
import com.particle.data.infrastructure.company.service.IDataCompanyPrimeStaffService;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业主要人员出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyPrimeStaffExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyPrimeStaffService iDataCompanyPrimeStaffService;

	/**
	 * 企业主要人员出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyPrimeStaffExWarehouseVO> exWarehouse(@Valid DataCompanyPrimeStaffExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyPrimeStaffDO> dataCompanyPrimeStaffDOPage = iDataCompanyPrimeStaffService.listPageByCompanyId(dataCompanyExWarehouseQueryCommand.getCompanyId(), dataCompanyExWarehouseQueryCommand);
		if (dataCompanyPrimeStaffDOPage == null || dataCompanyPrimeStaffDOPage.getRecords() == null || dataCompanyPrimeStaffDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyPrimeStaffAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyPrimeStaffDOPage);
	}
	/**
	 * 企业主要人员出库
	 * @param staffName
	 * @return
	 */
	public SingleResponse<DataCompanyPrimeStaffExWarehouseVO> exWarehouseByCompanyIdAndStaffName(Long companyId,String staffName) {
		DataCompanyPrimeStaffDO dataCompanyPrimeStaffDO = iDataCompanyPrimeStaffService.getByCompanyIdAndStaffName(companyId,staffName);
		if (dataCompanyPrimeStaffDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyPrimeStaffExWarehouseVO dataCompanyPrimeStaffExWarehouseVO = DataCompanyPrimeStaffAppStructMapping.instance.dataCompanyPrimeStaffDOToDataCompanyPrimeStaffExWarehouseVO(dataCompanyPrimeStaffDO);
		return SingleResponse.of(dataCompanyPrimeStaffExWarehouseVO);
	}
	/**
	 * 企业主要人员出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyPrimeStaffExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyPrimeStaffDO dataCompanyPrimeStaffDO = iDataCompanyPrimeStaffService.getById(id);
		if (dataCompanyPrimeStaffDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyPrimeStaffExWarehouseVO dataCompanyPrimeStaffExWarehouseVO = DataCompanyPrimeStaffAppStructMapping.instance.dataCompanyPrimeStaffDOToDataCompanyPrimeStaffExWarehouseVO(dataCompanyPrimeStaffDO);
		return SingleResponse.of(dataCompanyPrimeStaffExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyPrimeStaffService(IDataCompanyPrimeStaffService iDataCompanyPrimeStaffService) {
		this.iDataCompanyPrimeStaffService = iDataCompanyPrimeStaffService;
	}
}
