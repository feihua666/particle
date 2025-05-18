package com.particle.data.app.company.executor.representation.exwarehouse;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyAnnualReportSocialSecurityAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyAnnualReportSocialSecurityExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportSocialSecurityExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportSocialSecurityDO;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportSocialSecurityService;
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
 * 企业年报社保出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyAnnualReportSocialSecurityExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyAnnualReportSocialSecurityService iDataCompanyAnnualReportSocialSecurityService;

	/**
	 * 企业年报社保出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportSocialSecurityExWarehouseVO> exWarehouse(@Valid DataCompanyAnnualReportSocialSecurityExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyAnnualReportSocialSecurityDO> dataCompanyAnnualReportSocialSecurityDOPage = null;
        if (dataCompanyExWarehouseQueryCommand.getCompanyAnnualReportId() != null) {
			dataCompanyAnnualReportSocialSecurityDOPage = iDataCompanyAnnualReportSocialSecurityService.listPageByCompanyAnnualReportId(dataCompanyExWarehouseQueryCommand.getCompanyAnnualReportId(),
					dataCompanyExWarehouseQueryCommand);
        }else if (dataCompanyExWarehouseQueryCommand.getCompanyId() != null) {
			dataCompanyAnnualReportSocialSecurityDOPage = iDataCompanyAnnualReportSocialSecurityService.listPageByCompanyId(dataCompanyExWarehouseQueryCommand.getCompanyId(),
					dataCompanyExWarehouseQueryCommand.getYear(),
					dataCompanyExWarehouseQueryCommand);
        }

		if (dataCompanyAnnualReportSocialSecurityDOPage == null || dataCompanyAnnualReportSocialSecurityDOPage.getRecords() == null || dataCompanyAnnualReportSocialSecurityDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyAnnualReportSocialSecurityAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyAnnualReportSocialSecurityDOPage);
	}
	/**
	 * 企业年报社保出库
	 * @param companyId
	 * @param year
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportSocialSecurityExWarehouseVO> exWarehouseByCompanyIdAndYear(Long companyId,Integer year) {
		DataCompanyAnnualReportSocialSecurityDO dataCompanyAnnualReportSocialSecurityDO = iDataCompanyAnnualReportSocialSecurityService.getByCompanyIdAndYear(companyId,year);
		if (dataCompanyAnnualReportSocialSecurityDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyAnnualReportSocialSecurityExWarehouseVO dataCompanyAnnualReportSocialSecurityExWarehouseVO = DataCompanyAnnualReportSocialSecurityAppStructMapping.instance.dataCompanyAnnualReportSocialSecurityDOToDataCompanyAnnualReportSocialSecurityExWarehouseVO(dataCompanyAnnualReportSocialSecurityDO);
		return SingleResponse.of(dataCompanyAnnualReportSocialSecurityExWarehouseVO);
	}
	/**
	 * 企业年报社保出库
	 * @param companyAnnualReportId
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportSocialSecurityExWarehouseVO> exWarehouseByCompanyAnnualReportId(Long companyAnnualReportId) {
		DataCompanyAnnualReportSocialSecurityDO dataCompanyAnnualReportSocialSecurityDO = iDataCompanyAnnualReportSocialSecurityService.getByCompanyAnnualReportId(companyAnnualReportId);
		if (dataCompanyAnnualReportSocialSecurityDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyAnnualReportSocialSecurityExWarehouseVO dataCompanyAnnualReportSocialSecurityExWarehouseVO = DataCompanyAnnualReportSocialSecurityAppStructMapping.instance.dataCompanyAnnualReportSocialSecurityDOToDataCompanyAnnualReportSocialSecurityExWarehouseVO(dataCompanyAnnualReportSocialSecurityDO);
		return SingleResponse.of(dataCompanyAnnualReportSocialSecurityExWarehouseVO);
	}
	/**
	 * 企业年报社保出库
	 * @param companyAnnualReportIds
	 * @return
	 */
	public MultiResponse<DataCompanyAnnualReportSocialSecurityExWarehouseVO> exWarehouseByCompanyAnnualReportIds(List<Long> companyAnnualReportIds) {
		List<DataCompanyAnnualReportSocialSecurityDO> dataCompanyAnnualReportSocialSecurityDOs = iDataCompanyAnnualReportSocialSecurityService.listByCompanyAnnualReportIds(companyAnnualReportIds);
		if (CollectionUtil.isEmpty(dataCompanyAnnualReportSocialSecurityDOs)) {
			return MultiResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		List<DataCompanyAnnualReportSocialSecurityExWarehouseVO> dataCompanyAnnualReportSocialSecurityExWarehouseVOs = DataCompanyAnnualReportSocialSecurityAppStructMapping.instance.dataCompanyAnnualReportSocialSecurityDOsToDataCompanyAnnualReportSocialSecurityExWarehouseVOs(dataCompanyAnnualReportSocialSecurityDOs);
		return MultiResponse.of(dataCompanyAnnualReportSocialSecurityExWarehouseVOs);
	}
	/**
	 * 企业年报社保出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportSocialSecurityExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyAnnualReportSocialSecurityDO dataCompanyAnnualReportSocialSecurityDO = iDataCompanyAnnualReportSocialSecurityService.getById(id);
		if (dataCompanyAnnualReportSocialSecurityDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyAnnualReportSocialSecurityExWarehouseVO dataCompanyAnnualReportSocialSecurityExWarehouseVO = DataCompanyAnnualReportSocialSecurityAppStructMapping.instance.dataCompanyAnnualReportSocialSecurityDOToDataCompanyAnnualReportSocialSecurityExWarehouseVO(dataCompanyAnnualReportSocialSecurityDO);
		return SingleResponse.of(dataCompanyAnnualReportSocialSecurityExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyAnnualReportSocialSecurityService(IDataCompanyAnnualReportSocialSecurityService iDataCompanyAnnualReportSocialSecurityService) {
		this.iDataCompanyAnnualReportSocialSecurityService = iDataCompanyAnnualReportSocialSecurityService;
	}
}
