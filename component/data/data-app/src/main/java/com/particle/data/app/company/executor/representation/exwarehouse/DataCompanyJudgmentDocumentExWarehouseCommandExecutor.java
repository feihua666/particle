package com.particle.data.app.company.executor.representation.exwarehouse;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyJudgmentDocumentAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyJudgmentDocumentExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyJudgmentDocumentExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyJudgmentDocumentDO;
import com.particle.data.infrastructure.company.service.IDataCompanyJudgmentDocumentService;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业裁判文书出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyJudgmentDocumentExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyJudgmentDocumentService iDataCompanyJudgmentDocumentService;

	/**
	 * 企业裁判文书出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyJudgmentDocumentExWarehouseVO> exWarehouse(@Valid DataCompanyJudgmentDocumentExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyJudgmentDocumentDO> dataCompanyJudgmentDocumentDOPage = null;
        if (dataCompanyExWarehouseQueryCommand.getCompanyId() != null) {
			dataCompanyJudgmentDocumentDOPage = iDataCompanyJudgmentDocumentService.listPageByCompanyId(dataCompanyExWarehouseQueryCommand.getCompanyId(),
					dataCompanyExWarehouseQueryCommand.getCaseNo(),
					dataCompanyExWarehouseQueryCommand);
        }

		if (dataCompanyJudgmentDocumentDOPage == null || dataCompanyJudgmentDocumentDOPage.getRecords() == null || dataCompanyJudgmentDocumentDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyJudgmentDocumentAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyJudgmentDocumentDOPage);
	}
	/**
	 * 企业裁判文书出库
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyJudgmentDocumentExWarehouseVO> exWarehouseByDataMd5(String dataMd5) {
		DataCompanyJudgmentDocumentDO dataCompanyJudgmentDocumentDO = iDataCompanyJudgmentDocumentService.getByDataMd5(dataMd5);
		if (dataCompanyJudgmentDocumentDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyJudgmentDocumentExWarehouseVO dataCompanyJudgmentDocumentExWarehouseVO = DataCompanyJudgmentDocumentAppStructMapping.instance.dataCompanyJudgmentDocumentDOToDataCompanyJudgmentDocumentExWarehouseVO(dataCompanyJudgmentDocumentDO);
		return SingleResponse.of(dataCompanyJudgmentDocumentExWarehouseVO);
	}
	/**
	 * 企业裁判文书出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyJudgmentDocumentExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyJudgmentDocumentDO dataCompanyJudgmentDocumentDO = iDataCompanyJudgmentDocumentService.getById(id);
		if (dataCompanyJudgmentDocumentDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyJudgmentDocumentExWarehouseVO dataCompanyJudgmentDocumentExWarehouseVO = DataCompanyJudgmentDocumentAppStructMapping.instance.dataCompanyJudgmentDocumentDOToDataCompanyJudgmentDocumentExWarehouseVO(dataCompanyJudgmentDocumentDO);
		return SingleResponse.of(dataCompanyJudgmentDocumentExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyJudgmentDocumentService(IDataCompanyJudgmentDocumentService iDataCompanyJudgmentDocumentService) {
		this.iDataCompanyJudgmentDocumentService = iDataCompanyJudgmentDocumentService;
	}
}
