package com.particle.data.app.company.executor.representation.exwarehouse;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyAdministrativeLicenseAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyAdministrativeLicenseExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAdministrativeLicenseExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyAdministrativeLicenseDO;
import com.particle.data.infrastructure.company.service.IDataCompanyAdministrativeLicenseService;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业行政许可出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyAdministrativeLicenseExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyAdministrativeLicenseService iDataCompanyAdministrativeLicenseService;

	/**
	 * 企业行政许可出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyAdministrativeLicenseExWarehouseVO> exWarehouse(@Valid DataCompanyAdministrativeLicenseExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyAdministrativeLicenseDO> dataCompanyAdministrativeLicenseDOPage = iDataCompanyAdministrativeLicenseService.listPageByCompanyId(dataCompanyExWarehouseQueryCommand.getCompanyId(), dataCompanyExWarehouseQueryCommand);
		if (dataCompanyAdministrativeLicenseDOPage == null || dataCompanyAdministrativeLicenseDOPage.getRecords() == null || dataCompanyAdministrativeLicenseDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyAdministrativeLicenseAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyAdministrativeLicenseDOPage);
	}
	/**
	 * 企业行政许可出库
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyAdministrativeLicenseExWarehouseVO> exWarehouseByCompanyIdAndDataMd5(Long companyId,String dataMd5) {
		DataCompanyAdministrativeLicenseDO dataCompanyAdministrativeLicenseDO = iDataCompanyAdministrativeLicenseService.getByCompanyIdAndDataMd5(companyId,dataMd5);
		if (dataCompanyAdministrativeLicenseDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyAdministrativeLicenseExWarehouseVO dataCompanyAdministrativeLicenseExWarehouseVO = DataCompanyAdministrativeLicenseAppStructMapping.instance.dataCompanyAdministrativeLicenseDOToDataCompanyAdministrativeLicenseExWarehouseVO(dataCompanyAdministrativeLicenseDO);
		return SingleResponse.of(dataCompanyAdministrativeLicenseExWarehouseVO);
	}
	/**
	 * 企业行政许可出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyAdministrativeLicenseExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyAdministrativeLicenseDO dataCompanyAdministrativeLicenseDO = iDataCompanyAdministrativeLicenseService.getById(id);
		if (dataCompanyAdministrativeLicenseDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyAdministrativeLicenseExWarehouseVO dataCompanyAdministrativeLicenseExWarehouseVO = DataCompanyAdministrativeLicenseAppStructMapping.instance.dataCompanyAdministrativeLicenseDOToDataCompanyAdministrativeLicenseExWarehouseVO(dataCompanyAdministrativeLicenseDO);
		return SingleResponse.of(dataCompanyAdministrativeLicenseExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyAdministrativeLicenseService(IDataCompanyAdministrativeLicenseService iDataCompanyAdministrativeLicenseService) {
		this.iDataCompanyAdministrativeLicenseService = iDataCompanyAdministrativeLicenseService;
	}
}
