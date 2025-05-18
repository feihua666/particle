package com.particle.data.app.company.executor.representation.exwarehouse;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyCaseFilingAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyCaseFilingExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCaseFilingExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyCaseFilingDO;
import com.particle.data.infrastructure.company.service.IDataCompanyCaseFilingService;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业立案信息出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyCaseFilingExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyCaseFilingService iDataCompanyCaseFilingService;

	/**
	 * 企业立案信息出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyCaseFilingExWarehouseVO> exWarehouse(@Valid DataCompanyCaseFilingExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyCaseFilingDO> dataCompanyCaseFilingDOPage = null;
        if (dataCompanyExWarehouseQueryCommand.getCompanyId() != null) {
			dataCompanyCaseFilingDOPage = iDataCompanyCaseFilingService.listPageByCompanyId(dataCompanyExWarehouseQueryCommand.getCompanyId(),
					dataCompanyExWarehouseQueryCommand.getCaseNo(),
					dataCompanyExWarehouseQueryCommand);
        }

		if (dataCompanyCaseFilingDOPage == null || dataCompanyCaseFilingDOPage.getRecords() == null || dataCompanyCaseFilingDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyCaseFilingAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyCaseFilingDOPage);
	}
	/**
	 * 企业立案信息出库
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyCaseFilingExWarehouseVO> exWarehouseByDataMd5(String dataMd5) {
		DataCompanyCaseFilingDO dataCompanyCaseFilingDO = iDataCompanyCaseFilingService.getByDataMd5(dataMd5);
		if (dataCompanyCaseFilingDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyCaseFilingExWarehouseVO dataCompanyCaseFilingExWarehouseVO = DataCompanyCaseFilingAppStructMapping.instance.dataCompanyCaseFilingDOToDataCompanyCaseFilingExWarehouseVO(dataCompanyCaseFilingDO);
		return SingleResponse.of(dataCompanyCaseFilingExWarehouseVO);
	}
	/**
	 * 企业立案信息出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyCaseFilingExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyCaseFilingDO dataCompanyCaseFilingDO = iDataCompanyCaseFilingService.getById(id);
		if (dataCompanyCaseFilingDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyCaseFilingExWarehouseVO dataCompanyCaseFilingExWarehouseVO = DataCompanyCaseFilingAppStructMapping.instance.dataCompanyCaseFilingDOToDataCompanyCaseFilingExWarehouseVO(dataCompanyCaseFilingDO);
		return SingleResponse.of(dataCompanyCaseFilingExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyCaseFilingService(IDataCompanyCaseFilingService iDataCompanyCaseFilingService) {
		this.iDataCompanyCaseFilingService = iDataCompanyCaseFilingService;
	}
}
