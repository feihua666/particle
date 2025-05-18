package com.particle.data.app.company.executor.representation.exwarehouse;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyAnnualReportForeignGuaranteeAppStructMapping;
import com.particle.data.app.company.structmapping.DataCompanyAnnualReportForeignGuaranteeAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyAnnualReportForeignGuaranteeExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportForeignGuaranteeExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportForeignGuaranteeExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportForeignGuaranteeDO;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportForeignGuaranteeDO;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportForeignGuaranteeService;
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
 * 企业年报对外担保出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyAnnualReportForeignGuaranteeExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyAnnualReportForeignGuaranteeService iDataCompanyAnnualReportForeignGuaranteeService;

	/**
	 * 企业年报对外担保出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportForeignGuaranteeExWarehouseVO> exWarehouse(@Valid DataCompanyAnnualReportForeignGuaranteeExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyAnnualReportForeignGuaranteeDO> dataCompanyAnnualReportForeignGuaranteeDOPage = null;
        if (dataCompanyExWarehouseQueryCommand.getCompanyAnnualReportId() != null) {
			dataCompanyAnnualReportForeignGuaranteeDOPage = iDataCompanyAnnualReportForeignGuaranteeService.listPageByCompanyAnnualReportId(dataCompanyExWarehouseQueryCommand.getCompanyAnnualReportId(),
					dataCompanyExWarehouseQueryCommand);
        }else if (dataCompanyExWarehouseQueryCommand.getCompanyId() != null) {
			dataCompanyAnnualReportForeignGuaranteeDOPage = iDataCompanyAnnualReportForeignGuaranteeService.listPageByCompanyId(dataCompanyExWarehouseQueryCommand.getCompanyId(),
					dataCompanyExWarehouseQueryCommand.getYear(),
					dataCompanyExWarehouseQueryCommand);
        }

		if (dataCompanyAnnualReportForeignGuaranteeDOPage == null || dataCompanyAnnualReportForeignGuaranteeDOPage.getRecords() == null || dataCompanyAnnualReportForeignGuaranteeDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyAnnualReportForeignGuaranteeAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyAnnualReportForeignGuaranteeDOPage);
	}
	/**
	 * 企业年报对外担保出库
	 * @param companyId
	 * @param year
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportForeignGuaranteeExWarehouseVO> exWarehouseByCompanyIdAndYearAndDataMd5(Long companyId, Integer year, String dataMd5) {
		DataCompanyAnnualReportForeignGuaranteeDO dataCompanyAnnualReportForeignGuaranteeDO = iDataCompanyAnnualReportForeignGuaranteeService.getByCompanyIdAndYearAndDataMd5(companyId,year,dataMd5);
		if (dataCompanyAnnualReportForeignGuaranteeDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyAnnualReportForeignGuaranteeExWarehouseVO dataCompanyAnnualReportForeignGuaranteeExWarehouseVO = DataCompanyAnnualReportForeignGuaranteeAppStructMapping.instance.dataCompanyAnnualReportForeignGuaranteeDOToDataCompanyAnnualReportForeignGuaranteeExWarehouseVO(dataCompanyAnnualReportForeignGuaranteeDO);
		return SingleResponse.of(dataCompanyAnnualReportForeignGuaranteeExWarehouseVO);
	}
	/**
	 * 企业年报对外担保出库
	 * @param companyAnnualReportId
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportForeignGuaranteeExWarehouseVO> exWarehouseByCompanyAnnualReportIdAndDataMd5(Long companyAnnualReportId,String dataMd5) {
		DataCompanyAnnualReportForeignGuaranteeDO dataCompanyAnnualReportForeignGuaranteeDO = iDataCompanyAnnualReportForeignGuaranteeService.getByCompanyAnnualReportIdAndDataMd5(companyAnnualReportId,dataMd5);
		if (dataCompanyAnnualReportForeignGuaranteeDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyAnnualReportForeignGuaranteeExWarehouseVO dataCompanyAnnualReportForeignGuaranteeExWarehouseVO = DataCompanyAnnualReportForeignGuaranteeAppStructMapping.instance.dataCompanyAnnualReportForeignGuaranteeDOToDataCompanyAnnualReportForeignGuaranteeExWarehouseVO(dataCompanyAnnualReportForeignGuaranteeDO);
		return SingleResponse.of(dataCompanyAnnualReportForeignGuaranteeExWarehouseVO);
	}
	/**
	 * 企业年报对外担保出库
	 * @param companyAnnualReportIds
	 * @return
	 */
	public MultiResponse<DataCompanyAnnualReportForeignGuaranteeExWarehouseVO> exWarehouseByCompanyAnnualReportIds(List<Long> companyAnnualReportIds) {
		List<DataCompanyAnnualReportForeignGuaranteeDO> dataCompanyAnnualReportForeignGuaranteeDOs = iDataCompanyAnnualReportForeignGuaranteeService.listByCompanyAnnualReportIds(companyAnnualReportIds);
		if (CollectionUtil.isEmpty(dataCompanyAnnualReportForeignGuaranteeDOs)) {
			return MultiResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		List<DataCompanyAnnualReportForeignGuaranteeExWarehouseVO> dataCompanyAnnualReportForeignGuaranteeExWarehouseVOs = DataCompanyAnnualReportForeignGuaranteeAppStructMapping.instance.dataCompanyAnnualReportForeignGuaranteeDOsToDataCompanyAnnualReportForeignGuaranteeExWarehouseVOs(dataCompanyAnnualReportForeignGuaranteeDOs);
		return MultiResponse.of(dataCompanyAnnualReportForeignGuaranteeExWarehouseVOs);
	}
	/**
	 * 企业年报对外担保出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportForeignGuaranteeExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyAnnualReportForeignGuaranteeDO dataCompanyAnnualReportForeignGuaranteeDO = iDataCompanyAnnualReportForeignGuaranteeService.getById(id);
		if (dataCompanyAnnualReportForeignGuaranteeDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyAnnualReportForeignGuaranteeExWarehouseVO dataCompanyAnnualReportForeignGuaranteeExWarehouseVO = DataCompanyAnnualReportForeignGuaranteeAppStructMapping.instance.dataCompanyAnnualReportForeignGuaranteeDOToDataCompanyAnnualReportForeignGuaranteeExWarehouseVO(dataCompanyAnnualReportForeignGuaranteeDO);
		return SingleResponse.of(dataCompanyAnnualReportForeignGuaranteeExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyAnnualReportForeignGuaranteeService(IDataCompanyAnnualReportForeignGuaranteeService iDataCompanyAnnualReportForeignGuaranteeService) {
		this.iDataCompanyAnnualReportForeignGuaranteeService = iDataCompanyAnnualReportForeignGuaranteeService;
	}
}
