package com.particle.data.app.company.executor.representation.exwarehouse;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyIprTrademarkPartyAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprTrademarkPartyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkPartyExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkPartyDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkPartyService;
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
 * 企业知识产权商标当事人出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyIprTrademarkPartyExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyIprTrademarkPartyService iDataCompanyIprTrademarkPartyService;

	/**
	 * 企业知识产权商标当事人出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprTrademarkPartyExWarehouseVO> exWarehouse(@Valid DataCompanyIprTrademarkPartyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyIprTrademarkPartyDO> dataCompanyIprTrademarkPartyDOPage = null;
		if (dataCompanyExWarehouseQueryCommand.getCompanyId() != null) {
			dataCompanyIprTrademarkPartyDOPage = iDataCompanyIprTrademarkPartyService.listPageByCompanyIprTrademarkId(dataCompanyExWarehouseQueryCommand.getCompanyIprTrademarkId(),
					dataCompanyExWarehouseQueryCommand.getCompanyId(),
					dataCompanyExWarehouseQueryCommand);
		}

		if (dataCompanyIprTrademarkPartyDOPage == null || dataCompanyIprTrademarkPartyDOPage.getRecords() == null || dataCompanyIprTrademarkPartyDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		return DataCompanyIprTrademarkPartyAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyIprTrademarkPartyDOPage);
	}
	/**
	 * 企业知识产权商标当事人出库
	 * @param companyIprTrademarkIds
	 * @return
	 */
	public MultiResponse<DataCompanyIprTrademarkPartyExWarehouseVO> exWarehouseByCompanyIprTrademarkIds(List<Long> companyIprTrademarkIds) {
		List<DataCompanyIprTrademarkPartyDO> dataCompanyIprTrademarkPartyDOList = iDataCompanyIprTrademarkPartyService.listByCompanyIprTrademarkIds(companyIprTrademarkIds);
		if (CollectionUtil.isEmpty(dataCompanyIprTrademarkPartyDOList)) {
			return MultiResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		List<DataCompanyIprTrademarkPartyExWarehouseVO> dataCompanyIprTrademarkPartyExWarehouseVOS = DataCompanyIprTrademarkPartyAppStructMapping.instance.dataCompanyIprTrademarkPartyDOsToDataCompanyIprTrademarkPartyExWarehouseVOs(dataCompanyIprTrademarkPartyDOList);
		return MultiResponse.of(dataCompanyIprTrademarkPartyExWarehouseVOS);
	}
	/**
	 * 企业知识产权商标当事人出库
	 * @param companyCaseFilingId
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkPartyExWarehouseVO> exWarehouseByCompanyIprTrademarkIdAndDataMd5(Long companyCaseFilingId,String dataMd5) {
		DataCompanyIprTrademarkPartyDO dataCompanyIprTrademarkPartyDO = iDataCompanyIprTrademarkPartyService.getByCompanyIprTrademarkIdAndDataMd5(companyCaseFilingId,dataMd5);
		if (dataCompanyIprTrademarkPartyDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprTrademarkPartyExWarehouseVO dataCompanyIprTrademarkPartyExWarehouseVO = DataCompanyIprTrademarkPartyAppStructMapping.instance.dataCompanyIprTrademarkPartyDOToDataCompanyIprTrademarkPartyExWarehouseVO(dataCompanyIprTrademarkPartyDO);
		return SingleResponse.of(dataCompanyIprTrademarkPartyExWarehouseVO);
	}
	/**
	 * 企业知识产权商标当事人出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkPartyExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyIprTrademarkPartyDO dataCompanyIprTrademarkPartyDO = iDataCompanyIprTrademarkPartyService.getById(id);
		if (dataCompanyIprTrademarkPartyDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprTrademarkPartyExWarehouseVO dataCompanyIprTrademarkPartyExWarehouseVO = DataCompanyIprTrademarkPartyAppStructMapping.instance.dataCompanyIprTrademarkPartyDOToDataCompanyIprTrademarkPartyExWarehouseVO(dataCompanyIprTrademarkPartyDO);
		return SingleResponse.of(dataCompanyIprTrademarkPartyExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyIprTrademarkPartyService(IDataCompanyIprTrademarkPartyService iDataCompanyIprTrademarkPartyService) {
		this.iDataCompanyIprTrademarkPartyService = iDataCompanyIprTrademarkPartyService;
	}
}
