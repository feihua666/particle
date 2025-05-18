package com.particle.data.app.company.executor.representation.exwarehouse;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyAnnualReportForeignInvestAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyAnnualReportForeignInvestExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportForeignInvestExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportForeignInvestDO;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportForeignInvestService;
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
 * 企业年报对外投资出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyAnnualReportForeignInvestExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyAnnualReportForeignInvestService iDataCompanyAnnualReportForeignInvestService;

	/**
	 * 企业年报对外投资出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportForeignInvestExWarehouseVO> exWarehouse(@Valid DataCompanyAnnualReportForeignInvestExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyAnnualReportForeignInvestDO> dataCompanyAnnualReportForeignInvestDOPage = null;
        if (dataCompanyExWarehouseQueryCommand.getCompanyAnnualReportId() != null) {
			dataCompanyAnnualReportForeignInvestDOPage = iDataCompanyAnnualReportForeignInvestService.listPageByCompanyAnnualReportId(dataCompanyExWarehouseQueryCommand.getCompanyAnnualReportId(),
					dataCompanyExWarehouseQueryCommand);
        }else if (dataCompanyExWarehouseQueryCommand.getCompanyId() != null) {
			dataCompanyAnnualReportForeignInvestDOPage = iDataCompanyAnnualReportForeignInvestService.listPageByCompanyId(dataCompanyExWarehouseQueryCommand.getCompanyId(),
					dataCompanyExWarehouseQueryCommand.getYear(),
					dataCompanyExWarehouseQueryCommand);
        }

		if (dataCompanyAnnualReportForeignInvestDOPage == null || dataCompanyAnnualReportForeignInvestDOPage.getRecords() == null || dataCompanyAnnualReportForeignInvestDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyAnnualReportForeignInvestAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyAnnualReportForeignInvestDOPage);
	}
	/**
	 * 企业年报对外投资出库
	 * @param companyId
	 * @param year
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportForeignInvestExWarehouseVO> exWarehouseByCompanyIdAndYearAndDataMd5(Long companyId,Integer year,String dataMd5) {
		DataCompanyAnnualReportForeignInvestDO dataCompanyAnnualReportForeignInvestDO = iDataCompanyAnnualReportForeignInvestService.getByCompanyIdAndYearAndDataMd5(companyId,year,dataMd5);
		if (dataCompanyAnnualReportForeignInvestDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyAnnualReportForeignInvestExWarehouseVO dataCompanyAnnualReportForeignInvestExWarehouseVO = DataCompanyAnnualReportForeignInvestAppStructMapping.instance.dataCompanyAnnualReportForeignInvestDOToDataCompanyAnnualReportForeignInvestExWarehouseVO(dataCompanyAnnualReportForeignInvestDO);
		return SingleResponse.of(dataCompanyAnnualReportForeignInvestExWarehouseVO);
	}
	/**
	 * 企业年报对外投资出库
	 * @param companyAnnualReportId
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportForeignInvestExWarehouseVO> exWarehouseByCompanyAnnualReportIdAndDataMd5(Long companyAnnualReportId,String dataMd5) {
		DataCompanyAnnualReportForeignInvestDO dataCompanyAnnualReportForeignInvestDO = iDataCompanyAnnualReportForeignInvestService.getByCompanyAnnualReportIdAndDataMd5(companyAnnualReportId,dataMd5);
		if (dataCompanyAnnualReportForeignInvestDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyAnnualReportForeignInvestExWarehouseVO dataCompanyAnnualReportForeignInvestExWarehouseVO = DataCompanyAnnualReportForeignInvestAppStructMapping.instance.dataCompanyAnnualReportForeignInvestDOToDataCompanyAnnualReportForeignInvestExWarehouseVO(dataCompanyAnnualReportForeignInvestDO);
		return SingleResponse.of(dataCompanyAnnualReportForeignInvestExWarehouseVO);
	}
	/**
	 * 企业年报对外投资出库
	 * @param companyAnnualReportIds
	 * @return
	 */
	public MultiResponse<DataCompanyAnnualReportForeignInvestExWarehouseVO> exWarehouseByCompanyAnnualReportIds(List<Long> companyAnnualReportIds) {
		List<DataCompanyAnnualReportForeignInvestDO> dataCompanyAnnualReportForeignInvestDOs = iDataCompanyAnnualReportForeignInvestService.listByCompanyAnnualReportIds(companyAnnualReportIds);
		if (CollectionUtil.isEmpty(dataCompanyAnnualReportForeignInvestDOs)) {
			return MultiResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		List<DataCompanyAnnualReportForeignInvestExWarehouseVO> dataCompanyAnnualReportForeignInvestExWarehouseVOs = DataCompanyAnnualReportForeignInvestAppStructMapping.instance.dataCompanyAnnualReportForeignInvestDOsToDataCompanyAnnualReportForeignInvestExWarehouseVOs(dataCompanyAnnualReportForeignInvestDOs);
		return MultiResponse.of(dataCompanyAnnualReportForeignInvestExWarehouseVOs);
	}
	/**
	 * 企业年报对外投资出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportForeignInvestExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyAnnualReportForeignInvestDO dataCompanyAnnualReportForeignInvestDO = iDataCompanyAnnualReportForeignInvestService.getById(id);
		if (dataCompanyAnnualReportForeignInvestDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyAnnualReportForeignInvestExWarehouseVO dataCompanyAnnualReportForeignInvestExWarehouseVO = DataCompanyAnnualReportForeignInvestAppStructMapping.instance.dataCompanyAnnualReportForeignInvestDOToDataCompanyAnnualReportForeignInvestExWarehouseVO(dataCompanyAnnualReportForeignInvestDO);
		return SingleResponse.of(dataCompanyAnnualReportForeignInvestExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyAnnualReportForeignInvestService(IDataCompanyAnnualReportForeignInvestService iDataCompanyAnnualReportForeignInvestService) {
		this.iDataCompanyAnnualReportForeignInvestService = iDataCompanyAnnualReportForeignInvestService;
	}
}
