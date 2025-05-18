package com.particle.data.app.company.executor.representation.exwarehouse;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyVcFinancingAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyVcFinancingExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcFinancingExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyVcFinancingDO;
import com.particle.data.infrastructure.company.service.IDataCompanyVcFinancingService;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业融资出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyVcFinancingExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyVcFinancingService iDataCompanyVcFinancingService;

	/**
	 * 企业融资出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyVcFinancingExWarehouseVO> exWarehouse(@Valid DataCompanyVcFinancingExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyVcFinancingDO> dataCompanyVcFinancingDOPage = iDataCompanyVcFinancingService.listPageByCompanyId(dataCompanyExWarehouseQueryCommand.getCompanyId(), dataCompanyExWarehouseQueryCommand);
		if (dataCompanyVcFinancingDOPage == null || dataCompanyVcFinancingDOPage.getRecords() == null || dataCompanyVcFinancingDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyVcFinancingAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyVcFinancingDOPage);
	}

	/**
	 * 企业融资出库
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyVcFinancingExWarehouseVO> exWarehouseByCompanyIdAndDataMd5(Long companyId,String dataMd5) {
		DataCompanyVcFinancingDO dataCompanyVcFinancingDO = iDataCompanyVcFinancingService.getByCompanyIdAndDataMd5(companyId,dataMd5);
		if (dataCompanyVcFinancingDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyVcFinancingExWarehouseVO dataCompanyVcFinancingExWarehouseVO = DataCompanyVcFinancingAppStructMapping.instance.dataCompanyVcFinancingDOToDataCompanyVcFinancingExWarehouseVO(dataCompanyVcFinancingDO);
		return SingleResponse.of(dataCompanyVcFinancingExWarehouseVO);
	}
	/**
	 * 企业融资出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyVcFinancingExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyVcFinancingDO dataCompanyVcFinancingDO = iDataCompanyVcFinancingService.getById(id);
		if (dataCompanyVcFinancingDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyVcFinancingExWarehouseVO dataCompanyVcFinancingExWarehouseVO = DataCompanyVcFinancingAppStructMapping.instance.dataCompanyVcFinancingDOToDataCompanyVcFinancingExWarehouseVO(dataCompanyVcFinancingDO);
		return SingleResponse.of(dataCompanyVcFinancingExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyVcFinancingService(IDataCompanyVcFinancingService iDataCompanyVcFinancingService) {
		this.iDataCompanyVcFinancingService = iDataCompanyVcFinancingService;
	}
}
