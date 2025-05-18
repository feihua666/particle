package com.particle.data.app.company.executor.representation.exwarehouse;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyAnnualReportEquityChangeAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyAnnualReportEquityChangeExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportEquityChangeExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportEquityChangeDO;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportEquityChangeService;
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
 * 企业年报股权变更出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyAnnualReportEquityChangeExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyAnnualReportEquityChangeService iDataCompanyAnnualReportEquityChangeService;

	/**
	 * 企业年报股权变更出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportEquityChangeExWarehouseVO> exWarehouse(@Valid DataCompanyAnnualReportEquityChangeExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyAnnualReportEquityChangeDO> dataCompanyAnnualReportEquityChangeDOPage = null;
        if (dataCompanyExWarehouseQueryCommand.getCompanyAnnualReportId() != null) {
			dataCompanyAnnualReportEquityChangeDOPage = iDataCompanyAnnualReportEquityChangeService.listPageByCompanyAnnualReportId(dataCompanyExWarehouseQueryCommand.getCompanyAnnualReportId(),
					dataCompanyExWarehouseQueryCommand);
        }else if (dataCompanyExWarehouseQueryCommand.getCompanyId() != null) {
			dataCompanyAnnualReportEquityChangeDOPage = iDataCompanyAnnualReportEquityChangeService.listPageByCompanyId(dataCompanyExWarehouseQueryCommand.getCompanyId(),
					dataCompanyExWarehouseQueryCommand.getYear(),
					dataCompanyExWarehouseQueryCommand);
        }

		if (dataCompanyAnnualReportEquityChangeDOPage == null || dataCompanyAnnualReportEquityChangeDOPage.getRecords() == null || dataCompanyAnnualReportEquityChangeDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyAnnualReportEquityChangeAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyAnnualReportEquityChangeDOPage);
	}
	/**
	 * 企业年报股权变更出库
	 * @param companyId
	 * @param year
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportEquityChangeExWarehouseVO> exWarehouseByCompanyIdAndYearAndDataMd5(Long companyId,Integer year,String dataMd5) {
		DataCompanyAnnualReportEquityChangeDO dataCompanyAnnualReportEquityChangeDO = iDataCompanyAnnualReportEquityChangeService.getByCompanyIdAndYearAndDataMd5(companyId,year,dataMd5);
		if (dataCompanyAnnualReportEquityChangeDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyAnnualReportEquityChangeExWarehouseVO dataCompanyAnnualReportEquityChangeExWarehouseVO = DataCompanyAnnualReportEquityChangeAppStructMapping.instance.dataCompanyAnnualReportEquityChangeDOToDataCompanyAnnualReportEquityChangeExWarehouseVO(dataCompanyAnnualReportEquityChangeDO);
		return SingleResponse.of(dataCompanyAnnualReportEquityChangeExWarehouseVO);
	}
	/**
	 * 企业年报股权变更出库
	 * @param companyAnnualReportId
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportEquityChangeExWarehouseVO> exWarehouseByCompanyAnnualReportIdAndDataMd5(Long companyAnnualReportId,String dataMd5) {
		DataCompanyAnnualReportEquityChangeDO dataCompanyAnnualReportEquityChangeDO = iDataCompanyAnnualReportEquityChangeService.getByCompanyAnnualReportIdAndDataMd5(companyAnnualReportId,dataMd5);
		if (dataCompanyAnnualReportEquityChangeDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyAnnualReportEquityChangeExWarehouseVO dataCompanyAnnualReportEquityChangeExWarehouseVO = DataCompanyAnnualReportEquityChangeAppStructMapping.instance.dataCompanyAnnualReportEquityChangeDOToDataCompanyAnnualReportEquityChangeExWarehouseVO(dataCompanyAnnualReportEquityChangeDO);
		return SingleResponse.of(dataCompanyAnnualReportEquityChangeExWarehouseVO);
	}
	/**
	 * 企业年报网站网店出库
	 * @param companyAnnualReportIds
	 * @return
	 */
	public MultiResponse<DataCompanyAnnualReportEquityChangeExWarehouseVO> exWarehouseByCompanyAnnualReportIds(List<Long> companyAnnualReportIds) {
		List<DataCompanyAnnualReportEquityChangeDO> dataCompanyAnnualReportEquityChangeDOs = iDataCompanyAnnualReportEquityChangeService.listByCompanyAnnualReportIds(companyAnnualReportIds);
		if (CollectionUtil.isEmpty(dataCompanyAnnualReportEquityChangeDOs)) {
			return MultiResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		List<DataCompanyAnnualReportEquityChangeExWarehouseVO> dataCompanyAnnualReportEquityChangeExWarehouseVOs = DataCompanyAnnualReportEquityChangeAppStructMapping.instance.dataCompanyAnnualReportEquityChangeDOsToDataCompanyAnnualReportEquityChangeExWarehouseVOs(dataCompanyAnnualReportEquityChangeDOs);
		return MultiResponse.of(dataCompanyAnnualReportEquityChangeExWarehouseVOs);
	}
	/**
	 * 企业年报股权变更出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportEquityChangeExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyAnnualReportEquityChangeDO dataCompanyAnnualReportEquityChangeDO = iDataCompanyAnnualReportEquityChangeService.getById(id);
		if (dataCompanyAnnualReportEquityChangeDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyAnnualReportEquityChangeExWarehouseVO dataCompanyAnnualReportEquityChangeExWarehouseVO = DataCompanyAnnualReportEquityChangeAppStructMapping.instance.dataCompanyAnnualReportEquityChangeDOToDataCompanyAnnualReportEquityChangeExWarehouseVO(dataCompanyAnnualReportEquityChangeDO);
		return SingleResponse.of(dataCompanyAnnualReportEquityChangeExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyAnnualReportEquityChangeService(IDataCompanyAnnualReportEquityChangeService iDataCompanyAnnualReportEquityChangeService) {
		this.iDataCompanyAnnualReportEquityChangeService = iDataCompanyAnnualReportEquityChangeService;
	}
}
