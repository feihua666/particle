package com.particle.data.app.company.executor.representation.exwarehouse;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyAnnualReportWebsiteAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyAnnualReportWebsiteExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportWebsiteExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportWebsiteDO;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportWebsiteService;
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
 * 企业年报网站网店出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyAnnualReportWebsiteExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyAnnualReportWebsiteService iDataCompanyAnnualReportWebsiteService;

	/**
	 * 企业年报网站网店出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportWebsiteExWarehouseVO> exWarehouse(@Valid DataCompanyAnnualReportWebsiteExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyAnnualReportWebsiteDO> dataCompanyAnnualReportWebsiteDOPage = null;
        if (dataCompanyExWarehouseQueryCommand.getCompanyAnnualReportId() != null) {
			dataCompanyAnnualReportWebsiteDOPage = iDataCompanyAnnualReportWebsiteService.listPageByCompanyAnnualReportId(dataCompanyExWarehouseQueryCommand.getCompanyAnnualReportId(),
					dataCompanyExWarehouseQueryCommand);
        }else if (dataCompanyExWarehouseQueryCommand.getCompanyId() != null) {
			dataCompanyAnnualReportWebsiteDOPage = iDataCompanyAnnualReportWebsiteService.listPageByCompanyId(dataCompanyExWarehouseQueryCommand.getCompanyId(),
					dataCompanyExWarehouseQueryCommand.getYear(),
					dataCompanyExWarehouseQueryCommand);
        }

		if (dataCompanyAnnualReportWebsiteDOPage == null || dataCompanyAnnualReportWebsiteDOPage.getRecords() == null || dataCompanyAnnualReportWebsiteDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyAnnualReportWebsiteAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyAnnualReportWebsiteDOPage);
	}
	/**
	 * 企业年报网站网店出库
	 * @param companyId
	 * @param year
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportWebsiteExWarehouseVO> exWarehouseByCompanyIdAndYearAndDataMd5(Long companyId,Integer year,String dataMd5) {
		DataCompanyAnnualReportWebsiteDO dataCompanyAnnualReportWebsiteDO = iDataCompanyAnnualReportWebsiteService.getByCompanyIdAndYearAndDataMd5(companyId,year,dataMd5);
		if (dataCompanyAnnualReportWebsiteDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyAnnualReportWebsiteExWarehouseVO dataCompanyAnnualReportWebsiteExWarehouseVO = DataCompanyAnnualReportWebsiteAppStructMapping.instance.dataCompanyAnnualReportWebsiteDOToDataCompanyAnnualReportWebsiteExWarehouseVO(dataCompanyAnnualReportWebsiteDO);
		return SingleResponse.of(dataCompanyAnnualReportWebsiteExWarehouseVO);
	}
	/**
	 * 企业年报网站网店出库
	 * @param companyAnnualReportId
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportWebsiteExWarehouseVO> exWarehouseByCompanyAnnualReportIdAndDataMd5(Long companyAnnualReportId,String dataMd5) {
		DataCompanyAnnualReportWebsiteDO dataCompanyAnnualReportWebsiteDO = iDataCompanyAnnualReportWebsiteService.getByCompanyAnnualReportIdAndDataMd5(companyAnnualReportId,dataMd5);
		if (dataCompanyAnnualReportWebsiteDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyAnnualReportWebsiteExWarehouseVO dataCompanyAnnualReportWebsiteExWarehouseVO = DataCompanyAnnualReportWebsiteAppStructMapping.instance.dataCompanyAnnualReportWebsiteDOToDataCompanyAnnualReportWebsiteExWarehouseVO(dataCompanyAnnualReportWebsiteDO);
		return SingleResponse.of(dataCompanyAnnualReportWebsiteExWarehouseVO);
	}
	/**
	 * 企业年报网站网店出库
	 * @param companyAnnualReportIds
	 * @return
	 */
	public MultiResponse<DataCompanyAnnualReportWebsiteExWarehouseVO> exWarehouseByCompanyAnnualReportIds(List<Long> companyAnnualReportIds) {
		List<DataCompanyAnnualReportWebsiteDO> dataCompanyAnnualReportWebsiteDOs = iDataCompanyAnnualReportWebsiteService.listByCompanyAnnualReportIds(companyAnnualReportIds);
		if (CollectionUtil.isEmpty(dataCompanyAnnualReportWebsiteDOs)) {
			return MultiResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		List<DataCompanyAnnualReportWebsiteExWarehouseVO> dataCompanyAnnualReportWebsiteExWarehouseVOs = DataCompanyAnnualReportWebsiteAppStructMapping.instance.dataCompanyAnnualReportWebsiteDOsToDataCompanyAnnualReportWebsiteExWarehouseVOs(dataCompanyAnnualReportWebsiteDOs);
		return MultiResponse.of(dataCompanyAnnualReportWebsiteExWarehouseVOs);
	}
	/**
	 * 企业年报网站网店出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportWebsiteExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyAnnualReportWebsiteDO dataCompanyAnnualReportWebsiteDO = iDataCompanyAnnualReportWebsiteService.getById(id);
		if (dataCompanyAnnualReportWebsiteDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyAnnualReportWebsiteExWarehouseVO dataCompanyAnnualReportWebsiteExWarehouseVO = DataCompanyAnnualReportWebsiteAppStructMapping.instance.dataCompanyAnnualReportWebsiteDOToDataCompanyAnnualReportWebsiteExWarehouseVO(dataCompanyAnnualReportWebsiteDO);
		return SingleResponse.of(dataCompanyAnnualReportWebsiteExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyAnnualReportWebsiteService(IDataCompanyAnnualReportWebsiteService iDataCompanyAnnualReportWebsiteService) {
		this.iDataCompanyAnnualReportWebsiteService = iDataCompanyAnnualReportWebsiteService;
	}
}
