package com.particle.data.app.company.executor.representation.exwarehouse;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanySeriousIllegalAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanySeriousIllegalExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanySeriousIllegalExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanySeriousIllegalDO;
import com.particle.data.infrastructure.company.service.IDataCompanySeriousIllegalService;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业严重违法出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanySeriousIllegalExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanySeriousIllegalService iDataCompanySeriousIllegalService;

	/**
	 * 企业严重违法出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanySeriousIllegalExWarehouseVO> exWarehouse(@Valid DataCompanySeriousIllegalExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanySeriousIllegalDO> dataCompanySeriousIllegalDOPage = null;
        if (dataCompanyExWarehouseQueryCommand.getCompanyId() != null) {
			dataCompanySeriousIllegalDOPage = iDataCompanySeriousIllegalService.listPageByCompanyId(dataCompanyExWarehouseQueryCommand.getCompanyId(),
					dataCompanyExWarehouseQueryCommand);
        }

		if (dataCompanySeriousIllegalDOPage == null || dataCompanySeriousIllegalDOPage.getRecords() == null || dataCompanySeriousIllegalDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanySeriousIllegalAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanySeriousIllegalDOPage);
	}
	/**
	 * 企业严重违法出库
	 * @param companyId
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanySeriousIllegalExWarehouseVO> exWarehouseByCompanyIdAndDataMd5(Long companyId,String dataMd5) {
		DataCompanySeriousIllegalDO dataCompanySeriousIllegalDO = iDataCompanySeriousIllegalService.getByCompanyIdAndDataMd5(companyId,dataMd5);
		if (dataCompanySeriousIllegalDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanySeriousIllegalExWarehouseVO dataCompanySeriousIllegalExWarehouseVO = DataCompanySeriousIllegalAppStructMapping.instance.dataCompanySeriousIllegalDOToDataCompanySeriousIllegalExWarehouseVO(dataCompanySeriousIllegalDO);
		return SingleResponse.of(dataCompanySeriousIllegalExWarehouseVO);
	}
	/**
	 * 企业严重违法出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanySeriousIllegalExWarehouseVO> exWarehouseById(Long id) {
		DataCompanySeriousIllegalDO dataCompanySeriousIllegalDO = iDataCompanySeriousIllegalService.getById(id);
		if (dataCompanySeriousIllegalDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanySeriousIllegalExWarehouseVO dataCompanySeriousIllegalExWarehouseVO = DataCompanySeriousIllegalAppStructMapping.instance.dataCompanySeriousIllegalDOToDataCompanySeriousIllegalExWarehouseVO(dataCompanySeriousIllegalDO);
		return SingleResponse.of(dataCompanySeriousIllegalExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanySeriousIllegalService(IDataCompanySeriousIllegalService iDataCompanySeriousIllegalService) {
		this.iDataCompanySeriousIllegalService = iDataCompanySeriousIllegalService;
	}
}
