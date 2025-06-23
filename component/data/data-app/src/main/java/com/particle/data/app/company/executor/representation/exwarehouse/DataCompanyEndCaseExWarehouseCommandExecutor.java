package com.particle.data.app.company.executor.representation.exwarehouse;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyEndCaseAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyEndCaseExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyEndCaseExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyEndCaseDO;
import com.particle.data.infrastructure.company.service.IDataCompanyEndCaseService;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业终本案件出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyEndCaseExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyEndCaseService iDataCompanyEndCaseService;

	/**
	 * 企业终本案件出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyEndCaseExWarehouseVO> exWarehouse(@Valid DataCompanyEndCaseExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyEndCaseDO> dataCompanyEndCaseDOPage = iDataCompanyEndCaseService.listPageByCompanyId(dataCompanyExWarehouseQueryCommand.getCompanyId(),dataCompanyExWarehouseQueryCommand.getCaseNo(), dataCompanyExWarehouseQueryCommand);
		if (dataCompanyEndCaseDOPage == null || dataCompanyEndCaseDOPage.getRecords() == null || dataCompanyEndCaseDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyEndCaseAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyEndCaseDOPage);
	}
	/**
	 * 企业终本案件出库
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyEndCaseExWarehouseVO> exWarehouseByCompanyIdAndDataMd5(Long companyId,String dataMd5) {
		DataCompanyEndCaseDO dataCompanyEndCaseDO = iDataCompanyEndCaseService.getByExecutedPersonCompanyIdAndDataMd5(companyId,dataMd5);
		if (dataCompanyEndCaseDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyEndCaseExWarehouseVO dataCompanyEndCaseExWarehouseVO = DataCompanyEndCaseAppStructMapping.instance.dataCompanyEndCaseDOToDataCompanyEndCaseExWarehouseVO(dataCompanyEndCaseDO);
		return SingleResponse.of(dataCompanyEndCaseExWarehouseVO);
	}
	/**
	 * 企业终本案件出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyEndCaseExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyEndCaseDO dataCompanyEndCaseDO = iDataCompanyEndCaseService.getById(id);
		if (dataCompanyEndCaseDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyEndCaseExWarehouseVO dataCompanyEndCaseExWarehouseVO = DataCompanyEndCaseAppStructMapping.instance.dataCompanyEndCaseDOToDataCompanyEndCaseExWarehouseVO(dataCompanyEndCaseDO);
		return SingleResponse.of(dataCompanyEndCaseExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyEndCaseService(IDataCompanyEndCaseService iDataCompanyEndCaseService) {
		this.iDataCompanyEndCaseService = iDataCompanyEndCaseService;
	}
}
