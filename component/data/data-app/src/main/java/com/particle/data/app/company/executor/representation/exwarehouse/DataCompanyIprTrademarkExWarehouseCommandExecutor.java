package com.particle.data.app.company.executor.representation.exwarehouse;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyIprTrademarkAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprTrademarkExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkDO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkDO;
import com.particle.data.infrastructure.company.dto.DataCompanyIprTrademarkListPageByCompanyIdParam;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkService;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业知识产权商标出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyIprTrademarkExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyIprTrademarkService iDataCompanyIprTrademarkService;

	/**
	 * 企业知识产权商标出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprTrademarkExWarehouseVO> exWarehouse(@Valid DataCompanyIprTrademarkExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyIprTrademarkDO> dataCompanyIprTrademarkDOPage = null;
		dataCompanyIprTrademarkDOPage = iDataCompanyIprTrademarkService.listPage(DataCompanyIprTrademarkListPageByCompanyIdParam.create(dataCompanyExWarehouseQueryCommand.getCompanyId(),
						dataCompanyExWarehouseQueryCommand.getIsApplicant(),
						dataCompanyExWarehouseQueryCommand.getIsAgent(),
						dataCompanyExWarehouseQueryCommand.getRegNo(),dataCompanyExWarehouseQueryCommand.getApplyNo()),
				dataCompanyExWarehouseQueryCommand);

		if (dataCompanyIprTrademarkDOPage == null || dataCompanyIprTrademarkDOPage.getRecords() == null || dataCompanyIprTrademarkDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		return DataCompanyIprTrademarkAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyIprTrademarkDOPage);
	}

	/**
	 * 企业知识产权专利出库
	 * @param applyNo
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkExWarehouseVO> exWarehouseByApplyNo(String applyNo) {
		DataCompanyIprTrademarkDO dataCompanyIprTrademarkDO = iDataCompanyIprTrademarkService.getByApplyNo(applyNo);
		if (dataCompanyIprTrademarkDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprTrademarkExWarehouseVO dataCompanyIprTrademarkExWarehouseVO = DataCompanyIprTrademarkAppStructMapping.instance.dataCompanyIprTrademarkDOToDataCompanyIprTrademarkExWarehouseVO(dataCompanyIprTrademarkDO);
		return SingleResponse.of(dataCompanyIprTrademarkExWarehouseVO);
	}
	/**
	 * 企业知识产权专利出库
	 * @param regNo
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkExWarehouseVO> exWarehouseByRegNo(String regNo) {
		DataCompanyIprTrademarkDO dataCompanyIprTrademarkDO = iDataCompanyIprTrademarkService.getByRegNo(regNo);
		if (dataCompanyIprTrademarkDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprTrademarkExWarehouseVO dataCompanyIprTrademarkExWarehouseVO = DataCompanyIprTrademarkAppStructMapping.instance.dataCompanyIprTrademarkDOToDataCompanyIprTrademarkExWarehouseVO(dataCompanyIprTrademarkDO);
		return SingleResponse.of(dataCompanyIprTrademarkExWarehouseVO);
	}
	/**
	 * 企业知识产权商标出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyIprTrademarkDO dataCompanyIprTrademarkDO = iDataCompanyIprTrademarkService.getById(id);
		if (dataCompanyIprTrademarkDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprTrademarkExWarehouseVO dataCompanyIprTrademarkExWarehouseVO = DataCompanyIprTrademarkAppStructMapping.instance.dataCompanyIprTrademarkDOToDataCompanyIprTrademarkExWarehouseVO(dataCompanyIprTrademarkDO);
		return SingleResponse.of(dataCompanyIprTrademarkExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyIprTrademarkService(IDataCompanyIprTrademarkService iDataCompanyIprTrademarkService) {
		this.iDataCompanyIprTrademarkService = iDataCompanyIprTrademarkService;
	}
}
