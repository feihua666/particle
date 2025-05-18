package com.particle.data.app.company.executor.representation.exwarehouse;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyAnnualReportChangeAppStructMapping;
import com.particle.data.app.company.structmapping.DataCompanyAnnualReportChangeAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyAnnualReportChangeExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportChangeExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportChangeExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportChangeDO;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportChangeDO;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportChangeService;
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
 * 企业年报变更出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyAnnualReportChangeExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyAnnualReportChangeService iDataCompanyAnnualReportChangeService;

	/**
	 * 企业年报变更出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportChangeExWarehouseVO> exWarehouse(@Valid DataCompanyAnnualReportChangeExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyAnnualReportChangeDO> dataCompanyAnnualReportChangeDOPage = null;
        if (dataCompanyExWarehouseQueryCommand.getCompanyAnnualReportId() != null) {
			dataCompanyAnnualReportChangeDOPage = iDataCompanyAnnualReportChangeService.listPageByCompanyAnnualReportId(dataCompanyExWarehouseQueryCommand.getCompanyAnnualReportId(),
					dataCompanyExWarehouseQueryCommand);
        }else if (dataCompanyExWarehouseQueryCommand.getCompanyId() != null) {
			dataCompanyAnnualReportChangeDOPage = iDataCompanyAnnualReportChangeService.listPageByCompanyId(dataCompanyExWarehouseQueryCommand.getCompanyId(),
					dataCompanyExWarehouseQueryCommand.getYear(),
					dataCompanyExWarehouseQueryCommand);
        }

		if (dataCompanyAnnualReportChangeDOPage == null || dataCompanyAnnualReportChangeDOPage.getRecords() == null || dataCompanyAnnualReportChangeDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyAnnualReportChangeAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyAnnualReportChangeDOPage);
	}
	/**
	 * 企业年报变更出库
	 * @param companyId
	 * @param year
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportChangeExWarehouseVO> exWarehouseByCompanyIdAndYearAndDataMd5(Long companyId,Integer year,String dataMd5) {
		DataCompanyAnnualReportChangeDO dataCompanyAnnualReportChangeDO = iDataCompanyAnnualReportChangeService.getByCompanyIdAndYearAndDataMd5(companyId,year,dataMd5);
		if (dataCompanyAnnualReportChangeDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyAnnualReportChangeExWarehouseVO dataCompanyAnnualReportChangeExWarehouseVO = DataCompanyAnnualReportChangeAppStructMapping.instance.dataCompanyAnnualReportChangeDOToDataCompanyAnnualReportChangeExWarehouseVO(dataCompanyAnnualReportChangeDO);
		return SingleResponse.of(dataCompanyAnnualReportChangeExWarehouseVO);
	}
	/**
	 * 企业年报变更出库
	 * @param companyAnnualReportId
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportChangeExWarehouseVO> exWarehouseByCompanyAnnualReportIdAndDataMd5(Long companyAnnualReportId,String dataMd5) {
		DataCompanyAnnualReportChangeDO dataCompanyAnnualReportChangeDO = iDataCompanyAnnualReportChangeService.getByCompanyAnnualReportIdAndDataMd5(companyAnnualReportId,dataMd5);
		if (dataCompanyAnnualReportChangeDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyAnnualReportChangeExWarehouseVO dataCompanyAnnualReportChangeExWarehouseVO = DataCompanyAnnualReportChangeAppStructMapping.instance.dataCompanyAnnualReportChangeDOToDataCompanyAnnualReportChangeExWarehouseVO(dataCompanyAnnualReportChangeDO);
		return SingleResponse.of(dataCompanyAnnualReportChangeExWarehouseVO);
	}
	/**
	 * 企业年报变更出库
	 * @param companyAnnualReportIds
	 * @return
	 */
	public MultiResponse<DataCompanyAnnualReportChangeExWarehouseVO> exWarehouseByCompanyAnnualReportIds(List<Long> companyAnnualReportIds) {
		List<DataCompanyAnnualReportChangeDO> dataCompanyAnnualReportChangeDOs = iDataCompanyAnnualReportChangeService.listByCompanyAnnualReportIds(companyAnnualReportIds);
		if (CollectionUtil.isEmpty(dataCompanyAnnualReportChangeDOs)) {
			return MultiResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		List<DataCompanyAnnualReportChangeExWarehouseVO> dataCompanyAnnualReportChangeExWarehouseVOs = DataCompanyAnnualReportChangeAppStructMapping.instance.dataCompanyAnnualReportChangeDOsToDataCompanyAnnualReportChangeExWarehouseVOs(dataCompanyAnnualReportChangeDOs);
		return MultiResponse.of(dataCompanyAnnualReportChangeExWarehouseVOs);
	}
	/**
	 * 企业年报变更出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportChangeExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyAnnualReportChangeDO dataCompanyAnnualReportChangeDO = iDataCompanyAnnualReportChangeService.getById(id);
		if (dataCompanyAnnualReportChangeDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyAnnualReportChangeExWarehouseVO dataCompanyAnnualReportChangeExWarehouseVO = DataCompanyAnnualReportChangeAppStructMapping.instance.dataCompanyAnnualReportChangeDOToDataCompanyAnnualReportChangeExWarehouseVO(dataCompanyAnnualReportChangeDO);
		return SingleResponse.of(dataCompanyAnnualReportChangeExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyAnnualReportChangeService(IDataCompanyAnnualReportChangeService iDataCompanyAnnualReportChangeService) {
		this.iDataCompanyAnnualReportChangeService = iDataCompanyAnnualReportChangeService;
	}
}
