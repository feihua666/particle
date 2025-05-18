package com.particle.data.app.company.executor.representation.exwarehouse;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyJudgmentDebtorAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyJudgmentDebtorExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyJudgmentDebtorExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyJudgmentDebtorDO;
import com.particle.data.infrastructure.company.service.IDataCompanyJudgmentDebtorService;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业被执行人出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyJudgmentDebtorExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyJudgmentDebtorService iDataCompanyJudgmentDebtorService;

	/**
	 * 企业被执行人出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyJudgmentDebtorExWarehouseVO> exWarehouse(@Valid DataCompanyJudgmentDebtorExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyJudgmentDebtorDO> dataCompanyJudgmentDebtorDOPage = null;
        if (dataCompanyExWarehouseQueryCommand.getCompanyId() != null) {
			dataCompanyJudgmentDebtorDOPage = iDataCompanyJudgmentDebtorService.listPageByExecutedPersonCompanyId(dataCompanyExWarehouseQueryCommand.getCompanyId(),
					dataCompanyExWarehouseQueryCommand.getCaseNo(),
					dataCompanyExWarehouseQueryCommand);
        }

		if (dataCompanyJudgmentDebtorDOPage == null || dataCompanyJudgmentDebtorDOPage.getRecords() == null || dataCompanyJudgmentDebtorDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyJudgmentDebtorAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyJudgmentDebtorDOPage);
	}
	/**
	 * 企业被执行人出库
	 * @param executedPersonCompanyId
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyJudgmentDebtorExWarehouseVO> exWarehouseByCompanyIdAndDataMd5(Long executedPersonCompanyId,String dataMd5) {
		DataCompanyJudgmentDebtorDO dataCompanyJudgmentDebtorDO = iDataCompanyJudgmentDebtorService.getByExecutedPersonCompanyIdAndDataMd5(executedPersonCompanyId,dataMd5);
		if (dataCompanyJudgmentDebtorDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyJudgmentDebtorExWarehouseVO dataCompanyJudgmentDebtorExWarehouseVO = DataCompanyJudgmentDebtorAppStructMapping.instance.dataCompanyJudgmentDebtorDOToDataCompanyJudgmentDebtorExWarehouseVO(dataCompanyJudgmentDebtorDO);
		return SingleResponse.of(dataCompanyJudgmentDebtorExWarehouseVO);
	}
	/**
	 * 企业被执行人出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyJudgmentDebtorExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyJudgmentDebtorDO dataCompanyJudgmentDebtorDO = iDataCompanyJudgmentDebtorService.getById(id);
		if (dataCompanyJudgmentDebtorDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyJudgmentDebtorExWarehouseVO dataCompanyJudgmentDebtorExWarehouseVO = DataCompanyJudgmentDebtorAppStructMapping.instance.dataCompanyJudgmentDebtorDOToDataCompanyJudgmentDebtorExWarehouseVO(dataCompanyJudgmentDebtorDO);
		return SingleResponse.of(dataCompanyJudgmentDebtorExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyJudgmentDebtorService(IDataCompanyJudgmentDebtorService iDataCompanyJudgmentDebtorService) {
		this.iDataCompanyJudgmentDebtorService = iDataCompanyJudgmentDebtorService;
	}
}
