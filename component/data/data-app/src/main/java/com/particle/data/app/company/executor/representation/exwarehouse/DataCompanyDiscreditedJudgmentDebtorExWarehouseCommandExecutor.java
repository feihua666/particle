package com.particle.data.app.company.executor.representation.exwarehouse;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyDiscreditedJudgmentDebtorAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyDiscreditedJudgmentDebtorExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyDiscreditedJudgmentDebtorExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyDiscreditedJudgmentDebtorDO;
import com.particle.data.infrastructure.company.service.IDataCompanyDiscreditedJudgmentDebtorService;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业失信被执行人出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyDiscreditedJudgmentDebtorExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyDiscreditedJudgmentDebtorService iDataCompanyDiscreditedJudgmentDebtorService;

	/**
	 * 企业失信被执行人出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyDiscreditedJudgmentDebtorExWarehouseVO> exWarehouse(@Valid DataCompanyDiscreditedJudgmentDebtorExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyDiscreditedJudgmentDebtorDO> dataCompanyDiscreditedJudgmentDebtorDOPage = iDataCompanyDiscreditedJudgmentDebtorService.listPageByCompanyId(dataCompanyExWarehouseQueryCommand.getCompanyId(),dataCompanyExWarehouseQueryCommand.getCaseNo(), dataCompanyExWarehouseQueryCommand);
		if (dataCompanyDiscreditedJudgmentDebtorDOPage == null || dataCompanyDiscreditedJudgmentDebtorDOPage.getRecords() == null || dataCompanyDiscreditedJudgmentDebtorDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyDiscreditedJudgmentDebtorAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyDiscreditedJudgmentDebtorDOPage);
	}
	/**
	 * 企业失信被执行人出库
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyDiscreditedJudgmentDebtorExWarehouseVO> exWarehouseByCompanyIdAndDataMd5(Long companyId,String dataMd5) {
		DataCompanyDiscreditedJudgmentDebtorDO dataCompanyDiscreditedJudgmentDebtorDO = iDataCompanyDiscreditedJudgmentDebtorService.getByDishonestExecutedPersonCompanyIdAndDataMd5(companyId,dataMd5);
		if (dataCompanyDiscreditedJudgmentDebtorDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyDiscreditedJudgmentDebtorExWarehouseVO dataCompanyDiscreditedJudgmentDebtorExWarehouseVO = DataCompanyDiscreditedJudgmentDebtorAppStructMapping.instance.dataCompanyDiscreditedJudgmentDebtorDOToDataCompanyDiscreditedJudgmentDebtorExWarehouseVO(dataCompanyDiscreditedJudgmentDebtorDO);
		return SingleResponse.of(dataCompanyDiscreditedJudgmentDebtorExWarehouseVO);
	}
	/**
	 * 企业失信被执行人出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyDiscreditedJudgmentDebtorExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyDiscreditedJudgmentDebtorDO dataCompanyDiscreditedJudgmentDebtorDO = iDataCompanyDiscreditedJudgmentDebtorService.getById(id);
		if (dataCompanyDiscreditedJudgmentDebtorDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyDiscreditedJudgmentDebtorExWarehouseVO dataCompanyDiscreditedJudgmentDebtorExWarehouseVO = DataCompanyDiscreditedJudgmentDebtorAppStructMapping.instance.dataCompanyDiscreditedJudgmentDebtorDOToDataCompanyDiscreditedJudgmentDebtorExWarehouseVO(dataCompanyDiscreditedJudgmentDebtorDO);
		return SingleResponse.of(dataCompanyDiscreditedJudgmentDebtorExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyDiscreditedJudgmentDebtorService(IDataCompanyDiscreditedJudgmentDebtorService iDataCompanyDiscreditedJudgmentDebtorService) {
		this.iDataCompanyDiscreditedJudgmentDebtorService = iDataCompanyDiscreditedJudgmentDebtorService;
	}
}
