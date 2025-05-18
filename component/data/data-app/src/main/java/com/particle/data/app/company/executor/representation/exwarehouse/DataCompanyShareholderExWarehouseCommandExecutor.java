package com.particle.data.app.company.executor.representation.exwarehouse;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyShareholderAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyShareholderExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyShareholderExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyShareholderDO;
import com.particle.data.infrastructure.company.service.IDataCompanyShareholderService;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业股东出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyShareholderExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyShareholderService iDataCompanyShareholderService;

	/**
	 * 企业股东出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyShareholderExWarehouseVO> exWarehouse(@Valid DataCompanyShareholderExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyShareholderDO> dataCompanyShareholderDOPage = iDataCompanyShareholderService.listPageByCompanyId(dataCompanyExWarehouseQueryCommand.getCompanyId(), dataCompanyExWarehouseQueryCommand);
		if (dataCompanyShareholderDOPage == null || dataCompanyShareholderDOPage.getRecords() == null || dataCompanyShareholderDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyShareholderAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyShareholderDOPage);
	}
	/**
	 * 企业股东出库
	 * @param companyId
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyShareholderExWarehouseVO> exWarehouseByCompanyIdAndDataMd5(Long companyId,String dataMd5) {
		DataCompanyShareholderDO dataCompanyShareholderDO = iDataCompanyShareholderService.getByCompanyIdAndDataMd5(companyId,dataMd5);
		if (dataCompanyShareholderDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyShareholderExWarehouseVO dataCompanyShareholderExWarehouseVO = DataCompanyShareholderAppStructMapping.instance.dataCompanyShareholderDOToDataCompanyShareholderExWarehouseVO(dataCompanyShareholderDO);
		return SingleResponse.of(dataCompanyShareholderExWarehouseVO);
	}

	/**
	 * 企业股东出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyShareholderExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyShareholderDO dataCompanyShareholderDO = iDataCompanyShareholderService.getById(id);
		if (dataCompanyShareholderDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyShareholderExWarehouseVO dataCompanyShareholderExWarehouseVO = DataCompanyShareholderAppStructMapping.instance.dataCompanyShareholderDOToDataCompanyShareholderExWarehouseVO(dataCompanyShareholderDO);
		return SingleResponse.of(dataCompanyShareholderExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyShareholderService(IDataCompanyShareholderService iDataCompanyShareholderService) {
		this.iDataCompanyShareholderService = iDataCompanyShareholderService;
	}
}
