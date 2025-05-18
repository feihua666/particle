package com.particle.data.app.company.executor.representation.exwarehouse;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyAnnualReportShareholderAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyAnnualReportShareholderExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportShareholderExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportShareholderDO;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportShareholderService;
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
 * 企业年报股东出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyAnnualReportShareholderExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyAnnualReportShareholderService iDataCompanyAnnualReportShareholderService;

	/**
	 * 企业年报股东出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportShareholderExWarehouseVO> exWarehouse(@Valid DataCompanyAnnualReportShareholderExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyAnnualReportShareholderDO> dataCompanyAnnualReportShareholderDOPage = null;
        if (dataCompanyExWarehouseQueryCommand.getCompanyAnnualReportId() != null) {
			dataCompanyAnnualReportShareholderDOPage = iDataCompanyAnnualReportShareholderService.listPageByCompanyAnnualReportId(dataCompanyExWarehouseQueryCommand.getCompanyAnnualReportId(),
					dataCompanyExWarehouseQueryCommand);
        }else if (dataCompanyExWarehouseQueryCommand.getCompanyId() != null) {
			dataCompanyAnnualReportShareholderDOPage = iDataCompanyAnnualReportShareholderService.listPageByCompanyId(dataCompanyExWarehouseQueryCommand.getCompanyId(),
					dataCompanyExWarehouseQueryCommand.getYear(),
					dataCompanyExWarehouseQueryCommand);
        }

		if (dataCompanyAnnualReportShareholderDOPage == null || dataCompanyAnnualReportShareholderDOPage.getRecords() == null || dataCompanyAnnualReportShareholderDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyAnnualReportShareholderAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyAnnualReportShareholderDOPage);
	}
	/**
	 * 企业年报股东出库
	 * @param companyId
	 * @param year
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportShareholderExWarehouseVO> exWarehouseByCompanyIdAndYearAndDataMd5(Long companyId,Integer year,String dataMd5) {
		DataCompanyAnnualReportShareholderDO dataCompanyAnnualReportShareholderDO = iDataCompanyAnnualReportShareholderService.getByCompanyIdAndYearAndDataMd5(companyId,year,dataMd5);
		if (dataCompanyAnnualReportShareholderDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyAnnualReportShareholderExWarehouseVO dataCompanyAnnualReportShareholderExWarehouseVO = DataCompanyAnnualReportShareholderAppStructMapping.instance.dataCompanyAnnualReportShareholderDOToDataCompanyAnnualReportShareholderExWarehouseVO(dataCompanyAnnualReportShareholderDO);
		return SingleResponse.of(dataCompanyAnnualReportShareholderExWarehouseVO);
	}
	/**
	 * 企业年报股东出库
	 * @param companyAnnualReportId
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportShareholderExWarehouseVO> exWarehouseByCompanyAnnualReportIdAndDataMd5(Long companyAnnualReportId,String dataMd5) {
		DataCompanyAnnualReportShareholderDO dataCompanyAnnualReportShareholderDO = iDataCompanyAnnualReportShareholderService.getByCompanyAnnualReportIdAndDataMd5(companyAnnualReportId,dataMd5);
		if (dataCompanyAnnualReportShareholderDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyAnnualReportShareholderExWarehouseVO dataCompanyAnnualReportShareholderExWarehouseVO = DataCompanyAnnualReportShareholderAppStructMapping.instance.dataCompanyAnnualReportShareholderDOToDataCompanyAnnualReportShareholderExWarehouseVO(dataCompanyAnnualReportShareholderDO);
		return SingleResponse.of(dataCompanyAnnualReportShareholderExWarehouseVO);
	}
	/**
	 * 企业年报股东出库
	 * @param companyAnnualReportIds
	 * @return
	 */
	public MultiResponse<DataCompanyAnnualReportShareholderExWarehouseVO> exWarehouseByCompanyAnnualReportIds(List<Long> companyAnnualReportIds) {
		List<DataCompanyAnnualReportShareholderDO> dataCompanyAnnualReportShareholderDOs = iDataCompanyAnnualReportShareholderService.listByCompanyAnnualReportIds(companyAnnualReportIds);
		if (CollectionUtil.isEmpty(dataCompanyAnnualReportShareholderDOs)) {
			return MultiResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		List<DataCompanyAnnualReportShareholderExWarehouseVO> dataCompanyAnnualReportShareholderExWarehouseVOs = DataCompanyAnnualReportShareholderAppStructMapping.instance.dataCompanyAnnualReportShareholderDOsToDataCompanyAnnualReportShareholderExWarehouseVOs(dataCompanyAnnualReportShareholderDOs);
		return MultiResponse.of(dataCompanyAnnualReportShareholderExWarehouseVOs);
	}
	/**
	 * 企业年报股东出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportShareholderExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyAnnualReportShareholderDO dataCompanyAnnualReportShareholderDO = iDataCompanyAnnualReportShareholderService.getById(id);
		if (dataCompanyAnnualReportShareholderDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyAnnualReportShareholderExWarehouseVO dataCompanyAnnualReportShareholderExWarehouseVO = DataCompanyAnnualReportShareholderAppStructMapping.instance.dataCompanyAnnualReportShareholderDOToDataCompanyAnnualReportShareholderExWarehouseVO(dataCompanyAnnualReportShareholderDO);
		return SingleResponse.of(dataCompanyAnnualReportShareholderExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyAnnualReportShareholderService(IDataCompanyAnnualReportShareholderService iDataCompanyAnnualReportShareholderService) {
		this.iDataCompanyAnnualReportShareholderService = iDataCompanyAnnualReportShareholderService;
	}
}
