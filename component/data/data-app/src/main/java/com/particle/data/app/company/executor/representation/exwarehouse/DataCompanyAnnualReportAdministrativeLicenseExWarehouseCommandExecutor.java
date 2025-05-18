package com.particle.data.app.company.executor.representation.exwarehouse;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyAnnualReportAdministrativeLicenseAppStructMapping;
import com.particle.data.app.company.structmapping.DataCompanyAnnualReportAdministrativeLicenseAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyAnnualReportAdministrativeLicenseExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportAdministrativeLicenseExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportAdministrativeLicenseExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportAdministrativeLicenseDO;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportAdministrativeLicenseDO;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportAdministrativeLicenseService;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 企业年报行政许可出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyAnnualReportAdministrativeLicenseExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyAnnualReportAdministrativeLicenseService iDataCompanyAnnualReportAdministrativeLicenseService;

	/**
	 * 企业年报行政许可出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportAdministrativeLicenseExWarehouseVO> exWarehouse(@Valid DataCompanyAnnualReportAdministrativeLicenseExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyAnnualReportAdministrativeLicenseDO> dataCompanyAnnualReportAdministrativeLicenseDOPage = null;
        if (dataCompanyExWarehouseQueryCommand.getCompanyAnnualReportId() != null) {
			dataCompanyAnnualReportAdministrativeLicenseDOPage = iDataCompanyAnnualReportAdministrativeLicenseService.listPageByCompanyAnnualReportId(dataCompanyExWarehouseQueryCommand.getCompanyAnnualReportId(),
					dataCompanyExWarehouseQueryCommand);
        }else if (dataCompanyExWarehouseQueryCommand.getCompanyId() != null) {
			dataCompanyAnnualReportAdministrativeLicenseDOPage = iDataCompanyAnnualReportAdministrativeLicenseService.listPageByCompanyId(dataCompanyExWarehouseQueryCommand.getCompanyId(),
					dataCompanyExWarehouseQueryCommand.getYear(),
					dataCompanyExWarehouseQueryCommand);
        }

		if (dataCompanyAnnualReportAdministrativeLicenseDOPage == null || dataCompanyAnnualReportAdministrativeLicenseDOPage.getRecords() == null || dataCompanyAnnualReportAdministrativeLicenseDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyAnnualReportAdministrativeLicenseAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyAnnualReportAdministrativeLicenseDOPage);
	}
	/**
	 * 企业年报行政许可出库
	 * @param companyId
	 * @param year
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportAdministrativeLicenseExWarehouseVO> exWarehouseByCompanyIdAndYearAndDataMd5(Long companyId,Integer year,String dataMd5) {
		DataCompanyAnnualReportAdministrativeLicenseDO dataCompanyAnnualReportAdministrativeLicenseDO = iDataCompanyAnnualReportAdministrativeLicenseService.getByCompanyIdAndYearAndDataMd5(companyId,year,dataMd5);
		if (dataCompanyAnnualReportAdministrativeLicenseDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyAnnualReportAdministrativeLicenseExWarehouseVO dataCompanyAnnualReportAdministrativeLicenseExWarehouseVO = DataCompanyAnnualReportAdministrativeLicenseAppStructMapping.instance.dataCompanyAnnualReportAdministrativeLicenseDOToDataCompanyAnnualReportAdministrativeLicenseExWarehouseVO(dataCompanyAnnualReportAdministrativeLicenseDO);
		return SingleResponse.of(dataCompanyAnnualReportAdministrativeLicenseExWarehouseVO);
	}
	/**
	 * 企业年报行政许可出库
	 * @param companyAnnualReportId
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportAdministrativeLicenseExWarehouseVO> exWarehouseByCompanyAnnualReportIdAndDataMd5(Long companyAnnualReportId,String dataMd5) {
		DataCompanyAnnualReportAdministrativeLicenseDO dataCompanyAnnualReportAdministrativeLicenseDO = iDataCompanyAnnualReportAdministrativeLicenseService.getByCompanyAnnualReportIdAndDataMd5(companyAnnualReportId,dataMd5);
		if (dataCompanyAnnualReportAdministrativeLicenseDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyAnnualReportAdministrativeLicenseExWarehouseVO dataCompanyAnnualReportAdministrativeLicenseExWarehouseVO = DataCompanyAnnualReportAdministrativeLicenseAppStructMapping.instance.dataCompanyAnnualReportAdministrativeLicenseDOToDataCompanyAnnualReportAdministrativeLicenseExWarehouseVO(dataCompanyAnnualReportAdministrativeLicenseDO);
		return SingleResponse.of(dataCompanyAnnualReportAdministrativeLicenseExWarehouseVO);
	}
	/**
	 * 企业年报行政许可出库
	 * @param companyAnnualReportIds
	 * @return
	 */
	public MultiResponse<DataCompanyAnnualReportAdministrativeLicenseExWarehouseVO> exWarehouseByCompanyAnnualReportIds(List<Long> companyAnnualReportIds) {
		List<DataCompanyAnnualReportAdministrativeLicenseDO> dataCompanyAnnualReportAdministrativeLicenseDOs = iDataCompanyAnnualReportAdministrativeLicenseService.listByCompanyAnnualReportIds(companyAnnualReportIds);
		if (CollectionUtil.isEmpty(dataCompanyAnnualReportAdministrativeLicenseDOs)) {
			return MultiResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		List<DataCompanyAnnualReportAdministrativeLicenseExWarehouseVO> dataCompanyAnnualReportAdministrativeLicenseExWarehouseVOs = DataCompanyAnnualReportAdministrativeLicenseAppStructMapping.instance.dataCompanyAnnualReportAdministrativeLicenseDOsToDataCompanyAnnualReportAdministrativeLicenseExWarehouseVOs(dataCompanyAnnualReportAdministrativeLicenseDOs);
		return MultiResponse.of(dataCompanyAnnualReportAdministrativeLicenseExWarehouseVOs);
	}
	/**
	 * 企业年报行政许可出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportAdministrativeLicenseExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyAnnualReportAdministrativeLicenseDO dataCompanyAnnualReportAdministrativeLicenseDO = iDataCompanyAnnualReportAdministrativeLicenseService.getById(id);
		if (dataCompanyAnnualReportAdministrativeLicenseDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyAnnualReportAdministrativeLicenseExWarehouseVO dataCompanyAnnualReportAdministrativeLicenseExWarehouseVO = DataCompanyAnnualReportAdministrativeLicenseAppStructMapping.instance.dataCompanyAnnualReportAdministrativeLicenseDOToDataCompanyAnnualReportAdministrativeLicenseExWarehouseVO(dataCompanyAnnualReportAdministrativeLicenseDO);
		return SingleResponse.of(dataCompanyAnnualReportAdministrativeLicenseExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyAnnualReportAdministrativeLicenseService(IDataCompanyAnnualReportAdministrativeLicenseService iDataCompanyAnnualReportAdministrativeLicenseService) {
		this.iDataCompanyAnnualReportAdministrativeLicenseService = iDataCompanyAnnualReportAdministrativeLicenseService;
	}
}
