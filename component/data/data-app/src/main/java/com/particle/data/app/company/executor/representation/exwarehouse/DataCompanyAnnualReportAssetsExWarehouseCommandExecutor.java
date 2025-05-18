package com.particle.data.app.company.executor.representation.exwarehouse;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyAnnualReportAssetsAppStructMapping;
import com.particle.data.app.company.structmapping.DataCompanyAnnualReportAssetsAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyAnnualReportAssetsExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportAssetsExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportAssetsExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportAssetsDO;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportAssetsDO;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportAssetsService;
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
 * 企业资产状况信息出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyAnnualReportAssetsExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyAnnualReportAssetsService iDataCompanyAnnualReportAssetsService;

	/**
	 * 企业资产状况信息出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportAssetsExWarehouseVO> exWarehouse(@Valid DataCompanyAnnualReportAssetsExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyAnnualReportAssetsDO> dataCompanyAnnualReportAssetsDOPage = null;
        if (dataCompanyExWarehouseQueryCommand.getCompanyAnnualReportId() != null) {
			dataCompanyAnnualReportAssetsDOPage = iDataCompanyAnnualReportAssetsService.listPageByCompanyAnnualReportId(dataCompanyExWarehouseQueryCommand.getCompanyAnnualReportId(),
					dataCompanyExWarehouseQueryCommand);
        }else if (dataCompanyExWarehouseQueryCommand.getCompanyId() != null) {
			dataCompanyAnnualReportAssetsDOPage = iDataCompanyAnnualReportAssetsService.listPageByCompanyId(dataCompanyExWarehouseQueryCommand.getCompanyId(),
					dataCompanyExWarehouseQueryCommand.getYear(),
					dataCompanyExWarehouseQueryCommand);
        }

		if (dataCompanyAnnualReportAssetsDOPage == null || dataCompanyAnnualReportAssetsDOPage.getRecords() == null || dataCompanyAnnualReportAssetsDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyAnnualReportAssetsAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyAnnualReportAssetsDOPage);
	}
	/**
	 * 企业资产状况信息出库
	 * @param companyId
	 * @param year
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportAssetsExWarehouseVO> exWarehouseByCompanyIdAndYear(Long companyId,Integer year) {
		DataCompanyAnnualReportAssetsDO dataCompanyAnnualReportAssetsDO = iDataCompanyAnnualReportAssetsService.getByCompanyIdAndYear(companyId,year);
		if (dataCompanyAnnualReportAssetsDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyAnnualReportAssetsExWarehouseVO dataCompanyAnnualReportAssetsExWarehouseVO = DataCompanyAnnualReportAssetsAppStructMapping.instance.dataCompanyAnnualReportAssetsDOToDataCompanyAnnualReportAssetsExWarehouseVO(dataCompanyAnnualReportAssetsDO);
		return SingleResponse.of(dataCompanyAnnualReportAssetsExWarehouseVO);
	}
	/**
	 * 企业资产状况信息出库
	 * @param companyAnnualReportId
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportAssetsExWarehouseVO> exWarehouseByCompanyAnnualReportId(Long companyAnnualReportId) {
		DataCompanyAnnualReportAssetsDO dataCompanyAnnualReportAssetsDO = iDataCompanyAnnualReportAssetsService.getByCompanyAnnualReportId(companyAnnualReportId);
		if (dataCompanyAnnualReportAssetsDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyAnnualReportAssetsExWarehouseVO dataCompanyAnnualReportAssetsExWarehouseVO = DataCompanyAnnualReportAssetsAppStructMapping.instance.dataCompanyAnnualReportAssetsDOToDataCompanyAnnualReportAssetsExWarehouseVO(dataCompanyAnnualReportAssetsDO);
		return SingleResponse.of(dataCompanyAnnualReportAssetsExWarehouseVO);
	}
	/**
	 * 企业资产状况信息出库
	 * @param companyAnnualReportIds
	 * @return
	 */
	public MultiResponse<DataCompanyAnnualReportAssetsExWarehouseVO> exWarehouseByCompanyAnnualReportIds(List<Long> companyAnnualReportIds) {
		List<DataCompanyAnnualReportAssetsDO> dataCompanyAnnualReportAssetsDOs = iDataCompanyAnnualReportAssetsService.listByCompanyAnnualReportIds(companyAnnualReportIds);
		if (CollectionUtil.isEmpty(dataCompanyAnnualReportAssetsDOs)) {
			return MultiResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		List<DataCompanyAnnualReportAssetsExWarehouseVO> dataCompanyAnnualReportAssetsExWarehouseVOs = DataCompanyAnnualReportAssetsAppStructMapping.instance.dataCompanyAnnualReportAssetsDOsToDataCompanyAnnualReportAssetsExWarehouseVOs(dataCompanyAnnualReportAssetsDOs);
		return MultiResponse.of(dataCompanyAnnualReportAssetsExWarehouseVOs);
	}
	/**
	 * 企业资产状况信息出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportAssetsExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyAnnualReportAssetsDO dataCompanyAnnualReportAssetsDO = iDataCompanyAnnualReportAssetsService.getById(id);
		if (dataCompanyAnnualReportAssetsDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyAnnualReportAssetsExWarehouseVO dataCompanyAnnualReportAssetsExWarehouseVO = DataCompanyAnnualReportAssetsAppStructMapping.instance.dataCompanyAnnualReportAssetsDOToDataCompanyAnnualReportAssetsExWarehouseVO(dataCompanyAnnualReportAssetsDO);
		return SingleResponse.of(dataCompanyAnnualReportAssetsExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyAnnualReportAssetsService(IDataCompanyAnnualReportAssetsService iDataCompanyAnnualReportAssetsService) {
		this.iDataCompanyAnnualReportAssetsService = iDataCompanyAnnualReportAssetsService;
	}
}
